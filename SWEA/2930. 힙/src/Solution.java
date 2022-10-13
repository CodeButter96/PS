import java.util.Collections;
import java.util.PriorityQueue;
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
            System.out.print("#"+test_case+" ");
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int N = sc.nextInt();
            for(int i=0;i<N;i++){
                int q = sc.nextInt();
                switch(q){
                    case 1:{
                        int x = sc.nextInt();
                        pq.add(x);
                    }
                    break;
                    case 2:{
                        if(pq.isEmpty())System.out.print(-1+" ");
                        else System.out.print(pq.poll()+" ");
                    }
                    break;
                }
            }
			System.out.println();

		}
	}
}