package fr.dauphine.business.offre;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import fr.dauphine.business.UserLoginBean;
import fr.dauphine.business.dao.PersistenceService;



@ManagedBean(name = "makeEnchereBean")
@RequestScoped
public class MakeEnchereBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String offerId;
	private String contratLibelle;
	private String description;
	private int prixActuel;
	private int prix;
	
	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;
	
	public MakeEnchereBean()
	{
	System.out.println("dfsd");	
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


	public int getPrixActuel() {
		return prixActuel;
	}

	public void setPrixActuel(int prixActuel) {
		this.prixActuel = prixActuel;
	}

	/** méthodes de navigation. */
	public String makeEnchere()
	{
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			PersistenceService ps = PersistenceService.getInstance();
			String retunTo = "companies-offers";
			
			offerId = req.getParameter("idOffer");
			prixActuel = Integer.valueOf(req.getParameter("prixActuel"));
			
			if (prixActuel < prix)
			{
				try{ 
					ps.saveEnchere(userLoginBean.getEmail(), description,offerId, prix);
				}
				catch (Exception e) {
					return retunTo;
				}
			}
			else
			{
				return retunTo;
			}
			
			return retunTo;
	}

}
