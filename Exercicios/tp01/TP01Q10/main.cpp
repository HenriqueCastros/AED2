#include <stdio.h> 
#include <string.h>

int main ( int argc, char* argv [ ] )
{
    int n = 0;
    int x = 0;
    int end = 0;
    double val = 0;
    char c = '_';
    FILE* file = fopen("TP01Q10.txt", "w");

    scanf("%d", &n);
    for (int i = 0; i < n; i++){
        scanf("%lf", &val);
        fprintf(file, "%lf\n", val);
    }
    fclose(file);

    file = fopen("TP01Q10.txt", "r");
    fseek(file, 0, SEEK_END);

    end = ftell(file);
    end -= 2;

    while (end > 0)
    {
        /* Encontrar onde esta o primeiro '\n' 
           e uma vez encontrado salvar essa posicao em 'end'
           e escanear numero*/ 
        do{
            fseek(file, end-1, SEEK_SET);
            fscanf(file, "%c", &c);
            end--;
        }while (c != '\n' && end >= 0);
        end = (end < 0) ? 0 : end; // caso end fique negativo, previnir erro
        fseek(file, end, SEEK_SET);    
        fscanf(file, "%lf", &val);
        printf("%g\n", val);
    }
    
}