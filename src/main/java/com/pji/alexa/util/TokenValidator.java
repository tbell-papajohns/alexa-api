package com.pji.alexa.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenValidator {
	
	@Autowired
	private Util util;
	/**
	 * This method only extracts the claims from the JWT token, No validation is performed.
	 * 
	 * @param jwttoken
	 * @return
	 * @throws Exception
	 */
	public Claims getClaims(String jwttoken) throws Exception {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
	    X509Certificate cert = (X509Certificate)cf.generateCertificate(new FileInputStream(new File(util.getProperty(Constants.PJI_OAUTH_JWT_PUBLIC_CERT_FILE))));
	    PublicKey publicKey = cert.getPublicKey();
		Claims claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwttoken).getBody();
		return claims;
	}
}
