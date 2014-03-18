package fr.dauphine.business.vo;

import java.io.Serializable;
import java.util.Date;

public class MessageVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date creationDate;
	private String subject;
	private String message;
	private String senderFirstName;
	private String senderLastName;
	private String recipientFirstName;
	private String recipientLastName;
	private String senderId;
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return 
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param
	 */
	public void setSubject(String title) {
		this.subject = title;
	}
	/**
	 * @return the description
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param 
	 */
	public void setMessage(String description) {
		this.message = description;
	}
	/**
	 * @return the name
	 */
	public String getSenderFirstName() {
		return senderFirstName;
	}
	/**
	 * @param name the name to set
	 */
	public void setSenderFirstName(String name) {
		this.senderFirstName = name;
	}
	/**
	 * @return the supplierId
	 */
	public String getSenderId() {
		return senderId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSenderId(String supplierId) {
		this.senderId = supplierId;
	}
	/**
	 * @return the senderLastName
	 */
	public String getSenderLastName() {
		return senderLastName;
	}
	/**
	 * @param senderLastName the senderLastName to set
	 */
	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
	}
	/**
	 * @return the recipientFristName
	 */
	public String getRecipientFirstName() {
		return recipientFirstName;
	}
	/**
	 * @param recipientFristName the recipientFristName to set
	 */
	public void setRecipientFirstName(String recipientFristName) {
		this.recipientFirstName = recipientFristName;
	}
	/**
	 * @return the recipientLastName
	 */
	public String getRecipientLastName() {
		return recipientLastName;
	}
	/**
	 * @param recipientLastName the recipientLastName to set
	 */
	public void setRecipientLastName(String recipientLastName) {
		this.recipientLastName = recipientLastName;
	}
	
}
