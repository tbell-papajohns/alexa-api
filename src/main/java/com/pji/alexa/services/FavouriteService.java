package com.pji.alexa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.pji.alexa.model.v2.CartDealForm;
import com.pji.alexa.model.v2.CartProductForm;
import com.pji.alexa.model.v2.CustomerFavoriteForm;
import com.pji.alexa.model.v2.CustomerFavourites;
import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.ProductDescription;
import com.pji.alexa.util.Constants;

@Service
public class FavouriteService extends BaseService {

	public FavouriteService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
	}

	/**
	 * This method returns details of all the favorites
	 * @param customerId
	 * @param storeId
	 * @param token
	 * @return
	 */
	public CustomerFavourites getCustomerFavorites(String customerId, String storeId,String token) throws Exception{
		this.setTokenValue(token);
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_GET)));
		this.setUrl(createURL());
		return (CustomerFavourites)sendHttpRequest(CustomerFavourites.class,customerId,storeId);	
	}
	/**
	 * This method returns List of Favorites
	 * in the form of Item object
	 * @param customerId
	 * @param storeId
	 * @param token
	 * @return
	 */
	public List<Item> getFavoriteItems(String customerId, String storeId, String token) throws Exception{
		CustomerFavourites customerFavourites = this.getCustomerFavorites(customerId,storeId,token);
		String favoritePositionOrdinal=	null;
		String favoriteName	= null;
		String favoriteId = null;

		List<Item> favoriteList = new ArrayList<>();
		for(int i=0; i < customerFavourites.getData().size(); i++){
				CustomerFavoriteForm customerFavoriteForm = customerFavourites.getData().get(i);
				favoriteName = customerFavoriteForm.getName();
				favoriteId=  customerFavoriteForm.getId().toString();
				favoritePositionOrdinal = this.getUtil().getOrdinal(i+1);
				List<String> utterenceList = new LinkedList<>();
				utterenceList.add(favoritePositionOrdinal.trim().toLowerCase()); //first, second etc
				utterenceList.add(favoriteName.trim().toLowerCase());// favorite names
			    if(i== customerFavourites.getData().size()-1) {
			    	utterenceList.add("last");
			    }
			    Item item = new Item(i+1, favoriteId, favoriteName, getFavoriteProductDetails(customerFavoriteForm), utterenceList.toArray(new String[utterenceList.size()]), false, false);
			    favoriteList.add(item);
		}
		return favoriteList;		
	}
	
	/**
	 * This method returns the product and counts of each product
	 * @param customerFavoriteForm
	 * @return
	 */
	private List<ProductDescription> getFavoriteProductDetails(CustomerFavoriteForm customerFavoriteForm) {
		List<ProductDescription> productDetails= new ArrayList<>();
	    List<CartProductForm> products = customerFavoriteForm.getCartStateForm().getProducts();
		List<CartDealForm> deals = customerFavoriteForm.getCartStateForm().getDeals();
		updateProductDetails(productDetails,products);
		for(CartDealForm cartDealForm: deals) {
			products = cartDealForm.getProducts();
			updateProductDetails(productDetails,products);
		}
		return productDetails;
	}
	private String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_CUSTOMER_FAVORITES_LIST_URL);
	}
}
