package com.pji.alexa.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.pji.alexa.model.v2.Item;

@Configuration
@Component
@Scope("singleton")
public class Util {
	
    private static final Logger logger = LoggerFactory.getLogger(Util.class);    
    
	private Properties verbiageCollection;
	
	@Autowired	
	private Transformer transformer;
	
	@Autowired
	private Environment environment;
	/**
	 * This method returns the verbage value when passed with a key and params
	 * @param key
	 * @param params
	 * @return
	 */
	public String getVerbiage(String key, Object... params) {
		String value="";
		if(StringUtils.isNotEmpty(key))
			value = MessageFormat.format(verbiageCollection.getProperty(key), params);
		return value;
	}

	/**
	 * This method returns the value for a particualr key
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return this.getEnvironment().getProperty(key);
	}
	
	/**
	 * This method returns the verbage value when passed with a key
	 * @param key
	 * @return
	 */
	public String getVerbiage(String key) {
		String value="";
		if(StringUtils.isNotEmpty(key)) {
			value = verbiageCollection.getProperty(key);
		}
		return value;
	}	

	/**
	 * This method returns current application profile, vis. local, dev, qa etc.
	 * @return
	 */
	public String getCurrentProfile() {
		if(environment != null)
			return environment.getActiveProfiles()[0];
		else
			return "";
	}	
	/**
	 * This method fetches the verbages from cloud storage.
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private Properties getVerbiageFromCloudStorage() throws FileNotFoundException, IOException {
		String bucketName= environment.getProperty(Constants.CONFIG_VERBAGE_CLOUD_STORAGE_BUCKET_NAME);
		String fileName= environment.getProperty(Constants.CONFIG_VERBAGE_CLOUD_STORAGE_FILE_NAME);
		Storage storage = StorageOptions.getDefaultInstance().getService();		
		BlobId blobId = BlobId.of(bucketName, fileName);
		byte[] content = storage.readAllBytes(blobId);
		String contentString = new String(content, StandardCharsets.UTF_8);	
		Properties properties = new Properties();
		properties.load(new StringReader(contentString));
		return properties;
	}
	
	/**
	 * This method does the following:
	 * 1. Reloads the verbages into the bean context.
	 */
	@Scheduled(fixedDelay = Constants.CONFIG_VERBAGE_RELOAD_TIME)
	protected void reloadVerbiage() throws FileNotFoundException,IOException{
		Properties properties= getVerbiageFromCloudStorage();
		if(this.getVerbiageCollection() == null) {
			logger.debug(properties.toString());
		}
		this.setVerbiageCollection(properties);
	}

	/**
	 * This method returns the HttpMethod instance
	 * @param method
	 * @return
	 */
	public HttpMethod getHttpMethod(String method) {
		if(StringUtils.equalsIgnoreCase("GET", method)) {
			return HttpMethod.GET;
		}else if(StringUtils.equalsIgnoreCase("POST", method)) {
			return HttpMethod.POST;
		}else {
			return null;
		}
	}

	/**
	 * This method converts the number strings to their named number strings for example:
	 * 123 would be converted to ONE TWO THREE
	 * @param number
	 * @return
	 */
	public String convertNumberStringToNumberNames(String number) {
		StringBuilder builder = new StringBuilder();
		for(char ch: number.toCharArray()) {
			if(Character.isDigit(ch)) {
				builder.append(NumberCode.getText(Integer.parseInt(String.valueOf(ch))).getText());
				builder.append(" ");
			}else {
				break;
			}
		}
		return builder.toString();
	}
	/**
	 * This method returns the Inet Address
	 * @return
	 */
	public String getIpAddress() {
		String ipAddress = "";
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			ipAddress = ip.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
		return ipAddress;
	}
	
