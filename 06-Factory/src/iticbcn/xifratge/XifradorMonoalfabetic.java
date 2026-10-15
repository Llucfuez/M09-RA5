package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.charset.StandardCharsets;

public class XifradorMonoalfabetic implements Xifrador{
    
    final static char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 
                          'E', 'É', 'È', 'F', 'G', 'H', 'I',
                          'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 
                          'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 
                          'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
                          'V', 'W', 'X', 'Y', 'Z'};
    
    char[] alfabetPermutat;

    public XifradorMonoalfabetic(){
        alfabetPermutat = permutaAlfabet(alfabet);
    }

    @Override 
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String xifrat = xifraMonoAlfa(msg).toString();
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat textXifrat, String clau) throws ClauNoSuportada{
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }

        return desxifraMonoAlfa(textXifrat.toString()).toString();
    }

    
    public char[] permutaAlfabet(char[] alfabet){

        ArrayList<Character> llista = new ArrayList<>();

        for (int i = 0; i < alfabet.length; i++) {
            llista.add(alfabet[i]);
        }

        Collections.shuffle(llista);

        char[] nouAlfabet = new char[alfabet.length];

        for (int i = 0; i < llista.size(); i++) {
            nouAlfabet[i] = llista.get(i);
        }

        return nouAlfabet;

    }

    public StringBuffer xifraMonoAlfa(String cadena){
        StringBuffer xifrat = new StringBuffer();
        char caracter;
        boolean esMajuscula;

        for (int i = 0; i < cadena.length(); i++){

            caracter = cadena.charAt(i);
            int posicio = trobaPosicio(caracter, true);

            if (posicio == -1){
                xifrat.append(caracter);
                continue;
            }

            esMajuscula = Character.isUpperCase(caracter);
            char nouCaracter = asignaNouCaracter(posicio, esMajuscula, true);

            xifrat.append(nouCaracter);
            
        }

        return xifrat;
        
    }

    public StringBuffer desxifraMonoAlfa(String cadena){
        StringBuffer desxifrat = new StringBuffer();
        char caracter;
        boolean esMajuscula;

        for (int i = 0; i < cadena.length(); i++){

            caracter = cadena.charAt(i);
            int posicio = trobaPosicio(caracter, false);

            if (posicio == -1){
                desxifrat.append(caracter);
                continue;
            }

            esMajuscula = Character.isUpperCase(caracter);
            char nouCaracter = asignaNouCaracter(posicio, esMajuscula, false);

            desxifrat.append(nouCaracter);
            
        }

        return desxifrat;
    }


    public int trobaPosicio(char caracter, boolean estemIncriptant){
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

    public char asignaNouCaracter(int posicio, boolean esMajuscula, boolean estemIncriptant){ 
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