package nounous.ejb.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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
@Entity
public class Enfant {
	//let me write some comment tsague's computer
	// let try to do this project and end it for real
	@Id
	@Column(name = "idenfant")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String firstname;
	private String lastname;
	private Date dateNaissance;
	
	@ManyToOne
	@JoinColumn(name="idParent")
	private Parent parent;
	@OneToOne(mappedBy = "enfant")
	private Contrat contrat;
	

}
