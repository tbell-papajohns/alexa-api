package com.pji.alexa.model.v2;

/**

PapaJohns
- cartResponse: optional
- lastOrderInformation: optional
- orderCustomerInformation: optional
- orderNumber: optional
- orderReadyEstimate: optional
- paymentInformationSummary: optional
- registrationAuthenticatedCustomerForm: optional
- selectedPaymentCategories: optional
- submitOrderResponseCode: optional, Allowable Values: ["OK_INFO", "REDUCED_TIP_INFO", "INVALID_FIELD_LENGTH_ERROR", "INVALID_ORDER_TYPE_ERROR", "INVALID_STORE_ERROR", "INVALID_STORE_STATUS_ERROR", "INVALID_ORDER_PAYMENT_ID_ERROR", "MISSING_REQUEST_ERROR", "MISSING_CUSTOMER_ERROR", "MISSING_IPADDRESS_ERROR", "MISSING_SHOPCART_ERROR", "MISSING_ORDER_TICKET_ERROR", "MISSING_PAYMENT_CATEGORY_ERROR", "MISSING_PAYMENT_INFO_ERROR", "MISSING_CARRIER_CODE_WHEN_MOBILE_PHONE_IS_PRESENT_ERROR", "BADLY_FORMED_EMAIL_ERROR", "INVALID_MOBILE_PHONE_NUMBER_ERROR", "INVALID_PHONE_NUMBER_ERROR", "SHOPCART_ERROR", "MONETRA_ERROR", "GIFTCARD_FAILED_AUTH_ERROR", "MAX_GIFTCARDS_EXCEEDED_ERROR", "MAX_PAYMENTS_EXCEEDED_ERROR", "INSUFFICIENT_FUNDS_ERROR", "ECHECK_FAILED_VERIFICATION_ERROR", "ECHECK_MAX_ATTEMPTS_ERROR", "CREDITCARD_FAILED_AUTH_ERROR", "INVALID_CARD_INFO_ERROR", "STORED_CARD_ID_ERROR", "PAYMENT_TOTAL_ERROR", "PAYMENT_TYPE_NOT_ACCEPTED_ERROR", "ORDER_SAVE_FAILED_ERROR", "INTERNAL_ERROR", "CANNOT_STORE_CARD_WARNING", "ANONYMOUS_STORED_CARD_WARNING", "MAX_ORDER_AMT_EXCEEDED_ERROR", "MIN_ORDER_AMOUNT_REQUIRED_ERROR", "STORE_NOT_OPEN_ERROR", "MISSING_ADDRESS_INFO_ERROR", "INVALID_BILLING_INFO_ERROR", "DUPLICATE_ORDER_ERROR", "GUEST_ORDER_NOT_ALLOWED", "GUEST_ORDER_ONLY_FOR_CCGC", "PAYPAL_INTEGRATION_ERROR", "PAYPAL_AUTHENTICATION_ERROR", "PAYPAL_TOKEN_ERROR", "MAX_PAYPAL_AMOUNT_ERROR", "PAYPAL_MAX_ATTEMPTS_ERROR", "ALTERNATIVE_PAYMENT_NEEDED_ERROR", "PAYPAL_TEMP_UNAVAILABLE_ERROR", "PAYPAL_REDIRECT_ERROR", "PAYPAL_SECURITY_ERROR", "BILLING_AGREEMENT_ERROR", "CANNOT_USE_PAYPAL_ERROR", "TRANSACTION_IN_PROGRESS_ERROR", "PAYPAL_USER_AGREEMENT_ERROR", "INVALID_GIFTCARD_PLAN_AHEAD_ORDER", "PAYPAL_FAILED_AUTH_ERROR", "ORDER_TYPE_DELIVERY_LOCATION_MISMATCH_ERROR", "VISA_CHECKOUT_PAYMENT_TYPE_NOT_ACCEPTED_ERROR", "APPLEPAY_PAYMENTTYPE_NOT_ACCEPTED_ERROR"]
- subscriptionId: optional
- updatedCustomer: optional
*/

import java.io.Serializable;
import java.util.List;

public class OrderResponseForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartResponseForm cartResponse;

    private LastOrderInformation lastOrderInformation;

    private OrderCustomerInformationForm orderCustomerInformation;

    private Long orderNumber;

    private OrderReadyEstimate orderReadyEstimate;

    private PaymentInformationSummary paymentInformationSummary;

    private AuthenticatedCustomerForm registrationAuthenticatedCustomerForm;

    private List<PaymentCategoryForm> selectedPaymentCategories;

    private String submitOrderResponseCode;

    private String subscriptionId;

    private CustomerForm updatedCustomer;

	public CartResponseForm getCartResponse() {
		return cartResponse;
	}

	public void setCartResponse(CartResponseForm cartResponse) {
		this.cartResponse = cartResponse;
	}

	public LastOrderInformation getLastOrderInformation() {
		return lastOrderInformation;
	}

	public void setLastOrderInformation(LastOrderInformation lastOrderInformation) {
		this.lastOrderInformation = lastOrderInformation;
	}

	public OrderCustomerInformationForm getOrderCustomerInformation() {
		return orderCustomerInformation;
	}

	public void setOrderCustomerInformation(OrderCustomerInformationForm orderCustomerInformation) {
		this.orderCustomerInformation = orderCustomerInformation;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public OrderReadyEstimate getOrderReadyEstimate() {
		return orderReadyEstimate;
	}

	public void setOrderReadyEstimate(OrderReadyEstimate orderReadyEstimate) {
		this.orderReadyEstimate = orderReadyEstimate;
	}

	public PaymentInformationSummary getPaymentInformationSummary() {
		return paymentInformationSummary;
	}

	public void setPaymentInformationSummary(PaymentInformationSummary paymentInformationSummary) {
		this.paymentInformationSummary = paymentInformationSummary;
	}

	public AuthenticatedCustomerForm getRegistrationAuthenticatedCustomerForm() {
		return registrationAuthenticatedCustomerForm;
	}

	public void setRegistrationAuthenticatedCustomerForm(AuthenticatedCustomerForm registrationAuthenticatedCustomerForm) {
		this.registrationAuthenticatedCustomerForm = registrationAuthenticatedCustomerForm;
	}

	public List<PaymentCategoryForm> getSelectedPaymentCategories() {
		return selectedPaymentCategories;
	}

	public void setSelectedPaymentCategories(List<PaymentCategoryForm> selectedPaymentCategories) {
		this.selectedPaymentCategories = selectedPaymentCategories;
	}

	public String getSubmitOrderResponseCode() {
		return submitOrderResponseCode;
	}

	public void setSubmitOrderResponseCode(String submitOrderResponseCode) {
		this.submitOrderResponseCode = submitOrderResponseCode;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public CustomerForm getUpdatedCustomer() {
		return updatedCustomer;
	}

	public void setUpdatedCustomer(CustomerForm updatedCustomer) {
		this.updatedCustomer = updatedCustomer;
	}

}
