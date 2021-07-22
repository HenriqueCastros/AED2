import java.util.Scanner;
public class imparOuPar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();

        if (a == 0){
            System.out.println("PAR e NEUTRO");
        }else if(Math.abs(a)% 2 == 1){
            if(a > 0)
            {
                System.out.println("IMPAR e POSITIVO");
            }else{
                System.out.println("IMPAR e NEGATIVO");
            }
        } else{
            if(a > 0)
            {
                System.out.println("PAR e POSITIVO");
            }else{
                System.out.println("PAR e NEGATIVO");
            }
        }
        input.close();
    }
}
