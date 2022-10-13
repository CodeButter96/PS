import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	static String s;
	static int[] number;
	static int N, M;
	static int res;
	static void swap(int[] number,int i,int j){
		int tmp = number[i];
		number[i] = number[j];
		number[j] = tmp;
	}

	static int val(int[] number){
		int N = number.length;
		int res = 0;
		int mult = 1;
		for(int i=N-1;i>=0;i--){
			res+=number[i]*mult;
			mult*=10;
		}
		return res;
	}

	static void dfs(int n, int cnt){
		if(cnt == M){
			res = Math.max(res,val(number));
			return;
		}

		for(int i=n;i<N-1;i++){
			for(int j=i+1;j<N;j++){
				if(number[i]<=number[j]||N==2){
					swap(number, i, j);
					dfs(i,cnt+1);
					swap(number, i, j);
				}
			}
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			s = sc.next();
			N = s.length();
			number = new int[N];
			for(int i=0;i<N;i++){
				number[i] = s.charAt(i) - '0';
			}
			if(N<M)M=N;
			M = sc.nextInt();
			
			
			res = 0;
			dfs(0, 0);
			System.out.println("#"+test_case+" "+res);

		}
	}
}