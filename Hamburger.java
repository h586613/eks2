package oppgave2;

public class Hamburger {

	private int id;

	public Hamburger(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "◖" + id + "◗";
	}

	public void setNavn(int id) {
		this.id = id;
	}
}
