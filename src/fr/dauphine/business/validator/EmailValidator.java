package fr.dauphine.business.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import fr.dauphine.business.util.Messages;


@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
 
	  private Pattern pattern;
	  private Matcher matcher;

	  private static final String EMAIL_PATTERN = 
                  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	  public EmailValidator(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	  }

	  /**
	   * Validate hex with regular expression
	   * @param hex hex for validation
	   * @return true valid hex, false invalid hex
	   */
	  public boolean validate(final String hex){

		  matcher = pattern.matcher(hex);
		  return matcher.matches();

	  }
	  
	  /**
	   * méthode appelée par le framework. 
	   */
	  public void validate(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
 
        String email = (String) value;

        if(!validate(email)) {
            FacesMessage message = Messages.getMessage("fr.dauphine.business.validator.defaultErrorsMessages", "invalidEmail", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}