package fr.dauphine.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "profiles", uniqueConstraints = @UniqueConstraint(columnNames = { "NAME" }))
@NamedQueries(value = { 
@NamedQuery(
		name = "findProfileByName", 
		query = "SELECT prf FROM Profile prf WHERE prf.name = :profileName"
						
			) 
})
public class Profile implements Serializable{

	/** Serial Id par défaut. */
	private static final long serialVersionUID = 1L;

	/** Clé primaire. */
	private int id;


	/** Attribut "name". */
	private String name;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}
	
	/**
	 * Définit la clé primaire.
	 *
	 * @param id Valeur de la clé.
	 */
	public void setId(final int id)
	{
		this.id = id;
	}

	/**
	 * @return Nom du profil.
	 */
	@Column(length = 255, nullable = false)
	public String getName()
	{
		return name;
	}


	/**
	 * Définit le nom du profil.
	 *
	 * @param name Nom du profil.
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

}
