import java.util.*;

class CCelula {
	public Object item;
	public CCelula prox;    	
    public CCelula(Object valorItem, CCelula proxCelula)
    {
        item = valorItem;
        prox = proxCelula;
    }    			
    public CCelula(Object valorItem)
    {
        item = valorItem;
        prox = null;
    }    			        	
    public CCelula()
    {
    	item = null;
        prox = null;
    }    			        	
}

class CCelulaDup {
	public Object item; // O Item armazendo pela clula
	public CCelulaDup ant; // Referencia a celula anterior
	public CCelulaDup prox; // Referencia a proxima celula

	public CCelulaDup() {
		item = null;
		ant = null;
		prox = null;
	}

	public CCelulaDup(Object valorItem) {
		item = valorItem;
		ant = null;
		prox = null;
	}

	public CCelulaDup(Object valorItem, CCelulaDup celulaAnt, CCelulaDup proxCelula) {
		item = valorItem;
		ant = celulaAnt;
		prox = proxCelula;
	}
}

class CCelulaDicionario{
	public Object key, value;
	public CCelulaDicionario prox;
	// Construtora que anula os três atributos da célula
	public CCelulaDicionario(){
		key = value = null;
		prox = null;
	}
	// Construtora que inicializa key e value com os argumentos passados
	// por parâmetro e anula a referência à próxima célula
	public CCelulaDicionario(Object chave, Object valor){
		key = chave;
		value = valor;
		prox = null;
	}
	// Construtora que inicializa todos os atribulos da célula com os argumentos
	// passados por parâmetro
	public CCelulaDicionario(Object chave, Object valor, CCelulaDicionario proxima){
		key = chave;
		value = valor;
		prox = proxima;
	}
}

class CDicionario{
	private CCelulaDicionario primeira, ultima;
	public CDicionario(){
		primeira = new CCelulaDicionario();
		ultima = primeira;
	}
	public boolean vazio(){
		return primeira == ultima;
	}
	public void adiciona(Object chave, Object valor){
		if(recebeValor(chave) == null){
			CCelulaDicionario aux = new CCelulaDicionario(chave, valor);
			ultima.prox = aux;
			ultima = aux;
		}
	}
	public Object recebeValor(Object chave){
		boolean achou = false;
		CCelulaDicionario aux = primeira.prox;
		while(aux != null && !achou){
			achou = aux.key.equals(chave);
			if(!achou)
				aux = aux.prox;
			else
				return aux.value;
		}
		return null;
	}

	public void imprime() {
		CCelulaDicionario aux = primeira.prox;

		while (aux != null) {
			System.out.print(aux.value + "["+aux.key+"] ");
			aux = aux.prox;
		}
		System.out.println("");
	}
}

class CLista {
	private CCelula primeira; 
	private CCelula ultima; 
	private int qtde; 

	public CLista() {
		primeira = new CCelula();
		ultima = primeira;
	}

	public boolean vazia() {
		return primeira == ultima;
	}

	public void insereFim(Object valorItem) {
		ultima.prox = new CCelula(valorItem);
		ultima = ultima.prox;
		qtde++;
	}

	public void insereComeco(Object valorItem) {
		CCelula aux = primeira.prox;
		primeira.prox = new CCelula(valorItem, aux);
		if (aux == null)
			ultima = primeira.prox;
		qtde++;
	}

	public boolean insereIndice(Object valorItem, int posicao) {
		// Exercício
		return true;
	}

	public void imprime() {
		CCelula aux = primeira.prox;

		while (aux != null) {
			System.out.print(aux.item + " ");
			aux = aux.prox;
		}
		System.out.println("");
	}

	public void imprimeFor() {
		for (CCelula aux = primeira.prox; aux != null; aux = aux.prox)
			System.out.print(aux.item + " ");
	}

	public void imprimeFormatoLista() {
		System.out.print("[/]->");
		for (CCelula aux = primeira.prox; aux != null; aux = aux.prox)
			System.out.print("[" + aux.item + "]->");
		System.out.println("null");
	}

