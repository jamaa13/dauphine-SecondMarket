package fr.dauphine.business.offre;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.dauphine.business.UserLoginBean;
import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.util.Messages;


@ManagedBean(name ="makeOffreBean")
@RequestScoped
public class MakeOfferBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;
	
	private String contractType;
	private String label;
	private String comment;
	
	private int initPrice;
	private int prixMaturite;	
	private int prixOption;
	private Date dateMaturite;
	private String actionPrice;
	private String actionNumber;
	
	
	
	
	
	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getInitPrice() {
		return initPrice;
	}

	public void setInitPrice(int initPrice) {
		this.initPrice = initPrice;
	}
	
	public String getActionPrice() {
		return actionPrice;
	}

	public void setActionPrice(String actionPrice) {
		this.actionPrice = actionPrice;
	}

	public String getActionNumber() {
		return actionNumber;
	}

	public void setActionNumber(String actionNumber) {
		this.actionNumber = actionNumber;
	}

	public int getPrixMaturite() {
		return prixMaturite;
	}

	public void setPrixMaturite(int prixMaturite) {
		this.prixMaturite = prixMaturite;
	}

	public int getPrixOption() {
		return prixOption;
	}

	public void setPrixOption(int prixOption) {
		this.prixOption = prixOption;
	}

	public Date getDateMaturite() {
		return dateMaturite;
	}

	public void setDateMaturite(Date dateMaturite) {
		this.dateMaturite = dateMaturite;
	}

	/** méthodes de navigation. */
	public String makeOffer()
	{
		
			PersistenceService ps = PersistenceService.getInstance();
			String retunToPage = "home";
			
			try{
				ps.saveContrat(userLoginBean.getEmail(), this);
				Messages.addSuccessMessage("Offre crée avec succée");
			}
			catch (Exception e) {
				Messages.addErrorMessage("Offres non crée");
				return retunToPage;
			}
			
			return retunToPage;
	}
}
