import java.io.*;
import java.net.*;

class Main {
   //função que faz a requisição e pega todo o html colocando-o em uma variavel do tipo string
   public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } 

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }

   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static boolean Consoante(char c){
      return ((c != 'a' || c != 'e' || c != 'i' || c != 'o' || c != 'u') && (c >= 'a' && c <= 'z'));
   }

   //função que irá passar por todo o html e contar o numero de cada ocorrencia relatada
   public static void Contador(String html, int[]cont){
      for(int i = 0; i < html.length(); i++){
         if(html.charAt(i) == 'a')
            cont[0]++;
         else if(html.charAt(i) == 'e')
            cont[1]++;
         else if(html.charAt(i) == 'i')
            cont[2]++;
         else if(html.charAt(i) == 'o')
            cont[3]++;
         else if(html.charAt(i) == 'u')
            cont[4]++;
         else if((int)html.charAt(i) == 225)
            cont[5]++;
         else if((int)html.charAt(i) == 233)
            cont[6]++;
         else if((int)html.charAt(i) == 237)
            cont[7]++;
         else if((int)html.charAt(i) == 243)
            cont[8]++;
         else if((int)html.charAt(i) == 250)
            cont[9]++;
         else if((int)html.charAt(i) == 224)
            cont[10]++;
         else if((int)html.charAt(i) == 232)
            cont[11]++;
         else if((int)html.charAt(i) == 236)
            cont[12]++;
         else if((int)html.charAt(i) == 242)
            cont[13]++;
         else if((int)html.charAt(i) == 249)
            cont[14]++;
         else if((int)html.charAt(i) == 227)
            cont[15]++;
         else if((int)html.charAt(i) == 245)
            cont[16]++;
         else if((int)html.charAt(i) == 226)
            cont[17]++;
         else if((int)html.charAt(i) == 234)
            cont[18]++;
         else if((int)html.charAt(i) == 238)
            cont[19]++;
         else if((int)html.charAt(i) == 244)
            cont[20]++;
         else if((int)html.charAt(i) == 251)
            cont[21]++;
         else if(Consoante(html.charAt(i)) == true)
            cont[22]++;
         if(html.charAt(i) == '<')
            if(html.charAt(i+1) == 'b')
               if(html.charAt(i+2) == 'r')
                  if(html.charAt(i+3) == '>'){
                     cont[23]++;
                     i += 3;
                  }
         if(html.charAt(i) == '<')
            if(html.charAt(i+1) == 't')
               if(html.charAt(i+2) == 'a')
                  if(html.charAt(i+3) == 'b')
                     if(html.charAt(i+4) == 'l')
                        if(html.charAt(i+5) == 'e')
                           if(html.charAt(i+6) == '>'){
                              cont[24]++;
                              i += 6;
                           }
            
      }
   }

   //função para printar na tela
   public static void MostrarTela(String nome, int [] v){
      MyIO.println("a("+v[0]+") e("+v[1]+") i("+v[2]+") o("+v[3]+") u("+v[4]+") á("+v[5]+") é("+v[6]+") í("+v[7]+") ó("+v[8]+") ú("+v[9]+") à("+v[10]+") è("+v[11]+") ì("+v[12]+") ò("+v[13]+") ù("+v[14]+") ã("+v[15]+") õ("+v[16]+") â("+v[17]+") ê("+v[18]+") î("+v[19]+") ô("+v[20]+") û("+v[21]+") consoante("+v[22]+") <br>("+v[23]+") <table>("+v[24]+") "+nome);
   }

   public static void main(String[] args) {
      String endereco, html, nome;
      int [] cont;

      nome = MyIO.readLine();
      do{
         //pega a URL, inicia um novo array de tamanho 25, e chama a funçao de contar quantos de cada caractere e a função de printa-los na tela
         endereco = MyIO.readLine();
         cont = new int[25];
         html = getHtml(endereco);
         Contador(html, cont);
         MostrarTela(nome, cont);
         nome = MyIO.readLine();
      }while(isFim(nome) == false);
   }
}