	public void imprimeFormatoLista(String titulo) {
		System.out.println(titulo + "\n");
		System.out.print("[/]->");
		for (CCelula aux = primeira.prox; aux != null; aux = aux.prox)
			System.out.print("[" + aux.item + "]->");
		System.out.println("null");
	}

	public boolean contem(Object elemento) {
		boolean achou = false;
		for (CCelula aux = primeira.prox; aux != null && !achou; aux = aux.prox)
			achou = aux.item.equals(elemento);
		return achou;
	}

    public void InsereAntesDe(Object ElementoAInserir, Object elemento){
		boolean achou = false;
        CCelula aux = primeira;
        while(!achou && aux.prox!=null){
            achou = aux.prox.item.equals(elemento);
            if(!achou)
                aux = aux.prox;
        }
        CCelula tmp = new CCelula(ElementoAInserir, aux.prox);
        aux.prox = tmp;
	}

    public void InsereDepoisDe(Object ElementoAInserir, Object elemento) {
        boolean achou = false;
        CCelula aux = primeira;
        for(; !achou && aux.prox!=null; aux = aux.prox) 
            achou = aux.prox.item.equals(elemento);
        CCelula tmp = new CCelula(ElementoAInserir, aux.prox);
        aux.prox = tmp;
    }

    public void InsereOrdenado(int ElementoAInserir){
        boolean menor = false;
        CCelula aux = primeira;
        while( !menor && aux.prox!=null){
            menor = (ElementoAInserir < (int) aux.prox.item);
            if(!menor)
                aux = aux.prox;
        }
        CCelula tmp = new CCelula(ElementoAInserir, aux.prox);
        aux.prox = tmp;
    }

	public Object retornaPrimeiro() {
		if (primeira != ultima)
			return primeira.prox.item;
		else
			return null;
	}

	public Object retornaUltimo() {
		if (primeira != ultima)
			return ultima.item;
		else
			return null;
	}

	public Object retornaIndice(int posicao) {

		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			CCelula aux = primeira.prox;
			for (int i = 1; i < posicao; i++, aux = aux.prox)
				;
			return aux.item;
		}
		return null;
	}

	public Object removeRetornaComeco() {
		if (primeira != ultima) {
			CCelula aux = primeira.prox;
			primeira.prox = aux.prox;
			if (primeira.prox == null) 
				ultima = primeira;
			qtde--;
			return aux.item;
		}
		return null;
	}

	public Object removeRetornaComecoSimples() {
		if (primeira != ultima) {
			primeira = primeira.prox;
			qtde--;
			return primeira.item;
		}
		return null;
	}

	public void removeComecoSemRetorno() {
		if (primeira != ultima) {
			primeira = primeira.prox;
			qtde--;
		}
	}

	public Object removeRetornaFim() {
		if (primeira != ultima) {
			CCelula aux = primeira;
			while (aux.prox != ultima)
				aux = aux.prox;
			CCelula aux2 = aux.prox;
			ultima = aux;
			ultima.prox = null;
			qtde--;
			return aux2.item;
		}
		return null;
	}

	public void removeFimSemRetorno() {
		if (primeira != ultima) {
			CCelula aux = primeira;
			while (aux.prox != ultima)
				aux = aux.prox;
			ultima = aux;
			ultima.prox = null;
			qtde--;
		}
	}

	public void remove(Object valorItem) {
		if (primeira != ultima) {
			CCelula aux = primeira;
			boolean achou = false;
			while (aux.prox != null && !achou) {
				achou = aux.prox.item.equals(valorItem);
				if (!achou)
					aux = aux.prox;
			}
			if (achou) {
				// achou o elemento
				aux.prox = aux.prox.prox;
				if (aux.prox == null)
					ultima = aux;
				qtde--;
			}
		}
	}

	public boolean removeIndice(int posicao) {
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			int i = 0;
			CCelula aux = primeira;
			while (i < posicao - 1) {
				aux = aux.prox;
				i++;
			}
			aux.prox = aux.prox.prox;
			if (aux.prox == null)
				ultima = aux;
			qtde--;
			return true;
		}
		return false;
	}

	public Object removeRetornaIndice(int posicao) {
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			int i = 0;
			CCelula aux = primeira;
			while (i < posicao - 1) {
				aux = aux.prox;
				i++;
			}
			CCelula aux2 = aux.prox;
			aux.prox = aux.prox.prox;
			if (aux.prox == null)
				ultima = aux;
			qtde--;
			return aux2.item;
		}
		return null;
	}

	public void RemovePos(int n) {
		if ((n >= 1) && (n <= qtde) && (primeira != ultima)) {
			int i = 0;
			CCelula aux = primeira;
			while (i < n - 1) {
				aux = aux.prox;
				i++;
			}
			aux.prox = aux.prox.prox;
			if (aux.prox == null)
				ultima = aux;
			qtde--;
		}
	}

	public int quantidade() {
		return qtde;
	}

}

