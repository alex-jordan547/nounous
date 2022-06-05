package nounous.ejb.data;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
public class Enfant {
	//let me write some comment tsague's computer
	// let try to do this project and end it for real
	private int id;
	private String firstname;
	private String lastanme;
	private Date date_naissance;

}
