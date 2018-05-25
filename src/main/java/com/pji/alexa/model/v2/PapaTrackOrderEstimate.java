package com.pji.alexa.model.v2;

import java.io.Serializable;

public class PapaTrackOrderEstimate implements Serializable {

	private static final long serialVersionUID = 1L;
	
    public String orderReadyEstimateMinutesMin;

    public String orderReadyEstimateMinutesMax;

	public String getOrderReadyEstimateMinutesMin() {
		return orderReadyEstimateMinutesMin;
	}

	public void setOrderReadyEstimateMinutesMin(String orderReadyEstimateMinutesMin) {
		this.orderReadyEstimateMinutesMin = orderReadyEstimateMinutesMin;
	}

	public String getOrderReadyEstimateMinutesMax() {
		return orderReadyEstimateMinutesMax;
	}

	public void setOrderReadyEstimateMinutesMax(String orderReadyEstimateMinutesMax) {
		this.orderReadyEstimateMinutesMax = orderReadyEstimateMinutesMax;
	}

}
