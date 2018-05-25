package com.pji.alexa.model.v2;

/**

PapaJohns
- cartStateForm: required
- favoritePriority: optional
- favoriteType: optional
- id: optional
- name: required
- setAsGoToFave: optional
- statusMessages: optional
- storeId: required
*/

import java.io.Serializable;
import java.util.List;

public class CustomerFavoriteForm implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartStateForm cartStateForm;

    private Integer favoritePriority;

    private String favoriteType;

    private Long id;

    private String name;

    private Boolean setAsGoToFave;

    private List<ResponseMessage> statusMessages;

    private Integer storeId;

	public CartStateForm getCartStateForm() {
		return cartStateForm;
	}

	public void setCartStateForm(CartStateForm cartStateForm) {
		this.cartStateForm = cartStateForm;
	}

	public Integer getFavoritePriority() {
		return favoritePriority;
	}

	public void setFavoritePriority(Integer favoritePriority) {
		this.favoritePriority = favoritePriority;
	}

	public String getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(String favoriteType) {
		this.favoriteType = favoriteType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSetAsGoToFave() {
		return setAsGoToFave;
	}

	public void setSetAsGoToFave(Boolean setAsGoToFave) {
		this.setAsGoToFave = setAsGoToFave;
	}

	public List<ResponseMessage> getStatusMessages() {
		return statusMessages;
	}

	public void setStatusMessages(List<ResponseMessage> statusMessages) {
		this.statusMessages = statusMessages;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
}
