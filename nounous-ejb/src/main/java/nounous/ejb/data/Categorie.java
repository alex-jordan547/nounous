package nounous.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "categorie" )
public class Categorie {
    
	
    // Champs
    
	@Id
	@GeneratedValue( strategy = IDENTITY )
	@Column( name = "idcategorie" )
    private int         	id;
    
	@Column( name = "libelle" )
    private String      	libelle;
	
	
	// Constructeurs

	public Categorie() {
		super();
	}
    
    public Categorie(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
    
    
    // Getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
    // soString()
    @Override
    public String toString() {
    	return libelle;
    }

    
	// hashcode() & equals()

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
		Categorie other = (Categorie) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
}
