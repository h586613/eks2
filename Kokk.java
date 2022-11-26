package oppgave2;

import java.util.Random;

public class Kokk extends Thread{

	
	private Hamburgerbrett brett;
	private String navn;
	
	public Kokk(Hamburgerbrett brett, String navn) {
		this.brett = brett;
		this.navn = navn;
	}

	@Override
	public void run() {
//		husk while-løkke etterhvert her + avbrytende statement
		 Random rn = new Random();
			int low = 2;
	        int high = 7;
	for(int i=0;i<=10;i++){
		int result = rn.nextInt(high - low) + low;
        try {
            Thread.sleep(result * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		try {
			brett.addBurger(navn);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
	
	
	
}
