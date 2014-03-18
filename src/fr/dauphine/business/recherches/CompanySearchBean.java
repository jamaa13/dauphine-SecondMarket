package fr.dauphine.business.recherches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.dauphine.business.dao.PersistenceService;
import fr.dauphine.business.entity.Company;
import fr.dauphine.business.vo.CompanyVO;


@ManagedBean(name="companySearchBean")
@SessionScoped
public class CompanySearchBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String companyName;
	
	private String companyIndustry;

	private String companyWebSite;
	
	private List<CompanyVO> result;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getCompanyWebSite() {
		return companyWebSite;
	}

	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}

	public List<CompanyVO> getResult() {
		if (null == result)
		{
			result = new ArrayList<CompanyVO>();
		}
		return result;
	}

	public void setResult(List<CompanyVO> result) {
		this.result = result;
	}

	/**Méthodes de navigation*/
	
	public String search(){
		PersistenceService dao = PersistenceService.getInstance();
		
		setResult(null);

		List<Company> companies = dao.searcheCompanyByCriteria(companyName, companyIndustry, companyWebSite);
		
		if (null != companies && companies.size() > 0)
		{
		for (Company company : companies)
		{
			CompanyVO cpy = new CompanyVO();
			cpy.setCompanyName(company.getCompanyName());
			cpy.setCompanyIndustry(company.getCompanyIndustry());
			cpy.setCompanyWebSite(company.getWebsite());
			cpy.setCompanyDescription(company.getCompanyDescription());
			getResult().add(cpy);
		}
		}
		else
		{
			setResult(null);
		}
		
		return "search-companies";
	}

	
	
}
