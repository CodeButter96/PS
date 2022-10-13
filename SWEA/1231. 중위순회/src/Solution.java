import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static void traversal(char[] tree,int index, int N){
        if(index > N){
            return;
        }            
        traversal(tree,2*index,N);
        System.out.print(tree[index]);
        traversal(tree,2*index+1,N);
    }
	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            sc.nextLine();
            char[] tree = new char[N+1];
            for(int i=1;i<=N;i++){
                int n = sc.nextInt();
                //String s = sc.nextLine();
                tree[i] = sc.nextLine().charAt(1);
                //System.out.println(s);
            }
            System.out.print("#"+test_case+" ");
            traversal(tree,1,N);
            System.out.println();
		}
	}
}