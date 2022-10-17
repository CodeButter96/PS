import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/input.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //N! = N * N-1!
        //N! = 1*2*3*4*5*6*7*8*9*...*N;
        BigInteger nFac = factorial(N);
        int cnt = 0;
        while(nFac.remainder(BigInteger.TEN).compareTo(BigInteger.ZERO)==0){
            cnt++;
            nFac = nFac.divide(BigInteger.TEN);
        }
        System.out.println(cnt);
    }

    public static BigInteger factorial(int N) {
        if(N==0||N==1){
            return BigInteger.valueOf(1);
        }
        return factorial(N-1).multiply(BigInteger.valueOf(N));
    }
}
