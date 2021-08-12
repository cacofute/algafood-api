package br.com.dit.algafood;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
		
		//LocalDate dataAtual    = LocalDate.now();
		//LocalDate dataAnterior = dataAtual.minusDays(1);
		//executarProcessamento(dataAnterior);
		
	}
	
	/*public static LocalDate findUtilDate(LocalDate date) {
		if(isUtilDate(date)) {
			return date;
		}
		return findUtilDate(date.minusDays(1));
	}
	
	
	public static boolean isUtilDate(LocalDate date) {	
		//lógica para saber se uma data é útil ou não
		return true;
	}
	
	
	public static void executarProcessamento(LocalDate data) {
		// executa o processamento da data passada por parâmetro
		
	}
	*/

}
