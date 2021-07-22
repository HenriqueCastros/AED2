import java.io.*;
import java.nio.charset.*;
import java.util.Random;

public class TP01Q04 {
    private static Random gen = new Random();

    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * randomAlter - altera aleatoriamente um char por outro
    */
    public static String randomAlter(String s){ 
        String aux = "";
        char find = (char) ('a' + (Math.abs(gen.nextInt())%26));
        char set = (char) ('a' + (Math.abs(gen.nextInt())%26));
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == find) 
                aux += set;
            else 
                aux += s.charAt(i);
        }
        return aux;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String val = "";
        gen.setSeed(4);

        try {
            val = in.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        while (!isFim(val)) {
            MyIO.println(randomAlter(val));
            try {
                val = in.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        in.close();
    }

}