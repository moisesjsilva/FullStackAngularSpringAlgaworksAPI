package com.jsm.service;



import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jsm.amazon.s3.storange.S3;
import com.jsm.dto.lancamento.LancamentoEstatisticaCategoriaDTO;
import com.jsm.dto.lancamento.LancamentoEstatisticaDiaDTO;
import com.jsm.dto.lancamento.LancamentoEstatisticaPessoaDTO;
import com.jsm.email.Mailer;
import com.jsm.email.model.ContatoEmail;
import com.jsm.email.model.EmailContent;
import com.jsm.email.service.ContatoEmailService;
import com.jsm.model.Lancamento;
import com.jsm.model.Pessoa;
import com.jsm.model.filter.LancamentoFilter;
import com.jsm.report.JasperProcessor;
import com.jsm.repository.LancamentoRepository;
import com.jsm.service.exceptions.PessoaInativaException;

import net.sf.jasperreports.engine.JRException;

@Service
public class LancamentoService extends ServiceImpl<Lancamento,LancamentoRepository> {

	@Autowired
	private PessoaService pesService;
	
	@Autowired
	private ContatoEmailService cemails;
	
	
	private EmailContent emailContente;
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	private S3 s3;
	
	private static Logger logger = LoggerFactory.getLogger(LancamentoService.class)	;
	
	
	@Autowired
	private JasperProcessor jasperProcessor;
	
	@Scheduled(cron="0 0 6 * * * ")// Neste case envia os email sempre as 06:00  da manha
	//@Scheduled(fixedDelay=1000*60*30)
	public void avisarLancamentosVencidosTask() {
		if(logger.isDebugEnabled()) {
			logger.debug("Preparando envio de emails de aviso de lançamentos vencidos");
		}
		List<Lancamento> lancamentos = getRepository().findByDataVencimentoLessThanEqualAndDataPagamentoIsNull(LocalDate.now());
		
		if(lancamentos.isEmpty()) {
			logger.info("Sem Lançamentos vencidos para aviso.");
			return;
		}
		
		logger.info("Existem {} lancamentos vencendo hoje ou vencidos", lancamentos.size());
		List<ContatoEmail> contatos = cemails.findByDepartamento("default");
		
		
		
		List<String> destinatarios = contatos.stream().map(c ->c.getEmail()).collect(Collectors.toList());
		
		if(destinatarios.isEmpty()) {
			logger.warn("Existem lançamentos vencidos mas o sistema não encontrou destinatários, para envio do email.");
			return;
		}
		
		
		emailContente = new EmailContent()		
		.setAssunto("Resumo de lançamentos vencidos")
		.setDestinatarios(destinatarios)
		.setRemententeEmail("jsilva.moises@gmail.com")
		.setTemplatePath("email/aviso_lancamentos_vencidos")
		.putObject("lancamentos", lancamentos)
		.putObject("empresa", "JSM Mobile Corporation S/A");
		
		mailer.sendEmail(emailContente);
		
		logger.info("Envio de email de aviso concluído");
		
	}
	
	
	@Override
	public Lancamento save( Lancamento object) {
		Pessoa pessoa = pesService.getOne(object.getPessoa().getId());
		
		if(!pessoa.getAtivo()) {
			throw new PessoaInativaException(String.format("O Registro não pode ser salvo porque a pessoa vinculada está inativa. [%d-%s]",pessoa.getId(),pessoa.getNome()));
		}
		
		if(StringUtils.hasText(object.getAnexo())) {
			s3.persistFile(object.getAnexo());
		}
		return super.save(object);
	}
	
	@Override
	public Lancamento update(Long id, Lancamento object) {
		Lancamento lctoSalvo = findById(id).get();
		
		if(StringUtils.isEmpty(object.getAnexo()) && StringUtils.hasText(lctoSalvo.getAnexo())) {
			s3.removeFile(lctoSalvo.getAnexo());
		}else if(StringUtils.hasText(object.getAnexo()) && 
				!object.getAnexo().equals(lctoSalvo.getAnexo())) {
			s3.replace(lctoSalvo.getAnexo(),object.getAnexo());
		}
				
				
				
		
		return super.update(id, object);
	}

	public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable){
		return getRepository().filtrar(filter, pageable);
	}
	
	
	public List<LancamentoEstatisticaCategoriaDTO> resumoPorCategoria(){
		return getRepository().porCategoria(LocalDate.now());
	}
	
	public List<LancamentoEstatisticaDiaDTO> resumoPorDia(){
		return getRepository().porDia(LocalDate.now());
	}
	
	public List<Lancamento> findByDataVencimentoLessThanEqualAndDataPagamentoIsNull(LocalDate date){
		return getRepository().findByDataVencimentoLessThanEqualAndDataPagamentoIsNull(date);
	}
	
	
	public byte[] relatorioPorPessoa(LocalDate inicio,LocalDate fim) {
		List<LancamentoEstatisticaPessoaDTO> lista = getRepository().porPessoa(inicio, fim);
		System.out.println("Lista Size "+lista.size());
		Map<String,Object> args = new HashMap<>();
		args.put("DT_INICIO", Date.valueOf(inicio));
		args.put("DT_FIM", Date.valueOf(fim));
		args.put("REPORT_LOCALE", new Locale("pt","BR"));
		
		try {
			return  jasperProcessor.exportToPdf("/relatorios/lancamentos_por_pessoa.jasper", args, lista);
		} catch (JRException e) {			
			throw new RuntimeException("Erro ao gerar relatório.",e);
		
		}
		
	}
}
