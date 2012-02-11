package il.faut.mettre.un.packag;

import il.faut.mettre.un.packag.Handler_Personne.NotSelfArgument;

public class Monsieur implements IPersonne {

	public void setAge(int age) {
		this.age = age;
	}

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
	public void setAdresse(String adr) {
		// TODO Auto-generated method stub
		this.adresse = adr;
	}
	
	public void toggleCigare() {
		this.cigare = !this.cigare;
	}

	@NotSelfArgument
	public String getNom() {
		return nom;
	}
	
	@NotSelfArgument
	public void setNom( String nom ) {
		this.nom = nom;
	}


}
