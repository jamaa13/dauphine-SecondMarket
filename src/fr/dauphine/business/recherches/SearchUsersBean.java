package fr.dauphine.business.recherches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.entity.User;
import fr.dauphine.business.util.Messages;
import fr.dauphine.business.vo.UserVO;


@ManagedBean(name="searchUsersBean")
@SessionScoped
public class SearchUsersBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String profile;
	
	private List<UserVO> result;
	
	private int selectedUser;
	
	private String status;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @return the result
	 */
	public List<UserVO> getResult() {
		if (null == result)
		{
			result = new ArrayList<UserVO>();
		}
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<UserVO> result) {
		this.result = result;
	}
	
	public UserVO getCurrentSelectedUser(){
		
		return getResult().get(selectedUser);		
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**Méthodes de navigation*/
	
	public String search(){
		PersistenceService dao = PersistenceService.getInstance();
		
		setResult(null);

		List<User> users = dao.searcheUserByCriteria(email, status, profile);
		
		if (null != users && users.size() > 0)
		{
		for (User user : users)
		{
			UserVO userVO = new UserVO();
			userVO.setEmail(user.getEmail());
			userVO.setFirstName(user.getFirstName());
			userVO.setLastName(user.getLastName());
			userVO.setPhoneNbr(user.getPhoneNbr());
			userVO.setStatus(user.getStatus());
			userVO.setValidate(false);
			getResult().add(userVO);
		}
		}
		else
		{
			setResult(null);
		}
		
		return "search-users";
	}

	/**Méthodes de navigation*/
	
	public String apply(){
		//TODO le premier paramètre deverai être null
		
		PersistenceService dao = PersistenceService.getInstance();
		List<String> userToValidate = new ArrayList<String>();
		for (UserVO userVO : result)
		{
			if (userVO.isValidate())
			{
				userToValidate.add(userVO.getEmail());
			}
		}
		
		boolean validateUsers = dao.validateUsers(userToValidate);

		if(validateUsers)
		{
			FacesMessage message = Messages.getMessage("fr.dauphine.business.messages", "confirm_message", null);
	        FacesContext.getCurrentInstance().addMessage("confirm_message", message);
		}
		Messages.addSuccessMessage("Le ou les utilisateurs ont sont validés");
		return  "search-users";
	}

}

