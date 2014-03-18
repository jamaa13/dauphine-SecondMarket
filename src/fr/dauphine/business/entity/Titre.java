package fr.dauphine.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "titre")
@NamedQueries(value = { 
		@NamedQuery(
				name = "findTitreByContratId", 
				query = "SELECT tit FROM Titre tit WHERE tit.contrat.id = :id"
					)
					
})
public class Titre implements Serializable{

	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;

	/** Clé primaire. */
	private int id;

	/** Attribut "name". */
	private int prixDepart;

	private int prixActuel;
	
	private Date creationDate;
	
	@OneToOne
	private Contrat contrat;
	
	private List<Enchere> encheres;
	
	
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
	@Column(name = "PRIX_DEPART")
	public int getPrixDepart() {
		return prixDepart;
	}

	public void setPrixDepart(int prixDepart) {
		this.prixDepart = prixDepart;
	}
	@Column(name = "PRIX_ACTUEL")
	public int getPrixActuel() {
		return prixActuel;
	}

	public void setPrixActuel(int prixActuel) {
		this.prixActuel = prixActuel;
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
	
	@OneToMany(mappedBy = "titre", fetch = FetchType.LAZY)
	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

}
