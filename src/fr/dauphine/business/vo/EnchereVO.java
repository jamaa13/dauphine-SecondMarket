package fr.dauphine.business.vo;

import java.io.Serializable;


public class EnchereVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int prix;

	private String userId;

	private String status;
	
	private String libelleOffre;

	private int prixActuel;
	
	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLibelleOffre() {
		return libelleOffre;
	}

	public void setLibelleOffre(String libelleOffre) {
		this.libelleOffre = libelleOffre;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrixActuel() {
		return prixActuel;
	}

	public void setPrixActuel(int prixActuel) {
		this.prixActuel = prixActuel;
	}

	
}

