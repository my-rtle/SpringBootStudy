#include<stdio.h>

int main(void)
{
    int num,i,j,m;
    printf("시작할 숫자를 입력하시오:");
    scanf("%d",&num);
    for (i=num;i<=9;i++)
        printf("\n")
        for (j=1;j<=9;j++)
            {m=i*j;
            printf("%d*%d=%d",i,j,m);}
 
 return 0;   
}