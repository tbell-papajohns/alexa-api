package com.pji.alexa.configuration;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pji.alexa.model.v2.Server;
import com.pji.alexa.util.Constants;
import com.pji.alexa.util.Util;

@Component
@Scope("singleton")
public class ServerConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);
	
	@Autowired
	private Util util;
	
	@Autowired
	private Server server;
	
	private List<Server> serverList;

	
	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	@PostConstruct
	public void setCurrentServerDetails() throws Exception {			
		if(util != null) {
		        File file = new File(util.getProperty(Constants.PJI_SERVER_FILENAME)); 
		        FileSystemResource resource = new FileSystemResource(file);
			    InputStream inputStream = resource.getInputStream();
			    byte[] bytes = IOUtils.toByteArray(inputStream);
		        String serverJson= new String(bytes,"UTF-8");
		        Gson gson = new Gson();
		        this.serverList =gson.fromJson(serverJson, new TypeToken<List<Server>>() {}.getType());		
				String pjiEnv= util.getProperty(Constants.PJI_ENDPOINT_ENVIRONMENT);
				for(Server ser: serverList) {
					if(pjiEnv.equalsIgnoreCase(ser.getName())) {
						this.server= ser;
						break;
					}
				}
				if(StringUtils.isEmpty(this.getServer().getUserAndPassword())){
					throw new Exception("Configured Downstream Server not available");
				}
			}
		}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
}
