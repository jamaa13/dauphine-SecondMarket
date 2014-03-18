package fr.dauphine.business.offre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import fr.dauphine.business.UserLoginBean;
import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.entity.Contrat;
import fr.dauphine.business.entity.Proposition;
import fr.dauphine.business.entity.User;
import fr.dauphine.business.util.Messages;
import fr.dauphine.business.vo.PropositionVO;


@ManagedBean(name = "propositionBean")
@RequestScoped
public class PropositionBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;

	private List<PropositionVO> result;

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public List<PropositionVO> getResult() {
		if (null == result)
		{
			result = new ArrayList<PropositionVO>();
		}
		return result;
	}

	public void setResult(List<PropositionVO> result) {
		this.result = result;
	}

	
	public String sentProposition()
	{
		PersistenceService ps = PersistenceService.getInstance();
		List<Proposition> offersByUserId = ps.getSentProposition(userLoginBean.getEmail(),"ACTIVE");
		for (Proposition ppt : offersByUserId)
		{
			PropositionVO proposition = new PropositionVO();
			proposition.setDescription(ppt.getDescription());
			
			Contrat contrat = ppt.getContrat();
			if(null != contrat)
			{
				proposition.setLibelleOffre(contrat.getLibelle());	
			}
			
			proposition.setPrix(ppt.getPrix());
			User user = ppt.getUser();
			
			if(null != user)
			{
				proposition.setUserId(user.getEmail());
			}
			getResult().add(proposition);
			
		}
		
		
		return "proposition-envoyes";
	}
	
	
	public String receivedProposition()
	{
		PersistenceService ps = PersistenceService.getInstance();
		List<Proposition> offersByUserId = ps.getReceivedProposition(userLoginBean.getEmail(),"ACTIVE");
		for (Proposition ppt : offersByUserId)
		{
			PropositionVO proposition = new PropositionVO();
			proposition.setDescription(ppt.getDescription());
			Contrat contrat = ppt.getContrat();
			if(null != contrat)
			{
				proposition.setLibelleOffre(contrat.getLibelle());	
			}
			
			proposition.setPrix(ppt.getPrix());
			
			proposition.setId(ppt.getId());
			
			proposition.setStatus(ppt.getStatus());
			
			User user = ppt.getUser();
			
			if(null != user)
			{
				proposition.setUserId(user.getEmail());
			}
			getResult().add(proposition);
		}
		
		
		return "proposition-recus";
	}
	
	
	public String validatePropisition()
	{
		PersistenceService dao = PersistenceService.getInstance();
		
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String propId = req.getParameter("propId");
		
		boolean validateProp = dao.validateProposition(propId);

		if(validateProp)
		{
			FacesMessage message = Messages.getMessage("fr.dauphine.business.messages", "confirm_message", null);
	        FacesContext.getCurrentInstance().addMessage("confirm_message", message);
		}
		Messages.addSuccessMessage("La proposition a été validée");
		
		return "proposition-recus";
	}
}
