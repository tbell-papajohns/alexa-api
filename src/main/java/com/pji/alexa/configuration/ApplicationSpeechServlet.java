package com.pji.alexa.configuration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.amazon.speech.Sdk;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import com.pji.alexa.handler.RequestHandler;

/**
 * This class is the servlet class which acts as a front controller, All the requests coming from the alexa devices
 *  would be processed by this servlet
 * @author anubhav
 *
 */

@Configurable
@WebServlet("/v1/alexa/handler")
public class ApplicationSpeechServlet extends SpeechletServlet {
	
	@Autowired
	private RequestHandler requestHandler;
	
	/**
	 * Disabling signature verification for development 
	 */
	 static { 
		 System.setProperty(Sdk.DISABLE_REQUEST_SIGNATURE_CHECK_SYSTEM_PROPERTY, "true"); 
		 System.setProperty(Sdk.SUPPORTED_APPLICATION_IDS_SYSTEM_PROPERTY, ""); 
		 System.setProperty(Sdk.TIMESTAMP_TOLERANCE_SYSTEM_PROPERTY, "");
	 } 	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	    this.setSpeechlet(requestHandler);
	}
}