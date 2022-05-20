package nounous.jsf.data;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
public class Telephone implements Serializable {

	
	// Champs

	private Integer			id;
	
	@NotBlank( message = "Le libellé doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le libellé : 25 car. maxi" )
	private String			libelle;
	
	@NotBlank( message = "Le numéro de téléphone doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le numéro de téléphone : 25 car. maxi" )
	private String			numero;

	
	// Constructeurs
	
	public Telephone() {
	}

	public Telephone(Integer id, String libelle, String numero) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.numero = numero;
	}
	

	// Getters & setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		var other = (Telephone) obj;
		return Objects.equals(id, other.id);
	}
	
}
