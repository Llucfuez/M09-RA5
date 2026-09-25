import java.util.Random;

public class Monoalfabetic{
    
    char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 
                          'E', 'É', 'È', 'F', 'G', 'H', 'I',
                          'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 
                          'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 
                          'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
                          'V', 'W', 'X', 'Y', 'Z'};
    
    char[] alfabetPermutat;

    public Monoalfabetic(){
        alfabetPermutat = permutaAlfabet(alfabet);
    }
    
    public char[] permutaAlfabet(char[] alfabet){

        char[] nouAlfabet = alfabet.clone();
        Random random = new Random();

        for (int i = 0; i < nouAlfabet.length; i++){
            
            int j = i + random.nextInt(nouAlfabet.length - i);
                
            char temporal = nouAlfabet[i];
            nouAlfabet[i] = nouAlfabet[j];
            nouAlfabet[j] = temporal;

            
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



    
    public static void main(String[] args){
        Monoalfabetic monoalfabetic = new Monoalfabetic();

        String[] exemples = {"Bona tarda", "Demà passat", "Mola,Pila"};
        String[] sortides = {monoalfabetic.xifraMonoAlfa(exemples[0]) + "", monoalfabetic.xifraMonoAlfa(exemples[1]) + "", monoalfabetic.xifraMonoAlfa(exemples[2]) + ""};


        System.out.println("XIFRA");
        System.out.print(exemples[0] + "-->");
        System.out.println(monoalfabetic.xifraMonoAlfa(exemples[0]));

        System.out.print(exemples[1] + "-->");
        System.out.println(monoalfabetic.xifraMonoAlfa(exemples[1]));

        System.out.print(exemples[2] + "-->");
        System.out.println(monoalfabetic.xifraMonoAlfa(exemples[2]));

        System.out.println();
        
        System.out.println("DESXIFRA");
        
        System.out.print(sortides[0] + "-->");
        System.out.println(monoalfabetic.desxifraMonoAlfa(sortides[0]));

        System.out.print(sortides[1] + "-->");
        System.out.println(monoalfabetic.desxifraMonoAlfa(sortides[1]));

        System.out.print(sortides[2] + "-->");
        System.out.println(monoalfabetic.desxifraMonoAlfa(sortides[2]));

        


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

    public char asignaNouCaracter(int posicio, boolean esMajuscula, boolean estemIncriptant){ // CANVIAR
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