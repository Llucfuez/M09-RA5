public class RotX {
    
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

    public StringBuffer xifraRotX(String cadena, int desplaçament){
        
        char caracter;
        char nouValor;
        StringBuffer xifrat = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++){
            caracter = cadena.charAt(i);

            int posicio = trobaPosicio(caracter);

            if(posicio == -1){
                xifrat.append(caracter);
            } else {
                boolean esMajuscula = Character.isUpperCase(caracter);
                nouValor = asignaNouCaracter(posicio, desplaçament, esMajuscula, true);
                xifrat.append(nouValor);        
            }

        }
        return xifrat;
    }

    public StringBuffer desxifraRotX(String cadena, int desplaçament){ //Revisar
        char caracter;
        char nouValor;
        StringBuffer desxifrat = new StringBuffer();

        for (int i = 0; i < cadena.length(); i++){
            caracter = cadena.charAt(i);

            int posicio = trobaPosicio(caracter);

            if(posicio == -1){
                desxifrat.append(caracter);
            } else {
                boolean esMajuscula = Character.isUpperCase(caracter);
                nouValor = asignaNouCaracter(posicio, desplaçament, esMajuscula, false);
                desxifrat.append(nouValor);        
            }

        }
        return desxifrat;

    }

    public int trobaPosicio(char caracter){    
        for(int i = 0; i < minuscules.length; i++){
            if(caracter == minuscules[i]){
                return i;
            }
        }
        
        for(int i = 0; i < majuscules.length; i++){
            if(caracter == majuscules[i]){
                return i;
            }
        }
        return -1;
    }

    public char asignaNouCaracter(int posicio, int desplaçament, boolean esMajuscula, boolean xifra){
        int valor;
        if (xifra){
            valor = (posicio + desplaçament) % minuscules.length;  
        } else {
            valor = (posicio - desplaçament + majuscules.length) % minuscules.length;
        }

        if (esMajuscula){
            return majuscules[valor];
        } else {
            return minuscules[valor];     
        }
    }

    public String[] forcaBrutaRotX(String cadena){
        String[] resultats = new String[majuscules.length];
        
        for(int i = 0; i < majuscules.length; i++){
            resultats[i] = "(" + i + ") -> " + desxifraRotX(cadena, i);
        }

        return resultats;

    }

    public static void main(String[] args){
        RotX rotX = new RotX();

        System.out.println("Xifrat");
        System.out.println("------");
        System.out.println("(0)-ABC  ==>  " + rotX.xifraRotX("ABC", 0));
        System.out.println("(2)-XYZ  ==>  " + rotX.xifraRotX("XYZ", 2));
        System.out.println("(4)-Hola, Mr. calçot  ==>  " + rotX.xifraRotX("Hola, Mr. calçot", 4));
        System.out.println("(6)-Perdó, per tu què és  ==>  " + rotX.xifraRotX("Perdó, per tu què és", 6));
        System.out.println();

        System.out.println("Desxiftrat");
        System.out.println("------");
        System.out.println("(0)ABC  ==>  " + rotX.desxifraRotX("ABC", 0));
        System.out.println("(2)ZAÁ  ==>  " + rotX.desxifraRotX("ZAÁ", 2));
        System.out.println("(4)Ïqoc, Óú. écoèqü  ==>  " + rotX.desxifraRotX("Ïqoc, Óú. écoèqü", 4));
        System.out.println("(6)Úiüht, úiü wx ùxì ív?  ==>  " + rotX.desxifraRotX("Úiüht, úiü wx ùxì ív?", 6));
        System.out.println();

        
        System.out.println("Missatge xifrat: Úiüht, úiü wx ùxì ív?");
        System.out.println("---------------------");
        String[] resultats = rotX.forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");

        for(int i = 0; i < resultats.length; i++){
            System.out.println(resultats[i]);
        }
    }
    
}
