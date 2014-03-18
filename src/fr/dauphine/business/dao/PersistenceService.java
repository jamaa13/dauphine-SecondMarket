package fr.dauphine.business.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.dauphine.business.CompanySignUPBean;
import fr.dauphine.business.MyAccountBean;
import fr.dauphine.business.entity.Action;
import fr.dauphine.business.entity.Company;
import fr.dauphine.business.entity.Contrat;
import fr.dauphine.business.entity.Enchere;
import fr.dauphine.business.entity.Profile;
import fr.dauphine.business.entity.Proposition;
import fr.dauphine.business.entity.StockOption;
import fr.dauphine.business.entity.Titre;
import fr.dauphine.business.entity.User;
import fr.dauphine.business.entity.Action;
import fr.dauphine.business.enums.ProfileNameEnum;
import fr.dauphine.business.offre.MakeOfferBean;


public class PersistenceService {
	
	private static PersistenceService instance = new PersistenceService();
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private PersistenceService(){
		emf = Persistence.createEntityManagerFactory("WebProjectMarket");
		try{
		em = emf.createEntityManager();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PersistenceService getInstance(){
		return instance;
	}
	
	/********************************************** Profile **********************************************/
	private Profile getProfileByName(String profileName)
	{
		final Query query = em.createNamedQuery("findProfileByName");
		query.setParameter("profileName", profileName);
		
		Profile profile = (Profile) query.getSingleResult();
		return profile;
	}
	
	/********************************************** Users **********************************************/
	public void saveCondidat(MyAccountBean account){
		User user = new User();
		
		user.setEmail(account.getEmail());
		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName());
		user.setPassword(account.getPassword());
		user.setPhoneNbr(account.getPhoneNbr());
		user.setStatus("CREATED");
		Profile profileByName = getProfileByName(ProfileNameEnum.REGISTERED.name());
		user.setProfile(profileByName);
		
		try{
			em.getTransaction().begin();		
			em.persist(user);
			em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public List<User> searcheUserByCriteria(String email, String status, String profile)
	{
		/*
		 * et la requête :
		 */
		
		
		/** Contient la requête SQL qui sera exécutée. */
		StringBuilder sql = new StringBuilder();
		
		sql.append(" ").append("select").append(" ");
		sql.append("distinct u");
		sql.append(" ").append("from").append(" ");
		sql.append("User").append(" ").append("u").append(" ");
		
		if (null != profile && profile !="")
		{
			sql.append(" inner join ");
			sql.append("u.profile").append(" ").append("prf").append(" ");
		}
		
		sql.append("where").append(" ");
		sql.append("1=1").append(" ");
		
		if (null == email && email !="")
		{
			sql.append("and u.email=").append("'");
			sql.append(email).append("'").append(" ");
		}
		if (null != status && status !="")
		{
			sql.append("and u.status=").append("'");
			sql.append(status).append("'").append(" ");
		}

		if (null != profile && profile !="")
		{
			sql.append("and prf.name=").append("'");
			sql.append(profile).append("'").append(" ");
		}
		
		final Query query = em.createQuery(sql.toString());
		List<User> users = (List<User>) query.getResultList();
		
		return users;
	}
	

	public User getUser(String login){
		User user = new User();
		Query userQuery = em.createNativeQuery("select * from users where email = ?", User.class);
		userQuery.setParameter(1, login);
		try {
			user = (User) userQuery.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}finally{
		}
		return user;
	}
	
	public boolean validateUsers(List<String> emails)
	{
		for (String email : emails)
		{
			User user = getUser(email);
			user.setStatus("VALIDATED");
			
			/** Modifier le profile de l'utilisateur: 
			 * 1. SUBSCRIBER (si l'utilisateur n'esp pas lié a une societé).
			 * 2. SUBSCRIBER (si l'utilisateur n'esp pas lié a une societé).**/
			Profile profile = null;
			if (null != user.getCompany())
			{
				profile = getProfileByName(ProfileNameEnum.COMPANY_SUBSCRIBER.name());
			}
			else
			{
				profile = getProfileByName(ProfileNameEnum.SUBSCRIBER.name());
			}
			
			
			user.setProfile(profile);
			
			try
			{
				em.getTransaction().begin();
				em.merge(user);
				em.getTransaction().commit();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Boolean.TRUE;
	}
	

	/********************************************** Companies **********************************************/
public void saveCompany(CompanySignUPBean cpy){
		
		User user = getUser(cpy.getCompanyUser());
		
		Company company = new Company();
		company.setCompanyDescription(cpy.getCompanyDescription());
		company.setCompanyName(cpy.getCompanyName());
		company.setCompanyIndustry(cpy.getCompanyIndustry());
//		company.setEmployerNumber(Integer.valueOf(user.getEmployerNumber()));
		company.setWebsite(cpy.getCompanyWebsite());
		user.setCompany(company);
		
		try
		{
			em.getTransaction().begin();
			em.persist(company);
			em.merge(user);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Company> searcheCompanyByCriteria(String companyName, String companyIndustry, String companyWebSite)
	{
		/*
		 * et la requ�te :
		 */
		//final JPQLStringBuilder sql = new JPQLStringBuilder().select();
		
		/** Contient la requ�te SQL qui sera ex�cut�e. */
		StringBuilder sql = new StringBuilder();
		
		sql.append(" ").append("select").append(" ");
		sql.append("distinct cpy");
		sql.append(" ").append("from").append(" ");
		sql.append("Company").append(" ").append("cpy").append(" ");
		
		
		sql.append("where").append(" ");
		sql.append("1=1").append(" ");
		
		if (null != companyName && companyName !="")
		{
			sql.append("and cpy.companyName=").append("'");
			sql.append(companyName).append("'").append(" ");
		}
		if (null != companyIndustry && companyIndustry !="")
		{
			sql.append("and cpy.companyIndustry=").append("'");
			sql.append(companyIndustry).append("'").append(" ");
		}

		if (null != companyWebSite && companyWebSite !="")
		{
			sql.append("and cpy.website=").append("'");
			sql.append(companyWebSite).append("'").append(" ");
		}

		final Query query = em.createQuery(sql.toString());
		List<Company> Companies = (List<Company>) query.getResultList();
		
		return Companies;
	}
	
	/* **************************************************************************/ 
	
public void saveContrat(String email, MakeOfferBean offer){
		
		Calendar cal = Calendar.getInstance();
		
		try
		{
			
			em.getTransaction().begin();
			
			User user = getUser(email);
			
			Contrat contrat = new Contrat();
			contrat.setLibelle(offer.getLabel());
			contrat.setCommantaire(offer.getComment());
			contrat.setCreationDate(cal.getTime());
			contrat.setStatus("ACTIVE");
			contrat.setUser(user);
			
			em.persist(contrat);
			em.merge(user);
			/** 1 = Stock option
			 *  2 = Action
			 *  3 = Titre
			**/
			if (offer.getContractType().equals("1"))
			{
				StockOption stockOption = new StockOption();
				stockOption.setPrixInitial(offer.getInitPrice());
				stockOption.setPrixMaturite(offer.getPrixMaturite());
				stockOption.setPrixOption(offer.getPrixOption());
				stockOption.setDateMaturite(offer.getDateMaturite());
				stockOption.setCreationDate(cal.getTime());
				stockOption.setContrat(contrat);
			}			
			else if (offer.getContractType().equals("2"))
			{
				Action action = new Action();
				action.setPrix(Integer.valueOf(offer.getActionPrice()));
				action.setCreationDate(cal.getTime());
				action.setContrat(contrat);
				em.persist(action);
			}else if (offer.getContractType().equals("3"))
			{
				Titre titre = new Titre();
				titre.setPrixActuel(Integer.valueOf(offer.getInitPrice()));
				titre.setPrixDepart(Integer.valueOf(offer.getInitPrice()));
				titre.setCreationDate(cal.getTime());
				titre.setContrat(contrat);
				em.persist(titre);
			} 
			
			em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getLastOffers(int max){
		List<Contrat> cont = new ArrayList<Contrat>();
		List<String> lastOffersList = new ArrayList<String>();
		Query query = em.createNamedQuery("findLastOffers");
		query.setFirstResult(0);
		query.setMaxResults(max);
		try {
			cont = query.getResultList();
			for (Contrat ctt : cont) {
				lastOffersList.add(ctt.getLibelle());
			}
		} catch (NoResultException e) {
			cont = null;
		}
		return lastOffersList;
	}
	
	public Contrat getOfferById(String id){
		Contrat cont = new Contrat();
		Query query = em.createNamedQuery("findOfferById");
		query.setParameter("id", Integer.valueOf(id));
		
		try {
			cont = (Contrat) query.getSingleResult();			
		} catch (NoResultException e) {
			cont = null;
		}
		return cont;
	}
	
	public List<Contrat> getOffersByUserId(String email){
		List<Contrat> cont = new ArrayList<Contrat>();
		Query query = em.createNamedQuery("findOffersByUserId");
		query.setParameter("email", email);
		
		try {
			cont = query.getResultList();			
		} catch (NoResultException e) {
			cont = null;
		}
		return cont;
	}
	
	public List<Contrat> getOffersCompanniesByStatus(String status){
		List<Contrat> cont = new ArrayList<Contrat>();
		Query query = em.createNamedQuery("findCompanniesOffersByStatus");
		query.setParameter("status", status);
		
		try {
			cont = query.getResultList();			
		} catch (NoResultException e) {
			cont = null;
		}
		return cont;
	}
	
	public List<Contrat> getOffersInvestiseursByStatus(String status){
		List<Contrat> cont = new ArrayList<Contrat>();
		Query query = em.createNamedQuery("findInvestiseursOffersByStatus");
		query.setParameter("status", status);
		
		try {
			cont = query.getResultList();			
		} catch (NoResultException e) {
			cont = null;
		}
		return cont;
	}
	
	
	public String getTypeContrat(int id)
	{
		String type = null;
		Query query = em.createNamedQuery("findTitreByContratId");
		query.setParameter("id", id);
		
		try {
			Titre titre = (Titre) query.getSingleResult();
			if (null != titre)
			{
				type = "Titre";
			}
			else
			{
				type = "ActionOrStockOption";
			}
		} catch (NoResultException e) {
			type = null;
		}
		return type;
	}

	public int getPrixActuelByContratId(int id)
	{
		Query query = em.createNamedQuery("findTitreByContratId");
		query.setParameter("id", id);
		Titre titre = null;
		try {
			titre = (Titre) query.getSingleResult();
		} catch (NoResultException e) {
		}
		return titre.getPrixActuel();
	}

	/********************************************** Proposition **********************************************/
	
	public void saveProposition(String email, String description, String offerId, int prix)
	{
		Proposition prop = new Proposition();
		
		try
		{
			em.getTransaction().begin();
			
			User user = getUser(email);
			prop.setUser(user);
			
			Contrat contrat = getOfferById(offerId);
			
			prop.setContrat(contrat);
			
			prop.setDescription(description);
			prop.setPrix(prix);
			
			prop.setStatus("In Progress");
			
			em.persist(prop);
			
			em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Proposition> getSentProposition(String email, String status){
		List<Proposition> ppt = new ArrayList<Proposition>();
		Query query = em.createNamedQuery("findSendPropositions");
		query.setParameter("email", email);
//		query.setParameter("status", status);
		
		try {
			ppt = query.getResultList();			
		} catch (NoResultException e) {
			ppt = null;
		}
		return ppt;
	}
	
	
	public List<Proposition> getReceivedProposition(String email, String status){
		List<Proposition> ppt = new ArrayList<Proposition>();
		Query query = em.createNamedQuery("findReceivedPropositions");
		query.setParameter("email", email);
//		query.setParameter("status", status);
		
		try {
			ppt = query.getResultList();			
		} catch (NoResultException e) {
			ppt = null;
		}
		return ppt;
	}
	
	public boolean validateProposition(String propId)
	{
		try
		{
		em.getTransaction().begin();
		

		Proposition ppt = em.find(Proposition.class, Integer.valueOf(propId));
		ppt.setStatus("VALIDATED");
		em.merge(ppt);
		em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.TRUE;
	}
	
	
	
	public Titre getTitreByContrat(int offerId)
	{
		Titre titre = null;
		Query query = em.createNamedQuery("findTitreByContratId");
		query.setParameter("id", offerId);
		
		try {
			 titre = (Titre) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return titre;
	}
	
	/********************************************** Enchere **********************************************/
	
	public void saveEnchere(String email, String description, String offerId, int prix)
	{
		Enchere enchere= new Enchere();
		
		try
		{
			Calendar cal = Calendar.getInstance();
			
			em.getTransaction().begin();
			
			Titre titre = getTitreByContrat(Integer.valueOf(offerId));
			enchere.setTitre(titre);
			titre.setPrixActuel(prix);
			
			User user = getUser(email);
			enchere.setUser(user);
			
			enchere.setPrix(prix);
			enchere.setStatus("In Progress");
			enchere.setCreationDate(cal.getTime());
			

			em.persist(enchere);
			
			em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Enchere> getSentEnchere(String email, String status){
		List<Enchere> ppt = new ArrayList<Enchere>();
		Query query = em.createNamedQuery("findSendEncheres");
		query.setParameter("email", email);
//		query.setParameter("status", status);
		
		try {
			ppt = query.getResultList();			
		} catch (NoResultException e) {
			ppt = null;
		}
		return ppt;
	}
	
	
	public List<Enchere> getReceivedEnchere(String email, String status){
		List<Enchere> ppt = new ArrayList<Enchere>();
		Query query = em.createNamedQuery("findReceivedEncheres");
		query.setParameter("email", email);
//		query.setParameter("status", status);
		
		try {
			ppt = query.getResultList();			
		} catch (NoResultException e) {
			ppt = null;
		}
		return ppt;
	}
	
}