	public String getOrdinal(int number) {
		RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.UK, RuleBasedNumberFormat.SPELLOUT);
		if(number	>	0) {
			return nf.format(number, "%spellout-ordinal");
		}
		return "";
	}
	/**
	 * This method extracts the number 
	 * from the uttered string, also
	 * removes the 1 uttered at the last
	 * like the first one
	 * @param str
	 * @return
	 */
	public String extractNumber(String str) {
		if(str == null || str.isEmpty()) return "";
	    StringBuilder sb = new StringBuilder();
	    StringBuilder builder	=	new StringBuilder();
	    builder.append(str);
	    if(str.charAt(str.trim().length()-1) == '1')
		{
	    	str	=	builder.deleteCharAt(str.trim().length()-1).toString();
		}
	    boolean found = false;
	    for(char c : str.toCharArray()){
	        if(Character.isDigit(c)){
	            sb.append(c);
	            found = true;
	        } else if(found){
	            // If we already found a digit before and this char is not a digit, stop looping
	            break;                
	        }
	    }
	    return sb.toString();
	}
	
	/*
	 * this method returns the data
	 * in the form of 1st, 2nd
	 * @Param int
	 * @return String
	 */
	public String ordinal(int i) {
	    String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	    switch (i % 100) {
	    case 11:
	    case 12:
	    case 13:
	        return i + "th";
	    default:
	        return i + sufixes[i % 10];

	    }
	}
	
	
    /*
     * This method takes 2 inputs, slot value to search
     * and the list which contains the utterences array
     * in which to be searched by calling the fuzzy logic,
     * It returns the itemId which is matched, if there are multiple matches or no match it returns a null
     * @param String utteredStr
     * @param List<Item> itemTypeList
     * @return String
     */
	public String findItem(String utteredStr, List<Item> itemTypeList) {
		String itemId= null;
		Map<String, Double> itemIdDistanceMap = new LinkedHashMap<>();
		List<String> maxPercentageList = new LinkedList<>();
		List<char[]> utteredStrChar = stringToChar(utteredStr);
		itemTypeList.forEach(item ->
			{
				 Map<String, Double> utterenceDistanceMap = new LinkedHashMap<>();
				 Arrays.asList(item.getUtterences()).forEach(utterence -> {
					 List<char[]> utterenceArrayElement = stringToChar(utterence);
					 double currentValue = fuzzyCompare(utteredStrChar, utterenceArrayElement);
					 utterenceDistanceMap.put(utterence, currentValue);
				 });
				 double maxValueInMap = Collections.max(utterenceDistanceMap.values());
				 itemIdDistanceMap.put(item.getItemId(), maxValueInMap);
			});
		double maxValue = Collections.max(itemIdDistanceMap.values());
		itemIdDistanceMap.forEach((key,value)	->	{
				if(value ==	maxValue && value	!=	0.0) {
					maxPercentageList.add(key);
				}
		});
		if(maxPercentageList.size() == 1) {
			itemId=maxPercentageList.get(0);
		}
		return itemId;
	}
	/*
	 * Fuzzy logic to compare 2 strings
	 * and returns the double value, high
	 * being the most closer strings
	 * @param List<char[]> utteredStringToChar
	 * @param List<char[]> favoriteStringToChar
	 * @return double
	 */
	private double fuzzyCompare(List<char[]> utteredStringToChar, List<char[]> favoriteStringToChar)
	{
	    List<char[]> copy = new ArrayList<>(favoriteStringToChar);
	    int matches = 0;
	    for (int i = utteredStringToChar.size(); --i >= 0;)
	    {
	        char[] bigram = utteredStringToChar.get(i);
	        for (int j = copy.size(); --j >= 0;)
	        {
	            char[] toMatch = copy.get(j);
	            if (bigram[0] == toMatch[0] && bigram[1] == toMatch[1])
	            {
	                copy.remove(j);
	                matches += 2;
	                break;
	            }
	        }
	    }
	    return (double) matches / (utteredStringToChar.size() + favoriteStringToChar.size());
	}

	/*
	 * This method takes string as a input
	 * and returns the list of char of that
	 * string, which is required as input for
	 * the fuzzy logic.
	 * @param String input
	 * @return List<char[]>
	 */
	private List<char[]> stringToChar(String input)
	{
	    ArrayList<char[]> charList = new ArrayList<>();
	    for (int i = 0; i < input.length() - 1; i++)
	    {
	        char[] chars = new char[2];
	        chars[0] = input.charAt(i);
	        chars[1] = input.charAt(i+1);
	        charList.add(chars);
	    }
	    return charList;
	}
	
	/**
	 * This method checks if favorite name contains any special character or ambigious names
	 * @param favoriteName
	 * @return
	 */
	public boolean validateFavoriteName(String favoriteName) {
		boolean valid= true;
		if(StringUtils.isNotBlank(favoriteName)) {
			String[] invalidNames= this.getVerbiage("verbage.favorite.names.ignore.list").split(",");
			for(String invalidName: invalidNames) {
				if(favoriteName.toLowerCase().contains(invalidName.toLowerCase())) {
					valid=false;
					break;
				}
			}
		}
		return valid;
	}
	public Properties getVerbiageCollection() {
		return verbiageCollection;
	}
	public void setVerbiageCollection(Properties verbiageCollection) {
		this.verbiageCollection = verbiageCollection;
	}
	public Transformer getTransformer() {
		return transformer;
	}
	public Environment getEnvironment() {
		return environment;
	}
}
