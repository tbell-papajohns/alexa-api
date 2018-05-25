package com.pji.alexa.model.v2;

/**

PapaJohns
- birthDayOfMonth: required
- birthMonth: required
- cellphone: optional
- customerCommunicationPreferences: required
- customerId: optional
- customerLocations: required
- customerPoints: optional
- eligibleSignUpBonus: optional
- email: required
- firstname: required
- lastLocationId: optional
- lastStoreId: optional
- lastname: required
- over13: required
- phone: required
- rewardsFlag: required
- rewardsOptInDate: optional
- rewardsOptOutDate: optional
- segment: optional
*/

import java.io.Serializable;
import java.util.List;

public class CustomerForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer birthDayOfMonth;

    private Integer birthMonth;

    private String cellphone;

    private CustomerCommunicationPreferencesForm customerCommunicationPreferences;

    private Long customerId;

    private List<CustomerLocationForm> customerLocations;

    private CustomerPointsForm customerPoints;

    private Long eligibleSignUpBonus;

    private String email;

    private String firstname;

    private Long lastLocationId;

    private Integer lastStoreId;

    private String lastname;

    private Boolean over13;

    private String phone;

    private Boolean rewardsFlag;

    private String rewardsOptInDate;

    private String rewardsOptOutDate;

    private String segment;

	public Integer getBirthDayOfMonth() {
		return birthDayOfMonth;
	}

	public void setBirthDayOfMonth(Integer birthDayOfMonth) {
		this.birthDayOfMonth = birthDayOfMonth;
	}

	public Integer getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public CustomerCommunicationPreferencesForm getCustomerCommunicationPreferences() {
		return customerCommunicationPreferences;
	}

	public void setCustomerCommunicationPreferences(CustomerCommunicationPreferencesForm customerCommunicationPreferences) {
		this.customerCommunicationPreferences = customerCommunicationPreferences;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<CustomerLocationForm> getCustomerLocations() {
		return customerLocations;
	}

	public void setCustomerLocations(List<CustomerLocationForm> customerLocations) {
		this.customerLocations = customerLocations;
	}

	public CustomerPointsForm getCustomerPoints() {
		return customerPoints;
	}

	public void setCustomerPoints(CustomerPointsForm customerPoints) {
		this.customerPoints = customerPoints;
	}

	public Long getEligibleSignUpBonus() {
		return eligibleSignUpBonus;
	}

	public void setEligibleSignUpBonus(Long eligibleSignUpBonus) {
		this.eligibleSignUpBonus = eligibleSignUpBonus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Long getLastLocationId() {
		return lastLocationId;
	}

	public void setLastLocationId(Long lastLocationId) {
		this.lastLocationId = lastLocationId;
	}

	public Integer getLastStoreId() {
		return lastStoreId;
	}

	public void setLastStoreId(Integer lastStoreId) {
		this.lastStoreId = lastStoreId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Boolean getOver13() {
		return over13;
	}

	public void setOver13(Boolean over13) {
		this.over13 = over13;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getRewardsFlag() {
		return rewardsFlag;
	}

	public void setRewardsFlag(Boolean rewardsFlag) {
		this.rewardsFlag = rewardsFlag;
	}

	public String getRewardsOptInDate() {
		return rewardsOptInDate;
	}

	public void setRewardsOptInDate(String rewardsOptInDate) {
		this.rewardsOptInDate = rewardsOptInDate;
	}

	public String getRewardsOptOutDate() {
		return rewardsOptOutDate;
	}

	public void setRewardsOptOutDate(String rewardsOptOutDate) {
		this.rewardsOptOutDate = rewardsOptOutDate;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

}
