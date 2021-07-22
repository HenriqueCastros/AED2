import java.io.*;
import java.nio.charset.*;

public class TP01Q15 {

    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * isVog - descobre se String e composta por vogais
    */
    public static boolean isVog(String s, int pos){
        boolean flag = true;
        flag = (s.charAt(pos) == 'a' || s.charAt(pos) == 'A' ||
                    s.charAt(pos) == 'e' || s.charAt(pos) == 'E' ||
                    s.charAt(pos) == 'i' || s.charAt(pos) == 'I' ||
                    s.charAt(pos) == 'o' || s.charAt(pos) == 'O' ||
                    s.charAt(pos) == 'u' || s.charAt(pos) == 'U' );

        if (pos < (s.length()-1) && flag)
            flag = isVog(s, ++pos);
    
        return flag;
    }

    /**
     * isCon - descobre se String e composta por consoantes
    */
    public static boolean isCon(String s, int pos){
        boolean flag = true;

        if ((s.charAt(pos) >= 'a' && s.charAt(pos) <= 'z') || (s.charAt(pos) >= 'A' && s.charAt(pos) <='Z')) {
                flag = (s.charAt(pos) != 'a' && s.charAt(pos) != 'A' &&
                        s.charAt(pos) != 'e' && s.charAt(pos) != 'E' &&
                        s.charAt(pos) != 'i' && s.charAt(pos) != 'I' &&
                        s.charAt(pos) != 'o' && s.charAt(pos) != 'O' &&
                        s.charAt(pos) != 'u' && s.charAt(pos) != 'U' );
        }else
            flag = false;
                    
        if (pos < (s.length()-1) && flag)
            flag = isCon(s, ++pos);
        return flag;
    }

    /**
     * isInt - descobre se String forma um int
    */
    public static boolean isInt(String s, int pos){
        boolean flag = true;
        if (pos == 0) {
            flag = ((s.charAt(0) >= '0' && s.charAt(0) <= '9') ||
                         s.charAt(0) == '-' || s.charAt(0) == '+' );
        } else
            flag = (s.charAt(pos) >= '0' && s.charAt(pos) <= '9' );
        if(pos < (s.length()-1) && flag)
            flag = isInt(s, ++pos);
        return flag;
    }

    /**
     * isDouble - descobre se String forma um int
    */
    public static boolean isDouble(String s, int pos, int countPonto){
        boolean flag = true;

        if (pos == 0) {
            if(s.charAt(0) == '.' || s.charAt(0) == ',')
                countPonto++;
            else 
                flag = ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '-' || s.charAt(0) == '+' );
        } else{
            if(s.charAt(pos) == '.' || s.charAt(pos) == ',')
                countPonto++;
            else 
                flag = (s.charAt(pos) >= '0' && s.charAt(pos) <= '9');
        }

        if(pos < (s.length()-1) && flag && countPonto <=1)
            flag = isDouble(s, ++pos, countPonto);

        flag = flag&&(countPonto<=1) ? true:false;

        return flag;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String val = "";
        String temp = "";
        try {
            val = in.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        while (!isFim(val)) {
            temp = isVog(val, 0) ? "SIM " : "NAO ";
            temp += isCon(val, 0) ? "SIM " : "NAO ";
            temp += isInt(val, 0) ? "SIM " : "NAO ";
            temp += isDouble(val, 0, 0) ? "SIM" : "NAO";
            System.out.println(temp);
            try {
                val = in.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}