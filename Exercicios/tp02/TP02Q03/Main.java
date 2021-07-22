import java.io.FileWriter;

public class Main {

    public static int count = 0;

    public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static boolean cotains(String val[], String find){
        boolean found = false;
        for(int i = 0; i < val.length && !found; i++){
            found = find.equals(val[i]);
            count++;
        }
        return found;
    }

    public static void main(String[] args) throws Exception {
        double start = System.currentTimeMillis();
        String find = "";
        String temp = "";
        String aux[] = new String[500];
        int i = 0;

        //Capturar todas as IDs da entrada padrao
        try {
            aux[i] = MyIO.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        while (!isFim(aux[i])) {
            i++;
            try {
                aux[i] = MyIO.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            find = MyIO.readLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        while (!isFim(find)) {
            try {
                temp = cotains(aux, find) ? "SIM" : "NAO";
                find = MyIO.readLine();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(temp);
        }
        FileWriter myWriter = new FileWriter("matrícula_sequencial.txt");
        myWriter.write("711260\t" + ((System.currentTimeMillis()-start)/1000) + "\t" + count );
        myWriter.close();
    } 

}