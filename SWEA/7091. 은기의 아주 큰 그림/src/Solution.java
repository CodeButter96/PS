import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static final int TABLE_SIZE = 1<<20;
    static final int DIV = TABLE_SIZE-1;
    static final int MOD = 1000000007;

    static int calMul(int len, int shift){
        int mul = 1;
        for(int i=0;i<len-1;i++){
            //mul = ((mul<<shift)&DIV) + mul;
            mul = ((mul<<shift)%MOD) + mul;
        }
        //return mul&DIV;
        return mul%MOD;
    }

    static int getHash(int[] arr, int range, int shift){
        int hash = 0;
        for(int i=0;i<range;i++){
            //hash = ((hash<<shift)&DIV) + hash + arr[i];
            hash = ((hash<<shift)%MOD) + hash + arr[i];
        }
        //return hash&DIV;
        return hash%MOD;
    }

    static int getNext(int prev, int sub, int mul, int add, int shift){
        int hash = prev - (sub*mul);
        //hash = ((hash<<shift)&DIV) + hash + add;
        hash = ((hash<<shift)%MOD) + hash + add;
        //return hash&DIV;
        return hash%MOD;
    }

	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        int H, W, N, M;
        int[][] pattern, target;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            H = sc.nextInt(); W = sc.nextInt();
            N = sc.nextInt(); M = sc.nextInt();
            pattern = new int[H][W];
            target = new int[N][M];
            for(int i=0;i<H;i++){
                String s = sc.next();
                for(int j=0;j<W;j++){
                    if(s.charAt(j)=='o'){
                        pattern[i][j] = 1;
                    }else{
                        pattern[i][j] = 0;
                    }
                }
            }
            for(int i=0;i<N;i++){
                String s = sc.next();
                for(int j=0;j<M;j++){
                    if(s.charAt(j)=='o'){
                        target[i][j] = 1;
                    }else{
                        target[i][j] = 0;
                    }
                }
            }

            int[] patRowHash = new int[H];
            for(int i=0;i<H;i++){
                patRowHash[i] = getHash(pattern[i], W, 4);
            }
            int patHash = 0;
            patHash = getHash(patRowHash, H, 5);

            int[][] tmpHash = new int[M][N];
            int mulC = calMul(W, 4);
            for(int i=0;i<N;i++){
                tmpHash[0][i] = getHash(target[i], W, 4);
                for(int j=1;j<M-W+1;j++){
                    tmpHash[j][i] = getNext(tmpHash[j-1][i], target[i][j-1], 
                    mulC, target[i][j+W-1], 4);
                }
            }            
            int mulR = calMul(H, 5);
            int[][] hash = new int[N][M];
            for(int i=0;i<M-W+1;i++){
                hash[0][i] = getHash(tmpHash[i], H, 5);
                for(int j=1;j<N-H+1;j++){
                    hash[j][i] = getNext(hash[j-1][i], tmpHash[i][j-1], mulR, tmpHash[i][j+H-1], 5);
                }
            }
            int cnt = 0;

            for(int i=0;i<N-H+1;i++){
                for(int j=0;j<M-W+1;j++){
                    if(hash[i][j]==patHash){
                        cnt++;
                    }
                }
            }

            System.out.println("#"+test_case+" "+cnt);


            
		}
	}
}