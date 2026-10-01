public class PoliAlfabetic { //ACABAR
    char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 
                          'E', 'É', 'È', 'F', 'G', 'H', 'I',
                          'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 
                          'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 
                          'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
                          'V', 'W', 'X', 'Y', 'Z'};
    
    char[] alfabetPermutat;                      
    
    
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                         "Test 02 Taüll, DÍA, año",
                         "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.printIn("Xifratge:\n--------");
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
        
        public static String xifraPoliAlfa( String msg){ 

        }

        public static String desxifraPoliAlfa(String msgXifrat){

        }

        public static void permutaAlfabet(){

        }
    
    }
}