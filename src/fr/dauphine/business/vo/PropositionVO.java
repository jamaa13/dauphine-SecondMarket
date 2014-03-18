package fr.dauphine.business.vo;

import java.io.Serializable;


public class PropositionVO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String description;
	
	private int id;
	
	private int prix;

	private String userId;
	
	private String libelleOffre;

	private String status;
	
	private boolean validate;

	
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

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
