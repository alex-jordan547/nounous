package nounous.ejb.data;

import java.util.Date;
import java.util.List;

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
public class Parent{
	private int id;
	private String firstname;
	private String lastname;
	private String adress;
	private String phone;
	private Compte compte;
	private List<Enfant> enfants;
	
}
