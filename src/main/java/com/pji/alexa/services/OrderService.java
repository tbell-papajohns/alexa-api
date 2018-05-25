package com.pji.alexa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.pji.alexa.model.v2.AddCartItem;
import com.pji.alexa.model.v2.AlexaOrder;
import com.pji.alexa.model.v2.CartResponse;
import com.pji.alexa.model.v2.CartStateForm;
import com.pji.alexa.model.v2.CreditCardPaymentForm;
import com.pji.alexa.model.v2.CustomerForm;
import com.pji.alexa.model.v2.CustomerLocationForm;
import com.pji.alexa.model.v2.OrderResponse;
import com.pji.alexa.model.v2.PaymentCategoryForm;
import com.pji.alexa.model.v2.PaymentSummaryForm;
import com.pji.alexa.model.v2.SubmitOrderForm;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.PaymentCategoryCode;
 
@Service
public class OrderService extends BaseService{
	
	public OrderService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
	}
	
	/**
	 * This method returns the Order response
	 * @param submitOrderForm
	 * @param clientApp
	 * @param token
	 * @return
	 */
	
	public Map<String,String> createAndSubmitOrder(AlexaOrder order, String customerId, String customerToken) throws Exception{
		
		String storeId = order.getStoreId();
		String favoriteId = order.getFavoriteId();
		String pastOrderNumber = order.getPastOrderNumber();
		String cardId = order.getCardId();
		String addressId = order.getAddressId();
		int deliveryTerritoryId = order.getDeliveryTerritoryId();
		AddCartItem addCartItem = getCartService().getCartItem(customerId, storeId, customerToken, favoriteId, pastOrderNumber, deliveryTerritoryId);
		Double grandTotal = getCartService().getGrandTotal(addCartItem, customerToken);
		CartResponse cartResponse =  getCartService().addItemToCart(addCartItem, customerToken);
		CartStateForm cartStateForm = cartResponse.getData().getState();
		
		CustomerForm customerForm = getCustomerInfoService().getCustomerDetails(customerId, customerToken).getData();
		for(CustomerLocationForm customerLocationForm : customerForm.getCustomerLocations()) {
			if(customerLocationForm.getLocationId().toString().equals(addressId)) {
				customerLocationForm.setCurrentFlag(true);
			} else {
				customerLocationForm.setCurrentFlag(false);
			}
		}
		PaymentSummaryForm paymentSummaryForm = new PaymentSummaryForm();
		PaymentCategoryForm paymentCategoryForm = new PaymentCategoryForm();
		if(null	!=	cardId && null	!=	order.getPaymentType()) {
			if(order.getPaymentType().equalsIgnoreCase(Constants.ALEXA_PAYMENT_TYPE_CARD)) {
				List<CreditCardPaymentForm> customerCreditCard = new ArrayList<>();
				CreditCardPaymentForm creditCardPaymentForm = getCardDetailsService().getCustomerCreditCard(customerId, customerToken, cardId);
				creditCardPaymentForm.setAmount(grandTotal);
				customerCreditCard.add(creditCardPaymentForm);
				
				paymentSummaryForm.setCreditCardPayment(customerCreditCard);
				
				paymentCategoryForm.setCategoryId(PaymentCategoryCode.CREDIT_CARD.getCategoryId());
				paymentCategoryForm.setCategoryName(PaymentCategoryCode.CREDIT_CARD.getCategoryName());
			}
		}else if(null	!=	order.getPaymentType()) {
			if(order.getPaymentType().equalsIgnoreCase(Constants.ALEXA_PAYMENT_TYPE_CASH)) {
				paymentSummaryForm.setCashPaymentAmount(grandTotal);
				paymentCategoryForm.setCategoryId(PaymentCategoryCode.CASH.getCategoryId());
				paymentCategoryForm.setCategoryName(PaymentCategoryCode.CASH.getCategoryName());
			}
		}
		
		List<PaymentCategoryForm> paymentCategory= new ArrayList<>();
		paymentCategory.add(paymentCategoryForm);
						
		SubmitOrderForm submitOrderForm = new SubmitOrderForm();
		submitOrderForm.setCartState(cartStateForm);
		submitOrderForm.setCustomer(customerForm);
		submitOrderForm.setIpAddress(this.getUtil().getIpAddress());
		submitOrderForm.setPaymentSummary(paymentSummaryForm);
		submitOrderForm.setSelectedPaymentCategories(paymentCategory);
		OrderResponse orderResponse = this.submitOrder(submitOrderForm, this.getUtil().getProperty(Constants.PJI_ENDPOINT_HEADER_VALUE_CLIENT_APP), customerToken);
		Map<String, String> orderResponseMap = new HashMap<String, String>();
		String orderNumber = orderResponse.getData().getOrderNumber().toString();
		String subId = orderResponse.getData().getSubscriptionId().toString();
		orderResponseMap.put(Constants.ORDER_NUMBER, orderNumber);
		orderResponseMap.put(Constants.SUBSCRIPTION_ID, subId);
		return orderResponseMap;	
	}
	public OrderResponse submitOrder(SubmitOrderForm submitOrderForm, String clientApp, String token) throws Exception{
		this.setTokenValue(token);
		this.setUrl(createURL());
		this.getHeaders().put(this.getUtil().getProperty(Constants.PJI_ENDPOINT_HEADER_NAME_CLIENT_APP), clientApp);
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_POST)));
		String orderRequest = this.getTransformer().objectToString(submitOrderForm) ;
		this.setPayload(orderRequest);
		OrderResponse orderResponse = (OrderResponse)sendHttpRequest(OrderResponse.class);
		return orderResponse;
	}
	
	private String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_SUBMIT_ORDER_DETAILS);
	}
}
