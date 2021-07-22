#include <stdio.h> 
#include <string.h>

/**
 * isFim - descobre se String == "FIM"
*/
int isFim(char* s, int len){
    return ( s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

/**
 * isVog - descobre se String e composta por vogais
*/
int isVog(char* s, int len){
    int flag = 1;
    int i = 0;
    while(flag == 1 && i < len){
        flag = (s[i] == 'a' || s[i] == 'A' ||
                s[i] == 'e' || s[i] == 'E' ||
                s[i] == 'i' || s[i] == 'I' ||
                s[i] == 'o' || s[i] == 'O' ||
                s[i] == 'u' || s[i] == 'U' );
        i++;
    }
    return flag;
}

/**
 * isCon - descobre se String e composta por consoantes
*/
int isCon(char* s, int len){
    int flag = 1;
    int i = 0;
    while(flag == 1 && i < len){
        if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <='Z')) {
            flag = (s[i] != 'a' && s[i] != 'A' &&
                    s[i] != 'e' && s[i] != 'E' &&
                    s[i] != 'i' && s[i] != 'I' &&
                    s[i] != 'o' && s[i] != 'O' &&
                    s[i] != 'u' && s[i] != 'U' );
        }else
            flag = 0;
        
        i++;
    }
    return flag;
}

/**
 * isInt - descobre se String forma um int
*/
int isInt(char* s, int len){
    int flag = 1;
    flag = ((s[0] >= '0' && s[0] <= '9') || s[0] == '-' || s[0] == '+' );
    int i = 1;
    while(flag && i < len){
        flag = (s[i] >= '0' && s[i] <= '9' );
        i++;
    }
    return flag;
}

/**
 * isDouble - descobre se String forma um int
*/
int isDouble(char* s, int len){
    int flag = 1;
    int countPonto = 0;
    int i = 1;

    if(s[0] == '.' || s[0] == ',')
        countPonto++;
    else 
        flag = (s[0] >= '0' && s[0] <= '9' );

    while(flag && i < len && countPonto <=1){
        if(s[i] == '.' || s[i] == ',')
            countPonto++;
        else 
            flag = (s[i] >= '0' && s[i] <= '9' );
        i++;
    }
    flag = (flag&&(countPonto<=1)) ? 1 : 0;
    return flag;
}

int main(int argc, char const *argv[])
{
    char val[1024];
    char* temp;
    scanf(" %[^\n]", val);  
    while (isFim(val, strlen(val)) == 0)
    {
        temp = isVog(val, strlen(val)-1)? "SIM " : "NAO ";
        printf("%s", temp);
        temp = isCon(val, strlen(val)-1)? "SIM " : "NAO ";
        printf("%s", temp); 
        temp = isInt(val, strlen(val)-1)? "SIM " : "NAO ";
        printf("%s", temp); 
        temp = isDouble(val, strlen(val)-1)? "SIM\n" : "NAO\n";
        printf("%s", temp);

        scanf(" %[^\n]", val);
    }
    return 0;
}