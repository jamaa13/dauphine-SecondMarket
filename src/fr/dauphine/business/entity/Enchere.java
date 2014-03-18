package fr.dauphine.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "enchere")
@NamedQueries({
	@NamedQuery(
			  name="findReceivedEncheres",
			  query="Select ech from Enchere ech " 
					  +	" INNER JOIN  ech.titre tit "
					  +	" INNER JOIN  tit.contrat ctt "
					  + " where ctt.user.email = :email"
			),
	@NamedQuery(
			  name="findSendEncheres",
			  query="Select ech from Enchere ech " 
					  +	" INNER JOIN  ech.user usr " 
					  + " where usr.email = :email"
			)			
})

public class Enchere implements Serializable{

	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;

	/** Clé primaire. */
	private int id;

	/** Attribut "name". */
	private int prix;

	private Date creationDate;

	private String status;

	private User user;
	
	private Titre titre;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}
	
	/**
	 * Définit la clé primaire.
	 *
	 * @param id Valeur de la clé.
	 */
	public void setId(final int id)
	{
		this.id = id;
	}

	@Column(name = "PRIX")
	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	@ManyToOne
	@JoinColumn(name = "tit_id", nullable = true)
	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
	}
	
	@ManyToOne
	@JoinColumn(name = "usr_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CREATION_DATE")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
