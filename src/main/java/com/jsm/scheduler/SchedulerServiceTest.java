package com.jsm.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceTest {

	@Scheduled(fixedDelay=1000*10)
	public void testarAgendamentoTarefa() {
		System.out.println(">>>>##### Testando Shedule ######<<<<<<<");
	}
	
	// segundo minuto hora 
	@Scheduled(cron="0 16 19 * * *")
	public void testarCronometro() {
		System.out.println("Execução com hora marcada.");
	}
}
