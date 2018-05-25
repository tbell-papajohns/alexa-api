package com.pji.alexa.model.v2;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;



@Component
public class Item implements Comparable<Item>{
	
	private Integer id;
    private String itemId;
    private String[] utterences;
    private String itemName;
    private List<ProductDescription> itemDescription;
    private boolean uttered;
    private boolean primary;
    
    public Item() {
    	//Default Constructor 
    }
    public Item(Integer id, String itemId, String itemName, List<ProductDescription> itemDescription, String utterances[],boolean isUttered, boolean isPrimary) {
    	this.id	=id;
    	this.itemId	=itemId;
    	this.utterences=utterances;
    	this.itemName=itemName;
    	this.itemDescription= itemDescription;
    	this.uttered= isUttered;
    	this.primary= isPrimary;
    }
    
    @Override
	public int compareTo(Item item) {
    	return (this.id - item.id);
	}
    
    @Override
    public String toString() {
        return "[id=" + this.id + ", itemId=" + this.itemId + ", utterences=" + this.utterences + "]";
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String[] getUtterences() {
		return utterences;
	}
	public void setUtterences(String[] utterences) {
		this.utterences = utterences;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public List<ProductDescription> getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(List<ProductDescription> itemDescription) {
		this.itemDescription = itemDescription;
	}
	public boolean isUttered() {
		return uttered;
	}
	public void setUttered(boolean uttered) {
		this.uttered = uttered;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
}
