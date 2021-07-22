import java.io.FileWriter;
import java.io.IOException;

class No {
    int elemento;
    No esq;
    No dir;

    No(int elemento) {
        this(elemento, null, null);
    }

    No(int elemento, No esq, No dir) {
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

    void inserir(int x, Counter c) throws Exception {
        raiz = (No) inserir(x, raiz, c);
    }

    private Object inserir(int x, No i, Counter c) throws Exception {
        if (i == null) {
            i = new No(x);
            c.count++;
        } else if (x < i.elemento) {
            i.esq = (No) inserir(x, i.esq, c);
            c.count++;
        } else if (x > i.elemento){
            i.dir = (No) inserir(x, i.dir, c);
            c.count++;
        } else {
            throw new Exception("Erro");
        }
        return i;
    }
    
    // void inserirPai(String x) throws Exception {
    //     if (raiz == null) {
    //         raiz = new No(x);
    //     } else if (x.compareTo(raiz.elemento) < 0) {
    //         inserirPai(x, raiz.esq, raiz);
    //     } else if (x.compareTo(raiz.elemento) > 0) {
    //         inserirPai(x, raiz.dir, raiz);
    //     } else {
    //         throw new Exception("Erro");
    //     }
    // }

    // private void inserirPai(String x, No i, No pai) throws Exception {
    //     if (i == null) {
    //         if (x.compareTo(pai.elemento) < 0){
    //             pai.esq = new No(x);
    //         } else {
    //             pai.dir = new No(x);
    //         }
    //     } else if (x.compareTo(i.elemento)<0) {
    //         inserirPai(x, i.esq, i);
    //     } else if (x.compareTo(i.elemento)>0) {
    //         inserirPai(x, i.dir, i);
    //     } else {
    //         throw new Exception("Erro");
    //     }
    // }
    
    // boolean pesquisar(String x, Counter c) { 
    //     System.out.print(x + "\nraiz ");
    //     return pesquisar(x, raiz, c);
    // }

    // private boolean pesquisar(String x, No i, Counter c) {
    //     boolean resp;
    //     if (i == null) {
    //         System.out.print("NAO\n");
    //         resp = false;
    //         c.count++;
    //     } else if (x.equals(i.elemento)) {
    //         System.out.print("SIM\n");
    //         c.count++;
    //         resp = true;
    //     } else if (x.compareTo(i.elemento) <0) {
    //         System.out.print("esq ");
    //         c.count++;
    //         resp = pesquisar(x, i.esq, c);
    //     } else {
    //         System.out.print("dir ");
    //         resp = pesquisar(x, i.dir, c);
    //     }
    //     return resp;
    // }
    
    void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + " ");
            caminharCentral(i.dir);
        }
    }
    
    int countPrintPos(No i) {
        if (i != null) {
            int filhos = countPrintPos(i.esq);
            filhos += countPrintPos(i.dir);
            System.out.print(i.elemento + " ");
            System.out.println(" - Elementeo tem "+filhos+" filhos");
            return filhos+1;
        }else{
            return 0;
        }
    }

    void printFilhosPre(No i) {
        if (i != null) {
            Object filhoEsq = i.esq == null? "" : i.esq.elemento;
            Object filhoDir = i.dir == null? "" : i.dir.elemento;
            System.out.println(i.elemento + " - filhos: "+filhoEsq+ " " + filhoDir);
            printFilhosPre(i.esq);
            printFilhosPre(i.dir);
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
            System.out.print(i.elemento + ";");
        }
    }
    
    // void remover(String x) throws Exception {
    //     raiz = remover(x, raiz);
    // }

    // private No remover(String x, No i) throws Exception {
    //     if (i == null) { 
    //         throw new Exception("Erro");
    //     } else if(x.compareTo(i.elemento) < 0) { 
    //         i.esq = remover(x, i.esq);
    //     } else if(x.compareTo(i.elemento) > 0) { 
    //         i.dir = remover(x, i.dir);
    //     } else if(i.dir == null) { 
    //         i = i.esq;
    //     } else if(i.esq == null) { 
    //         i = i.dir;
    //     } else { 
    //         i.esq = anterior(i, i.esq); 
    //     }
    //     return i;
    // }

    // private No anterior(No i, No j) {
    //     if (j.dir != null) 
    //         j.dir = anterior(i, j.dir);
    //     else { 
    //         i.elemento = j.elemento; 
    //         j = j.esq; 
    //     }
    //     return j;
    // }
}

/**
 * Main
 */
public class Main {

    public void printValues(No234 no){
        System.out.println(no.v1 +" "+ no.v2+" " +no.v3);
    }

    public void print234(No234 no){
        if(no.r1 != null){
            print234(no.r1);
        }
        System.out.println(no.v1);
        if(no.r2 != null){
            print234(no.r2);
        }
        System.out.println(no.v2);
        if(no.r3 != null){
            print234(no.r3);
        }
        System.out.println(no.v3);
        if(no.r4 != null){
            print234(no.r4);
        }
    }

    public static int countComp = 0;

    public static int misterio(No no) {
        return (no == null)? 0: misterio(no.esq) + misterio(no.dir) + 1;
    }
    public static int misterio2(No no) {
        return (no != null)? misterio(no.esq) + misterio(no.dir) + 1:0;
    }

    public static void main(String[] args) throws IOException {
        Counter auxCount = new Counter(0);
        ArvoreBinaria arv = new ArvoreBinaria();
        try{

            arv.inserir(31, auxCount);
            arv.inserir(20, auxCount);
            arv.inserir(53, auxCount);
            arv.inserir(13, auxCount);
            arv.inserir(26, auxCount);
            arv.inserir(47, auxCount);
            arv.inserir(67, auxCount);
            arv.inserir(11, auxCount);
            arv.inserir(19, auxCount);
            arv.inserir(23, auxCount);
            arv.inserir(29, auxCount);
            arv.inserir(37, auxCount);
            arv.inserir(59, auxCount);
            arv.inserir(71, auxCount);
            arv.inserir(17, auxCount);
            arv.inserir(27, auxCount);
            arv.inserir(57, auxCount);
            arv.inserir(61, auxCount);
            arv.inserir(83, auxCount);
            arv.caminharPos(arv.raiz);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}