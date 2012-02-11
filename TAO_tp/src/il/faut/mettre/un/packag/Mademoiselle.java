package il.faut.mettre.un.packag;

import il.faut.mettre.un.packag.Handler_Personne.NotSelfArgument;

public class Mademoiselle implements IPersonne {

	private String nom;
	private String adresse;
	private int age;
	private int nbConquete;
	
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

	@NotSelfArgument
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNbConquete() {
		return nbConquete;
	}

	public void setNbConquete(int nbConquete) {
		this.nbConquete = nbConquete;
	}


	@Override
	public void setAdresse(String adr) {
		this.adresse = adr;
	}
	
	public int getTourDePoitrine() {
		return (nom.hashCode()*this.age) / 2;
	}

	@NotSelfArgument
	public String getNom() {
		return nom;
	}



}