class CListaDup {
	private CCelulaDup primeira; // Referencia a primeira celula da lista (celula cabeca)
	private CCelulaDup ultima; // Referencia a ultima celula da lista
	private int qtde;

	public CListaDup() {
		primeira = new CCelulaDup(0);
		ultima = primeira;
	}

	public boolean vazia() {
		return primeira == ultima;
	}

	public void insereFim(Object valorItem) {
		ultima.prox = new CCelulaDup(valorItem, ultima, null);
		ultima = ultima.prox;
		qtde++;
	}

	public void insereComeco(Object valorItem) {
		if (primeira == ultima) { // Se a lista estiver vazia insere no fim
			ultima.prox = new CCelulaDup(valorItem, ultima, null);
			ultima = ultima.prox;
		} else { // senao insere no comeco
			primeira.prox = new CCelulaDup(valorItem, primeira, primeira.prox);
			primeira.prox.prox.ant = primeira.prox;
		}
		qtde++;
	}

	public void removeComecoSemRetorno() {
		if (primeira != ultima) {
			primeira = primeira.prox;
			primeira.ant = null;
			qtde--;
		}
	}

	public void imprime() {
		CCelulaDup aux = primeira.prox;
		while (aux != null) {
			System.out.print(aux.item+" ");
			aux = aux.prox;
		}
        System.out.println("");
	}

	public void imprimeInv() {
		CCelulaDup aux = ultima;
		while (aux.ant != null) {
			System.out.println(aux.item);
			aux = aux.ant;
		}
	}

	public boolean contem(Object elemento) {
		boolean achou = false;
		CCelulaDup aux = primeira.prox;
		while (aux != null && !achou) {
			achou = aux.item.equals(elemento);
			aux = aux.prox;
		}
		return achou;
	}

	public Object retornaPrimeiro() {
		if (primeira != ultima)
			return primeira.prox.item;
		return null;
	}

	public Object retornaIndice(int posicao) {
		if ((posicao >= 1) && (posicao <= qtde) && (primeira != ultima)) {
			CCelulaDup aux = primeira.prox;
			for (int i = 1; i < posicao; i++, aux = aux.prox)
				;
			if (aux != null)
				return aux.item;
		}
		return null;
	}

	public Object retornaUltimo() {
		if (primeira != ultima)
			return ultima.item;
		return null;
	}

	public void removeFimSemRetorno() {
		if (primeira != ultima) {
			ultima = ultima.ant;
			ultima.prox = null;
			qtde--;
		}
	}

	public void remove(Object valorItem) {
		if (primeira != ultima) {
			CCelulaDup aux = primeira.prox;
			boolean achou = false;
			while (aux != null && !achou) {
				achou = aux.item.equals(valorItem);
				if (!achou)
					aux = aux.prox;
			}
			if (achou) { // achou o elemento
				CCelulaDup anterior = aux.ant;
				CCelulaDup proximo = aux.prox;
				anterior.prox = proximo;
				if (proximo != null)
					proximo.ant = anterior;
				else
					ultima = anterior;
				qtde--;
			}
		}
	}

