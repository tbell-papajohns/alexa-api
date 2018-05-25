package com.pji.alexa.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesBase {
		
	
	public Properties getPropertyMethod()
	{
		 Properties props = new Properties();
		try {

            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream("alexa-test.properties");

           

            props.load(inputStream);
            
         } catch (IOException io) {
             io.printStackTrace();
             
         }
		
		return props;
		
	}
}
