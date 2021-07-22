import java.util.Scanner;
public class triangulos {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a  = input.nextInt();
        int b  = input.nextInt();
        int c  = input.nextInt();

        if(a <= (b + c) && b <= (a + c) && c <= (a + b)){
            if(a == b && b == c ){
                System.out.println("EQUILATERO");
            } else if (a == b || b == c || a == c){
                System.out.println("ISOSCELES");
            }else{
                System.out.println("ESCALENO");
            }
        }else{
            System.out.println("NAO FORMA UM TRIANGULO");
        }
        
        input.close();
    }
}
