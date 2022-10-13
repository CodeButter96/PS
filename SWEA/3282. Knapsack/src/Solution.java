import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt(), K = sc.nextInt();//물건 수, 가방 부피
			int[][] VC = new int[N+1][2];
			for(int i=1;i<N+1;i++){
				VC[i][0] = sc.nextInt();//부피
				VC[i][1] = sc.nextInt();//가격
			}
			int[][] P = new int[N+1][K+1];
			for(int i=1;i<N+1;i++){
				for(int j=1;j<K+1;j++){
					if(VC[i][0]>j){
						P[i][j] = P[i-1][j];
					}else{
						P[i][j] = Math.max(VC[i][1]+P[i-1][j-VC[i][0]], P[i-1][j]);
					}
				}
			}
			System.out.println("#"+test_case+" "+P[N][K]);


		}
	}
}