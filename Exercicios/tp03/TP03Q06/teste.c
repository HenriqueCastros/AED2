#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include <limits.h>

typedef struct Celula{
    int elemento;
    struct Celula *prox;
}Celula;
//=============================================================================
Celula* new_celula(int elemento){
    Celula *temp = (Celula*)malloc(sizeof(Celula));
    temp->elemento = elemento;
    temp->prox = NULL;
    return temp;
}
//FILA
//=============================================================================
typedef struct Fila{
    struct Celula *primeiro, *ultimo;
    int size;
} Fila;
//=============================================================================
Fila new_fila(){
    Fila temp;
    temp.primeiro = temp.ultimo = NULL;
    temp.size = 0;
    return temp;
}
//=============================================================================
int size_fila(Fila *f){
    return f->size;
}
//=============================================================================
int dequeue_fila(Fila *f){
    
    if (f->primeiro == f->ultimo){
        printf("\nA fila esta vazia!\n");
        return INT_MIN;
    }
    //Caso esse seja a ultima celula
    int val; 
    if (f->primeiro == f->ultimo) {
        val = f->primeiro->elemento;
        free(f->primeiro);
        f->primeiro = NULL;
        f->ultimo = NULL;
        
    }
    else//Caso existam mais de uma celula na fila
    {   
        Celula* temp = f->primeiro;
        val = temp->elemento;
        f->primeiro = f->primeiro->prox;
        f->ultimo->prox = f->primeiro;
        free(temp);
    }
    
    printf("tste ref == %d\n", (f->primeiro == f->ultimo->prox));
    f->size--;
    return val;
}
//=============================================================================
void enqueue_fila(Fila *f, int elemento){
    Celula* tmp = new_celula(elemento);
    if(f->primeiro == NULL){
        f->primeiro = tmp;
        f->size++;
    }else if(f->size >= 5){
        dequeue_fila(f);
        enqueue_fila(f, elemento);
    }else{
        f->ultimo->prox = tmp;
        f->size++;
    }
    f->ultimo = tmp;
    f->ultimo->prox = f->primeiro;
}//=============================================================================
void print_fila(Fila *f){
    Celula *i;
    int j;
    printf("[");
    for (i = f->primeiro, j = 0; i != f->ultimo->prox && j < f->size; i = i->prox, j++)
    {
        printf("%d, ", i->elemento);
    }
    printf("] \n");

}
//=============================================================================
void delete_fila(Fila *f){
    while(f->size > 0)
        dequeue_fila(f);
    free(f->primeiro);
}
//=============================================================================


int main(){
    char str[] = "I 579wiJu8HXaGXod6rU3NiB";

    char* token = strtok(str, " ");
    //Musica holder = malloc(sizeof(Musica));

    if(strcmp(token, "I") == 0){
        char* token =strtok(NULL, " ");
        printf("%s\n", token);
    } else if(strcmp(token, "R") == 0){
        printf("aaaa\n");
    }

    return 0;
}