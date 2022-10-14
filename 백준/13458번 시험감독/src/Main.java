import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] A = new int[N];
        for(int i=0;i<N;i++){
            A[i] = Integer.parseInt(inputs[i]);
        }
        inputs = br.readLine().split(" ");
        int B = Integer.parseInt(inputs[0]);
        int C = Integer.parseInt(inputs[1]);
        long cnt = 0;
        for(int i=0;i<N;i++){
            A[i]-=B;
            cnt++;
            if(A[i]>0){
                cnt += A[i]/C;
                if(A[i]%C!=0){cnt++;}
            }            
        }
        System.out.println(cnt);
    }
}
