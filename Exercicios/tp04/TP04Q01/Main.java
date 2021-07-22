import java.io.FileWriter;
import java.io.IOException;

class No {
    String elemento;
    No esq;
    No dir;

    No(String elemento) {
        this(elemento, null, null);
    }

    No(String elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Counter{
    int count;

    Counter(int count){
        this.count = count;
    }
}

class ArvoreBinaria {
    No raiz;

    ArvoreBinaria() { 
        raiz = null; 
    }

    void inserir(String x, Counter c) throws Exception {
        raiz = (No) inserir(x, raiz, c);
    }

    private Object inserir(String x, No i, Counter c) throws Exception {
        if (i == null) {
            i = new No(x);
            c.count++;
        } else if (x.compareTo(i.elemento)  < 0) {
            i.esq = (No) inserir(x, i.esq, c);
            c.count++;
        } else if (x.compareTo(i.elemento) > 0) {
            i.dir = (No) inserir(x, i.dir, c);
            c.count++;
        } else {
            throw new Exception("Erro");
        }
        return i;
    }
    
    void inserirPai(String x) throws Exception {
        if (raiz == null) {
            raiz = new No(x);
        } else if (x.compareTo(raiz.elemento) < 0) {
            inserirPai(x, raiz.esq, raiz);
        } else if (x.compareTo(raiz.elemento) > 0) {
            inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro");
        }
    }

    private void inserirPai(String x, No i, No pai) throws Exception {
        if (i == null) {
            if (x.compareTo(pai.elemento) < 0){
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        } else if (x.compareTo(i.elemento)<0) {
            inserirPai(x, i.esq, i);
        } else if (x.compareTo(i.elemento)>0) {
            inserirPai(x, i.dir, i);
        } else {
            throw new Exception("Erro");
        }
    }
    
    boolean pesquisar(String x, Counter c) { 
        System.out.print(x + "\nraiz ");
        return pesquisar(x, raiz, c);
    }

    private boolean pesquisar(String x, No i, Counter c) {
        boolean resp;
        if (i == null) {
            System.out.print("NAO\n");
            resp = false;
            c.count++;
        } else if (x.equals(i.elemento)) {
            System.out.print("SIM\n");
            c.count++;
            resp = true;
        } else if (x.compareTo(i.elemento) <0) {
            System.out.print("esq ");
            c.count++;
            resp = pesquisar(x, i.esq, c);
        } else {
            System.out.print("dir ");
            resp = pesquisar(x, i.dir, c);
        }
        return resp;
    }
    
    void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }
    
    void caminharPre(No i) {
        if (i != null) {
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }
    
    void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }
    
    void remover(String x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(String x, No i) throws Exception {
        if (i == null) { 
            throw new Exception("Erro");
        } else if(x.compareTo(i.elemento) < 0) { 
            i.esq = remover(x, i.esq);
        } else if(x.compareTo(i.elemento) > 0) { 
            i.dir = remover(x, i.dir);
        } else if(i.dir == null) { 
            i = i.esq;
        } else if(i.esq == null) { 
            i = i.dir;
        } else { 
            i.esq = anterior(i, i.esq); 
        }
        return i;
    }

    private No anterior(No i, No j) {
        if (j.dir != null) 
            j.dir = anterior(i, j.dir);
        else { 
            i.elemento = j.elemento; 
            j = j.esq; 
        }
        return j;
    }
}

/**
 * Main
 */
public class Main {

    public static int countComp = 0;
    /**
     * isFim - descobre se String == "FIM"
    */
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static void main(String[] args) throws IOException {
        Counter auxCount = new Counter(0);
        String id = null;
        ArvoreBinaria arv = new ArvoreBinaria();
        id = MyIO.readString();
        double start = System.currentTimeMillis();
        
        while(!isFim(id)){
            try{
                arv.inserir(id, auxCount);
            }catch (Exception er){
                System.out.println(er);
            }
            id = MyIO.readString();
        }

        id = MyIO.readString();
        while(!isFim(id)){
            try{
                arv.pesquisar(id, auxCount);
            }catch (Exception er){
                System.out.println(er);
            }
            id = MyIO.readString();
        }

        FileWriter myWriter = new FileWriter("matrícula_quicksortParcial.txt");
        myWriter.write("711260\t" + auxCount.count+"\t"+"\t"+((System.currentTimeMillis()-start)/1000) );
        myWriter.close();
    }
}