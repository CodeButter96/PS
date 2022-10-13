import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static class xy{
        int x;
        int y;
        public xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static char[][] map;
    static int[][] mine;
    static boolean[][] visited;
    static int minClick;
    static int[] dx = {0,0,1,1,1,-1,-1,-1};    
    static int[] dy = {1,-1,1,-1,0,1,-1,0};
    static int N;

    

	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            map = new char[N][N];
            mine = new int[N][N];
            minClick = 0;
            visited = new boolean[N][N];
            for(int i=0;i<N;i++){
                String s = sc.next();
                for(int j=0;j<N;j++){
                    char c = s.charAt(j);
                    map[i][j] = c;
                    if(c=='*'){
                        int nx, ny;
                        for(int dir=0;dir<8;dir++){
                            nx = i + dx[dir];
                            ny = j + dy[dir];
                            if(nx<0 || ny<0 || nx >=N || ny >=N){
                                continue;
                            }
                            mine[nx][ny]++;
                        }
                    }
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(mine[i][j]==0 && map[i][j]!='*' && !visited[i][j]){
                        bfs(i,j);
                        minClick++;
                    }
                }
            }
            
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(mine[i][j]!=0 && map[i][j] !='*' && !visited[i][j]){
                        minClick++;
                    }
                }
            }
            System.out.println("#"+test_case+" "+minClick);
		}
	}

    static void bfs(int x, int y){

        Queue<xy> q = new LinkedList<>();
        q.add(new xy(x, y));
        visited[x][y] = true;
        while(!q.isEmpty()){
            xy now = q.poll();
            int cx = now.x;
            int cy = now.y;
            for(int dir=0;dir<8;dir++){
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if(nx<0 || ny<0 || nx >=N || ny >=N){
                    continue;
                }
                if(visited[nx][ny]||map[nx][ny]=='*'){
                    continue;
                }
                if(mine[nx][ny]==0){
                    q.add(new xy(nx,ny));
                }
                visited[nx][ny] = true;
            }
        }
        
    }
}