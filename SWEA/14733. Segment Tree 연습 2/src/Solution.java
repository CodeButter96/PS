import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{
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

		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb = new StringBuilder().append("#"+test_case+" ");
		
			String strings[] = br.readLine().split(" ");
            int n = Integer.parseInt(strings[0]);
            int q = Integer.parseInt(strings[1]);            
            int i = 0;
            while(n>2*i){
                i++;
            }
            int m = (int)Math.pow(2, i);

            int[] arr = new int[m];
            int[] segtree_even = new int[m];            
            int[] segtree_odd = new int[m];
            i=0;
            for(String s : br.readLine().split(" ")){
                arr[i++] = Integer.parseInt(s);
            }
            for(i=0;i<m/2;i++){
                segtree_odd[m/2 + i] = arr[2*i+1];                
                segtree_even[m/2 + i] = arr[2*i];
            }
            for(i=m/2-1;i!=0;i--){                 
                segtree_odd[i] = segtree_odd[i*2] + segtree_odd[i*2+1];  
                segtree_even[i] = segtree_even[i*2] + segtree_even[i*2+1];
            }
            //printArr(segtree_odd);
            //printArr(segtree_even);
            
            for(i=0;i<q;i++){
                strings = br.readLine().split(" ");
                int a = Integer.parseInt(strings[0]);                
                int b = Integer.parseInt(strings[1]);                
                int c = Integer.parseInt(strings[2]);
                switch(a){
                    case 0:{
                        //System.out.println("Update arr["+b+"] to "+c+" ");
                        if(b%2==0){
                            b = (m/2)+(b/2);
                            segtree_even[b] = c;
                            while((b/=2)!=0){
                                segtree_even[b] = segtree_even[b*2] + segtree_even[b*2+1];
                            }
                        }else{        
                            b = (m/2)+(b/2);                    
                            segtree_odd[b] = c;
                            while((b/=2)!=0){                                
                                segtree_odd[b] = segtree_odd[b*2] + segtree_odd[b*2+1];  
                            }
                        }
                        //printArr(segtree_odd); printArr(segtree_even);
                    }
                    break;
                    case 1:{
                        int result = 0;
                        //System.out.println("Query from "+b+" to "+c);
                        if(b%2==0){
                        	b = (m/2)+(int)Math.ceil(b/(float)2);
                            c = (m/2)+(int)Math.ceil(c/(float)2);
                            //System.out.println(c);
                            for(;b!=c;b/=2,c/=2){
                                if((b%2)!=0){
                                    //System.out.println("-"+segtree_odd[b1]);
                                    result-=segtree_odd[b];
                                    result+=segtree_even[b++];  
                                }                   
                                if((c%2)!=0){                                                     
                                    result-=segtree_odd[--c];              
                                    result+=segtree_even[c]; 
                                    //System.out.println("-"+segtree_odd[c1]);
                                }  
                            }
                        }else{   
                        	int b1 = (m/2)+(b/2);
                        	int b2 = (m/2)+(int)Math.ceil(b/(float)2);
                            int c1 = (m/2)+(int)Math.ceil(c/(float)2);
                            int c2 = (m/2)+(int)Math.ceil(c/(float)2);
                            //System.out.println(c);
                            for(;b1!=c1;b1/=2,c1/=2){
                                if((b1%2)!=0){
                                    //System.out.println("+"+segtree_odd[b1]);
                                    result+=segtree_odd[b1++];
                                }                   
                                if((c1%2)!=0){                                                     
                                    result+=segtree_odd[--c1];
                                    //System.out.println("+"+segtree_odd[c1]);
                                }  
                            }
                            for(;b2!=c2;b2/=2,c2/=2){
                                if((b2%2)!=0){
                                    //System.out.println("-"+segtree_even[b2]);
                                    result-=segtree_even[b2++];                   
                                }                   
                                if((c2
                                		%2)!=0){                                    
                                    result-=segtree_even[--c2];  
                                    //System.out.println("-"+segtree_even[c2]);
                                }  
                            }
                        }
                        sb.append(result+" ");
                        
                    }
                    break;
                }
            }
            sb.append("\n");
            System.out.print(sb.toString());

		}
	}
}