	public void RemovePos(int n) {
		if ((n >= 1) && (n <= qtde) && (primeira != ultima)) {
			CCelulaDup aux = primeira.prox;
			for (int i = 1; i < n; i++, aux = aux.prox);
			aux.ant.prox = aux.prox;
			if(aux.prox != null)
				aux.prox.ant = aux.ant;
			else
				ultima = aux.ant;
			qtde--;
		}
	}

	public Object removeRetornaComeco() {
		if (primeira != ultima) {
			CCelulaDup aux = primeira.prox;
			primeira = primeira.prox;
			primeira.ant = null;
			qtde--;
			return aux.item;
		}
		return null;
	}

	public Object removeRetornaFim() {
		if (primeira != ultima) {
			CCelulaDup aux = ultima;
			ultima = ultima.ant;
			ultima.prox = null;
			qtde--;
			return aux.item;
		}
		return null;
	}

	public int quantidade() {
		return qtde;
	}

    public int primeiraOcorrenciaDe(Object elemento){
        int i = 0;
        boolean achou = false;
		CCelulaDup aux = primeira.prox;
		while (aux != null && !achou) {
			achou = aux.item.equals(elemento);
			aux = aux.prox;
            i++;
		}
		return achou ? i : -1;
    }

    public int ultimaOcorrenciaDe(Object elemento){
        int i = qtde+1;
        boolean achou = false;
		CCelulaDup aux = ultima;
		while (aux != primeira && !achou) {
			achou = aux.item.equals(elemento);
			aux = aux.ant;
            i--;
		}
		return achou ? i : -1;
    }

	public void prova(){
		CCelulaDup i = primeira.prox;
		CCelulaDup j = ultima;
		CCelulaDup k;
		while (j.prox != i){
			Object tmp = i.item;
			i.item = j.item;
			j.item = tmp;
			i = i.prox;
			for (k = primeira; k.prox != j; k = k.prox); j = k;
		}
	}
}

class CFila {
	private CCelula frente; // Celula cabeca.
	private CCelula tras; // Ultima celula.
	private int qtde;

	public CFila() {
		frente = new CCelula();
		tras = frente;
	}

	public boolean vazia() {
		return frente == tras;
	}

	public void mostra() {
		System.out.print("[ ");
		for (CCelula c = frente.prox; c != null; c = c.prox)
			System.out.print(c.item + " ");
		System.out.println("] "+qtde);
	}

	public void enfileira(Object valorItem) {
		tras.prox = new CCelula(valorItem);
		tras = tras.prox;
		qtde++;
	}

	public Object desenfileira() {
		Object item = null;
		if (frente != tras) {
			frente = frente.prox;
			item = frente.item;
			qtde--;
		}
		return item;
	}
	
	public Object desenfileirav2() {
		if (frente != tras) {
			frente = frente.prox;
			qtde--;
			return frente.item;
		}
		return null;
	}
	
	public Object peek() {
		if (frente != tras)
			return frente.prox.item;
		else
			return null;
	}

	public boolean contem(Object valorItem) {
		CCelula aux = frente.prox;
		while (aux != null) {
			if (aux.item.equals(valorItem))
				return true;
			aux = aux.prox;
		}
		return false;
	}

	public boolean contemFor(Object valorItem) {
		for (CCelula aux = frente.prox; aux != null; aux = aux.prox)
			if (aux.item.equals(valorItem))
				return true;
		return false;
	}

	public int quantidade() {
		return qtde;
	}

}

class RandomQueue {
	private CCelula frente; // Celula cabeca.
	private CCelula tras; // Ultima celula.
	private int qtde;

	public RandomQueue() {
		frente = new CCelula();
		tras = frente;
	}

	public boolean isEmpty() {
		return frente == tras;
	}

	public void Enqueue(Object valorItem) {
		tras.prox = new CCelula(valorItem);
		tras = tras.prox;
		qtde++;
	}

	public Object Dequeue() {
        Random r = new Random();
        int i = r.nextInt(qtde);
		CCelula aux = frente;
        for(; i > 0; i--, aux = aux.prox);

        Object item = aux.prox.item;
        aux.prox = aux.prox.prox;
		
		return item;
	}

