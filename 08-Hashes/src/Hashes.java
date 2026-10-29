import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;

    private static final String CHARSET = "abcdefABCDEF1234567890!";
    private static final int MAX_LEN = 6;

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(pw.getBytes());

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] bytes = skf.generateSecret(spec).getEncoded();

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    private boolean comprova(String alg, String candidat, String salt, String hashObjectiu) throws Exception {
        String hashCandidat = alg.equals("SHA-512")
                ? getSHA512AmbSalt(candidat, salt)
                : getPBKDF2AmbSalt(candidat, salt);

        return hashCandidat.equals(hashObjectiu);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;
        char[] pw = new char[MAX_LEN];
        int n = CHARSET.length();

        for (int i0 = 0; i0 < n; i0++) {
            pw[0] = CHARSET.charAt(i0);
            npass++;
            if (comprova(alg, new String(pw, 0, 1), salt, hash)) return new String(pw, 0, 1);

            for (int i1 = 0; i1 < n; i1++) {
                pw[1] = CHARSET.charAt(i1);
                npass++;
                if (comprova(alg, new String(pw, 0, 2), salt, hash)) return new String(pw, 0, 2);

                for (int i2 = 0; i2 < n; i2++) {
                    pw[2] = CHARSET.charAt(i2);
                    npass++;
                    if (comprova(alg, new String(pw, 0, 3), salt, hash)) return new String(pw, 0, 3);

                    for (int i3 = 0; i3 < n; i3++) {
                        pw[3] = CHARSET.charAt(i3);
                        npass++;
                        if (comprova(alg, new String(pw, 0, 4), salt, hash)) return new String(pw, 0, 4);

                        for (int i4 = 0; i4 < n; i4++) {
                            pw[4] = CHARSET.charAt(i4);
                            npass++;
                            if (comprova(alg, new String(pw, 0, 5), salt, hash)) return new String(pw, 0, 5);

                            for (int i5 = 0; i5 < n; i5++) {
                                pw[5] = CHARSET.charAt(i5);
                                npass++;
                                if (comprova(alg, new String(pw, 0, 6), salt, hash)) return new String(pw, 0, 6);
                            }
                        }
                    }
                }
            }
        }
        return null; // no trobada
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;

        long millis = diff % 1000;
        long segons = (diff / 1000) % 60;
        long minuts = (diff / (1000 * 60)) % 60;
        long hores  = (diff / (1000 * 60 * 60)) % 24;
        long dies   = diff / (1000 * 60 * 60 * 24);

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                dies, hores, minuts, segons, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}