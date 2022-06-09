package nounous.jsf.data;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
public class Garder {
	
	private int id;
	
	
	private String heureDebut;
	
	
	private String heureFin;
	
	
	private Date dateJ; 
	
	
	private boolean aMange;
	
	
	private Contrat contrat;
	
	@Override
	public String toString() {
		return heureDebut.toString() + " - "+ heureFin.toString();
	}
	
	
}
