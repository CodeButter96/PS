import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution
{
	static int N,M;
	static int[][][][] dp;
	static boolean[][][][] visited;
	static int[] A; 
	static int[] B;

	public static class QuickSort {
		public static void sort(int[] a) {
			m_pivot_sort(a, 0, a.length - 1);
		}
		private static void m_pivot_sort(int[] a, int lo, int hi) {	
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			m_pivot_sort(a, lo, pivot);
			m_pivot_sort(a, pivot + 1, hi);
		}
		private static int partition(int[] a, int left, int right) {
			int lo = left - 1;
			int hi = right + 1;
			int pivot = a[(left + right) / 2];
			while(true) {
				do { 
					lo++; 
				} while(a[lo] < pivot);
				do {
					hi--;
				} while(a[hi] > pivot && lo <= hi);
				if(lo >= hi) {
					return hi;
				}
				swap(a, lo, hi);
			}			
		}		
		private static void swap(int[] a, int i, int j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	static public int Find(int idx, int l, int r, int take){
		if(visited[idx][l][r][take]){return dp[idx][l][r][take];}
		if(idx==0&&l==0||l+r>M){
			visited[idx][l][r][take] = true;
			return dp[idx][l][r][take]=0;
		}
		int val;
		if(take == 1){
			int f1 = 0, f2 = 0;
			if(idx>0){
				f1 = Find(idx-1,l,r,0)+A[idx-1];
			}
			if(l>0){
				f2 = Find(idx,l-1,r,0)+B[M-l];
			}
			val = Math.max(f1, f2);
		}else{	
			int f1 = 0, f2 = 0, f3 = 0, f4 = 0;
        	if (idx > 0) {
            	f1 = Find(idx - 1, l, r, 0);
            	f2 = Find(idx - 1, l, r, 1);
        	}
        	if (r > 0) {
            	f3 = Find(idx, l, r - 1, 0);
            	f4 = Find(idx, l, r - 1, 1);
        	}
			val = Math.max(Math.max(f1, f2), Math.max(f3, f4));				
		}
		visited[idx][l][r][take] = true;
		dp[idx][l][r][take] = val;	
		return val;
	}

	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/sample_input.txt"));

		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		//T=sc.nextInt();
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = Integer.parseInt(br.readLine());
			A = new int[N];//0~N
			for(int i=0;i<N;i++){
				A[i] = Integer.parseInt(br.readLine());
			}
			M = Integer.parseInt(br.readLine());
			B = new int[M];//0~M
			for(int i=0;i<M;i++){
				B[i] = Integer.parseInt(br.readLine());
			}
			QuickSort.sort(B);			
			dp = new int[N+1][M+1][M+1][2];
			visited = new boolean[N+1][M+1][M+1][2];

			int max = 0;
			for (int l = 0; l <= M; l++) {
				int r = M - l;
				max = Math.max(max, Find(N, l, r, 0));
				max = Math.max(max, Find(N, l, r, 1));
			}
			System.out.println("#"+test_case+" "+max);

		}
	}
}