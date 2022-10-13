import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
    public static int slide(int[][] peak, int[] time, int N, int L){
        int mid, temp;
        int max = 0;

        for(int i=0;i<N;i++){
            temp=0;
            int start = i;
            int end = N-1;
            while(start!=end){
                mid = (start+end+1)/2;
                if(peak[mid][0]<peak[i][0]+L){
                    start = mid;
                }else{
                    end = mid-1;
                }
            }
            //System.out.println("i: "+i+" start: "+start);
            
            
            if(i>0){
                temp+=(time[start-1] - time[i-1]);
            }else if(start>0){
                temp+=time[start-1];
            }
            
            //if(start!=0&&i!=0)
            //    temp+=(time[start-1]-time[i-1]);
            //for(int j=i;j<start;j++){
            //    temp+=(time[j]);
            //}
            if(peak[start][1]>peak[i][0]+L){//마지막에 부분만 포함됨
                temp+=(peak[i][0]+L-peak[start][0]);
            }else{//마지막에 전체 포함됨
                if(start>0){                    
                    temp+=(time[start]-time[start-1]);
                }else{
                    temp+=(time[start]);
                }
            }
            if(temp>max){
                max = temp;                
            }
        }
        return max;
    }
	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/2_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int L = sc.nextInt();
			int N = sc.nextInt();
            int s=0;
            int e=0;
            int[][] peak = new int[N][2];
            int[] time = new int[N];
            int tmp = 0;
            for(int i=0;i<N;i++){
                s = sc.nextInt();
                e = sc.nextInt();
                peak[i][0]=s;
                peak[i][1]=e;     
                tmp+=e-s;
                time[i] = tmp;  
            }
            //for(int i : time){
            //    System.out.print(i+" ");
            //}
            //System.out.println();
            
            int answer = slide(peak, time, N, L);
            System.out.println("#"+test_case+" "+answer);
		}
	}
}