    public Object Sample(){
        Random r = new Random();
        int i = r.nextInt(qtde);
		CCelula aux = frente;
        for(; i > 0; i--, aux = aux.prox);

        Object item = aux.prox.item;
		
		return item;
    }

}

class CPilha {
	private CCelula topo = null;
	private int qtde;

	public CPilha() {
		// nenhum código
	}

	public boolean vazia() {
		return topo == null;
	}

	public void empilha(Object valorItem) {
		topo = new CCelula(valorItem, topo);
		qtde++;
	}

	public Object desempilha() {
		Object item = null;
		if (topo != null) {
			item = topo.item;
			topo = topo.prox;
			qtde--;
		}
		return item;
	}

	public boolean contem(Object valorItem) {
		CCelula aux = topo;
		while (aux != null) {
			if (aux.item.equals(valorItem))
				return true;
			aux = aux.prox;
		}
		return false;
	}

	public boolean contemFor(Object valorItem) {
		for (CCelula aux = topo; aux != null; aux = aux.prox)
			if (aux.item.equals(valorItem))
				return true;
		return false;
	}

	public Object peek() {
		return (topo != null) ? topo.item : null;
	}

	public void mostra() {
		for (CCelula c = topo; c != null; c = c.prox)
			System.out.print( c.item + " ");
        System.out.println("");
    }

	public int quantidade() {
		return qtde;
	}

}

class Deque {
	private CCelulaDup front; 
	private CCelulaDup back;
	private int size;

	public Deque() {
		front = new CCelulaDup();
		back = front;
	} 
	public boolean isEmpty() {
		return front == back;
	} 
	public int size() { 
		return size;
	} 
	public void pushLeft(Object item) { 
		if(front == back){
			back.prox = new CCelulaDup(item,back,null);
			back = back.prox;
		}else{
			front.prox = new CCelulaDup(item, front, front.prox);
			front.prox.prox.ant = front.prox;
		}
		size++;
	} 
	public void pushRight(Object item) { 
		back.prox = new CCelulaDup(item,back,null);
		back = back.prox;
		size++;
	} 
	public Object popLeft() { 
		if(front.prox != null){
			CCelulaDup aux = front.prox;
			front = front.prox;
			front.ant = null;
			size--;
			return aux.item;
		}

		return null;
	} 
	public Object popRight() { 
		if(front != back.prox){
			CCelulaDup aux = back;
			back = back.ant;
			back.prox = null;
			size--;
			return aux.item;
		}
		return null;
	} 
}

class CCelulaSimples{
	public Object item;
	public CCelulaSimples prox;
}

class CListaSimples{
	private CCelulaSimples primeira, ultima;
	public CListaSimples(){
		// Código da função construtora
		primeira = new CCelulaSimples();
		ultima = primeira;
	}
	public boolean vazia(){
		// Código para verificar se a Lista está vazia
		return ((primeira.item == null) && (primeira == ultima));
	}
	public void insereComeco(Object valorItem){
		// Código para inserir valorItem no início da Lista
		if(vazia())
			primeira.item = valorItem;
		else{
			CCelulaSimples aux = new CCelulaSimples();
			aux.item = valorItem;
			aux.prox = primeira;
			primeira = aux;
		}
	}
	public Object removeComeco(){
		// Código para remover e retornar o elemento do início da Lista
		if(!vazia()){
			if(primeira == ultima){
				Object aux = primeira.item;
				primeira.item = null;
				return aux;
			}else{
				CCelulaSimples aux = primeira;
				primeira = primeira.prox;
				return aux.item;
			}
		}
		return null;
	}
	public void insereFim(Object valorItem){
		// Código para inserir valorItem no fim da Lista
		if(vazia())
			primeira.item = valorItem;
		else{
			CCelulaSimples aux = new CCelulaSimples();
			aux.item = valorItem;
			ultima.prox = aux;
			ultima = ultima.prox;
		}
	}
	public Object removeFim(){
		// Código para remover e retornar o elemento do fim da Lista
		if(!vazia()){
			if(primeira == ultima){
				Object aux = primeira.item;
				primeira.item = null;
				return aux;
			}else{
				CCelulaSimples aux = primeira;
				for(aux = primeira; aux.prox != ultima; aux = aux.prox);
				Object item = aux.prox.item;
				aux.prox = null;
				return item;
			}
		}
		return null;
	}
	public void imprime(){
		// Código para imprimir todos os elementos da Lista
		CCelulaSimples aux = primeira;

		while (aux != null) {
			System.out.print(aux.item + " ");
			aux = aux.prox;
		}
		System.out.println("");
	}
	public boolean contem(Object elemento){
		// Código para verifica se a Lista contem o elemento passado
		// como parâmetro
		CCelulaSimples aux = primeira;
		while (aux != null) {
			if (aux.item.equals(elemento))
				return true;
			aux = aux.prox;
		}
		return false;
	}
}

