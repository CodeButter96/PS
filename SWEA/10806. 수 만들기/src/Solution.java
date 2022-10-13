import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{
        class Node{
            int X;
            int cnt;
            public Node(int X, int cnt) {
                this.X = X;
                this.cnt = cnt;
            }

        }
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer = 0;
            int N = sc.nextInt();
            int[] A = new int[N];
            for(int i=0;i<N;i++){
                A[i] = sc.nextInt();
            }
            int K = sc.nextInt();
            PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2)->n1.cnt-n2.cnt);
            pq.add(new Node(K,0));
            while(!pq.isEmpty()){
                Node cur = pq.poll();
                if(cur.X==0){
                    answer = cur.cnt;
                    break;
                }
                pq.add(new Node(0, cur.cnt+cur.X));
                for(int i=0;i<N;i++){
                    pq.add(new Node(cur.X/A[i], cur.cnt+cur.X%A[i]));
                }
            }
            System.out.println("#"+test_case+" "+answer);

		}
	}
}