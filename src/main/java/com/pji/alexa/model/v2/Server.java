package com.pji.alexa.model.v2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Server {
	private String name;
	private String urlPrefix;
	private String deliveryAlertUrl;
	private String userAndPassword;
	private boolean disableAltEncodings;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlPrefix() {
		return urlPrefix;
	}
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	public String getDeliveryAlertUrl() {
		return deliveryAlertUrl;
	}
	public void setDeliveryAlertUrl(String deliveryAlertUrl) {
		this.deliveryAlertUrl = deliveryAlertUrl;
	}
	public String getUserAndPassword() {
		return userAndPassword;
	}
	public void setUserAndPassword(String userAndPassword) {
		this.userAndPassword = userAndPassword;
	}
	public boolean isDisableAltEncodings() {
		return disableAltEncodings;
	}
	public void setDisableAltEncodings(boolean disableAltEncodings) {
		this.disableAltEncodings = disableAltEncodings;
	}
}
