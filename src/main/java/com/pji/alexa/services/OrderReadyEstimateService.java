package com.pji.alexa.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.pji.alexa.model.v2.PapaTrackOrderEstimate;
import com.pji.alexa.util.Constants;

@Service
public class OrderReadyEstimateService extends BaseService {

	public OrderReadyEstimateService(RestTemplateBuilder restTemplateBuilder) {
		super(restTemplateBuilder);
		// TODO Auto-generated constructor stub
	}
	
	@Retryable
	public String getOrderReadyEstimate(String orderNumber, String subId) throws Exception{
		String orderReadyEstimate = null;
		this.setHttpMethod(this.getUtil().getHttpMethod(this.getUtil().getProperty(Constants.PJI_ENDPOINT_METHOD_GET)));
		this.setUrl(createURL());
		PapaTrackOrderEstimate papaTrackOrderEstimate = (PapaTrackOrderEstimate) sendHttpRequest(PapaTrackOrderEstimate.class,orderNumber,subId);
		orderReadyEstimate = papaTrackOrderEstimate.getOrderReadyEstimateMinutesMin().concat(" to ").concat(papaTrackOrderEstimate.getOrderReadyEstimateMinutesMax());
		return orderReadyEstimate;
	}
	
	private String createURL() {		
		return this.getBaseUrl()+ this.getUtil().getProperty(Constants.PJI_ENDPOINT_ORDER_READY_ESTIMATE);
	}
}
