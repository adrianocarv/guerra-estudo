package br.org.esplanada.guerraestudo.interfaces.daemon;

import br.org.esplanada.guerraestudo.application.GuerraEstudoService;

public class Main {

	private static long startTime = -1;
	
	public static void main(String[] args) {
		setStartTime();
		
		System.out.println("\nBatalha iniciada\n");

		try {
			
			GuerraEstudoService service = new GuerraEstudoService();
			service.execute();
			service.gravarResultado();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		System.out.println("\nAplicativo finalizado. Tempo de execucao: " + getDuractionTime());
		System.out.println("\n");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void setStartTime(){
		startTime = System.currentTimeMillis();
	}

	private static String getDuractionTime(){
		long duraction = System.currentTimeMillis() - startTime;
		
		duraction /= 1000;
		if(duraction < 60)
			return duraction + " segundos.";
		
		duraction /= 60;
		return duraction + " minutos.";
	}
}