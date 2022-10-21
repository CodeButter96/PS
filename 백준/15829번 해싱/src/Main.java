import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int L = Integer.parseInt(br.readLine());
        
        String input = br.readLine();
        long hash = 0;
        for(int i=L-1;i>=0;i--){
            hash = (hash*31 + (input.charAt(i)-'a'+1))%1234567891;
        }
        
        System.out.println(hash);
    }
}
