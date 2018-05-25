package com.pji.alexa.helper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pji.alexa.model.v2.Item;
import com.pji.alexa.model.v2.ProductDescription;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Util;

@Component
public class FavoriteSpeechHelper extends SpeechHelper {
	
	@Autowired
	private Util utils;
	/**`
	 * This method returns the prompt for listing the favorites
	 * @param customerId
	 * @param customerToken
	 * @return
	 */
	public Map<String, String> getPromptForListingFavourites(List<Item> favoriteNameList, boolean invalidResponse) throws Exception{
		String speech= null;
		StringBuilder favoriteDetailsSpeech = new StringBuilder();
		String speechResponseForCard	=	null;
		Map<String, String> promptForFavoriteMap	=	new HashMap<>();
		int i=0;
		for(Item favoriteItem: favoriteNameList) {
			i++;
			List<ProductDescription> productDescriptionList= favoriteItem.getItemDescription();
			//Sorting on the basis of cost of the products
			Collections.sort(productDescriptionList);
			ProductDescription costliestProduct= productDescriptionList.get(productDescriptionList.size()-1);
			String productName= costliestProduct.getProductName();
			Integer productQuantity= costliestProduct.getProductQuantity();
			Integer noOfProducts= productDescriptionList.size();
			StringBuilder productDescriptionBuilder= new StringBuilder();
			productDescriptionBuilder.append(productQuantity);
			productDescriptionBuilder.append(" ");
			productDescriptionBuilder.append(productName);
			
			if(noOfProducts > 1) {
				productDescriptionBuilder.append(" " +Constants.VERBAGE_FAVORITE_RECENT_PRODUCT_ITEM_AND + " ");
				productDescriptionBuilder.append(noOfProducts-1);
				productDescriptionBuilder.append(" other Items.");
			}
			if(utils.validateFavoriteName(favoriteItem.getItemName())) {
				favoriteDetailsSpeech.append(" "+this.getUtil().getVerbiage(Constants.VERBAGE_FAVORITE_ITEMS_DESC,this.getUtil().getOrdinal(i), "is '"+ favoriteItem.getItemName() + "' and it ",productDescriptionBuilder.toString()));
			}else {
				favoriteDetailsSpeech.append(" "+this.getUtil().getVerbiage(Constants.VERBAGE_FAVORITE_ITEMS_DESC,this.getUtil().getOrdinal(i), "",productDescriptionBuilder.toString()));
			}			
		}
		favoriteDetailsSpeech.replace(favoriteDetailsSpeech.length()-2, favoriteDetailsSpeech.length()-2, "");
		speech= this.getUtil().getVerbiage(Constants.VERBAGE_FAVORITE_LISTING,favoriteNameList.size(),favoriteDetailsSpeech.toString());
		speechResponseForCard	=	this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_FAVORITE_LISTING,favoriteNameList.size(),favoriteDetailsSpeech.toString());
		if(invalidResponse) {
			speech	= this.getUtil().getVerbiage(Constants.VERBAGE_FAVORITE_LISTING_AFTER_INVALID_RESPONSE, speech);
			speechResponseForCard	= this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_FAVORITE_LISTING_AFTER_INVALID_RESPONSE, speechResponseForCard);//need to update verbiage
		}else {
			speech	= this.getUtil().getVerbiage(Constants.VERBAGE_FAVORITE_LISTING_AFTER_VALID_RESPONSE, speech);
			speechResponseForCard	= this.getUtil().getVerbiage(Constants.ALEXA_CARD_VERBAGE_FAVORITE_LISTING_AFTER_VALID_RESPONSE, speechResponseForCard);//need to update verbiage
		}
		promptForFavoriteMap.put("SpeechPrompt", speech);
		promptForFavoriteMap.put("SpeechResponseForCard", speechResponseForCard);
		return promptForFavoriteMap;
	}
}
