#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <limits.h>
#include <math.h>

#define TOTAL_MUSICAS 170625

typedef struct Musicas {
    char id[50];
    char name[200];
    char key[20];
    char artists[10][100];
    int num_artists;
    char release_date[12];
    double acousticness;
    double danceability;
    double energy;
    int duration_ms;
    double instrumentalness;
    double valence;
    int popularity;
    float tempo;
    double liveness;
    double loudness;
    double speechiness;
    int year;
} Musica;

/**
 * isFim - descobre se String == "FIM"
*/
int isFim(char* s, int len){
    return ( s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

/**
 * readCsv - le uma linha do arquivo
 * @param bd - array onde as linhas podem ser armazenadas
*/
void readCsv( char** bd){
    char line[1024];
    FILE *stream = fopen("/tmp/data.csv", "r");
    fgets(line, 1024, stream);
    
    int i = 0;
    while(fgets(line, 1024, stream) != NULL){
        strcpy(bd[i], line);
        i++;
    } 
    fclose(stream);
}

/**
 * getInput - Processa a entrada de dados
 * @param inputIDs - array onde os IDs podem ser salvas
 * @return i - qtd de id lidas
*/
int getInput( char inputIDs[][50]){
    char ID[50];
    scanf(" %s", ID);

    int i = 0;
    while (strcmp(ID, "FIM") != 0)
    {
        strcpy(inputIDs[i], ID);
        scanf(" %s", ID);
        i++;
    }
    return i;
}

/**
 * processDate - Processa e formata a data
 * @param Song - Musica que recebera a data
 * @param date - String com valor da data
*/
void processDate(Musica* Song, char* date){
    char aux[10];
    if(strlen(date) == 10){
        strncat(aux, date+8, 2);    
        strcat(aux, "/");
        strncat(aux, date+5, 2);
        strcat(aux, "/");
        strncat(aux, date, 4);
    } else if (strlen(date) == 4){
      strcpy(aux, "01/01/");
      strcat(aux, date);
    }
    strcpy(Song->release_date, aux);
}

void getArtists(Musica* Song, char* temp){
    int len = atoi( Song->artists[0]);
    strcat(temp, "[");
    for(int i = 1; i < len; i++){
        strcat(temp, Song->artists[i]);
        strcat(temp, ", ");
    }
    strcat(temp, Song->artists[len]);
    strcat(temp, "]");
}

void addArtist(Musica* Song, char* artist, int pos){
    char test[100];
    char del = '_';
    int i = 0, j = strlen(artist)-1;

    while (artist[i] == 32) 
        i++;
    
    if(artist[i] == 34 || artist[i] == 39 )
        del = artist[i++];

    while (artist[j] == 32 || artist[j] == del) 
        j--;

    strncpy(Song->artists[pos+1], artist+i, j-i+1);
    strcat(Song->artists[pos+1], "\0");

}

/**
 * processArtists - Processa e formata os artistas
 * @param Song - Musica que recebera os artistas
 * @param artists - String com valor dos artistas
*/
void processArtists(Musica* Song, char* artists){
    char temp [300];
    int j = 0;
    int pos = 0;
    char size[2];
    for(int i = 0; i < strlen(artists); i++){
        if (artists[i] == 44 && artists[i+1] == 32 && artists[i-1] == 39) {
            temp[j++] = '\0';
            addArtist(Song, temp, pos++);
            j=0;
        }else if (artists[i] != 91 && artists[i] != 93) 
            temp[j++] = artists[i];       
    }
    temp[j++] = '\0';
    addArtist(Song, temp, pos++);
    size[0] = (char) (pos+48);
    strncpy(Song->artists[0], size,1);
}

void prepNum(char* number){
    char temp[100]; 
    int index = 0;
    int len = 0;

    char* replace = strchr(number,'e');
    if(replace){
        *replace = 'E';
        index = (int)(replace - number);
        strncpy(temp, number, index+2);
        temp[index+2] = '\0';
        for(int i = strlen(number)-1; i >=0 && number[i] != '0';i-- )
            len = i;
        strcat(temp, number+len);
        strcpy(number, temp);
        printf("%s", number);
    }else if(strlen(number) > 9)
        printf("%s", number);
    else if (atof(number) == 0.0)
        printf("0.0");
    else if(atof(number) <= 0.001 && atof(number) > 0){
        sprintf(temp, "%.2e",atof(number));
        prepNum(temp);
    }
    else
        printf("%lg", atof(number));
}

/**
 * printSong - Printa a musica conforme padrao
 * @param Song - Musica que sera printada
 * @param reg - array com informacoes
*/
void printSong(Musica* Song, char reg[20][300]){
    char temp [300];
    printf("%s", reg[8]);
    printf("%s", " ## ");
    temp[0]='\0';
    getArtists(Song, temp);
    printf("%s", temp);
    printf("%s", " ## ");
    printf("%s", reg[14]);
    printf("%s", " ## ");
    printf("%s", Song->release_date);
    printf("%s", " ## ");
    prepNum(reg[2]);
    printf("%s", " ## ");
    prepNum(reg[4]);
    printf("%s", " ## ");
    prepNum(reg[9]);
    printf("%s", " ## ");
    prepNum(reg[11]);
    printf("%s", " ## ");
    prepNum(reg[12]);
    printf("%s", " ## ");
    prepNum(reg[17]);
    printf("%s", " ## ");
    prepNum(reg[6]);
    printf("%s", " ## ");
    prepNum(reg[5]);
    printf("\n");
}

void processSong(char* line, Musica* Song){
    int j = 0, x = 0;
    char del = '\0';
    char temp[1024];
    char reg[20][300];

    for (int i = 0; i < strlen(line); i++)
    {
        if((int)line[i] == 34 && (int)line[i-1] == 44)
            del = line[i];
        else if( del != '\0' && (int)line[i+1] == 44 && line[i] == del)
            del = '\0';
        else if ((int) line[i] == 44 && line[i + 1] != 32)
            temp[j++] = '\t';
        else if ((int) line[i] == 34 && line[i + 1] == 34)
            temp[j++] = line[i++];
        else
            temp[j++] = line[i];
        
    }
    temp[j]='\0';
    j = 0;
    for (int i = 0; i < strlen(temp); i++)
    {
        if( temp[i] != '\t')
            reg[x][j++] = temp[i];
        else{
            reg[x][j++] = '\0';
            j = 0;
            x++;
        }
    }
    reg[18][j]= '\0';
    
    Song->valence = atof(reg[0]);
    Song->year = atoi(reg[1]);
    Song->acousticness = atof(reg[2]);
    processArtists(Song, reg[3]);
    Song->danceability = atof(reg[4]);
    Song->duration_ms = atof(reg[5]);
    Song->energy = atof(reg[6]);
    strcpy(Song->id, reg[8]);
    Song->instrumentalness = atof(reg[9]);
    strcpy(Song->key, reg[10]);
    Song->liveness = atof(reg[11]);
    Song->loudness = atof(reg[12]);
    strcpy(Song->name, reg[14]);
    Song->popularity = atoi(reg[15]);
    processDate(Song, reg[16]);
    Song->speechiness = atof(reg[17]);
    Song->tempo = atof(reg[18]);
}


void pprintSong(char* line, Musica* Song){
    int j = 0, x = 0;
    char del = '\0';
    char temp[1024];
    char reg[20][300];

    for (int i = 0; i < strlen(line); i++)
    {
        if((int)line[i] == 34 && (int)line[i-1] == 44)
            del = line[i];
        else if( del != '\0' && (int)line[i+1] == 44 && line[i] == del)
            del = '\0';
        else if ((int) line[i] == 44 && line[i + 1] != 32)
            temp[j++] = '\t';
        else if ((int) line[i] == 34 && line[i + 1] == 34)
            temp[j++] = line[i++];
        else
            temp[j++] = line[i];
        
    }
    temp[j]='\0';
    j = 0;
    for (int i = 0; i < strlen(temp); i++)
    {
        if( temp[i] != '\t')
            reg[x][j++] = temp[i];
        else{
            reg[x][j++] = '\0';
            j = 0;
            x++;
        }
    }
    reg[18][j]= '\0';
    
    Song->valence = atof(reg[0]);
    Song->year = atoi(reg[1]);
    Song->acousticness = atof(reg[2]);
    processArtists(Song, reg[3]);
    Song->danceability = atof(reg[4]);
    Song->duration_ms = atof(reg[5]);
    Song->energy = atof(reg[6]);
    strcpy(Song->id, reg[8]);
    Song->instrumentalness = atof(reg[9]);
    strcpy(Song->key, reg[10]);
    Song->liveness = atof(reg[11]);
    Song->loudness = atof(reg[12]);
    strcpy(Song->name, reg[14]);
    Song->popularity = atoi(reg[15]);
    processDate(Song, reg[16]);
    Song->speechiness = atof(reg[17]);
    Song->tempo = atof(reg[18]);

    printSong(Song,reg);
}

/**
 * processId - Encontra a dtring baseado em um ID
 * @param id - ID a ser utilizada para procurar
 * @param bd - banco de dados de musicas
 * @return song - posicao da musica encontrada dentro do banco de dados
*/
int processId(char id[50], char **bd){
    int song = -1;
    for(int i = 0; i< TOTAL_MUSICAS && song == -1; i++){
        if(strstr(bd[i], id) != NULL){
            song = i;
        }
    }
    return song;
}

//CELULA DUPLA
 //=============================================================================
 typedef struct CelulaDupla{
    Musica* elemento;
    int line;
    struct CelulaDupla *prox, *ant;
}CelulaDupla;
//=============================================================================
CelulaDupla* new_celula_dupla(Musica* elemento, int pos){
    CelulaDupla *temp = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    temp->elemento = (Musica*)malloc(sizeof(Musica));
    temp->elemento = elemento;
    temp->line = pos;
    temp->ant = NULL;
    temp->prox = NULL;
    return temp;
}

CelulaDupla* newTmp_celula_dupla( int pos){
    CelulaDupla *temp = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    temp->elemento = (Musica*)malloc(sizeof(Musica));
    temp->elemento = NULL;
    temp->line = pos;
    temp->ant = NULL;
    temp->prox = NULL;
    return temp;
}
//=============================================================================

//LISTA DUPLAY
//=============================================================================
typedef struct ListaDupla{
    struct CelulaDupla *primeiro, *ultimo;
    int size;
} ListaDupla;
//=============================================================================
ListaDupla new_lista_dupla(){
    ListaDupla temp;
    temp.primeiro = temp.ultimo = newTmp_celula_dupla(-1);
    temp.size = 0;
    return temp;
}
//=============================================================================
int size_lista_dupla(ListaDupla *l){
    return l->size;
}
//=============================================================================
void insert_begin_dupla(ListaDupla *l, Musica* elemento, int pos){
    
    CelulaDupla *temp = newTmp_celula_dupla(-1);
    temp->prox = l->primeiro;

    l->primeiro->elemento = elemento;
    l->primeiro->line = pos;  
    l->primeiro->ant = temp;   
    l->primeiro = temp;
    l->size++;
}
//=============================================================================
void insert_end_dupla(ListaDupla *l, Musica* elemento, int pos){    
    l->ultimo->prox = new_celula_dupla(elemento, pos);
    l->ultimo->prox->ant = l->ultimo;
    l->ultimo = l->ultimo->prox;
    l->size++;
}
//=============================================================================
void insert_at_dupla(ListaDupla *l, Musica* elemento, int line, int pos){
    
    if(pos < 0 || pos > l->size)
        printf("Erro ao tentar inserir na posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if (pos == 0)
        insert_begin_dupla(l, elemento, line);
    else if (pos == l->size)
        insert_end_dupla(l, elemento, line);
    else{
        
        CelulaDupla *ant = l->primeiro;
        for(int i=0; i<pos;i++) 
            ant = ant->prox;
            
        CelulaDupla *temp = new_celula_dupla(elemento, line);  
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
    Musica* elemento = temp->elemento;

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
        Musica* elemento = temp->elemento;

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
//=============================================================================
void print_lista_dupla(ListaDupla *l, char** bd){
    CelulaDupla *i;
    for (i = l->primeiro->prox; i != NULL; i = i->prox)
    {
        pprintSong( bd[i->line], i->elemento);
    }
}
//=============================================================================
void print_lista_dupla_inverso(ListaDupla *l, char** bd){
    CelulaDupla *i;
    for (i = l->ultimo; i != l->primeiro; i = i->ant)
    {
        pprintSong( bd[i->line], i->elemento);
    }
}
//=============================================================================
void delete_lista_dupla(ListaDupla *l){
    while(l->size > 0)
        remove_at_dupla(l,0);
    free(l->primeiro);
}
//=============================================================================
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
//QUICKSORT
void swap(ListaDupla *l, int i, int j) {
    if(i != j){
        CelulaDupla* celJ = remove_at_dupla(l, j);
        insert_at_dupla(l, celJ->elemento, celJ->line, i);

        CelulaDupla* celI = remove_at_dupla(l, i+1);
        insert_at_dupla(l, celI->elemento, celI->line, j);
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
        while (celI->elemento->duration_ms < pivo->elemento->duration_ms)
        {
            i++;
            celI = celI->prox;
            *countComp = *countComp + 1;
        } 
        while (celJ->elemento->duration_ms > pivo->elemento->duration_ms){
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

int main ( int argc, char* argv [ ] )
{
    char idList[100][50];
    double total;
    int len = getInput(idList);
    int countComp = 0, countMov = 0;
    Musica mySongs[len];
    char **bdMusicas;
    ListaDupla l;
    l = new_lista_dupla();
    clock_t inicio, fim;

    bdMusicas = (char **)malloc(sizeof(char *) * 170700);
    for (int i = 0; i < 170625; i++)
        bdMusicas[i] = (char *)malloc(sizeof(char *) * 1024);

    readCsv(bdMusicas);
    int song = 0;
    for(int i = 0; i < len; i++){
        song = processId(idList[i], bdMusicas);
        processSong(bdMusicas[song], &mySongs[i]);
        insert_begin_dupla(&l, &mySongs[i], song);
    }

    inicio = clock();
    quicksort(&l, l.size, &countComp, &countMov);
    fim = clock();
    
    print_lista_dupla(&l, bdMusicas);
    FILE * file = fopen("matrícula_quicksort2.txt","w");
    fprintf(file, "711260\t%d\t%d\t%.20f", countComp, countMov, total);
    fclose(file);
    return 0;
}