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
            PriorityQueue<Long> minHeap = new PriorityQueue<>();
            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            int N = sc.nextInt();
            long A = sc.nextLong();
            maxHeap.add(A);
            long result = 0;
            for(int i=0;i<N;i++){
                long X = sc.nextLong();
                minHeap.add(X);
                if(maxHeap.peek()>minHeap.peek()){
                    long maxTop = maxHeap.poll();
                    long minTop = minHeap.poll();
                    minHeap.add(maxTop);
                    maxHeap.add(minTop);
                }
                long Y = sc.nextLong();
                maxHeap.add(Y);
                if(maxHeap.peek()>minHeap.peek()){
                    long maxTop = maxHeap.poll();
                    long minTop = minHeap.poll();
                    minHeap.add(maxTop);
                    maxHeap.add(minTop);
                }
                result += maxHeap.peek();
            }
            result%=20171109;
            System.out.println("#"+test_case+" "+result);

		}
	}
}