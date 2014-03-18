package fr.dauphine.business.util;

public final class QueriesSettings {

	
	/** get all Users. */
	public static class FindAllUsers
	{
		/** Nom de la requête. */
		public static final String NAME = "findAllUsers";
	}
	
	public static class FindUsrByEmailAndPassword
	{
		/** Nom de la requête. */
		public static final String NAME = "findUsrByEmailAndPassword";
		
		/** Nom du paramètre <i>user email</i>. */
		public static final String PARAM_EMAIL = "email";

		/** Nom du paramètre <i>user password</i>. */
		public static final String PARAM_PASSWORD = "password";
	}
	
}
