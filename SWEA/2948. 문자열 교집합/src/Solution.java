import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/input.txt"));

		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int M = sc.nextInt();
            //TreeSet<String> ts = new TreeSet<>();
            HashSet<String> ts = new HashSet<>();
            for(int i=0;i<N;i++){
                ts.add(sc.next());
            }
            int cnt = 0;
            for(int i=0;i<M;i++){
                if(ts.contains(sc.next())){cnt++;}
            }
            System.out.println("#"+test_case+" "+cnt);
			
		}
	}
}