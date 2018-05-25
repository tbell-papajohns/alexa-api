package com.pji.alexa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.pji.alexa.model.v2.AddCartItem;
import com.pji.alexa.model.v2.CartPromoForm;
import com.pji.alexa.model.v2.CartResponse;
import com.pji.alexa.model.v2.CartStateForm;
import com.pji.alexa.model.v2.CustomerFavoriteForm;
import com.pji.alexa.model.v2.CustomerFavourites;
import com.pji.alexa.model.v2.PastOrderForm;
import com.pji.alexa.model.v2.PastOrders;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;
 
@Service
public class CartService extends BaseService {

	public CartService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
	}
	
	/**
	 * This method returns the Cart response
	 * @param addCartItem
	 * @param token
	 * @return
	 */
	public CartResponse addItemToCart(AddCartItem addCartItem, String token) throws Exception{
		this.setTokenValue(token);
		this.setUrl(createURL());
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_POST)));
		String addCartItemRequest = getTransformer().objectToString(addCartItem);
		this.setPayload(addCartItemRequest);
		return (CartResponse) sendHttpRequest(CartResponse.class);
	}

	/**
	 * This method returns the grand total of the given cart item
	 * @param addCartItem
	 * @param token
	 * @return
	 */
	public Double getGrandTotal(AddCartItem addCartItem, String token) throws Exception{
		CartResponse cartResponse = addItemToCart(addCartItem,token);
		return cartResponse.getData().getPrice().getGrandTotal();
	}
	
	/**
	 * This method returns CartStateForm of the given favorite
	 * @param customerId
	 * @param storeId
	 * @param token
	 * @param favoriteId
	 * @return
	 */
	public CartStateForm getFavoriteCartStateForm(String customerId, String storeId,String token, String favoriteId) throws Exception{
		CartStateForm cartStateForm = new CartStateForm();
		CustomerFavourites customerFavourites = getFavouriteService().getCustomerFavorites(customerId,storeId,token);
		for (CustomerFavoriteForm customerFavoriteForm : customerFavourites.getData()) {
			if(customerFavoriteForm.getId().toString().equals(favoriteId)) {
				cartStateForm = customerFavoriteForm.getCartStateForm();
				cartStateForm.setStoreId(Integer.parseInt(storeId));				
				break;
			} 
		}
		return cartStateForm;
	}
	
	/**
	 * This method returns CartStateForm of the given past order
	 * @param customerId
	 * @param storeId
	 * @param token
	 * @param pastOrderNumber
	 * @return
	 */
	public CartStateForm getProductCartStateForm(String customerId, String storeId,String token, String pastOrderNumber) throws Exception{
		CartStateForm cartStateForm = new CartStateForm();
		PastOrders pastOrders = getPastOrderService().getPastOrders(customerId,storeId,token);
		for (PastOrderForm pastOrderForm : pastOrders.getData()) {
			if(pastOrderForm.getOrderNumber().toString().equals(pastOrderNumber)) {
				cartStateForm = pastOrderForm.getCartState();
				cartStateForm.setStoreId(Integer.parseInt(storeId));
				break;
			} 
		}
		return cartStateForm;
	}

	/**
	 * This method returns AddCartItem for a given favorite
	 * @param customerId
	 * @param storeId
	 * @param token
	 * @param favoriteId
	 * @param deliveryTerritoryId
	 * @return
	 */
	public AddCartItem getCartItem(String customerId, String storeId, String token, String favoriteId, String orderNumber, int deliveryTerritoryId) throws Exception{
		CartStateForm cartStateForm;
		if(orderNumber == null) {
			cartStateForm = getFavoriteCartStateForm(customerId, storeId, token, favoriteId);
		}else {
			cartStateForm = getProductCartStateForm(customerId, storeId, token, orderNumber);
		}
		cartStateForm.setStoreId(Integer.parseInt(storeId));
		cartStateForm.setOrderType(Constants.ALEXA_ORDER_TYPE);
		cartStateForm.setDeliveryTerritoryId(deliveryTerritoryId);
		
		CartPromoForm cartPromoForm = new CartPromoForm();
		cartPromoForm.setPromoCode(getUtil().getProperty(Constants.ALEXA_DISCOUNT_PROMOCODE));
		List<CartPromoForm> discountPromos = new ArrayList<>();
		discountPromos.add(cartPromoForm);
		cartStateForm.setDiscountPromos(discountPromos);
				
		AddCartItem addCartItem = new AddCartItem();
		addCartItem.setState(cartStateForm);
		return addCartItem;
	}

	private String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_ADD_CART_ITEMS_URL);
	}
}
