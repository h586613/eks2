package oppgave2;


public class Hamburgerbrett {

	private int kapasitet;
	private Hamburger[] brett; // er det meningen vi skal bruke Queue, eller lage våre egne Queue-lignende
								// burgerbrett bare?
	private int antallburgere;
	private int foran;
	private int nextavailablespace;
	private int burgernr;
	private Hamburger burger;
	String forklaring;
	private int burgerid;

	public Hamburgerbrett(int kapasitet) {
		this.kapasitet = kapasitet;
		brett = new Hamburger[kapasitet];
		antallburgere = 0;
		foran = kapasitet - 1;
		nextavailablespace = foran;
		burgernr = 1;
	}

	public synchronized void addBurger(String navn) throws InterruptedException {
		while(isFull()) {
			forklaring = navn + ": Brett fullt, kan ikke legge til, venter. Antall burgere er "
					+ antallburgere + ": ";
			skrivutferdig();
			wait();
		}
		if (isEmpty()) {
			burger = new Hamburger(burgernr);
			
				brett[foran] = burger;
				
			burgernr++;
			antallburgere++;
			nextavailablespace--;
			
			forklaring = navn + ": Brettet var tomt, la til første burger ◖"+ burger.getId()
						+"◗. Antall burgere på brettet er "
						+ antallburgere + ": ";
			
			skrivutferdig(); //de gangene vi vil skrive ut en setning/forklaring + hvordan selve brettet ser ut bruker vi denne
			
			
			
		} else if (!isFull()) {
			burger = new Hamburger(burgernr);
			
			brett[nextavailablespace] = burger;
			
			antallburgere++;
			burgernr++;
			if(!isFull()) {				//siden det er fare for at man gjør brettet fullt mens man er inne i den første løkken må man sjekke igjen
				nextavailablespace--;
			}
			else if(isFull()) {
				nextavailablespace=0;
			}
			forklaring = navn + ": Det var plass på brettet, la på burger ◖"
						+ burger.getId() + "◗. Antall burgere på brettet er "
						+ antallburgere + ": ";
			
			skrivutferdig();
			
		} 
		notify();
	}

	public synchronized void removeBurger(String navn) throws InterruptedException {
		while (isEmpty()){
			System.out.println(navn + ": Brett tomt, ingen burgere å fjerne, venter.");
			wait();
		}

		if (!isEmpty()) { //hvis brettet har burgere på seg (ikke er tomt), kan vi gå fram med å fjerne den fremste i køen
			burgerid = brett[foran].getId(); //setter av burger for å bruke id-en til utskrift lengre nede
			brett[foran] = null; //"fjerner" den fremste burgeren aka setter den til null
			antallburgere--;
			
			for(int i = foran-1; i>=0; i--) { //(foran-1) istedenfor (antallburgere)
				brett[i+1] = brett[i];		  //forskyver alle resterende burgere ett hakk opp
			}
			brett[0]=null; //setter [0] til null etter hver gang vi har tatt ut én burger og flyttet alle burgerne ett hakk opp
			
			
			if(!isEmpty()) { //hvis vi har fjernet en burger og det er burger(e) igjen
				
				forklaring = navn + " fjernet burger ◖"+ burgerid
									+ "◗. Antall burgere igjen på brettet etter fjerning er "
									+ antallburgere + ": ";
				
				skrivutferdig();
				nextavailablespace++;
				
			}else if(isEmpty()) { //hvis vi har fjernet en burger og brettet nå er tomt
				System.out.println(navn + " fjernet burger ◖"+ burgerid + "◗. Brettet er nå tomt.");
				nextavailablespace=foran;
			}
		
		}
		notify();
	}


	
	
//	@Override
//	public String toString() {
//		return Arrays.toString(brett);
//	}
	
	
	public String finerebrett() {
		String s="";
		for (Hamburger burger:brett) {
			if(burger != null) {
			s = s + burger.toString();
			}
		}
		return s;
	}
	
	private String absoluttforskyvbrett(String setning) {
		String s = "";
		String st = setning;
		while((setning+finerebrett()).length() < 120) {
				s = s + " ";
				setning = st + s;
			}
		return setning + finerebrett();
		}
	
	
	private void skrivutferdig() {
	System.out.println(absoluttforskyvbrett(forklaring));
	}
	

//	private void setNextavailablespace() {
//		if(isEmpty()) {
//				nextavailablespace = foran;
//			}
//		else if(!isEmpty()) {
//			
//		}
//				
//		else if (!isFull()) {
//				nextavailablespace-- ;
//				}
//				
//
//		
//	}
	
	
	
	public boolean isEmpty() {
		return antallburgere == 0;
	}

	public boolean isFull() {
		return antallburgere==kapasitet;
	}

}
