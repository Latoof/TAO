
public class Mademoiselle implements IPersonne {

	String nom;
	String adresse;
	int age;
	
	public Mademoiselle() {
		this.nom = "nomgen";
		this.age = 18;
	}
	
	public Mademoiselle(String nom) {
		this.nom = nom;
	}
	
	public Mademoiselle(String nom, int age) {
		this.nom = nom;
		this.age = age;
	}
	
	@Override
	public String getAdresse() {
		return this.adresse;
	}

	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public String getName() {
		return this.nom;
	}

	@Override
	public void rename(String name) {
		this.nom = name;
	}

	@Override
	public void setAdresse(String adr) {
		this.adresse = adr;
	}
	
	public int getTourDePoitrine() {
		return (nom.hashCode()*this.age) / 2;
	}

}
