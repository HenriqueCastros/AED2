import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula(){
        this(0, null, null, null, null);
    }

    public Celula(int elemento){
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz {
    public Celula inicio;
    private int linha, coluna, count;

    public Matriz (){
        this(3, 3);
    }

    public Matriz (int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        count = 1;
        inicio = new Celula();

        int i = 0, j = 0;
        Celula aux = inicio;

        //Cria primeira linha
        for(j = 1; j < coluna; j++, aux = aux.dir)
            aux.dir = new Celula(0, null, null, aux, null);//count++
        
        //Cria outras linhas
        //Metodo: 1- cria tmp tendo aux como seu info
        //        2- conecta aux a tmp
        //        3- cria nova linha mantendo conexoes
        //        4- 'sobe' aux de linha  
        aux = inicio;
        for(i = 1; i < linha; i++, aux = aux.sup){
            Celula tmp = new Celula(0, aux, null, null, null);//count++
            aux.sup = tmp;
            for(j = 1; j < coluna; j++, tmp = tmp.dir){
                tmp.dir = new Celula(0, tmp.inf.dir, null, tmp,null);//count++ 
                tmp.inf.dir.sup = tmp.dir;
                
            }
        }
    }

    //Metodo para printar matriz
    public void show(){
        for(Celula aux = inicio; aux != null;aux = aux.sup){
            for(Celula tmp = aux; tmp != null; tmp = tmp.dir)
                System.out.print(tmp.elemento +" ");
            System.out.println("");
        }
    }

    public Matriz soma (Matriz m2) {
        Matriz resp = null;

        if(this.linha == m2.linha && this.coluna == m2.coluna){
            resp = new Matriz(this.linha, this.coluna);
            Celula thisInicio= this.inicio;
            Celula m2Inicio = m2.inicio;
            for(Celula aux = resp.inicio; aux != null;aux = aux.sup, thisInicio = thisInicio.sup, m2Inicio =m2Inicio.sup){
                Celula thisAux = thisInicio;
                Celula m2Aux = m2Inicio;
                for(Celula tmp = aux; tmp != null; tmp = tmp.dir, thisAux = thisAux.dir, m2Aux =m2Aux.dir)
                    tmp.elemento = m2Aux.elemento + thisAux.elemento;
                
            }
        }else{
            System.out.println("Matrizes incompativeis");
        }

        return resp;
    }

    public Object getElement (int x, int y){
        if(this.linha < x || this.coluna < y){
            System.out.println("Tamanho incompativel");
            return null;
        }else{
            Celula resp = inicio;
            for(int i = 0; i < x; i++)
                resp = resp.dir;
            for(int j = 0; j < y; j++)
                resp = resp.sup;
            
            return resp.elemento;
        }
    }

    public Object getCell (int x, int y){
        if(this.linha < x || this.coluna < y){
            System.out.println("Tamanho incompativel");
            return null;
        }else{
            Celula resp = inicio;
            for(int i = 0; i < x; i++)
                resp = resp.dir;
            for(int j = 0; j < y; j++)
                resp = resp.sup;
            
            return resp;
        }
    }

    public Matriz multiplicacao (Matriz m2) {
        Matriz resp = null;

        if(this.coluna == m2.linha){
            resp = new Matriz(this.linha, m2.coluna);
            for(int i = 0; i < this.linha; i++){
                for(int j = 0; j <m2.coluna; j++){
                    for(int k=0; k < m2.linha; k++){
                        Celula celResp = (Celula) resp.getCell(j,i);
                        celResp.elemento += (int)this.getElement(k,i) * (int)m2.getElement(j,k); 
                    }
                }
            }
        }else{
            System.out.println("Matrizes incompativeis");
        }

        return resp;
    }

    public boolean isQuadrada(){
        return (this.linha == this.coluna);
    }

    public void mostrarDiagonalPrincipal (){
        if(isQuadrada() == true){
            for(int i =0; i< this.linha; i++){
                System.out.print(this.getElement(i,i) +" ");
            }
            System.out.println("");
        }
    }

    public void mostrarDiagonalSecundaria (){
        if(isQuadrada() == true){
            for(int j = 0; j < this.coluna; j++){
                System.out.print(this.getElement(this.linha - 1 -j,j)+" ");
            }
            System.out.println("");
        }
    }

    public void read() {
        for(Celula aux = inicio; aux != null;aux = aux.sup){
            for(Celula tmp = aux; tmp != null; tmp = tmp.dir)
                tmp.elemento = MyIO.readInt();
        }
    }
    
}

public class Main {

    public static void main(String[] args) throws Exception{
        // Matriz m = new Matriz(2,2);
        // Matriz m2 = new Matriz(2,2);
        // Celula tmp = (Celula) m.getCell(0,0);
        // tmp.elemento = 1;
        // tmp = (Celula) m.getCell(1,0);
        // tmp.elemento = 2;
        // tmp = (Celula) m.getCell(0,1);
        // tmp.elemento = 3;
        // tmp = (Celula) m.getCell(1,1);
        // tmp.elemento = 4;
        // m.show();
        // tmp = (Celula) m2.getCell(0,0);
        // tmp.elemento = 5;
        // tmp = (Celula) m2.getCell(1,0);
        // tmp.elemento = 6;
        // tmp = (Celula) m2.getCell(0,1);
        // tmp.elemento = 7;
        // tmp = (Celula) m2.getCell(1,1);
        // tmp.elemento = 8;
        // System.out.println("m2");
        // m2.show();

        // Matriz m3 = m.multiplicacao(m2);
        // System.out.println("m3");
        // m3.show();
        // m3.mostrarDiagonalPrincipal();
        // m3.mostrarDiagonalSecundaria();

        Matriz m = new Matriz();
        Matriz m2 = new Matriz();
        int rep = MyIO.readInt();
        for (int i = 0; i < rep; i++){
            int x = MyIO.readInt();
            int y = MyIO.readInt();
            m = new Matriz(x,y);
            m.read();
            x = MyIO.readInt();
            y = MyIO.readInt();
            m2 = new Matriz(x,y);
            m2.read();
            m.mostrarDiagonalPrincipal();
            m.mostrarDiagonalSecundaria();
            Matriz m3 = m.soma(m2);
            m3.show();
            m3 = m.multiplicacao(m2);
            m3.show();
        }
    }

}