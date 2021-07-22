import java.util.Scanner;
import java.util.Arrays;

public class maiorMenorVet {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int v[] = new int[length];

        for(int i = 0; i < v.length; i++){
            v[i] = input.nextInt();
        }

        Arrays.sort(v);

        // for(int i = 0; i < v.length; i++){
        //     System.out.print(v[i] + " ");
        // }
     
        System.out.println("MAIOR = " + v[v.length-1]);
        System.out.println("MENOR = " + v[0]);

        input.close();
    }
}
