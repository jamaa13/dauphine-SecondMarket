package fr.dauphine.business.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.dauphine.business.util.QueriesSettings.FindUsrByEmailAndPassword;



@Entity
@Table(name = "users")
@NamedQueries(value = { 
		@NamedQuery(
				name = FindUsrByEmailAndPassword.NAME, 
				query = "SELECT usr FROM User usr WHERE usr.email = :"
								+ FindUsrByEmailAndPassword.PARAM_EMAIL
								 + " AND usr.password = :" + FindUsrByEmailAndPassword.PARAM_PASSWORD
					) 
})
public class User implements Serializable {

	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;

	/** Clé primaire. */
	private int id;

	/** Profils / spécialités. */
	private Profile profile;

	/** Attribut "email". */
	private String email;

	/** Attribut "password". */
	private String password;

	/** Attribut "firstName". */
	private String firstName;

	/** Attribut "lastName". */
	private String lastName;

	/** Attribut "phoneNbr". */
	private String phoneNbr;

	/** Attribut "phoneNbr". */
	private String status;

	
	/** Attribut "company". */
	private Company company;
	

	/** Attribut "contrat". */
//private List<Contrat> contrats;

	/** Attribut "propositions". */
	//private List<Proposition> propositions;	 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * Définit la clé primaire.
	 * 
	 * @param id
	 *            Valeur de la clé.
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return e-mail.
	 */
	@Column(length = 255, nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	/**
	 * Définit l'e-mail.
	 * 
	 * @param email
	 *            e-mail.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return Mot de passe (chiffré).
	 */
	@Column(length = 48, nullable = true)
	public String getPassword() {
		return password;
	}

	/**
	 * Définit le mot de passe (chiffré).
	 * 
	 * @param password
	 *            Mot de passe chiffré.
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @return Prénom de l'individu.
	 */
	@Column(name = "first_name", length = 255, nullable = false)
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Définit le prénom de l'individu.
	 * 
	 * @param firstName
	 *            Prénom.
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Nom [de famille] de l'individu.
	 */
	@Column(name = "last_name", length = 255, nullable = false)
	public String getLastName() {
		return lastName;
	}

	/**
	 * Définit le nom [de famille] de l'individu.
	 * 
	 * @param lastName
	 *            Nom.
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Numéro de téléphone.
	 */
	@Column(name = "phone_nbr", length = 255)
	public String getPhoneNbr() {
		return phoneNbr;
	}

	/**
	 * Définit le numéro de téléphone.
	 * 
	 * @param phoneNbr
	 *            Numéro de téléphone.
	 */
	public void setPhoneNbr(final String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	/**
	 * @return Les profils
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prf_id", nullable = true)
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Définit le profil.
	 * 
	 * @param profile
	 *            .
	 */
	public void setProfile(final Profile profile) {
		this.profile = profile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = true)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/*
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public List<Contrat> getContrats() {
		if (null == contrats) {
			contrats = new LinkedList<Contrat>();
		}
		return contrats;
	}

	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}
*/
	@Column(name = "status", length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	/*
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public List<Proposition> getPropositions() {
		if (null == propositions) {
			propositions = new LinkedList<Proposition>();
		}
		return propositions;
	}

	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	} */
	
}
