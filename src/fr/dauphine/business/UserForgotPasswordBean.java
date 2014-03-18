package fr.dauphine.business;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="userForgotPasswordBean")
@SessionScoped
public class UserForgotPasswordBean {
	
	/**email.*/
	private String email;
	/**password.*/
	private String password;
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
	
	/*******************************************************************/
	/*************************navigation methods*****************************/
	/*******************************************************************/

	public String forgotPassword(){
		return "login";
	}

}
