package fr.dauphine.business;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.xml.registry.infomodel.User;

import fr.dauphine.business.dao.PersistenceService;
//import fr.dauphine.business.dao.PersistenceService;
//import fr.dauphine.business.entity.User;
import fr.dauphine.business.util.Messages;



@ManagedBean(name="myAccountBean")
@SessionScoped
public class MyAccountBean {
	
	/******************************Données personnelles.**********************************/
	/**civility.*/
	private String civility;
	/**firstName.*/
	private String firstName;
	/**lastName.*/
	private String lastName;
	/**phoneNbr.*/
	private String phoneNbr;

	
	/****************************** Authentification.**********************************/
	/**email.*/
	private String email;
	/**confirmEmail.*/
	private String confirmEmail;

	/**password.*/
	private String password;
	/**confirmPassword.*/
	private String confirmPassword;
	

	/**phoneNbr.*/
	private String status;
	
	@ManagedProperty(value="#{userLoginBean}")
    private UserLoginBean userLoginBean; 
	
	
	public MyAccountBean()
	{
		
	}
	/**
	 * @return the userLoginBean
	 */
	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}
	/**
	 * @param userLoginBean the userLoginBean to set
	 */
	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	 * @return the civility
	 */
	public String getCivility() {
		return civility;
	}
	/**
	 * @param civility the civility to set
	 */
	public void setCivility(String civility) {
		this.civility = civility;
	}
	/**
	 * @return the confirmEmail
	 */
	public String getConfirmEmail() {
		return confirmEmail;
	}
	/**
	 * @param confirmEmail the confirmEmail to set
	 */
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*******************************************************************/
	/*************************navigation methods*****************************/
	/*******************************************************************/

	
	
	
	
	private void updateAccount()
	{
		PersistenceService ps = PersistenceService.getInstance();
		try
		{
			ps.saveCondidat(this);
			Messages.addSuccessMessage("L'utilisateur est crée en attente de validation");
		}
		catch (Exception e) {
			Messages.addErrorMessage("Erreur de création ");
		} 
		
	}
	public String updateAccountWithoutCompany(){
		updateAccount();		
		return "login";
	}
	
	public String updateUserWithCompanyAccount(){
		updateAccount();
		return "list-companies";
	}

	public String getAccount(){
	PersistenceService ps = PersistenceService.getInstance();		
	if(userLoginBean != null && userLoginBean.getEmail() != null){
      //Condidat user = ps.getUser(userLoginBean.getEmail());
 	  //this.birthDayDay = user.getBirthDay().toString();
	}
		return "search-users";
	}

	/**
	 * Vérifier la validité de mots de passe
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void checkLogin(FacesContext context, UIComponent component, Object value)
    throws ValidatorException {
		if(isExistUser((String) value)){
			FacesMessage message = Messages.getMessage("fr.dauphine.business.messages", "userAlreadyExistError", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
		}
	}
	
	private boolean isExistUser(String login) {
		PersistenceService ps = PersistenceService.getInstance();
		if(login!= null  && !"".equals(login)){
	     User user = (User) ps.getUser(login);
			if(user != null){
				return true;
			}
		}
		return false;
	}
	
	
	
}
