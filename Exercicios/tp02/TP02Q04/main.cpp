#include <stdio.h> 
#include <string.h>
#include <time.h>
#include <stdbool.h>
#include <stdlib.h> 

/**
 * isFim - descobre se String == "FIM"
*/
int isFim(char* s, int len){
    return ( s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

void swap(char *xp, char *yp)
{
    char* temp = (char*) malloc(sizeof(char) * 50);
    strcpy(temp,xp);
	strcpy(xp,yp);
	strcpy(yp,temp);

}

bool binSearch(char arr[][50], int esq, int dir, char* find, int* count)
{
    bool resp = false;
    while(esq <= dir && !resp){
        int meio = (esq +dir)/2;
        if(strcmp(arr[meio], find) == 0){
            resp = true;
            dir = -1;
        }else if(strcmp(arr[meio], find) > 0){
            dir = meio -1;
        }else {
            esq = meio + 1;
        }
    }
    return resp;
}

int main ( int argc, char* argv [ ] )
{
    FILE * file = fopen("matrícula_binaria.txt","w");
    char aux[300][50];    
    char val[50];
    int i = 0, h, g;
    int count = 0;
    clock_t inicio, fim;
    double total;
    
    scanf(" %[^\n]", aux[i]);
    while (isFim(aux[i], strlen(aux[i])) == 0)
    {
        i++;
        scanf(" %[^\n]", aux[i]);
    }

    for (h = 0; h < i-1; h++)  
        for (g = 0; g < i-h-1; g++) 
            if (strcmp(aux[g], aux[g+1]) > 0)
                swap(aux[g], aux[g+1]);

    inicio = clock();
    scanf(" %[^\n]", val);
    while (isFim(val, strlen(val)) == 0)
    {
        if(binSearch(aux, 0, 299, val, &count))
            printf("SIM\n");
        else
            printf("NAO\n");

        scanf(" %[^\n]", val);
    }
    fim = clock();
    total = ((fim - inicio) / (double)CLOCKS_PER_SEC); 
    fprintf(file, "711260\t%.20f\t%d", total, count);
    fclose(file);
    return 0;
}