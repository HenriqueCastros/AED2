#include <stdio.h>
#include <stdlib.h>
#include <string.h>
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
    FILE* stream = fopen("/tmp/data.csv", "r");
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
int getInput( char** inputIDs){
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

int main ( int argc, char* argv [ ] )
{
    char **bdMusicas;
    char **inputIDs;
    Musica mySongs[500];

    int len = 0;
    bdMusicas = (char**) malloc(sizeof(char *) * TOTAL_MUSICAS);
    inputIDs = (char**) malloc(sizeof(char *) * 400);

    for (int i = 0; i < TOTAL_MUSICAS; i++)
        bdMusicas[i] = (char*) malloc(sizeof(char*) * 1024);
    
    for (int i = 0; i < 400; i++)
        inputIDs[i] = (char*) malloc(sizeof(char*) * 50);
    
    readCsv(bdMusicas);
    len = getInput(inputIDs);

    int song = 0;
    for(int i = 0; i < len; i++){
        song = processId(inputIDs[i], bdMusicas);
        processSong(bdMusicas[song], &mySongs[i]);
    }
    
    return 0;
}