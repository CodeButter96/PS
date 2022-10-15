import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};    
    static int N;
    static int M;
    static boolean[][] visited;
    static int[][] map; 
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] tetroT = {{{0,0},{0,1},{1,1},{0,2}},
        {{0,0},{1,0},{2,0},{1,1}},
        {{0,0},{0,1},{0,2},{-1,1}},
        {{0,0},{0,1},{1,1},{-1,1}}};
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                dfs(i,j,0,0);
            }
        }   
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int k=0;k<4;k++){
                    int sum = 0;
                    for(int l=0;l<4;l++){
                        int nx = i+tetroT[k][l][0];
                        int ny = j+tetroT[k][l][1];
                        if(nx<0||nx>=N||ny<0||ny>=M){break;}
                        sum += map[nx][ny];
                    }
                    max = Math.max(max,sum);
                }
            }
        }      
        System.out.println(max);
    }

    public static void dfs(int x, int y, int cnt, int sum){
        if(cnt==4){
            max = Math.max(max,sum);
            return;
        }
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||nx>=N||ny<0||ny>=M){continue;}
            if(visited[nx][ny]){continue;}
            visited[nx][ny] = true;
            dfs(nx,ny,cnt+1,sum+map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}
