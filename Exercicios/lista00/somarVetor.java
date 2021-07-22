import java.util.Scanner;
public class somarVetor {
    public static int somar(int[] v){
        int soma = 0;
        for(int i = 0; i< v.length; i++){
            soma+= v[i];
        }
        return soma;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        int length = input.nextInt();
        int v[] = new int [length];

        for(int i = 0; i < v.length; i++){
            v[i] = input.nextInt();
        }
        System.out.println("SOMA = " + somar(v)); 
        input.close();
    }
}
