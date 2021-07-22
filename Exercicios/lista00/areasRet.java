import java.util.Scanner;
public class areasRet {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int basIni = input.nextInt();
        int basFin = input.nextInt();
        int heiIni = input.nextInt();
        int heiFin = input.nextInt();
        int aux = 0;

        if(basIni > basFin){
            aux = basIni;
            basIni = basFin;
            basFin = aux;
        }
        if(heiIni > heiFin){
            aux = heiIni;
            heiIni = heiFin;
            heiFin = aux;
        }
        

        for(int i = basIni; i<=basFin; i++){
            for(int j = heiIni; j<=heiFin; j++){
                System.out.println("BASE = "+ i +" - ALTURA = "+ j +" - AREA = "+ i*j +" - PERIMETRO = "+ (i+j)*2 +"");
            }
        }

        input.close();
    }
}
