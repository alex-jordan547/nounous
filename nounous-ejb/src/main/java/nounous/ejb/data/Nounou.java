package nounous.ejb.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@NoArgsConstructor
@Entity
public class Nounou{
	@Id
	@Column(name = "idNounou")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String firstname;
	private String lastname;
	private String adresse;
	private String phone;
	@OneToOne
	@JoinColumn(name = "idCompte")
	private Compte compte;
}
