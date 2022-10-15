import java.io.*;
import java.util.*;
public class Main{
    public static int N;
    public static int[] T;
    public static int[] P;
    public static boolean[] visited;
    public static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = new int[N];
        P = new int[N];
        visited = new boolean[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            dfs(i,0);
        }
        System.out.println(max);
    }

    public static void dfs(int d, int money){
        //ex)7일이면 d=6
        //T가 1일이면 d+T[d]=7 가능
        //T가 2일이면 d+T[d]=8 불가능
        if(d+T[d]>N){
            max = Math.max(max,money);
            return;
        }
        else if(d+T[d]==N){
            max = Math.max(max,money+P[d]);
            return;
        }
        money+=P[d];
        d+=T[d];
        for(int i=d;i<N;i++){
            dfs(i,money);
        }
    }
}