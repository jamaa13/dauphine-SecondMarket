package fr.dauphine.business;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.util.Messages;


@ManagedBean(name="companySignUpBean")
@SessionScoped
public class CompanySignUPBean {
	
	
	/**---------------Informations sur l'entreprise.-------------------------------*/
	/**companyName*/
	private String companyName;
	/**companyWebsite*/
	private String companyWebsite;
	/**employerNumber*/
	private String employerNumber;
	/**companyDescription*/
	private String companyDescription;
	/**companyIndustry.*/
	private String companyIndustry;
	
	/**login.*/
	private String companyUser;
	

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyWebsite
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	/**
	 * @param companyWebsite the companyWebsite to set
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	/**
	 * @return the employerNumber
	 */
	public String getEmployerNumber() {
		return employerNumber;
	}
	/**
	 * @param employerNumber the employerNumber to set
	 */
	public void setEmployerNumber(String employerNumber) {
		this.employerNumber = employerNumber;
	}
	/**
	 * @return the companyDescription
	 */
	public String getCompanyDescription() {
		return companyDescription;
	}
	
	public String getCompanyIndustry() {
		return companyIndustry;
	}
	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}
	
	

	public String getCompanyUser() {
		return companyUser;
	}
	public void setCompanyUser(String companyUser) {
		this.companyUser = companyUser;
	}
	/**
	 * @param companyDescription the companyDescription to set
	 */
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	/*******************************************************************/
	/*************************navigation methods*****************************/
	/*******************************************************************/

	public String signUP(){
		PersistenceService ps = PersistenceService.getInstance();
		String retunTo = "list-companies";
		try{
			ps.saveCompany(this);			
			Messages.addSuccessMessage("Entreprise est créée avec succès");
		}
		catch (Exception e) {
			return retunTo;
		}
		
		return retunTo;
	}
	

	public String createUser(){
		return "myAccountCompany";
	}
	
}
