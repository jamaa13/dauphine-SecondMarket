package fr.dauphine.business.vo;

import java.io.Serializable;
import java.util.Date;

public class OfferVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Attribut "id". */
	private int id;
	
	/** Attribut "libelle". */
	private String companyName;
	
	/** Attribut "libelle". */
	private String libelle;
	
	/** Attribut "commantaire". */
	private String commantaire;

	/** Attribut "status". */
	private String status;
	
	/** Attribut "type". */
	private String type;

	/** Attribut "creationDate". */
	private Date creationDate;
	
	private int prixActuel;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCommantaire() {
		return commantaire;
	}

	public void setCommantaire(String commantaire) {
		this.commantaire = commantaire;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrixActuel() {
		return prixActuel;
	}

	public void setPrixActuel(int prixActuel) {
		this.prixActuel = prixActuel;
	}
	
	
}
