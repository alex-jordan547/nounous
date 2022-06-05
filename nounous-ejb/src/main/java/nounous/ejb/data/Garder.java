package nounous.ejb.data;

import java.time.LocalTime;
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
	private LocalTime heure_debut;
	private LocalTime heure_fin;
	private boolean aMange;

}
