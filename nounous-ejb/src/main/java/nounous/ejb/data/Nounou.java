package nounous.ejb.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
public class Nounou{
	private int id;
	private String firstname;
	private String lastname;
	private String adress;
	private String phone;
	private Compte compte;
}
