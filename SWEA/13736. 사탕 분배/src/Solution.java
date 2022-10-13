import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static long powerMod(int x, long p, long m){
        if(p==0) return 1;
        long res = powerMod(x, p/2, m);
        res *= res;
        res%=m;
        if(p%2!=0)res=(res*x)%m;
        return res;
    }
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            long A = sc.nextInt();//나연 사탕 개수
            long B = sc.nextInt();//다현 사탕 개수
            long K = sc.nextInt();
            System.out.println("A: "+A+" B: "+B);
            long C = A+B;
            K = powerMod(2, K, C);
            A = (K*A)%C;
            B = C-A;
            long min = A<B?A:B;
            System.out.println("#"+test_case+" "+min);
		}
	}
}
