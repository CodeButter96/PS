import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//컴퓨터 수
        long B = Long.parseLong(st.nextToken());//예산
        long[] d = new long[N];//각 컴퓨터 성능 향상치
        st = new StringTokenizer(br.readLine());

        TreeMap<Long,Integer> tm = new TreeMap<>();//성능,개수
        for(int i=0;i<N;i++){
            long a = Long.parseLong(st.nextToken());
            if(!tm.containsKey(a)){
                tm.put(a,1);
            }else{
                int num = tm.get(a);
                tm.put(a,num+1);
            }
        }
        ArrayList<Integer> a = new ArrayList<>();
        long sum = 0;        
        while(true){
            long a = tm.firstKey();
            int num = tm.remove(a);  
            long next = tm.firstKey();       
            long up = 1;
            long sumTmp = sum;
            while(a+up<=next){
                int cnt = 0;
                long upSquare = up*up;
                while(cnt<=num){
                    sumTmp+=upSquare;
                    if(sumTmp>B){
                        
                    }
                    cnt++;
                }
            }
        }
        

    }
}