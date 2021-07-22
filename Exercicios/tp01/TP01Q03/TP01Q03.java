import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.*;
import java.util.Random;

public class TP01Q03 {
    
    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * cesarCypher - faz o ciframento de Cesar
    */
    public static String cesarCypher(String s){
        String aux = "";

        for (int i = 0; i < s.length(); i++) {
            aux += (char)((int)s.charAt(i) + 3);
        }

        return aux;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String val = "";

        try {
            val = in.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while ( !isFim(val)) {
            MyIO.println(cesarCypher(val));
            try {
                val = in.readLine();
            } catch (Exception e) {
                MyIO.println(e.getMessage());
            }
        }

        in.close();
    }

}