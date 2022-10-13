import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;
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
			int N = sc.nextInt();
			int[][] students = new int[N][2];
			int[] bokdo = new int[201];
			for(int i=0;i<N;i++){
				students[i][0] = sc.nextInt();//지금 방
				students[i][1] = sc.nextInt();//가야하는 방
				int now = (students[i][0]+1)/2;
				int next = (students[i][1]+1)/2;
				int start, end;
				if(now>next){
					start = next; end = now;
				}else{
					start = now; end = next;
				}
				for(int j=start;j<=end;j++){
					bokdo[j]++;
				}
			}
			Arrays.sort(bokdo);
			System.out.println("#"+test_case+" "+bokdo[200]);

			
			
		}
	}
}