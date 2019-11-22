import java.io.*;
import java.util.*;
import java.math.*;
class RSA
{
	 private BigInteger p;
	 private BigInteger q;
	 private BigInteger n;
	 private BigInteger e;
	 private BigInteger d;
	 private BigInteger phi;
	  private int bitlength =512 ;
	  private int blocksize=256 ;
	   private Random r;
	   public RSA()
	   {
	   	r=new Random();
	   	p=BigInteger.probablePrime(bitlength ,r);
	   	q=BigInteger.probablePrime(bitlength ,r);
	   	n=p.multiply(q);
	   	phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
	   	e=BigInteger.probablePrime(bitlength/2,r);
	   	
	   	while(phi.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(phi) <0)
	   	{
	   		e.add(BigInteger.ONE);
	   	}
	   	d=e.modInverse(phi);
	   }
	   
	   public RSA(BigInteger e, BigInteger d, BigInteger n) 
	   {
		this.e = e;
		this.d = d;
		this.n = n;
	   }
	
	public static void main (String[] args) throws IOException {
		RSA rsa = new RSA(); 
		
		DataInputStream in=new DataInputStream(System.in);
		
		String teststring ;
		
		System.out.println("Enter the plain text:");
		
		teststring=in.readLine();
		
		
		System.out.println("String to be Encrypted :" + teststring);
		
		System.out.println("String in Bytes: " + bytesToString(teststring.getBytes()));
		
		// encryption
		byte[] encrypted = rsa.encrypt(teststring.getBytes());
		
	
		
		// decryption
		byte[] decrypted = rsa.decrypt(encrypted);
		
		System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted));
		
		System.out.println("Decrypted String: " + new String(decrypted));
		
	}
	  
	  private static String bytesToString(byte[] encrypted) {
		String test = "";
		
		for (byte b : encrypted)
		 {
			test += Byte.toString(b);
		}
		
		return test;
	}
	
	
	//Encrypt message
	
	public  byte[] encrypt(byte[] message) 
	{
		return (new BigInteger(message)).modPow(e, n).toByteArray();
	}
	
	
	// Decrypt message
	
	public  byte[] decrypt(byte[] message) 
	{
		return (new BigInteger(message)).modPow(d, n).toByteArray();
	}

}
