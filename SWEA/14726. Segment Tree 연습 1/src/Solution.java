import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{
    static int max(int a, int b){
        return (a > b) ? a : b;
    }

    static int min(int a, int b){
        return (a < b) ? a : b;
    }

    static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }

	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/sample_in.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
		int T;
        T = Integer.parseInt(br.readLine());
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb = new StringBuilder().append("#"+test_case+" ");
		
			String strings[] = br.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int q = Integer.parseInt(strings[1]);
            int[] arr = new int[n];
            int[] segtree_max = new int[2*n];            
            int[] segtree_min = new int[2*n];
            int i = 0;
            for(String s : br.readLine().split(" ")){
                arr[i++] = Integer.parseInt(s);
            }
            for(i=0;i<n;i++){
                segtree_max[n + i] = arr[i]; 
                segtree_min[n + i] = arr[i]; 
            }
            for(i=n-1;i!=0;i--){                
                segtree_max[i] = max(segtree_max[i*2],segtree_max[(i*2)+1]);                
                segtree_min[i] = min(segtree_min[i*2],segtree_min[(i*2)+1]);
            }
            for(i=0;i<q;i++){
                strings = br.readLine().split(" ");
                int a = Integer.parseInt(strings[0]);                
                int b = Integer.parseInt(strings[1]);                
                int c = Integer.parseInt(strings[2]);
                switch(a){
                    case 0:{
                        //System.out.println("Update arr["+b+"] to "+c+" ");
                        segtree_max[b+=n]=c;                        
                        segtree_min[b]=c;
                        while((b/=2)!=0){                            
                            segtree_max[b] = max(segtree_max[b*2],segtree_max[(b*2)+1]);                
                            segtree_min[b] = min(segtree_min[b*2],segtree_min[(b*2)+1]);
                        }
                        //printArr(segtree_max);                        
                        //printArr(segtree_min);
                    }
                    break;
                    case 1:{
                        int Min = Integer.MAX_VALUE;
                        int Max = Integer.MIN_VALUE;
                        for(b+=n,c+=n;b!=c;b/=2,c/=2){
                            if((b%2)!=0){
                                Min = min(Min,segtree_min[b]);
                                Max = max(Max,segtree_max[b++]);
                            }                   
                            if((c%2)!=0){
                                Min = min(Min,segtree_min[--c]);
                                Max = max(Max,segtree_max[c]);
                            }                                                       
                        }                                              
                        //System.out.println(Max-Min);
                        sb.append((Max-Min)+" ");
                    }
                    break;
                }
            }
            sb.append("\n");
            System.out.print(sb.toString());

		}
	}
}