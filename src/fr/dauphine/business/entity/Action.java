package fr.dauphine.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "action")
public class Action implements Serializable{

	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;

	/** Clé primaire. */
	private int id;

	/** Attribut "name". */
	private int prix;

	private Date creationDate;
	
	private Contrat contrat;
	
	
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
	
	@Column(name = "CREATION_DATE")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@OneToOne
	@JoinColumn(name = "ctt_id", nullable = true)
	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

}
