import java.io.*;
import java.nio.charset.*;
import java.net.URL;

public class TP01Q08 {

    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * printData - 'printa' dados
    */
    public static void printData(String nome, int [] data){
      MyIO.println("a("+data[0]+") e("+data[1]+") i("+data[2]+") o("+data[3]+") u("+data[4]+") á("+data[5]+") é("+data[6]+") í("+data[7]+") ó("+data[8]+") ú("+data[9]+") à("+data[10]+") è("+data[11]+") ì("+data[12]+") ò("+data[13]+") ù("+data[14]+") ã("+data[15]+") õ("+data[16]+") â("+data[17]+") ê("+data[18]+") î("+data[19]+") ô("+data[20]+") û("+data[21]+") consoante("+data[22]+") <br>("+data[23]+") <table>("+data[24]+") "+nome);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
        
        String end = ""; 
        String nome = ""; 
        String content = "";
        int temp = ' ';

        try {
            nome = in.readLine();
            end = in.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        while (!isFim(nome)) {
            
            int cont[] = new int[25];
            URL url = new URL(end);
            Reader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader webReader = new BufferedReader(inputStreamReader);
            content = webReader.readLine();
            while (content != null) {
                for (int i = 0; i < content.length(); i++) {
                    temp = (int)content.charAt(i);
                    switch (temp) {
                        case 97:
                            cont[0]++;
                            break;
                        case 101:
                            cont[1]++;
                            break;
                        case 105:
                            cont[2]++;
                            break;
                        case 111:
                            cont[3]++;
                            break;
                        case 117:
                            cont[4]++;
                            break;
                        case 225:
                            cont[5]++;
                            break;
                        case 233:
                            cont[6]++;
                            break;
                        case 237:
                            cont[7]++;
                            break;
                        case 243:
                            cont[8]++;
                            break;
                        case 250:
                            cont[9]++;
                            break;
                        case 224:
                            cont[10]++;
                            break;
                        case 232:
                            cont[11]++;
                            break;
                        case 236:
                            cont[12]++;
                            break;
                        case 242:
                            cont[13]++;
                            break;
                        case 249:
                            cont[14]++;
                            break;
                        case 227:
                            cont[15]++;
                            break;
                        case 245:
                            cont[16]++;
                            break;
                        case 226:
                            cont[17]++;
                            break;
                        case 234:
                            cont[18]++;
                            break;
                        case 238:
                            cont[19]++;
                            break;
                        case 244:
                            cont[20]++;
                            break;
                        case 251:
                            cont[21]++;
                            break;
                        default:
                            if(content.charAt(i) == '<' && content.charAt(i+1) == 'b' && content.charAt(i+2) == 'r' 
                            && content.charAt(i+3) == '>'){
                                cont[23]++;
                                i += 3;
                            }else if(content.charAt(i) == '<'  && content.charAt(i+1) == 't' && content.charAt(i+2) == 'a' && 
                                    content.charAt(i+3) == 'b' && content.charAt(i+4) == 'l' 
                                    && content.charAt(i+5) == 'e'&& content.charAt(i+6) == '>'){
                                cont[24]++;
                                i += 6;
                            }
                            else if( (content.charAt(i) >='a' && content.charAt(i)  <= 'z'))
                                cont[22]++;
                            break;
                    }
                }
                content = webReader.readLine();
            }
            printData(nome, cont);
            try {
                nome = in.readLine();
                end = in.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        in.close();
    }
    
}