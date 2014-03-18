package fr.dauphine.business;

import java.io.IOException;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.registry.infomodel.User;

import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.enums.ProfileNameEnum;
import fr.dauphine.business.util.Messages;


@ManagedBean(name="userLoginBean")
@SessionScoped
public class UserLoginBean {
	
	/**email.*/
	private String email;
	/**password.*/
	private String password;
	/**userType.*/
	private String userType;
	/**connected.*/
	private boolean connected = false;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * @return the connected
	 */
	public boolean isConnected() {
		return connected;
	}
	/**
	 * @param connected the connected to set
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	  public String login(){

		if(!isExistUser()){	
			return null;
		}
		this.connected = true;

		if(ProfileNameEnum.ADMINISTRATEUR.name().equalsIgnoreCase(userType)){
			return "Administrateur-header-connected";
		}else{
			return "myAccount";
		}
	}

	public String disconnect(){

		this.connected = false;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		try {
			ec.redirect("login.xhtml");
		} catch (IOException e) {
		} 

		return "login";
	}

	public void checkLogin(){
		
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler navigationHandler =  (ConfigurableNavigationHandler) currentInstance.getApplication().getNavigationHandler();

		if(isExistUser()){
			return;
		}

		navigationHandler.performNavigation("login");
	}
	
	private boolean isExistUser() {
		PersistenceService ps = PersistenceService.getInstance();
		FacesMessage message = null;
		if(email != null  && !"".equals(email) && password != null && !"".equals(password)){
		    fr.dauphine.business.entity.User user =  ps.getUser(this.email);
			if(user != null &&  user.getEmail().equals(this.email) && user.getPassword().equalsIgnoreCase(password)){
				if(user.getStatus().equals("VALIDATED"))
				{
					userType = user.getProfile().getName();
					return true;					
				}
				else
				{
					message = Messages.getMessage("fr.dauphine.business.messages", "notYetActivated", null);
			        message.setSeverity(FacesMessage.SEVERITY_ERROR);
			        FacesContext.getCurrentInstance().addMessage("userEmail", message);					
			        return false;
				}
			}
		}
		
		message = Messages.getMessage("fr.dauphine.business.messages", "invalidLoginPassword", null);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("userEmail", message);

		return false;
	}

	public String forgotPassword(){

		return "forgot-password";
	}

	public String newAccount(){

		return "sign-up";
	}

}
