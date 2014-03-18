package fr.dauphine.business.offre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.dauphine.business.UserLoginBean;
import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.entity.Contrat;
import fr.dauphine.business.entity.Enchere;
import fr.dauphine.business.entity.Titre;
import fr.dauphine.business.entity.User;
import fr.dauphine.business.vo.EnchereVO;


@ManagedBean(name = "enchereBean")
@RequestScoped
public class EnchereBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;

	private List<EnchereVO> result;

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public List<EnchereVO> getResult() {
		if (null == result)
		{
			result = new ArrayList<EnchereVO>();
		}
		return result;
	}

	public void setResult(List<EnchereVO> result) {
		this.result = result;
	}

	
	public String sentEnchere()
	{
		PersistenceService ps = PersistenceService.getInstance();
		List<Enchere> encheres = ps.getSentEnchere(userLoginBean.getEmail(),"ACTIVE");
		for (Enchere ech : encheres)
		{
			EnchereVO enchereVO = new EnchereVO();
			Titre titre = ech.getTitre();
			
			Contrat contrat = titre.getContrat();

			if(null != contrat)
			{
				enchereVO.setLibelleOffre(contrat.getLibelle());	
			}
			
			enchereVO.setPrix(ech.getPrix());			
			enchereVO.setStatus(ech.getStatus());
			User user = ech.getUser();
			
			if(null != user)
			{
				enchereVO.setUserId(user.getEmail());
			}
			getResult().add(enchereVO);
		}
		
		
		return "enchere-envoyes";
	}
	
	
	public String receivedEchere()
	{
		PersistenceService ps = PersistenceService.getInstance();
		List<Enchere> encheres = ps.getReceivedEnchere(userLoginBean.getEmail(),"ACTIVE");
		for (Enchere ech : encheres)
		{
			EnchereVO enchereVO = new EnchereVO();
			
			Titre titre = ech.getTitre();
			Contrat contrat = titre.getContrat();
			
			if (null != titre)
			{
				enchereVO.setPrixActuel(titre.getPrixActuel());		
				
			}
			if(null != contrat)
			{
				enchereVO.setLibelleOffre(contrat.getLibelle());	
						
			}
			
			enchereVO.setPrix(ech.getPrix());
			
			enchereVO.setStatus(ech.getStatus());
			
			User user = ech.getUser();
			
			if(null != user)
			{
				enchereVO.setUserId(user.getEmail());
			}
			getResult().add(enchereVO);
		}
		
		
		return "enchere-recus";
	}
}