public class Main {

    public static CListaDup ConcatenaLD(CListaDup L1, CListaDup L2){
        CListaDup resp = new CListaDup();
        for(int i=0; i< L1.quantidade(); i++)
            resp.insereFim(L1.retornaIndice(i+1));
        for(int i=0; i< L2.quantidade(); i++)
            resp.insereFim(L2.retornaIndice(i+1));
        return resp;
    }

    public static CFila ConcatenaFila(CFila F1, CFila F2){
        CFila resp = new CFila();
        int f1Len = F1.quantidade();
        int f2Len = F2.quantidade();
        Object tmp[] = new Object[f1Len +f2Len];
        int i = 0;

        for(i=0; F1.quantidade() > 0; i++)
           tmp[i] = F1.desenfileira();
        for(; F2.quantidade() > 0; i++)
           tmp[i] = F2.desenfileira();

        for(i=0; i < f1Len; i++){
            resp.enfileira(tmp[i]);
            F1.enfileira(tmp[i]);
        }

        for(; i < f2Len+f1Len; i++){
            resp.enfileira(tmp[i]);
            F2.enfileira(tmp[i]);
        }
        return resp;
    }

    public static CPilha ConcatenaPilha(CPilha P1, CPilha P2){
        CPilha resp = new CPilha();
        int P1Len = P1.quantidade();
        int P2Len = P2.quantidade();
        Object tmp[] = new Object[P1Len +P2Len];
        int i = 0;

        for(i=0; P1.quantidade() > 0; i++)
           tmp[i] = P1.desempilha();
        for(; P2.quantidade() > 0; i++)
           tmp[i] = P2.desempilha();

        for(i=P1Len-1; i >=0; i--){
            resp.empilha(tmp[i]);
            P1.empilha(tmp[i]);
        }

        for(i=P1Len+P2Len-1; i >=P1Len; i--){
            resp.empilha(tmp[i]);
            P2.empilha(tmp[i]);
        }
        return resp;
    }

	public static void setURLs(CDicionario d){
		d.adiciona( "www.google.com","2800:3f0:4004:809::2004");
		d.adiciona("www.yahoo.com","2001:4998:44:3507::8001");
		d.adiciona("www.amazon.com","13.33.129.30");
		d.adiciona("www.uol.com.br","200.147.100.53");
		d.adiciona("www.pucminas.br","200.229.32.29");
		d.adiciona("www.microsoft.com","2600:1419:ac00:38f::356e");
		d.adiciona("research.microsoft.com","13.67.218.189");
		d.adiciona("www.hotmail.com","2620:1ec:c11::212");
		d.adiciona("www.gmail.com","2800:3f0:4004:802::2005");
		d.adiciona("www.twitter.com","104.244.42.129");
		d.adiciona("www.facebook.com","2a03:2880:f1ff:83:face:b00c:0:25de");
		d.adiciona("www.cplusplus.com","2607:5300:60:5d9b:c::");
		d.adiciona("www.youtube.com","2800:3f0:4004:80c::200e");
		d.adiciona("www.brasil.gov.br","170.246.255.242");
		d.adiciona("www.whitehouse.gov","2600:1419:ac00:38a::fc4");
		d.adiciona("www.nyt.com","151.101.177.164");
		d.adiciona("www.capes.gov.br","200.130.18.234");
		d.adiciona("www.wikipedia.com","2620:0:861:ed1a::9");
		d.adiciona("www.answers.com","151.101.176.203");
		d.adiciona("www.apple.com","2600:1419:ac00:295::1aca");
		d.adiciona("www.techtudo.com.br","186.192.81.152");
		d.adiciona("www.hltv.org","104.18.2.89");
		d.adiciona("www.twitch.tv","151.101.178.167");
		d.adiciona("www.pattrol.com.br","69.49.115.40");
		d.adiciona("brasil.diplo.de","46.243.125.105");
	}

