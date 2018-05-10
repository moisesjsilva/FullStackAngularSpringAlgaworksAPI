package com.jsm.resource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsm.amazon.s3.dto.Anexo;
import com.jsm.amazon.s3.storange.S3;
import com.jsm.dto.lancamento.LancamentoEstatisticaCategoriaDTO;
import com.jsm.dto.lancamento.LancamentoEstatisticaDiaDTO;
import com.jsm.model.Lancamento;
import com.jsm.model.filter.LancamentoFilter;
import com.jsm.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource extends ResourceImpl<Lancamento, LancamentoService> {

	@Autowired
	private LancamentoService service;
	
	@Autowired
	private S3 s3;
	

	@GetMapping("/pesquisa")
	public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable pageable) {
		Page<Lancamento> page = service.pesquisar(filter, pageable);
		return ResponseEntity.ok(page);
		
	}

	@GetMapping("/estatistica/por-categoria")
	public ResponseEntity<List<LancamentoEstatisticaCategoriaDTO>> porCategoria() {
		List<LancamentoEstatisticaCategoriaDTO> lista = service.resumoPorCategoria();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/estatistica/por-dia")
	public ResponseEntity<List<LancamentoEstatisticaDiaDTO>> porDia() {
		List<LancamentoEstatisticaDiaDTO> lista = service.resumoPorDia();
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path="/relatorio/por-pessoa", produces="application/pdf")
	public ResponseEntity<byte[]> porPessoa(
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate inicio,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fim) throws IOException{
		
		byte[] relatorio_por_pessoa = service.relatorioPorPessoa(inicio, fim);
		
		
		
		HttpHeaders headers = new HttpHeaders();		
		headers.add("content-disposition","attachment;filename=rel_lancanemnto_por_pessoa.pdf");
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

		return ResponseEntity.ok().headers(headers).body(relatorio_por_pessoa);
	}
	
	@PostMapping("/upload")
	public Anexo uploadAnexo(@RequestParam("anexo") MultipartFile file) throws IOException {
		/*File f = new File(System.getProperty("user.home")+"/api/upload/");
		if(!f.exists()) {
			f.mkdirs();
		}
		OutputStream out = new FileOutputStream(System.getProperty("user.home")+"/api/upload/"+file.getOriginalFilename());
		
		out.write(file.getBytes());
		out.close();*/
		
		String nome = s3.uploadTemp(file);
		System.out.println(nome);
		
		
		return new Anexo(nome,s3.configurarUrl(nome));
	}
}
