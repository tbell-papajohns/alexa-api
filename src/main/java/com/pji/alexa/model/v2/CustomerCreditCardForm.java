package com.pji.alexa.model.v2;

/**

PapaJohns
- cardId: optional
- cardNumber: optional
- expirationMonth: optional
- expirationYear: optional
- expired: optional
- image: optional
- nameOnCard: optional
- paymentTypeId: optional
- paymentTypeName: optional
*/

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown= true)
public class CustomerCreditCardForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer cardId;

	private String cardNumber;

	private String expirationMonth;

	private String expirationYear;

	private Boolean expired;

	private String image;

	private String nameOnCard;

	private Integer paymentTypeId;

	private String paymentTypeName;

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
