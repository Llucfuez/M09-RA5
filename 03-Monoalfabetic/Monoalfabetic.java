public class Monoalfabetic{
    
    char[][] alfabets = {{'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 
                          'E', 'É', 'È', 'F', 'G', 'H', 'I',
                          'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 
                          'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 
                          'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
                          'V', 'W', 'X', 'Y', 'Z'}, 
                         
                         {'Ú', 'Ç', 'Q', 'Ó', 'E', 'W', 'Ì', 
                          'Ù', 'P', 'G', 'H', 'N', 'U', 'O', 
                          'Z', 'Ò', 'Á', 'D', 'C', 'Ñ', 'Y', 
                          'I', 'T', 'M', 'L', 'Ї', 'X', 'Ü', 
                          'É', 'J', 'À', 'S', 'Í', 'È', 'B', 
                          'F', 'V', 'A', 'K', 'R'}};

    public StringBuffer xifraMonoAlfa(String cadena){
        StringBuffer xifrat = new StringBuffer();
        char caracter;
        boolean esMajuscula;

        for(int i = 0; i < cadena.length(); i++){
            caracter = cadena.charAt(i);
            

            int posicio = trobaPosicio(caracter, 0);
            if (posicio == -1){
                xifrat.append(caracter);
                continue;
            }

            esMajuscula = Character.isUpperCase(caracter);
            caracter = asignaNouCaracter(posicio, esMajuscula, 1);
            xifrat.append(caracter);
        }

        return xifrat;
    }

    public StringBuffer desxifraMonoAlfa(String cadena){
        StringBuffer desxifrat = new StringBuffer();
        char caracter;
        boolean esMajuscula;

        for(int i = 0; i < cadena.length(); i++){
            caracter = cadena.charAt(i);
            

            int posicio = trobaPosicio(caracter, 1);
            if (posicio == -1){
                desxifrat.append(caracter);
                continue;
            }

            esMajuscula = Character.isUpperCase(caracter);
            caracter = asignaNouCaracter(posicio, esMajuscula, 0);
            desxifrat.append(caracter);
        }

        return desxifrat;
    }



    
    public static void main(String[] args){
        System.out.println("Xifrat:");

        Monoalfabetic monoalfabetic = new Monoalfabetic();

        System.out.print("Test 01 àrbritre, coixí, Perímetre ->    ");
        System.out.println(monoalfabetic.xifraMonoAlfa("Test 01 àrbritre, coixí, Perímetre"));

        System.out.print("Test 02 Taüll, DÍA, año ->    ");
        System.out.println(monoalfabetic.xifraMonoAlfa("Test 02 Taüll, DÍA, año"));

        System.out.print("Test 03 Peça, Òrrius, Bòvila ->    ");
        System.out.println(monoalfabetic.xifraMonoAlfa("Test 03 Peça, Òrrius, Bòvila"));


        System.out.println();
        System.out.println("desxifrat:");

        System.out.print("Àùjà 01 qéóéoàéù, emoaz, Xùézyùàéù    ->");
        System.out.println(monoalfabetic.desxifraMonoAlfa("Àùjà 01 qéóéoàéù, emoaz, Xùézyùàéù"));

        System.out.print("Àùjà 02 Àúbñň, ÌzÚ, útm    ->");
        System.out.println(monoalfabetic.desxifraMonoAlfa("Àùjà 02 Àúbñň, ÌzÚ, útm"));

        System.out.print("Àùjà 03 Xùwú, Ïééosj, Óïfoñú    ->");
        System.out.println(monoalfabetic.desxifraMonoAlfa("Àùjà 03 Xùwú, Ïééosj, Óïfoñú"));
    }

    public int trobaPosicio(char caracter, int esEncriptacio){ //esEncriptacio: 0=Encriptar, 1=desEncriptar
        
        if(Character.isLetter(caracter) == false){
            return -1;        
        }
        
        char caracterAlfavet;
        caracter = Character.toUpperCase(caracter);

        for (int i = 0; i < alfabets[esEncriptacio].length; i++){
            caracterAlfavet = alfabets[esEncriptacio][i];
            
            
            if (caracter == caracterAlfavet){
                return i;
            }
        }

        return -1;
    }

    public char asignaNouCaracter(int posicio, boolean esMajuscula, int esEncriptacio){ //esEncriptacio: 0=desEncripta , 1=Encriptar
        
        char nouCaracter = alfabets[esEncriptacio][posicio];

        if (esMajuscula){
            return nouCaracter;
        } else {
            return Character.toLowerCase(nouCaracter);
        }
    }
}