
//=============================================================================
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
 //=============================================================================
 typedef struct CelulaDupla{
    int elemento;
    struct CelulaDupla *prox, *ant;
}CelulaDupla;
//=============================================================================
CelulaDupla* new_celula_dupla(int elemento){
    CelulaDupla *temp = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    temp->elemento = elemento;
    temp->ant = NULL;
    temp->prox = NULL;
    return temp;
}
//=============================================================================
typedef struct ListaDupla{
    struct CelulaDupla *primeiro, *ultimo;
    int size;
} ListaDupla;
//=============================================================================
ListaDupla new_lista_dupla(){
    ListaDupla temp;
    temp.primeiro = temp.ultimo = new_celula_dupla(-1);
    temp.size = 0;
    return temp;
}
//=============================================================================
int size_lista_dupla(ListaDupla *l){
    return l->size;
}
//=============================================================================
void insert_begin_dupla(ListaDupla *l, int elemento){
    
    CelulaDupla *temp = new_celula_dupla(-1);
    temp->prox = l->primeiro;

    l->primeiro->elemento = elemento; 
    l->primeiro->ant = temp;   
    l->primeiro = temp;
    l->size++;
}
//=============================================================================
void insert_end_dupla(ListaDupla *l, int elemento){    
    l->ultimo->prox = new_celula_dupla(elemento);
    l->ultimo->prox->ant = l->ultimo;
    l->ultimo = l->ultimo->prox;
    l->size++;
}
//=============================================================================
void insert_at_dupla(ListaDupla *l, int elemento, int pos){
    
    if(pos < 0 || pos > l->size)
        printf("Erro ao tentar inserir na posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if (pos == 0)
        insert_begin_dupla(l, elemento);
    else if (pos == l->size)
        insert_end_dupla(l, elemento);
    else{
        
        CelulaDupla *ant = l->primeiro;
        for(int i=0; i<pos;i++) 
            ant = ant->prox;
            
        CelulaDupla *temp = new_celula_dupla(elemento);  
        temp->prox = ant->prox;
        temp->prox->ant = temp;
        temp->ant = ant;
        ant->prox = temp;
        l->size++;
    }
}
//=============================================================================
CelulaDupla* remove_end_dupla(ListaDupla *l){

    if(l->primeiro == l->ultimo){
        printf("\nA lista esta vazia!\n");
        return NULL;
    }
    
    CelulaDupla *temp = l->ultimo;
    int elemento = temp->elemento;

    l->ultimo = l->ultimo->ant;
    l->ultimo->prox = NULL;
    l->size--;
    
    return temp;
}
//=============================================================================
CelulaDupla* remove_at_dupla(ListaDupla *l, int pos){

    if(l->primeiro == l->ultimo){
        printf("\nA lista esta vazia!\n");
        return NULL;
    }else if(pos < 0 || pos > l->size-1)
        printf("Erro ao tentar remover item da posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if(pos == l->size-1)
        remove_end_dupla(l);
    else{
        
        CelulaDupla *ant = l->primeiro;
        for(int i=0; i<pos;i++) 
            ant = ant->prox;
            
        CelulaDupla *temp = ant->prox;  
        int elemento = temp->elemento;

        temp->prox->ant = ant;
        ant->prox = temp->prox;  

        l->size--;

        return temp;
    }
}
//=============================================================================
CelulaDupla* remove_begin_dupla(ListaDupla *l){
    return remove_at_dupla(l, 0);
}
CelulaDupla* pesquisar_lista_at(ListaDupla *l, int pos){
    CelulaDupla *i;
    if(l->primeiro == l->ultimo){
        printf("\nA lista esta vazia!\n");
        return NULL;
    }else if(pos < 0 || pos > l->size-1)
        printf("Erro ao tentar pesquisar item da posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else{
        int j = 0;
        for (i = l->primeiro->prox; j < pos; i = i->prox)
            j++;
        
        return i;
    }
    return i;
}
//=============================================================================
bool pesquisar_lista_dupla(ListaDupla *l, int elemento){
    CelulaDupla *i;
    for (i = l->primeiro->prox; i != NULL; i = i->prox)
        if(i->elemento == elemento)
            return true;
    return false;
}
//=============================================================================
void print_lista_dupla(ListaDupla *l){
    CelulaDupla *i;
    printf("[");
    for (i = l->primeiro->prox; i != NULL; i = i->prox)
    {
        printf("%d, ", i->elemento);
    }
    printf("] \n");
}
//=============================================================================
void print_lista_dupla_inverso(ListaDupla *l){
    CelulaDupla *i;
    printf("[");
    for (i = l->ultimo; i != l->primeiro; i = i->ant)
    {
        printf("%d, ", i->elemento);
    }
    printf("] \n");
}
//=============================================================================
void delete_lista_dupla(ListaDupla *l){
    while(l->size > 0)
        remove_at_dupla(l,0);
    free(l->primeiro);
}
//=============================================================================
//QUICKSORT
void swap(ListaDupla *l, int i, int j) {
    if(i != j){
        CelulaDupla* celJ = remove_at_dupla(l, j);
        insert_at_dupla(l, celJ->elemento, i);

        CelulaDupla* celI = remove_at_dupla(l, i+1);
        insert_at_dupla(l, celI->elemento, j);
    }
    
}

void quicksortRec(ListaDupla *l, int esq, int dir, int* countComp, int* countMov) {
    int i = esq, j = dir;
    CelulaDupla* celI = (CelulaDupla*)malloc(sizeof(CelulaDupla)); 
    CelulaDupla* celJ = (CelulaDupla*)malloc(sizeof(CelulaDupla)); 
    CelulaDupla* pivo = (CelulaDupla*)malloc(sizeof(CelulaDupla)); 
    celI = pesquisar_lista_at(l, esq);
    celJ = pesquisar_lista_at(l, dir);
    pivo = pesquisar_lista_at(l, (int)((dir+esq)/2));
    while (i <= j) {
        while (celI->elemento < pivo->elemento)
        {
            i++;
            celI = celI->prox;
            *countComp = *countComp + 1;
        } 
        while (celJ->elemento > pivo->elemento){
            *countComp = *countComp + 1;
            celJ = celJ->ant;
            j--;
        }
        if (i <= j) {
            swap(l, i,j);
            *countMov = *countMov + 3;
            i++;
            j--;
            celJ = celJ->ant;
            celI = celI->prox;
        }
    }
    if (esq < j)  quicksortRec(l, esq, j, countComp, countMov);
    if (i < dir)  quicksortRec(l, i, dir, countComp, countMov);
}

void quicksort(ListaDupla *l, int n, int* countComp, int* countMov) {
    quicksortRec(l, 0, n-1, countComp, countMov);
}


int main() {

    ListaDupla l;
    int i, x1, x2, x3;
    int countComp = 0, countMov = 0;
    printf("==== LISTA FLEXIVEL ====\n");

    l = new_lista_dupla();

    for(i=0; i < 10; i++)
        insert_begin_dupla(&l, i);

    printf("Apos inserir os dados: \n");
    print_lista_dupla(&l);
    
    quicksort(&l, l.size, &countComp, &countMov);
    print_lista_dupla(&l);
    delete_lista_dupla(&l);

    return 0;
}