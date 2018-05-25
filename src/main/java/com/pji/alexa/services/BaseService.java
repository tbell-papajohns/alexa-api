package com.pji.alexa.services;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.pji.alexa.configuration.ServerConfig;
import com.pji.alexa.model.v2.CartProductForm;
import com.pji.alexa.model.v2.ProductDescription;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Transformer;
import com.pji.alexa.util.Util;
 
/**
 * 
 * @author anubh
 *
 */
@Service
public class BaseService {
	
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);    
			
	@Autowired
	private Util util;
	
	@Autowired
	private Transformer transformer;
	
	@Autowired
	private ServerConfig serverConfig;
	
	@Autowired
	private FavouriteService favouriteService;
	
	@Autowired
	private PastOrderService pastOrderService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	@Autowired
	private CardDetailsService cardDetailsService;
	
	@Autowired
	private CartService cartService;
	
	private final RestTemplate restTemplate;
	
	private Map<String,String> headers;
	
	private String baseUrl;

	private String tokenValue;

	private HttpMethod httpMethod;
	
	private String url;
	
	private String payload;
	
	private HttpEntity<String> httpEntity;
	
	public BaseService(RestTemplateBuilder restTemplateBuilder){
		this.restTemplate= restTemplateBuilder.build();
	}
	
	
	
	/**
	 * This method updates the product description vis, title, quantity and cost of each product.
	 * @param productDetails
	 * @param products
	 */
	protected void updateProductDetails(List<ProductDescription> productDetails, List<CartProductForm> products) {
		Map<String, Integer> quantityMap= getQuantityMapForProducts(products);
		ProductDescription productDescription = null;
		for(CartProductForm cartProductForm: products) {
			productDescription= new ProductDescription();
			productDescription.setProductName(cartProductForm.getTitle());
			productDescription.setProductQuantity(quantityMap.get(cartProductForm.getTitle()));
			if(cartProductForm.getDisplayPrice() == null) {
				productDescription.setProductCost(0.0);
			}else {
				productDescription.setProductCost(Double.valueOf(cartProductForm.getDisplayPrice()));	
			}
			productDetails.add(productDescription);
		}
	}
	/**
	 * This method gets the quantity and title for each product
	 * @param products
	 * @return
	 */
	private Map<String, Integer> getQuantityMapForProducts(List<CartProductForm> products){
		Map<String,Integer> quantityMap = new HashMap<>();
		for(CartProductForm cartProductForm: products) {
			Integer productCount= cartProductForm.getQuantity();
			String productTitle= cartProductForm.getTitle();
			if (quantityMap.containsKey(productTitle)) {
				productCount=quantityMap.get(productTitle);
				productCount=productCount+1;
			}
			quantityMap.put(productTitle, productCount);
		}
		return quantityMap;
	}
	/**
	 * This method makes all Http Request (GET, POST, PUT, DELETE)
	 * @param url
	 * @param returnClassType
	 * @param headersMap
	 * @param uriParams
	 * @return
	 */
	protected Object sendHttpRequest(Class<?> returnType,Object...uriParams) throws Exception{
		createHttpEntity();
        logger.debug("Request Params:" + Arrays.deepToString(uriParams));		
        ResponseEntity<?> response = restTemplate.exchange(this.getUrl(), this.getHttpMethod(), this.getHttpEntity(), returnType,uriParams);
        logger.debug("Response Body:" + new Gson().toJson(response.getBody()));
        return response.getBody();
	}
	private void createHttpEntity() {
		logger.debug("### Making API Request ###");
		logger.debug("URL:" + this.getUrl());
		logger.debug("Method:" + this.getHttpMethod());		
		logger.debug("Headers:" + this.getHeaders().toString());		
		final HttpHeaders header = new HttpHeaders();
		header.setAll(this.getHeaders());		
		if(this.getHttpMethod().name().equals("GET")) {
			this.setHttpEntity(new HttpEntity<String>(header));
		}else {
			header.setContentType(MediaType.APPLICATION_JSON);		
			logger.debug("Payload:" + this.getPayload());		
			this.setHttpEntity(new HttpEntity<String>(this.getPayload(),header));
		}
	}

	@PostConstruct
	private void constructBaseUrl() {
		headers = new HashMap<String,String>();
		setBaseUrl(util.getProperty(Constants.PJI_ENDPOINT_PROTOCAL) + "://" + util.getProperty(Constants.PJI_ENDPOINT_HOST) + ":" + util.getProperty(Constants.PJI_ENDPOINT_PORT));
	}	
	public Util getUtil() {
		return util;
	}
	public void setUtil(Util util) {
		this.util = util;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Map<String, String> getHeaders() {
		String usernameAndPass= this.getServerConfig().getServer().getUserAndPassword();
        String value = Constants.PJI_ENDPOINT_HEADER_AUTHORIZATION_TYPE + " " + Base64.getEncoder().encodeToString(usernameAndPass.getBytes(StandardCharsets.UTF_8));
		headers.put(Constants.PJI_ENDPOINT_HEADER_AUTHORIZATION, value);		
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {		
		this.headers = headers;
	}

	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
		if(headers != null)
			headers.put(util.getProperty(Constants.PJI_ENDPOINT_HEADER_TOKEN), this.getTokenValue());
	}

	public ServerConfig getServerConfig() {
		return serverConfig;
	}

	public void setServerConfig(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public HttpEntity<String> getHttpEntity() {
		return httpEntity;
	}

	public void setHttpEntity(HttpEntity<String> httpEntity) {
		this.httpEntity = httpEntity;
	}
	public Transformer getTransformer() {
		return transformer;
	}
	public void setTransformer(Transformer transformer) {
		this.transformer = transformer;
	}
	public FavouriteService getFavouriteService() {
		return favouriteService;
	}
	public void setFavouriteService(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}
	public PastOrderService getPastOrderService() {
		return pastOrderService;
	}
	public void setPastOrderService(PastOrderService pastOrderService) {
		this.pastOrderService = pastOrderService;
	}
	public StoreService getStoreService() {
		return storeService;
	}
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	public CustomerInfoService getCustomerInfoService() {
		return customerInfoService;
	}
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}
	public CardDetailsService getCardDetailsService() {
		return cardDetailsService;
	}
	public void setCardDetailsService(CardDetailsService cardDetailsService) {
		this.cardDetailsService = cardDetailsService;
	}
	public CartService getCartService() {
		return cartService;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
}
