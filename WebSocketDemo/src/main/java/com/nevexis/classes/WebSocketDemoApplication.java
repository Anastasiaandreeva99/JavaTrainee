package com.nevexis.classes;
//package com.nevexis;
//
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Date;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.glassfish.tyrus.server.Server;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class WebSocketDemoApplication {
//
//	private static final byte[] PRIVATE_KEY = null;
//	private int nonce = 0;
//
//	public static void main(String[] args) throws Exception {
//		SpringApplication.run(WebSocketDemoApplication.class, args);
//		// runServer();
//	}
//
//	public static void runServer() {
//
//		Server server = new Server("localhost", 8081, "/websockets", DemoServerEndPoint.class);
//
//		try {
//
//			server.start();
//			Thread.sleep(600000);
//		} catch (Exception e) {
//
//			throw new RuntimeException(e);
//
//		} finally {
//
//			server.stop();
//
//		}
//
//	}
//
//	private String signMessage(String endpoint, String nonce, String postData)
//			throws NoSuchAlgorithmException, InvalidKeyException {
//		// Step 1: concatenate postData, nonce + endpoint
//		String message = postData + nonce + endpoint;
//
//		// Step 2: hash the result of step 1 with SHA256
//		byte[] hash = MessageDigest.getInstance("SHA-256").digest(message.getBytes(StandardCharsets.UTF_8));
//
//		// step 3: base64 decode apiPrivateKey
//		byte[] secretDecoded = Base64.getDecoder().decode(PRIVATE_KEY);
//
//		// step 4: use result of step 3 to hash the resultof step 2 with
//		// HMAC-SHA512
//		Mac hmacsha512 = Mac.getInstance("HmacSHA512");
//		hmacsha512.init(new SecretKeySpec(secretDecoded, "HmacSHA512"));
//		byte[] hash2 = hmacsha512.doFinal(hash);
//
//		// step 5: base64 encode the result of step 4 and return
//		return Base64.getEncoder().encodeToString(hash2);
//
//	}
//
//	// Returns a unique nonce
//	private String createNonce() {
//		nonce += 1;
//		long timestamp = (new Date()).getTime();
//		return timestamp + String.format("%04d", nonce);
//	}
//
//}
