package fr.dauphine.business.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proposition")
@NamedQueries({
	@NamedQuery(
			  name="findReceivedPropositions",
			  query="Select ppt from Proposition ppt " 
					  +	" INNER JOIN  ppt.contrat ctt " 
					  + " where ctt.user.email = :email"
			),
	@NamedQuery(
			  name="findSendPropositions",
			  query="Select ppt from Proposition ppt " 
					  +	" INNER JOIN  ppt.user usr " 
					  + " where usr.email = :email"
			)			
})	
public class Proposition implements Serializable{

	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;

	/** Clé primaire. */
	private int id;
	
	/** */
	private String description;
	
	private int prix;
	
	private String status; 

	private User user;
	
	private Contrat contrat;
	
	/** Attribut "etatProposition". */
	private List<EtatProposition> etatProposition;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	@OneToMany(mappedBy = "proposition", fetch = FetchType.LAZY)
	public List<EtatProposition> getEtatProposition() {
		if (null == etatProposition) {
			etatProposition = new LinkedList<EtatProposition>();
		}
		return etatProposition;
	}

	public void setEtatProposition(List<EtatProposition> etatProposition) {
		this.etatProposition = etatProposition;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ctt_id", nullable = false)
	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usr_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
