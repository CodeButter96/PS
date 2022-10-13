import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int[] money = {50000,10000,5000,1000,500,100,50,10};

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();//32850
			int[] cnt = new int[8];
			
			int i = 0;
			while(N>0&&i<8){
				cnt[i] = N/money[i];
				N %= money[i];
				i++;
			}
			System.out.println("#"+test_case);

			System.out.print(cnt[0]);
			for(int j=1;j<8;j++){
				System.out.print(" "+cnt[j]);
			}
			System.out.println();
		}
	}
}