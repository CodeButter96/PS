import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{
    static int traversal(String[][] tree, int index, int N){
        String s = tree[index][0];
        switch(s){
            case "+":
            return traversal(tree, Integer.parseInt(tree[index][1]), N)+traversal(tree, Integer.parseInt(tree[index][2]), N);
            case "-":  
            return traversal(tree, Integer.parseInt(tree[index][1]), N)-traversal(tree, Integer.parseInt(tree[index][2]), N);         
            case "*":  
            return traversal(tree, Integer.parseInt(tree[index][1]), N)*traversal(tree, Integer.parseInt(tree[index][2]), N);       
            case "/":
            return traversal(tree, Integer.parseInt(tree[index][1]), N)/traversal(tree, Integer.parseInt(tree[index][2]), N);
            default:
            return Integer.parseInt(s);
        }
    }
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

		
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = Integer.parseInt(br.readLine());
            int answer = 0;
            String[][] tree = new String[N+1][3];
            for(int i=1;i<=N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                tree[i][0] = st.nextToken();
                if(st.hasMoreTokens()){
                    tree[i][1] = st.nextToken();
                    tree[i][2] = st.nextToken();
                }
            }
            answer = traversal(tree,1,N);
            sb.append("#"+test_case+" "+answer+"\n");
		}
        System.out.println(sb.toString());
	}
}