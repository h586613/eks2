package oppgave2;


public class Oppgave2Main {

	public static void main(String[] args) {
		
			   
			  final String[] kokker = {"Anne", "Erik", "Knut", "Valborg"}; 
			  final String[] servitorer = {"Mia", "Per", "Lise"}; 
			  final int KAPASITET = 9;
			 
			 skrivUtHeader(kokker, servitorer, KAPASITET); 
			  
			 Hamburgerbrett brett = new Hamburgerbrett(KAPASITET); 
			  
			 
			   
			  for (String navn : kokker) { 
			   new Kokk(brett, navn).start(); 
			  } 
			  for (String navn : servitorer) { 
			   new Servitor(brett, navn).start(); 
			  } 
	}

	
	private static void skrivUtHeader(String[] kokker, String[] servitorer, int kAPASITET) {
		
		skrivUtStringListe(kokker, "kokk");
		System.out.println();
		
		skrivUtStringListe(servitorer, "servitør");
		System.out.println();
		
		System.out.println("Brettets kapasitet er " + kAPASITET + " hamburgere.");
		System.out.println();
		
	}
	
	
	private static void skrivUtStringListe(String[] liste, String jobbtittel) {
		if(liste.length>1) {
			System.out.print("De " + liste.length + " " + jobbtittel + "ene er: ");
			for(int i=0;i<liste.length;i++) {
				if(i == liste.length-2) {
					System.out.print(liste[i] + " og ");
				}
				else if(i == liste.length-1) {
					System.out.print(liste[i] + ".");
				}
				else {
					System.out.print(liste[i] + ", ");
				}	
			}
		}
		else {
			System.out.print(jobbtittel + "en er: " + liste[0]);
		}	
	}

}
