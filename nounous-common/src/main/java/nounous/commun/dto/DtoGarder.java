package nounous.commun.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class DtoGarder implements Serializable {

	// Champs

	private int id;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private LocalDate dateJ; 
	private boolean aMange;

	
}
