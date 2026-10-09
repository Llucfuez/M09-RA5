import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte [MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";
    
    
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
        "Hola Andrés cómo está tu cuñado",
        "Àgora illa Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            
            byte[] bXifrats = null;
            String desxifrat = "";

            try{
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());   
            }

            System.out.println("---- -");
            System.out.println("Msg:" + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);    
        }
        
    }


    public static byte[] xifraAES(String msg, String clau) throws Exception {

        
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

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
    
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

    private static byte[] generaIv(){
        SecureRandom random = new SecureRandom(); 

        byte[] iv = new byte[MIDA_IV]; 

        random.nextBytes(iv); 

        return iv; 

    }

    private static SecretKeySpec generaHash(String clau) throws Exception{ 
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH); 

        byte[] hash = digest.digest(clau.getBytes(StandardCharsets.UTF_8)); 
    
        return new SecretKeySpec(hash, ALGORISME_XIFRAT); 
    }


}
