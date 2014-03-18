package fr.dauphine.business.offre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.dauphine.business.UserLoginBean;
import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.entity.Company;
import fr.dauphine.business.entity.Contrat;
import fr.dauphine.business.vo.OfferVO;


@ManagedBean(name = "offresBean")
@RequestScoped
public class OffresBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;

	private List<OfferVO> result;


	public List<OfferVO> getResult() {
		if (null == result)
		{
			result = new ArrayList<OfferVO>();
		}
		return result;
	}


	public void setResult(List<OfferVO> result) {
		this.result = result;
	}


	/**
	 * @return the userLoginBean
	 */
	public UserLoginBean getUserLoginBean()
	{
		return userLoginBean;
	}


	/**
	 * @param userLoginBean the userLoginBean to set
	 */
	public void setUserLoginBean(UserLoginBean userLoginBean)
	{
		this.userLoginBean = userLoginBean;
	}

	public String companiesOffers()
	{
		PersistenceService ps = PersistenceService.getInstance();
		List<Contrat> offersByUserId = ps.getOffersCompanniesByStatus("ACTIVE");
		for (Contrat ctt : offersByUserId)
		{
			OfferVO offre = new OfferVO();
			offre.setId(ctt.getId());
			offre.setCommantaire(ctt.getCommantaire());
			offre.setLibelle(ctt.getLibelle());
			offre.setStatus(ctt.getStatus());
			offre.setCreationDate(ctt.getCreationDate());
				
			String typeContrat = ps.getTypeContrat(ctt.getId());
			offre.setType(typeContrat);
			
			if (null != typeContrat && typeContrat.equals("Titre"))
			{
				offre.setPrixActuel(ps.getPrixActuelByContratId(ctt.getId()));
			}
			Company company = ctt.getUser().getCompany();
			if (null != company)
			{
				offre.setCompanyName(company.getCompanyName());
			}
			getResult().add(offre);
		}
		
		
		return "companies-offers";
	}
	
	public String investiseurOffers()
	{
		PersistenceService ps = PersistenceService.getInstance();
		List<Contrat> offersByUserId = ps.getOffersInvestiseursByStatus("ACTIVE");
		for (Contrat ctt : offersByUserId)
		{
			OfferVO offre = new OfferVO();
			offre.setId(ctt.getId());
			offre.setCommantaire(ctt.getCommantaire());
			offre.setLibelle(ctt.getLibelle());
			offre.setStatus(ctt.getStatus());
			offre.setCreationDate(ctt.getCreationDate());
			String typeContrat = ps.getTypeContrat(ctt.getId());
			offre.setType(typeContrat);
			Company company = ctt.getUser().getCompany();
			if (null != company)
			{
				offre.setCompanyName(company.getCompanyName());
			}
			getResult().add(offre);
		}
		
		return "companies-offers";
//		return "investors-offers";
	}

	/** méthodes de navigation. */
	public String selectCompanyOffers()
	{
		return "company-offers";
	}
	
	
	public String mesOffres()
	{
		PersistenceService ps = PersistenceService.getInstance();
		List<Contrat> contrat = ps.getOffersByUserId(userLoginBean.getEmail());
		for (Contrat ctt : contrat)
		{
			OfferVO offre = new OfferVO();
			offre.setCommantaire(ctt.getCommantaire());
			offre.setLibelle(ctt.getLibelle());
			offre.setStatus(ctt.getStatus());
			offre.setCreationDate(ctt.getCreationDate());
			
			Company company = ctt.getUser().getCompany();
			if (null != company)
			{
				offre.setCompanyName(company.getCompanyName());	
			}
			
			getResult().add(offre);
		}
		
		return "mes-offres";
	}
}
