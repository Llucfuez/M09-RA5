package iticbcn.xifratge;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private  byte[] iv = new byte [MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    @Override 
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] bytesXifrats = xifraAES(msg, clau);
            return new TextXifrat(bytesXifrats);
        } catch(Exception e){
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }

    @Override 
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }
    
    public  byte[] xifraAES(String msg, String clau) throws Exception {

        
        byte[] bytesString = msg.getBytes(StandardCharsets.UTF_8);
        
        byte[] iv = generaIv(); 
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        SecretKeySpec secretKey = generaHash(clau);
        
        Cipher cipher = Cipher.getInstance(FORMAT_AES); 
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec); 
        byte[] bMsgXifrat = cipher.doFinal(bytesString); 
        
        byte[] resultat = new byte[iv.length + bMsgXifrat.length];

        System.arraycopy(iv,0, resultat, 0, iv.length);

        System.arraycopy(bMsgXifrat, 0, resultat, iv.length, bMsgXifrat.length); 
        return resultat;
    }

    public  String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
    
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
    
        
        IvParameterSpec ivSpec = new IvParameterSpec(iv);  

        
        byte[] bMsgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV]; 
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, bMsgXifrat, 0, bMsgXifrat.length); 
        
        SecretKeySpec secretKey = generaHash(clau); 

        Cipher cipher = Cipher.getInstance(FORMAT_AES); 
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec); 
        byte[] bMsgDesxifrat = cipher.doFinal(bMsgXifrat); 

        
        return new String(bMsgDesxifrat, StandardCharsets.UTF_8);
    }

    private  byte[] generaIv(){
        SecureRandom random = new SecureRandom(); 

        byte[] iv = new byte[MIDA_IV]; 

        random.nextBytes(iv); 

        return iv; 

    }

    private  SecretKeySpec generaHash(String clau) throws Exception{ 
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH); 

        byte[] hash = digest.digest(clau.getBytes(StandardCharsets.UTF_8)); 
    
        return new SecretKeySpec(hash, ALGORISME_XIFRAT); 
    }


}
