package com.pji.alexa.util;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pji.alexa.model.v2.Item;


@RunWith(SpringJUnit4ClassRunner.class)
public class UtilTest {
	
	@Mock
	private Properties verbiageCollection;
	
	@InjectMocks
	private Util util;
	
	@Mock
	private Environment env;	
	
	@Test
	public void testGetProperty() {
		Mockito.when(env.getProperty("info.app.name")).thenReturn("Alexa Skill Handler");
		assertEquals("Alexa Skill Handler", util.getProperty("info.app.name"));
	}
	
	/**
	 * This Test class validates the Scenerio where an invalid favorite name is passed
	 */
	@Test
	public void testValidateFavoriteNameInvalidName() {
		Mockito.when(verbiageCollection.getProperty("verbage.favorite.names.ignore.list")).thenReturn("favorite,start over,first,second,third,&,%,$,#,@,%,*,cancel,cash");
		assertEquals(false, util.validateFavoriteName("favorite"));
		assertEquals(false, util.validateFavoriteName("Football#"));
		assertEquals(false, util.validateFavoriteName("cash"));
	}
	
	/**
	 * This Test class validates the Scenerio where an invalid favorite name is passed
	 */
	@Test
	public void testValidateFavoriteNameValidName() {
		Mockito.when(verbiageCollection.getProperty("verbage.favorite.names.ignore.list")).thenReturn("favorite,start over,first,second,third,&,%,$,#,@,%,*,cancel,cash");
		assertEquals(true, util.validateFavoriteName("Football"));
	}
	
	/**
	 * This method tests the fuzzy logic findings in success scenerio
	 */
	@Test
	public void testFindItemSuccess() {
		List<Item> itemList= new ArrayList<>();
		Item item1 = new Item();
		item1.setId(1);
		item1.setItemId("I123451");
		item1.setItemName("Item1");
		String[] utterences1= {"first", "football"};
		item1.setUtterences(utterences1);
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setItemId("I123452");
		item2.setItemName("Item2");
		String[] utterences2= {"second","lunch m"};
		item2.setUtterences(utterences2);
		
		Item item3 = new Item();
		item3.setId(3);
		item3.setItemId("I123453");
		item3.setItemName("Item3");
		String[] utterences3= {"third", "lunch d"};
		item3.setUtterences(utterences3);
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		assertEquals("I123451", util.findItem("first", itemList));
		assertEquals("I123451", util.findItem("foot", itemList));
		assertEquals("I123452", util.findItem("lunch m", itemList));
	}
	
	/**
	 * This method tests the fuzzy logic findings in failure scenarios
	 */
	@Test
	public void testFindItemFailure() {
		List<Item> itemList= new ArrayList<>();
		Item item1 = new Item();
		item1.setId(1);
		item1.setItemId("I123451");
		item1.setItemName("Item1");
		String[] utterences1= {"first", "football"};
		item1.setUtterences(utterences1);
		
		Item item2 = new Item();
		item2.setId(2);
		item2.setItemId("I123452");
		item2.setItemName("Item2");
		String[] utterences2= {"second","lunch m"};
		item2.setUtterences(utterences2);
		
		Item item3 = new Item();
		item3.setId(3);
		item3.setItemId("I123453");
		item3.setItemName("Item3");
		String[] utterences3= {"third", "lunch d"};
		item3.setUtterences(utterences3);
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		assertEquals(null, util.findItem("dinner", itemList));
		assertEquals(null, util.findItem("lunch", itemList));
	}
	
	
	
	
/*	
	@Before
	public void setUp() throws Exception {
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("alexa-verbiage-test.properties");
			verbiageCollection = new Properties();
			verbiageCollection.load(inputStream);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}*/
	    
	@After
	public void tearDown() throws Exception {
	}


	
	/*@Ignore
	@Test
	public void testGetPropertyNullString() {
	*//**
	 * Test case for GetProperty() Method : Negative scenario - When String as Null
	 *//*
		Properties props = prop.getPropertyMethod();
		assertNull(util.getProperty(props.getProperty("nameNegative")));
	}

	@Ignore
	@Test
	public void testgetVerbiage()
	{
		*//**
		 * Test case for getVerbiage() Method of Util class
		 *//*
		Properties props = prop.getPropertyMethod();
		when(verbiageCollection.getProperty(props.getProperty("namePositive"))).thenReturn("https");
		assertEquals("https", util.getVerbiage(props.getProperty("namePositive")));
	
	}
	
	@Ignore
	@Test
	public void testgetVerbiageNullString()
	{
		*//**
		 * Test case for getVerbiage() Method : Negative scenario - When String as Null
		 *//*
		Properties props = prop.getPropertyMethod();
		assertNull(util.getVerbiage(props.getProperty("nameNegative")));
	}
	
	@Ignore
	@Test
	public void testconvertNumberStringToNumberNames()
	{	
		*//**
		 * Test case for convertNumberStringToNumberNames() Method of Util class
		 *//*
		Properties props = prop.getPropertyMethod();
		assertEquals("THREE ", util.convertNumberStringToNumberNames(props.getProperty("number")));
	}
	
	@Ignore
	@Test//TODO
	public void testconvertNumberStringToNumberNamesNullString()
	{	
		*//**
		 * Test case for convertNumberStringToNumberNames() Method : Negative scenario - When String as Null
		 *//*
		Properties props = prop.getPropertyMethod();
		assertEquals("", util.convertNumberStringToNumberNames("ONE"));
	}
	
	@Ignore
	@Test
	public void testgetHttpMethodGet()
	{	
		*//**
		 * Test case for getHttpMethodGet() Method of Util class
		 *//*
		Properties props = prop.getPropertyMethod();
		assertEquals("GET", util.getHttpMethod(props.getProperty("methodGet")).toString());
		assertEquals("POST", util.getHttpMethod(props.getProperty("methodPost")).toString());
		
		try{
			assertNotNull(util.getHttpMethod(props.getProperty("methodRandom")).toString());
			
		}catch(Exception e)
		{
			logger.debug("Null pointer Exception handle");
			
		}
		 
	}
	
	@Ignore
	@Test
	public void testgetHttpMethodGetNullString()
	{	
		*//**
		 * Test case for getHttpMethodGet() Method : Negative scenario - When String as Null
		 *//*
		Properties props = prop.getPropertyMethod();
		assertNull(util.getHttpMethod(props.getProperty("nameNegative")));
		
	}*/
}