	public static void setAmin(CDicionario d){
		d.adiciona("UUU","Fenilalanina");
		d.adiciona("UUC","Fenilalanina");
		d.adiciona("UUA","Leucina");
		d.adiciona("UUG","Leucina");
		d.adiciona("UCU","Serina");
		d.adiciona("UCC","Serina");
		d.adiciona("UCA","Serina");
		d.adiciona("UCG","Serina");
		d.adiciona("UAU","Tirosina");
		d.adiciona("UAC","Tirosina");
		d.adiciona("UAA","Parada");
		d.adiciona("UAG","Parada");
		d.adiciona("UGU","Cisteina");
		d.adiciona("UGC","Cisteina");
		d.adiciona("UGA","Parada");
		d.adiciona("UGG","Triptofano");
		d.adiciona("CUU","Leucina");
		d.adiciona("CUC","Leucina");
		d.adiciona("CUA","Leucina");
		d.adiciona("CUG","Leucina");
		d.adiciona("CCU","Prolina");
		d.adiciona("CCC","Prolina");
		d.adiciona("CCA","Prolina");
		d.adiciona("CCG","Prolina");
		d.adiciona("CAU","Histidina");
		d.adiciona("CAC","Histidina");
		d.adiciona("CAA","Glutamina");
		d.adiciona("CAG","Glutamina");
		d.adiciona("CGU","Arginina");
		d.adiciona("CGC","Arginina");
		d.adiciona("CGA","Arginina");
		d.adiciona("CGG","Arginina");
		d.adiciona("AUU","Isoleucina");
		d.adiciona("AUC","Isoleucina");
		d.adiciona("AUA","Isoleucina");
		d.adiciona("AUG","Metionina");
		d.adiciona("ACU","Treonina");
		d.adiciona("ACC","Treonina");
		d.adiciona("ACA","Treonina");
		d.adiciona("ACG","Treonina");
		d.adiciona("AAU","Asparagina");
		d.adiciona("AAC","Asparagina");
		d.adiciona("AAA","Lisina");
		d.adiciona("AAG","Lisina");
		d.adiciona("AGU","Serina");
		d.adiciona("AGC","Serina");
		d.adiciona("AGA","Arginina");
		d.adiciona("AGG","Arginina");
		d.adiciona("GUU","Valina");
		d.adiciona("GUC","Valina");
		d.adiciona("GUA","Valina");
		d.adiciona("GUG","Valina");
		d.adiciona("GCU","Alanina");
		d.adiciona("GCC","Alanina");
		d.adiciona("GCA","Alanina");
		d.adiciona("GCG","Alanina");
		d.adiciona("GAU","Aspartato");
		d.adiciona("GAC","Aspartato");
		d.adiciona("GAA","Glutamato");
		d.adiciona("GAG","Glutamato");
		d.adiciona("GGU","Glicina");
		d.adiciona("GGC","Glicina");
		d.adiciona("GGA","Glicina");
		d.adiciona("GGG","Glicina");
	}
    public static void main(String[] args){
		CListaDup prova = new CListaDup();
		//prova.insereComeco(0);
		prova.insereFim(1);
		prova.insereFim(6);
		prova.insereFim(2);
		prova.insereFim(0);

		prova.imprime();
		prova.prova();
		prova.imprime();
    }
}
