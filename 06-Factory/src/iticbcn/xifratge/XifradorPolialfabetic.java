import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic { 
    
    private static int clauSecreta = 26082006;
    
    static Random random;
    public static void initRandom(int clau){
        random = new Random(clau);
    }
    
    static char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 
                          'E', 'É', 'È', 'F', 'G', 'H', 'I',
                          'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 
                          'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 
                          'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
                          'V', 'W', 'X', 'Y', 'Z'};
    
    static char[] alfabetPermutat;
    
    
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                         "Test 02 Taüll, DÍA, año",
                         "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++){
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }    
        
    public static String xifraPoliAlfa(String msg){ 
        String xifrat = "";
        char caracter;
        boolean esMajuscula;

        for (int i = 0; i < msg.length(); i++){
            caracter = msg.charAt(i);
            int posicio = trobaPosicio(caracter, true);

            if (posicio == -1){
                xifrat = xifrat + caracter;
                continue;
            }

            permutaAlfabet();

            esMajuscula = Character.isUpperCase(caracter);
            char nouCaracter = asignaNouCaracter(posicio, esMajuscula, true);

            xifrat = xifrat + nouCaracter;
                
        }

        return xifrat;
    }

   public static String desxifraPoliAlfa(String msgXifrat){
        String desxifrat = "";
        char caracter;
        boolean esMajuscula;

        for (int i = 0; i < msgXifrat.length(); i++){

            caracter = msgXifrat.charAt(i);
            
            
            if (Character.isLetter(caracter) == false){
                desxifrat = desxifrat + caracter;
                continue;
            }
            
            permutaAlfabet();
            int posicio = trobaPosicio(caracter, false);

            esMajuscula = Character.isUpperCase(caracter);
            char nouCaracter = asignaNouCaracter(posicio, esMajuscula, false);

            desxifrat = desxifrat + nouCaracter;
            
        }

        return desxifrat;
    }

    public static void permutaAlfabet(){
        ArrayList<Character> llista = new ArrayList<>();

        for (int i = 0; i < alfabet.length; i++) {
            llista.add(alfabet[i]);
        }

        Collections.shuffle(llista, random);

        char[] nouAlfabet = new char[alfabet.length];

        for (int i = 0; i < llista.size(); i++) {
            nouAlfabet[i] = llista.get(i);
        }

        alfabetPermutat = nouAlfabet;
    }

    public static int trobaPosicio(char caracter, boolean estemIncriptant){
        if (Character.isLetter(caracter) == false){
            return -1;
        }
            
        caracter = Character.toUpperCase(caracter);
            
        if (estemIncriptant){
            for (int i = 0; i < alfabet.length; i++){
                char caracterAlfabet = alfabet[i];

                if (caracter == caracterAlfabet){
                    return i;
                }
            }
        } else {
            for (int i = 0; i < alfabetPermutat.length; i++){
                char caracterAlfabet = alfabetPermutat[i];

                if (caracter == caracterAlfabet){
                    return i;
                }
            }
        }

        return -1;
        
    }

    public static char asignaNouCaracter(int posicio, boolean esMajuscula, boolean estemIncriptant){ 
        char nouCaracter;
        if(estemIncriptant){
            nouCaracter = alfabetPermutat[posicio]; 
        } else {
            nouCaracter = alfabet[posicio];
        }

        if (esMajuscula){
            return nouCaracter;
        } else {
            return Character.toLowerCase(nouCaracter);
        }
        
    }
    
}