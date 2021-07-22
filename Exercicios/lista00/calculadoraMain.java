import java.util.Scanner;
public class calculadoraMain {
   public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int a = input.nextInt();
    int b = input.nextInt();

    System.out.println("SOMA = " + (a+b));
    System.out.println("SUBTRACAO = " + (a-b));
    System.out.println("MULTIPLICACAO = " + (a*b));

    if(b==0){
        System.out.println("DIVISAO = INDETERMINADO");
    }else{
        double d = Double.valueOf(a)/Double.valueOf(b);
        System.out.printf("DIVISAO = %.2f", d);
    }

    input.close(); 
   } 
}
