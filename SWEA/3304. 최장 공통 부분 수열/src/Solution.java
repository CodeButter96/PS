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
			String s1 = sc.next(), s2 = sc.next();
			int n = s1.length(), m = s2.length();//6 6
			int[][] lsc = new int[n+1][m+1];//7 7
			for(int i=1;i<n+1;i++){
				char c1 = s1.charAt(i-1);
				for(int j=1;j<m+1;j++){ 
					char c2 = s2.charAt(j-1);
					if(c1==c2){
						lsc[i][j] = lsc[i-1][j-1]+1;
					}else{
						lsc[i][j] = Math.max(lsc[i-1][j], lsc[i][j-1]);
					}
				}
			}
			System.out.println("#"+test_case+" "+lsc[n][m]);

		}
	}
}