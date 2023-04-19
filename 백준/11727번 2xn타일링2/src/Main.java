import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n+1];
        System.out.println(numTile(n));
    }

    static int numTile(int N){
        if(N==1){
            return 1;
        }
        if(N==2){
            return 3;
        }
        if(dp[N]!=0){
            return dp[N];
        }
        dp[N] = (numTile(N-1) + 2*numTile(N-2))%10007;
        return dp[N];
    }
}
