import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] A;
    static int[] numOps;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        numOps = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            numOps[i] = Integer.parseInt(st.nextToken());
        }
        dfs(A[0],1);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int prev, int idx){
        if(idx>=N){
            if(prev>max){
                max = prev;
            }
            if(prev<min){
                min = prev;
            }
            return;
        }
        for(int i=0;i<4;i++){
            if(numOps[i]!=0){
                numOps[i]--;
                dfs(cal(prev,A[idx],i),idx+1);
                numOps[i]++;
            }
        }
    }

    static int cal(int op1, int op2, int op){
        switch(op){
            case 0: return op1 + op2;
            case 1: return op1 - op2;
            case 2: return op1 * op2;
            default: return op1 / op2;
        }
    }
}