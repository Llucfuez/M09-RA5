package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{ 
    
    Random random;
    
    public void initRandom(long clau){
        random = new Random(clau);
    }
    
     char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 
                          'E', 'É', 'È', 'F', 'G', 'H', 'I',
                          'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 
                          'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 
                          'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
                          'V', 'W', 'X', 'Y', 'Z'};
    
     char[] alfabetPermutat;

     @Override 
     public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{

        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e){
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        String xifrat = xifraPoliAlfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
     }

     @Override 
     public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e){
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }

        return desxifraPoliAlfa(xifrat.toString());
     }
    
        
    public  String xifraPoliAlfa(String msg){ 
        String xifrat = "";
        char caracter;
        boolean esMajuscula;

        for (int i = 0; i < msg.length(); i++){
            caracter = msg.charAt(i);
            permutaAlfabet();
            int posicio = trobaPosicio(caracter, true);

            if (posicio == -1){
                xifrat = xifrat + caracter;
                continue;
            }


            esMajuscula = Character.isUpperCase(caracter);
            char nouCaracter = asignaNouCaracter(posicio, esMajuscula, true);

            xifrat = xifrat + nouCaracter;
                
        }

        return xifrat;
    }

   public  String desxifraPoliAlfa(String msgXifrat){
        String desxifrat = "";
        char caracter;
        boolean esMajuscula;

        for (int i = 0; i < msgXifrat.length(); i++){

            caracter = msgXifrat.charAt(i);
            
             permutaAlfabet();
            int posicio = trobaPosicio(caracter, false);
            
            if (posicio == -1){
                desxifrat = desxifrat + caracter;
                continue;
            }
            
           

            esMajuscula = Character.isUpperCase(caracter);
            char nouCaracter = asignaNouCaracter(posicio, esMajuscula, false);

            desxifrat = desxifrat + nouCaracter;
            
        }

        return desxifrat;
    }

    public void permutaAlfabet(){
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

    public  int trobaPosicio(char caracter, boolean estemIncriptant){
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

    public  char asignaNouCaracter(int posicio, boolean esMajuscula, boolean estemIncriptant){ 
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