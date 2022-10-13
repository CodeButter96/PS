#pragma warning(disable:4996)
#include<stdio.h>

int player[100000];

int main()
{
	int n,k,move_count,target =0;
	int i,j;

	scanf("%d%d",&n,&k);

	for(i =0;i <n;i++){
		player[i]=i +1;
	}

	for(j =0;j <n -1;j++)
	{
		move_count =0;
		printf("총k번움직이되, -1은이동횟수에치지않는다.\n");
		while(move_count <k)
		{
			target =(target +1)%n;
			printf("target: %d\n", target);
			//총k번움직이되, -1은이동횟수에치지않는다.
			if(player[target]!=-1){
				move_count++;
			}
		}
		//k번이동한후인player를-1로바꾼다.
		printf("%d Killed\n", target);
		player[target]=-1;
		//그다음숫자부터다시세어야하므로
		//최초로등장하는숫자까지이동한다.
		printf("그다음숫자부터다시세어야하므로 최초로등장하는숫자까지이동한다.\n");
		/*while (player[target] != -1) {
			target =(target +1)%n;
			printf("target: %d\n", target);
		}*/
	}

	printf("%d\n",player[target]);

	return 0;
}