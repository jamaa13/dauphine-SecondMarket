package fr.dauphine.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
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
@Table(name = "contrat")
@NamedQueries({
	@NamedQuery(
			  name="findLastOffers",
			  query="Select ctt from Contrat ctt order by ctt.creationDate"
			),
	@NamedQuery(
			  name="findOffersByUserId",
			  query="Select ctt from Contrat ctt where ctt.user.email = :email"
			),
	@NamedQuery(
			  name="findCompanniesOffersByStatus",
			  query="Select ctt from Contrat ctt where ctt.status= :status"
			  		 + " AND ctt.user.company is not null"
			),
	@NamedQuery(
			  name="findInvestiseursOffersByStatus",
			  query="Select ctt from Contrat ctt where ctt.status= :status"
			  		 + " AND ctt.user.company is null"
			),

	@NamedQuery(
					  name="findOfferById",
					  query="Select ctt from Contrat ctt where ctt.id= :id"
					)			
})	

public class Contrat implements Serializable{
	
	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;
	
	/** Clé primaire. */
	private int id;
	
	/** Attribut "libelle". */
	private String libelle;
	
	/** Attribut "commantaire". */
	private String commantaire;

	/** Attribut "commantaire". */
	private String status;
	
	/** Attribut "user". */
	private User user;
	
	/** Attribut "propositions". */
	private List<Proposition> propositions;	
	
	/** Attribut "creationDate". */
	private Date creationDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 255, nullable = true)
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@Column(length = 255, nullable = true)
	public String getCommantaire() {
		return commantaire;
	}

	public void setCommantaire(String commantaire) {
		this.commantaire = commantaire;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usr_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the creationDate
	 */
	@Column(name = "creation_date")
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@OneToMany(mappedBy = "contrat", fetch = FetchType.LAZY)
	public List<Proposition> getPropositions() {
		if (null == propositions) {
			propositions = new LinkedList<Proposition>();
		}
		return propositions;
	}

	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
