package fr.dauphine.business;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.dauphine.business.vo.MessageVO;


@ManagedBean(name="messagesListBean")
@SessionScoped
public class MessagesListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String recipientId;
	
	private int selectedIndex = -1;

	@ManagedProperty(value="#{userLoginBean}")
    private UserLoginBean userLoginBean;
	
	
	private List<MessageVO> result;


	/**
	 * @return the keyword
	 */
	public String getRecipientId() {
		return recipientId;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setRecipientId(String keyword) {
		this.recipientId = keyword;
	}


	/**
	 * @return the result
	 */
	public List<MessageVO> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<MessageVO> result) {
		this.result = result;
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
	 * @return the selectedIndex
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	/**
	 * @param selectedIndex the selectedIndex to set
	 */
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public MessageVO getCurrentSelectedMessage() {
		return result.get(selectedIndex);
	}
	
	public String reply(){
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		String parm = getSelectedMsgParamater(currentInstance);
		if(parm != null){
			selectedIndex = new Integer(parm);
		}
		return "nouveauMessage";
	}
	
	private String getSelectedMsgParamater(FacesContext context) {
		Map<String, String> params = context.getExternalContext().
		getRequestParameterMap();
		return params.get("selectedMsg");
	}
}
