import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[3];
        arr[0] = Integer.parseInt(st.nextToken());
        arr[1] = Integer.parseInt(st.nextToken());
        arr[2] = Integer.parseInt(st.nextToken());
        sort(arr);
        while(!(arr[0]==0&&arr[1]==0&&arr[2]==0)){
            if(arr[0]*arr[0] == arr[1]*arr[1] + arr[2]*arr[2]){
                System.out.println("right");
            }else{                
                System.out.println("wrong");
            }
            st = new StringTokenizer(br.readLine());
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());
            sort(arr);
        }
    }
    public static void sort(int[] arr){
        int tmp;
        if(arr[1]>arr[0]){
            tmp = arr[0];
            arr[0] = arr[1];
            arr[1] = tmp;
        }
        if(arr[2]>arr[0]){
            tmp = arr[0];
            arr[0] = arr[2];
            arr[2] = tmp;
        }
    }
}
