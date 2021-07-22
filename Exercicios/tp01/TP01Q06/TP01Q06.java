import java.io.*;
import java.nio.charset.*;

public class TP01Q06 {

    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * isVog - descobre se String e composta por vogais
    */
    public static boolean isVog(String s){
        boolean flag = true;
        int i = 0;
        while(flag && i < s.length()){
            flag = (s.charAt(i) == 'a' || s.charAt(i) == 'A' ||
                    s.charAt(i) == 'e' || s.charAt(i) == 'E' ||
                    s.charAt(i) == 'i' || s.charAt(i) == 'I' ||
                    s.charAt(i) == 'o' || s.charAt(i) == 'O' ||
                    s.charAt(i) == 'u' || s.charAt(i) == 'U' );
            i++;
        }
        return flag;
    }

    /**
     * isCon - descobre se String e composta por consoantes
    */
    public static boolean isCon(String s){
        boolean flag = true;
        int i = 0;
        while(flag && i < s.length()){
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <='Z')) {
                flag = (s.charAt(i) != 'a' && s.charAt(i) != 'A' &&
                        s.charAt(i) != 'e' && s.charAt(i) != 'E' &&
                        s.charAt(i) != 'i' && s.charAt(i) != 'I' &&
                        s.charAt(i) != 'o' && s.charAt(i) != 'O' &&
                        s.charAt(i) != 'u' && s.charAt(i) != 'U' );
            }else
                flag = false;
            
            i++;
        }
        return flag;
    }

    /**
     * isInt - descobre se String forma um int
    */
    public static boolean isInt(String s){
        boolean flag = true;
        flag = ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '-' || s.charAt(0) == '+' );
        int i = 1;
        while(flag && i < s.length()){
            flag = (s.charAt(i) >= '0' && s.charAt(i) <= '9' );
            i++;
        }
        return flag;
    }

    /**
     * isDouble - descobre se String forma um int
    */
    public static boolean isDouble(String s){
        boolean flag = true;
        int countPonto = 0;
        if(s.charAt(0) == '.' || s.charAt(0) == ',')
            countPonto++;
        else 
            flag = ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '-' || s.charAt(0) == '+' );
        int i = 1;
        while(flag && i < s.length() && countPonto <=1){
            if(s.charAt(i) == '.' || s.charAt(i) == ',')
                countPonto++;
            else 
                flag = (s.charAt(i) >= '0' && s.charAt(i) <= '9' );
            i++;
        }
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
            temp = isVog(val) ? "SIM " : "NAO ";
            temp += isCon(val) ? "SIM " : "NAO ";
            temp += isInt(val) ? "SIM " : "NAO ";
            temp += isDouble(val) ? "SIM" : "NAO";
            System.out.println(temp);
            try {
                val = in.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}