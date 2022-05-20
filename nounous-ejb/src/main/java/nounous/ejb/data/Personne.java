package nounous.ejb.data;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table( name = "personne"  )
public class Personne {
	
	
	// Champs
	
	@Id
	@GeneratedValue( strategy = IDENTITY )
	@Column( name = "idpersonne" )
	private int				id;
	
	@Column( name = "nom" )
	private String			nom;
	
	@Column( name = "prenom" )
	private String			prenom;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "idcategorie" )
	private Categorie		categorie;

	@OneToMany( mappedBy = "personne", cascade = ALL, orphanRemoval = true  )
	@OrderBy( "libelle" )
	private List<Telephone>	telephones = new ArrayList<>();
	
	
	// Constructeurs
	
	public Personne() {
	}

	public Personne(int id, String nom, String prenom, Categorie categorie ) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setCategorie(categorie);
	}
	
	
	// Getters & setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

	
	// Actions
	
	public void ajouterTelephone( Telephone telephone ) {
		telephones.add( telephone );
	}
	
	public void retirerTelephone( Telephone telephone ) {
		telephones.remove(telephone);
	}
	
	
	// hashcode() + equals()
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
