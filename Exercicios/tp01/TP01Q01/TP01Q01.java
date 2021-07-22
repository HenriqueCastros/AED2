import java.io.*;
import java.nio.charset.*;

public class TP01Q01 {

    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * isPalin - descobre se e palindromo
    */
    public static boolean isPalin(String s){
        int i = 0;
        boolean con = true;

        con = true;
        i = 0;
        while((i <= s.length()/2) && (con)){
            if(s.charAt(i) != s.charAt(s.length()-i-1))
                con = false;
            i++;
        }
        return con;
    }


    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String val = "";
        

        try {
            val = in.readLine();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        while ( !isFim(val)) {
            if (isPalin(val))
                System.out.println("SIM");
            else
                System.out.println("NAO");

            try {
                val = in.readLine();
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}