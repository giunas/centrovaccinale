package centrovaccinale.security;
import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA {
	private static String sha1 = "";
	private static String sha256 = "";
	
	public static void setprencode(String plain) {
		// With the java libraries
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(plain.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception exc){
			System.out.println(exc.getMessage());
		}		
	}
	
	public static String getprencode() {
		return sha1;
	}
	
	public static void setpsw(String plain) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(plain.getBytes("utf8"));
	        sha256 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception exc){
			System.out.println(exc.getMessage());
		}	
	}
	
	public static String getpsw() {
		return sha256;
	}
}
