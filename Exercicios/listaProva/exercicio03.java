public class exercicio03{
    //f(n) = n*n = n^2;
    //Complexidade = teta(n^2)
    public static void somarMatriz(int a[][], int b[][], int n){
        int c[][]=new int[n][n];
             
        for(int i=0;i<n;i++){    
            for(int j=0;j<n;j++){    
                c[i][j]=a[i][j]+b[i][j]; 
                System.out.print(c[i][j]+" ");    
            }    
            System.out.println();
        }
    }

    //f(n) = n*n = n^2;
    //Complexidade = teta(n^2)
    public static void diffMatriz(int a[][], int b[][], int n){
        int c[][]=new int[n][n];
             
        for(int i=0;i<n;i++){    
            for(int j=0;j<n;j++){    
                c[i][j]=a[i][j]-b[i][j]; 
                System.out.print(c[i][j]+" ");    
            }    
            System.out.println();
        }
    }

    //f(n) = n*n*n = n^3;
    //Complexidade = teta(n^3)
    public static void multiMatriz(int a[][], int b[][], int n){
        int c[][]=new int[n][n]; 
               
        for(int i=0;i<n;i++){    
            for(int j=0;j<n;j++){    
                c[i][j]=0;      
                for(int k=0;k<n;k++)      
                {      
                    c[i][j]+=a[i][k]*b[k][j];      
                }
                System.out.print(c[i][j]+" ");
            }
            System.out.println();   
        }
    }

    //f(n) = n*n*n = n^3;
    //Complexidade = teta(n^3)
    public static void transMatriz(int a[][], int b[][], int n){
        int c[][]=new int[n][n]; 
               
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                b[i][j] = 0;
                for(int k = 0;k<n;k++){
                    b[i][j]=a[j][i];
                }
                System.out.print(b[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int a[][]={{1,1,1},{2,2,2},{3,3,3}};    
        int b[][]={{1,1,1},{2,2,2},{3,3,3}}; 

        somarMatriz(a, b, 3);
        System.out.println();
        diffMatriz(a, b, 3);
        System.out.println();
        multiMatriz(a, b, 3);
        System.out.println();
        transMatriz(a, b, 3);
   }
}