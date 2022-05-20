package nounous.jsf.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class Compte implements Serializable  {

	
	// Champs
	
	Integer		id;
	
	@NotBlank( message = "Le pseudo doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le pseuo : 25 car. maxi" )
	private String		pseudo;

	@NotBlank( message = "Le mot de passe doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le mot de passe : 25 car. maxi" )
	private String		motDePasse;

	@NotBlank( message = "L'adresse e-mail doit être renseigné")
	@Size(max=100, message = "Valeur trop longue pour l'adresse e-mail : 100 car. maxi" )
	@Email( message = "Adresse e-mail invalide" )
	private String		email;
	
	private List<String> roles = new ArrayList<>();	

	
	// Constructeurs
	
	public Compte() {
	}
	
	public Compte(Integer id, String pseudo, String motDePasse, String email) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
	}

	
	// Getters & setters

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
	public boolean isInRole( String role ) {
		return roles.contains( role );
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
		var other = (Compte) obj;
		return Objects.equals(id, other.id);
	}
	
}
