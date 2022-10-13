import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{
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
            int answer = 1;
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();
                char node = st.nextToken().charAt(0);
                if(st.hasMoreTokens()){
                    if(node>='0'&&node<='9'){
                        answer = 0;
                    }
                }
                else{
                    if(node<'0'&&node>'9'){
                        answer = 0;
                    }
                }
            }
            sb.append("#"+test_case+" "+answer+"\n");
		}
        System.out.println(sb.toString());
	}
}