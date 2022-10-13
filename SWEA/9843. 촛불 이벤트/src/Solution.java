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
            long N = sc.nextLong();
            long k = (-1+(long)Math.sqrt(1+8*N))/2;
            if(k*(k+1)/2!=N){
                k=-1;
            }
            System.out.println("#"+test_case+" "+k);
		}
	}
}