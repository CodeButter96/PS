import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int M = Integer.parseInt(br.readLine());
        int set = 0;
        int full = (int)Math.pow(2,20)-1;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int target = 0;
            if(st.hasMoreTokens()){target = Integer.parseInt(st.nextToken());}
            switch(command){
                case "add":
                    set |= 1<<(target-1);
                    break;
                case "remove":
                    set &= ~(1<<(target-1));
                    break;
                case "check":
                    if((set&1<<(target-1))!=0){sb.append(1+"\n");}
                    else{sb.append(0+"\n");}
                    break;
                case "toggle":
                    set ^= 1<<(target-1);
                    break;
                case "all":
                    set = full;
                    break;
                case "empty":
                    set = 0;
            }
        }
        System.out.print(sb.toString());
    }
}
