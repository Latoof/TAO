
public class Monsieur implements IPersonne {

	private String nom, adresse;
	private int	age;
	private boolean cigare;
	
	public Monsieur() {
		this.nom = "nomGen";
		this.age = 192;
		this.cigare = true;
	}

	public Monsieur(String nom, String adresse, int age, boolean cigare) {
		//super();
		this.nom = nom;
		this.adresse = adresse;
		this.age = age;
		this.cigare = cigare;
	}

	@Override
	public String getAdresse() {
		// TODO Auto-generated method stub
		return this.adresse;
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return this.age;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.nom;
	}

	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		this.nom = name;
	}

	@Override
	public void setAdresse(String adr) {
		// TODO Auto-generated method stub
		this.adresse = adr;
	}
	
	public void toggleCigare() {
		this.cigare = !this.cigare;
	}

}
