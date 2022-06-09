package nounous.ejb.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Parent{
	@Id
	@Column(name = "idParent")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String firstname;
	private String lastname;
	private String adresse;
	private String phone;
	@OneToOne
	@JoinColumn(name = "idCompte")
	private Compte compte;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Enfant> enfants=new ArrayList<>();;
	
}
