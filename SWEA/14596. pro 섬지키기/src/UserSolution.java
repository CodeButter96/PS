import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class UserSolution
{
    int[][] A, B;
    int n;
    int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    //HashMap<Integer,Integer> TABLE;
    int[] TABLE = new int[50005];

    int[] ls = new int[8005];
    int idx = 0;
	public void init(int N, int mMap[][])
	{
        B = new int[N+2][N+2];
        A = new int[N+2][N+2];
        //TABLE = new HashMap<>();
        for(int i=0;i<idx;i++){TABLE[ls[i]]=0;}
        idx = 0;
        n = N;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                A[i][j] = B[i][j] = mMap[i-1][j-1];
            }
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                int base = A[i][j];

                for(int k=0;k<4;k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    int num = 4;

                    for(int m=1;m<5;m++){
                        if(nx<=0||ny<=0||nx>N||ny>N){break;}
                        num = num*10 + (A[nx][ny] - base + 4);
                        //if(TABLE.containsKey(num)){TABLE.put(num, TABLE.get(num)+1);}
                        //else{TABLE.put(num,1);}
                        TABLE[num]++;
                        ls[idx++] = num;
                        nx += dx[k];
                        ny += dy[k];

                    }
                }
            }
        }
	}

    boolean palindrome(int num){
        int a = num, b = 0;
        while(num>0){
            b = b*10 + num%10;
            num /= 10;
        }
        return a==b;
    }

	public int numberOfCandidate(int M, int mStructure[])
	{
        if(M==1){return n*n;}
        int num = 4;
        for(int i=1;i<M;i++){num = num*10 + (mStructure[0] - mStructure[i] + 4);}
        
        int ret = TABLE[num];
        //int ret = 0;
        //if(TABLE.containsKey(num)){
        //    ret = TABLE.get(num);
        //}
        if(palindrome(num)){ret/=2;}

		return ret;
	}

    Queue<Pair> q;
    boolean[][] visited;
    class Pair{
        int first;
        int second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

    }
    int cal(int lev){
        q = new LinkedList<>();
        visited = new boolean[n+2][n+2];

        q.add(new Pair(0, 0));
        visited[0][0] = true;

        int ret = (n+2)*(n+2);

        while(!q.isEmpty()){
            Pair cur = q.poll();
            ret--;

            for(int i=0;i<4;i++){
                int nx = cur.first + dx[i];
                int ny = cur.second + dy[i];
                if(nx<0||ny<0||nx>n+1||ny>n+1){continue;}
                if(!visited[nx][ny]&&B[nx][ny]<lev){
                    visited[nx][ny] = true;
                    q.add(new Pair(nx, ny));
                }

            }
        }
        return ret;
    }

	public int maxArea(int M, int mStructure[], int mSeaLevel)
	{
        int ans = -1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                int base = A[i][j];
                int t = base + mStructure[0];

                for(int k=0;k<4;k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    boolean flag = true;
                    for(int m=1;m<M;m++){
                        if(nx<=0||ny<=0||nx>n||ny>n){
                            flag = false;
                            break;
                        }
                        if(A[nx][ny]+mStructure[m]!=t){
                            flag = false;
                            break;
                        }
                        nx += dx[k];
                        ny += dy[k];
                    }

                    if(flag){
                        nx = i;
                        ny = j;

                        for(int m=0;m<M;m++){
                            B[nx][ny] = t;
                            nx += dx[k];
                            ny += dy[k];
                        }

                        ans = Math.max(ans, cal(mSeaLevel));
                        nx = i;
                        ny = j;
                        for(int m=0;m<M;m++){
                            B[nx][ny] = A[nx][ny];
                            nx += dx[k];
                            ny += dy[k];
                        }
                    }
                }
            }
        }
		return ans;
	}
}