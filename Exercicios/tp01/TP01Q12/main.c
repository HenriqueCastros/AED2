#include <stdio.h> 
#include <string.h>

/**
 * isFim - descobre se String == "FIM"
*/
int isFim(char* s, int len){
    return ( s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

/**
* isPalin - descobre se String e palindromo
* @return - 1 ou 0
*/
int isPalin(char val[], int pos) { 
    int con = 1;
    int len = strlen(val) -1 ;
        if(pos <= len/2){
            if (val[pos] != val[len-pos-1])
                con = 0;
            else
                con = isPalin(val, ++pos);
        } else 
            con = 1;

    return con;
} 

int main ( int argc, char* argv [ ] )
{
    char val[1024];
    scanf(" %[^\n]", val);
    while (isFim(val, strlen(val)) == 0)
    {
        if(isPalin(val, 0))
            printf("SIM\n");
        else
            printf("NAO\n");
        scanf(" %[^\n]", val);
    }
    
    return 0;
}