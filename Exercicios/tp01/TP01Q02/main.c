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
int isPalin(char val[]) { 
    int i = 0;
    int len = strlen(val) - 1;
    int con = 1;

    while((i <= len/2) && (con == 1)){
        if(val[i] != val[len-i-1])
            con = 0;
        i++;
    } 

    return con;
} 

int main ( int argc, char* argv [ ] )
{
    char val[1024];
    scanf(" %[^\n]", val);
    while (isFim(val, strlen(val)) == 0)
    {
        if(isPalin(val))
            printf("SIM\n");
        else
            printf("NAO\n");
        scanf(" %[^\n]", val);
    }
    
    return 0;
}