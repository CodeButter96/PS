import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String s = br.readLine();
            if(s.charAt(0)=='0'){
                break;
            }
            System.out.println(isPalindrome(s));
        }
    }

    private static String isPalindrome(String numStr){
        int len = numStr.length();
        if(len==1){
            return "yes";
        }
        int mid = len/2;
        for(int i=0;i<mid;i++){
            if(numStr.charAt(i)!=numStr.charAt(len-1-i)){
                return "no";
            }
        }
        return "yes";
    }
}
