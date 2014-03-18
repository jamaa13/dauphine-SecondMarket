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
@Table(name = "stock_option")
public class StockOption implements Serializable{

	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;

	/** Clé primaire. */
	private int id;

	private int prixInitial;
	
	private int prixMaturite;
	
	private int prixOption;
	
	private Date dateMaturite;
	
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
	
	@Column(name = "PRIX_INITIAL")
	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	@Column(name = "PRIX_MATURITE")
	public int getPrixMaturite() {
		return prixMaturite;
	}

	public void setPrixMaturite(int prixMaturite) {
		this.prixMaturite = prixMaturite;
	}
	@Column(name = "PRIX_OPTION")
	public int getPrixOption() {
		return prixOption;
	}

	public void setPrixOption(int prixOption) {
		this.prixOption = prixOption;
	}
	@Column(name = "DATE_MATURITE")
	public Date getDateMaturite() {
		return dateMaturite;
	}

	public void setDateMaturite(Date dateMaturite) {
		this.dateMaturite = dateMaturite;
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
