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
		printf("��k�������̵�, -1���̵�Ƚ����ġ���ʴ´�.\n");
		while(move_count <k)
		{
			target =(target +1)%n;
			printf("target: %d\n", target);
			//��k�������̵�, -1���̵�Ƚ����ġ���ʴ´�.
			if(player[target]!=-1){
				move_count++;
			}
		}
		//k���̵�������player��-1�ιٲ۴�.
		printf("%d Killed\n", target);
		player[target]=-1;
		//�״������ں��ʹٽü�����ϹǷ�
		//���ʷε����ϴ¼��ڱ����̵��Ѵ�.
		printf("�״������ں��ʹٽü�����ϹǷ� ���ʷε����ϴ¼��ڱ����̵��Ѵ�.\n");
		/*while (player[target] != -1) {
			target =(target +1)%n;
			printf("target: %d\n", target);
		}*/
	}

	printf("%d\n",player[target]);

	return 0;
}