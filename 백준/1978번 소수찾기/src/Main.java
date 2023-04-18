import java.io.*;
import java.util.*;

public class Main {
    static boolean[] prime = new boolean[1001];
    static boolean[] checked = new boolean[1001];
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        checked[0] = true;
        checked[1] = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        for(int i : numbers){
            if(isPrime(i)){cnt++;}
        }
        System.out.println(cnt);
    }

    static boolean isPrime(int num){
        if(checked[num]){return prime[num];}
        for(int i=0;i<=num/2;i++){
            if(!checked[i]){
                prime[i] = isPrime(i);
            }
            if(!prime[i]){continue;}
            if(num%i==0){return false;}
        }
        return true;
    }
}
