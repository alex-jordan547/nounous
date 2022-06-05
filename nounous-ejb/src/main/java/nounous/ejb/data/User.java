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
public class User {
	private int id;
	private String email;
	private String password;
	private String firstname;
	private String lastanme;
	private String adress;
	private String phone;

}
