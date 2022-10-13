import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
class Solution
{
    public static int lowerBound(int[] array,  int value) {
        int low = 0;
        int high = array.length;
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (value <= array[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static int upperBound(int[] array, int value) {
        int low = 0;
        int high = array.length;
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (value < array[mid]) {                
                high = mid;
            } else {                
                low = mid + 1;
            }
            /*if (value >= array[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }*/
        }
        return low;
    }

    static int C1, C2;
    static int dist(int P, int Q){
        return Math.abs(P-Q)+Math.abs(C1-C2);
    }
    

	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] cow = new int[N];
            int[] horse = new int[M];
			C1 = sc.nextInt();
            C2 = sc.nextInt();
            int z;
            for(int i=0;i<N;i++){
                z = sc.nextInt();
                cow[i] = z;//x
            }
            for(int i=0;i<M;i++){
                z = sc.nextInt();
                horse[i] = z;//x
            }
            //Arrays.sort(cow);
            Arrays.sort(horse);

            int LB=0;
            int UB=0;
            int min = Integer.MAX_VALUE;
            int cnt = 0;
            for(int i=0;i<N;i++){
                
                int l=0;
                int r=M;
                while(l<r){
                    //System.out.println(l+" "+r);
                    int mid = (l+r)/2;
                    if(cow[i]<=horse[mid]){
                        r = mid;
                    }else{
                        l = mid + 1;
                    }
                }
                LB = l;
                l=0;
                r=M;
                while(l<r){    
                    //System.out.println(l+" "+r);                
                    int mid = (l+r)/2;
                    if(cow[i]<horse[mid]){                        
                        r = mid;
                    }else{
                        l = mid + 1;
                    }
                }
                UB = l;
                
                //LB = lowerBound(horse,cow[i]);
                //UB = upperBound(horse,cow[i]);
                
                if(UB==M){
                    int tmp = dist(cow[i],horse[M-1]);
                    if(tmp<min){
                        min = tmp;
                        cnt = 1;
                        //System.out.println("min update: "+tmp+" cow: "+cow[i]+" horse: "+(M-1)+" cnt: "+cnt);
                    }else if(tmp==min){
                        cnt++;
                        //System.out.println("min: "+tmp+" cow: "+cow[i]+" horse: "+(M-1)+" cnt: "+cnt);
                    }
                }else if(LB>0){
                    int tmp = 0;
                    for(int j=LB-1;j<=UB;j++){
                        tmp = dist(cow[i],horse[j]);
                        if(tmp<min){
                            min = tmp;
                            cnt = 1;                            
                            //System.out.println("min update: "+tmp+" cow: "+cow[i]+" horse: "+j+" cnt: "+cnt);
                        }else if(tmp==min){
                            cnt++;
                            //System.out.println("min: "+tmp+" cow: "+cow[i]+" horse: "+j+" cnt: "+cnt);
                        }
                    }
                }else{
                    int tmp = dist(cow[i],horse[0]);
                    if(tmp<min){
                        min = tmp;
                        cnt = 1;                            
                        //System.out.println("min update: "+tmp+" cow: "+cow[i]+" horse: "+0+" cnt: "+cnt);
                    }else if(tmp==min){
                        cnt++;
                        //System.out.println("min: "+tmp+" cow: "+cow[i]+" horse: "+0+" cnt: "+cnt);
                    }
                }
                
                //System.out.println("#"+test_case+" "+LB+" "+UB+" "+cow[i]);
            }
            System.out.println("#"+test_case+" "+min+" "+cnt);
		}
	}
}
