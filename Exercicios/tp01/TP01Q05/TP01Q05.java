import java.io.*;
import java.nio.charset.*;

public class TP01Q05 {

    /**
     * isNull - descobre se String == "0"
    */
    public static boolean isNull(String s){
        return (s.length() == 1 && s.charAt(0) == '0');
    }


    /**
     * processAlg - processa expressao algebrica booleana
     *  funciona de maneira recursiva
     *  @return "1" ou "0"
     */
    public static String processAlg(String std) {
        String str = std;
        String aux = "";
        int n = (int)str.charAt(0) - '0';
        int A, B, C, res;
        A = B = C = res = 0;
        boolean done = false;
        char start = '_';
        char end = '_';
        int sta = 0;
        int e = 0;
        int i = 0;

        /*Inicializazao de var A, B e C, 
          baseado na qtd de varaiveis n*/
        if (n == 2) {
            A = ((int)str.charAt(2) - '0');
            B = ((int)str.charAt(4) - '0');
        } else if (n == 3){
            A = ((int)str.charAt(2) - '0');
            B = ((int)str.charAt(4) - '0');
            C = ((int)str.charAt(6) - '0');
        } else if (n == 0){
            done = true;
        }

        do {
            i=0;
            end = '_';
            sta = 0;
            /*Descobre posicoes do primeiro parentesis mais interno
            Ex: and(not(A)) -> retorna posicoes 7 e 9
                01234567890 */
            while (end == '_' && i < str.length()) {
                if (str.charAt(i) == '(') {
                    start = str.charAt(i);
                    sta = i;
                }else if(str.charAt(i) == ')'){
                    end = str.charAt(i);
                    e = i;
                }
                i++;
            }

            /*Avalia string que vem antes do primeiro parentesis
            e apartir da avaliacao, faz os calculos booleanos necessarios
            */
            if(sta == 0){
                done = true;
            }else if (str.charAt(sta - 1) == 't') {
                if(str.charAt(sta + 1) == 'A')
                    res = (A == 1 ? 0 : 1);
                else if (str.charAt(sta + 1) == 'B')
                    res = (B == 1 ? 0 : 1);
                else if (str.charAt(sta + 1) == 'C')
                    res = (C == 1 ? 0 : 1);
                else if (str.charAt(sta + 1) == '1')
                    res = 0;
                else if (str.charAt(sta + 1) == '0') 
                    res = 1; 
                sta -= 3;
            } else if (str.charAt(sta - 1) == 'd'){
                res = 1;
                for ( i = sta; i <= e; i++) {
                    if(str.charAt(i) == 'A')
                        res *= A;
                    else if (str.charAt(i) == 'B')
                        res *= B;
                    else if (str.charAt(i) == 'C')
                        res *= C;
                    else if (str.charAt(i) == '1')
                        res *= 1;
                    else if (str.charAt(i) == '0') 
                        res *= 0; 
                }
                sta = sta - 3;
            } else if (str.charAt(sta - 1) == 'r'){
                res = 0;
                for ( i = sta; i <= e; i++) {
                    if(str.charAt(i) == 'A')
                        res += A;
                    else if (str.charAt(i) == 'B')
                        res += B;
                    else if (str.charAt(i) == 'C')
                        res += C;
                    else if (str.charAt(i) == '1')
                        res += 1;
                    else if (str.charAt(i) == '0') 
                        res += 0;
                }
                res = (res > 1 ? 1 : res);
                sta -= 2;
            }
            /*Passa a string a limpo*/
            if (!done) {
                aux = "";
                for (i = 0; i < str.length() ; i++) {
                    if(i < sta || i > e){
                        aux += str.charAt(i);
                    }else if (i == sta) {
                        aux += res;
                    }
                }
                str = aux;
            } else{
                aux = "" + str.charAt(n*2 + 2);
            }
        } while(!done);
        return aux;
    }
    

    public static void main(String[] args) throws Exception  {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        String val = "";
        try {
            val = in.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        while( !isNull(val)){
            System.out.println(processAlg(val));
            try {
                val = in.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        in.close();
    }

}