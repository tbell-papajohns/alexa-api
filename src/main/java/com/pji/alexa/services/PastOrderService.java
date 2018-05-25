package com.pji.alexa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.pji.alexa.model.v2.CartDealForm;
import com.pji.alexa.model.v2.CartProductForm;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.PastOrderForm;
import com.pji.alexa.model.v2.PastOrders;
import com.pji.alexa.model.v2.ProductDescription;
import com.pji.alexa.util.Constants;

@Service
public class PastOrderService extends BaseService {

	public PastOrderService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
	}
	
	/**
	 * This method returns past orders
	 * @param customerId
	 * @param storeId
	 * @param token
	 * @return
	 */
	public PastOrders getPastOrders(String customerId, String storeId, String token) throws Exception{
		this.setTokenValue(token);
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_GET)));
		this.setUrl(createURL());
		return (PastOrders)sendHttpRequest(PastOrders.class,customerId,storeId);		
	}
	
	/**
	 * This method get the past order details
	 * @param customerId
	 * @param storeCode
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public List<Item> getPastOrderDetails(String customerId, String storeCode, String token) throws Exception{
		List<Item> pastOrderList= new ArrayList<>();
		PastOrders pastOrders= getPastOrders(customerId,storeCode,token);
		List<PastOrderForm> pastOrderFormList = pastOrders.getData();
		int ctr=0;
		for(PastOrderForm pastOrderForm: pastOrderFormList) {
			ctr++;
			List<ProductDescription> itemDescription= getPastProductDetails(pastOrderForm);
			Item item = new Item(ctr,pastOrderForm.getOrderNumber().toString(),null,itemDescription, null, false,false);
			pastOrderList.add(item);
			//Fetching only last order
			if(ctr==1)
				break;
		}
		return pastOrderList;
	}
	
	/**
	 * This method returns the product and counts of each product
	 * @param customerFavoriteForm
	 * @return
	 */
	private List<ProductDescription> getPastProductDetails(PastOrderForm pastOrderForm) {
		List<ProductDescription> productDetails = new ArrayList<>();
	    List<CartProductForm> products = pastOrderForm.getCartState().getProducts();
		List<CartDealForm> deals = pastOrderForm.getCartState().getDeals();
		updateProductDetails(productDetails,products);
		for(CartDealForm cartDealForm: deals) {
			products = cartDealForm.getProducts();
			updateProductDetails(productDetails,products);
		}
		return productDetails;
	}
	private String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_CUSTOMER_PAST_ORDERS_URL);
	}
}
