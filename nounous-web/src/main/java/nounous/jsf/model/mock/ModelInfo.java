package nounous.jsf.model.mock;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
 

@RequestScoped
@Named
public class ModelInfo {
	
	// Champs
	
	private String 		titre;
	
	private String		texte;

	
	// Getters & Setters
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	

}
