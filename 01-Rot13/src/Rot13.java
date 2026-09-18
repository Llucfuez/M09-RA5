public class Rot13{

    char[] majuscules = {
    'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N',
    'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U',
    'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    char[] minuscules = {
    'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
    'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n',
    'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u',
    'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    public String xifraRot13(String cadena){
        String xifrat = "";
        char caracter;
        
        for (int i = 0; i < cadena.length(); i++){
            caracter = cadena.charAt(i);
            if (Character.isLetter(caracter)){
                
                int posicio = trobaPosicio(caracter);

                if(posicio == -1){
                    xifrat = xifrat + caracter;
                } else {
                    posicio = posicio + 13;
                    if (posicio >= minuscules.length){
                        posicio = posicio - minuscules.length;
                    }
                    if (Character.isLowerCase(caracter)){
                        caracter = minuscules[posicio];
                    } else {
                        caracter = majuscules[posicio];
                    }

                }
            }

            xifrat = xifrat + caracter;
        }
        
        return xifrat;
    }

    public String desxifraRot13(String cadena){
        String xifrat = "";
        char caracter;
        
        for (int i = 0; i < cadena.length(); i++){
            caracter = cadena.charAt(i);
            if (Character.isLetter(caracter)){
                
                int posicio = trobaPosicio(caracter);

                if(posicio == -1){
                    xifrat = xifrat + caracter;
                } else {
                    posicio = posicio - 13;
                    if (posicio < 0){
                        posicio = posicio + minuscules.length;
                    }
                    if (Character.isLowerCase(caracter)){
                        caracter = minuscules[posicio];
                    } else {
                        caracter = majuscules[posicio];
                    }

                }
            }

            xifrat = xifrat + caracter;
        }
        
        return xifrat;
    }

    public static void main(String[] args){
        Rot13 rot13 = new Rot13();
        String cadena;

        System.out.println("Xifrat");
        System.out.println("---------");
        System.out.println("ABC  =>  " + rot13.xifraRot13("ABC"));
        System.out.println("XYZ  =>  " + rot13.xifraRot13("XYZ"));
        System.out.println("Hola, Mr. Calçot  =>  " + rot13.xifraRot13("Hola, Mr. Calçot"));
        System.out.println("Perdó, per tu què és?  =>  " + rot13.xifraRot13("Perdó, per tu què és?"));
        
        System.out.println();

        System.out.println("Desxifrat");
        System.out.println("---------");
        System.out.println("IÏJ  =>  " + rot13.desxifraRot13("IÏJ"));
        System.out.println("FGH  =>  " + rot13.desxifraRot13("FGH"));
        System.out.println("Òwúi, Ùá. Jiúkwb  =>  " + rot13.desxifraRot13("Òwúi, Ùá. Jiúkwb"));
        System.out.println("Zmálx, zmá bc acñ nà?  =>  " + rot13.desxifraRot13("Zmálx, zmá bc acñ nà?"));
    }

    public int trobaPosicio(char caracter){

        if (Character.isLowerCase(caracter)){
            for(int i = 0; i < minuscules.length; i++){
                if(caracter == minuscules[i]){
                    return i;
                }
            }
        } else {
            for(int i = 0; i < majuscules.length; i++){
                if(caracter == majuscules[i]){
                    return i;
                }
            }
        }
        return -1;
    }
    
}