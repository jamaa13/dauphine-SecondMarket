package fr.dauphine.business.offre;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import fr.dauphine.business.UserLoginBean;
import fr.dauphine.business.dao.PersistenceService;



@ManagedBean(name = "makePropositionBean")
@RequestScoped
public class MakePropositionBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String offerId;
	private String contratLibelle;
	private String description;
	private int prix;
	
	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;
	
	
	public MakePropositionBean()
	{
	
	}
	
	public String getOfferId() {
		return offerId;
	}


	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}


	public String getDescription() {
		return description;
	}
	
	
	public String getContratLibelle() {
		return contratLibelle;
	}


	public void setContratLibelle(String contratLibelle) {
		this.contratLibelle = contratLibelle;
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

	
	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}


	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}


	/** méthodes de navigation. */
	public String makeOffer()
	{
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			PersistenceService ps = PersistenceService.getInstance();
			String retunTo = "companies-offers";
			offerId = req.getParameter("idOffer");
			try{
				ps.saveProposition(userLoginBean.getEmail(), description,offerId, prix);
			}
			catch (Exception e) {
				return retunTo;
			}
			
			return retunTo;
	}

}

