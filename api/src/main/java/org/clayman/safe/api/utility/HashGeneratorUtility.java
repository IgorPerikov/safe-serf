package org.clayman.safe.api.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;

@Service
public class HashGeneratorUtility {

    private MessageDigest md;

    @Value("${hash.secret:some_default_secret_key}")
    private String secret;

    @PostConstruct
    private void init() throws Exception {
        md = MessageDigest.getInstance("SHA-256");
    }

    public String generateHash(String source) {
        String saltedSource = source + secret;
        byte[] digest = md.digest(saltedSource.getBytes());
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }
}
