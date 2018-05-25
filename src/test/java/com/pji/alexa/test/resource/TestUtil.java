package com.pji.alexa.test.resource;

import java.util.ArrayList;
import java.util.List;

import com.pji.alexa.model.v2.CampusAddressForm;
import com.pji.alexa.model.v2.CampusBuildingForm;
import com.pji.alexa.model.v2.CampusForm;
import com.pji.alexa.model.v2.CartComplimentarySideForm;
import com.pji.alexa.model.v2.CartDealForm;
import com.pji.alexa.model.v2.CartProductForm;
import com.pji.alexa.model.v2.CartProductInstructionForm;
import com.pji.alexa.model.v2.CartSectionForm;
import com.pji.alexa.model.v2.CartStateForm;
import com.pji.alexa.model.v2.PapaSize;
import com.pji.alexa.model.v2.ProductSKU;
import com.pji.alexa.model.v2.ResponseMessage;

public class TestUtil {
	public CampusAddressForm CampusAddressFormDetail()
	{
		CampusForm campusForm=new CampusForm();
		campusForm=CampusFormDetail();
		CampusBuildingForm campusBuildingForm=new CampusBuildingForm();
		CampusAddressForm campusAddressForm=new CampusAddressForm();
		campusAddressForm.setBuildingNumber("newzes");
		campusAddressForm.setCampus(campusForm);
		campusAddressForm.setCampusBuilding(campusBuildingForm);
		campusAddressForm.setRoomNumber("697");
		
		return campusAddressForm;
	}
	public CampusForm CampusFormDetail()
	{
		CampusForm campusForm=new CampusForm();
		campusForm.setId(279l);
		campusForm.setName("Sidden");
		return campusForm;
	}
	public CampusBuildingForm CampusBuildingFormDetail()
	{
		CampusBuildingForm campusBuildingForm=new CampusBuildingForm();
		campusBuildingForm.setBuildingNumberRequired(true);
		campusBuildingForm.setId(6794l);
		campusBuildingForm.setName("DeadIn");
		
		return campusBuildingForm;
	}
	
	 public CartComplimentarySideForm CartComplimentarySideFormDetail()
	 {
		 CartComplimentarySideForm cartComplimentarySideForm=new CartComplimentarySideForm();
		 
			cartComplimentarySideForm.setId(675435);
			cartComplimentarySideForm.setSku("Papa Johns");
			
			return  cartComplimentarySideForm;
	 }
	 public CartStateForm CartStateFormDetails()
		{
			CartStateForm cartStateForm=new CartStateForm();
			CartDealForm cartDealForm=new CartDealForm();
			CartComplimentarySideForm cartComplimentarySideForm=new CartComplimentarySideForm();
			cartComplimentarySideForm.setId(145);
			cartComplimentarySideForm.setSku("fgt");
			cartDealForm.setDealId(1234l);
			cartDealForm.setDisplayPrice("2367");
			cartDealForm.setFavoriteId(456l);
			cartDealForm.setIgnoreMinAmount(false);
			
			List<CartDealForm>list=new ArrayList<CartDealForm>();
			list.add(cartDealForm);
			cartStateForm.setDeals(list);
			return cartStateForm;
		}
		
		 public CartProductForm CartProductFormDetail()
		 {
			 CartProductInstructionForm  cartProductInstructionForm=new  CartProductInstructionForm();
			 cartProductInstructionForm=CartProductInstructionFormDetail();
			 List<CartProductInstructionForm> instructions=new ArrayList<CartProductInstructionForm>();
			 instructions.add(cartProductInstructionForm);
			 
			 PapaSize papaSize=PapaSizeDetail();
			 
			 CartSectionForm sectionOne=cartSectionFormDetais();
			 CartSectionForm sectionTwo=cartSectionFormDetais();
			 
			 CartProductForm cartProductForm=new CartProductForm();
			 
			 CartComplimentarySideForm cartComplimentarySideForm=new CartComplimentarySideForm();
			 cartComplimentarySideForm=CartComplimentarySideFormDetail();
			 List<CartComplimentarySideForm>sides=new ArrayList<CartComplimentarySideForm>();
			 sides.add(cartComplimentarySideForm);
			 
			 ResponseMessage statusMessage=ResponseMessageDetails();
			 
			 cartProductForm.setDisplayPrice("h3456");
			 cartProductForm.setFavoriteId(45678l);
			 cartProductForm.setImage("welcome.png");
			 cartProductForm.setInstructions(instructions);
			 cartProductForm.setPapaSize(papaSize);
			 cartProductForm.setPapaSized(true);
			 cartProductForm.setProductConfigurationId(34);
			 cartProductForm.setQuantity(67);
			 cartProductForm.setSauceId(456l);
			 cartProductForm.setSectionOne(sectionOne);
			 cartProductForm.setSectionTwo(sectionTwo);
			 cartProductForm.setSectionWhole(sectionTwo);
			 cartProductForm.setShopcartItemId("456789");
			 cartProductForm.setSides(sides);
			 cartProductForm.setSku("papa zones");
			 cartProductForm.setStatus("ok");
			 cartProductForm.setStatusMessage(statusMessage);
			 cartProductForm.setTitle("Papa Johns");
			
			 return cartProductForm;
			 
		 }

		 public CartProductInstructionForm CartProductInstructionFormDetail()
		 {
			 CartProductInstructionForm cartProductInstructionForm=new CartProductInstructionForm();
			 cartProductInstructionForm.setDetailId(3467l);
			 cartProductInstructionForm.setGroupId(478l);
			 
			 return cartProductInstructionForm;
		 }
		 
		 public PapaSize PapaSizeDetail()
		 {
			 PapaSize papaSize=new PapaSize();
			ProductSKU papaSizeSKU=ProductSKUDetails();
			 papaSize.setPapaSizeSKU(papaSizeSKU);
			 papaSize.setPrice(45668d);
			
			 return papaSize;

				
		 }
		 public ProductSKU ProductSKUDetails()
		 {
			 ProductSKU productSKU=new ProductSKU();
			 productSKU.setBaseIngredientSizeId(45l);
			 productSKU.setBaseIngredientTypeId(367l);
			 productSKU.setCustomizationId(279l);
			 productSKU.setProductTypeId(749l);
			 productSKU.setSku("Papa Detail");
			 
			 return productSKU;
			 
		 }
		 
		 public CartSectionForm cartSectionFormDetais()
		 {
			 List<Long> toppings=new ArrayList<Long>();
			 toppings.add(45l);
			 CartSectionForm cartSectionForm=new CartSectionForm();
			 cartSectionForm.setToppings(toppings);
			 
			 return cartSectionForm;
		 }
		 
	
		 
		 public ResponseMessage ResponseMessageDetails()
		 {
			 ResponseMessage responseMessage=new ResponseMessage();
			 responseMessage.setCode("505");
			 responseMessage.setDescription("Detail of Papa Johns");
			 
			 return responseMessage;
		 }
}
