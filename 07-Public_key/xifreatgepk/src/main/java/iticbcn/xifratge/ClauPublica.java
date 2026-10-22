package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ClauPublica {
    
    public KeyPair generaParellClausRSA() throws Exception{}

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {}

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception {}

}
