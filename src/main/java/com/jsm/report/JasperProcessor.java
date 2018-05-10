package com.jsm.report;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class JasperProcessor {

	public byte[] exportToPdf(String relatorioPath,Map<String,Object> args, List<?> lista) throws JRException {
		InputStream is = this.getClass().getResourceAsStream(relatorioPath);
		JasperPrint jasperPrint = JasperFillManager.fillReport(is, args, new JRBeanCollectionDataSource(lista));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	
}
