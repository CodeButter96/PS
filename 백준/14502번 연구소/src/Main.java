import java.io.*;
import java.util.*;
public class Main{
    
    public static int N;
    public static int M;
    public static int[] dx = {0,0,1,-1};
    public static int[] dy = {1,-1,0,0};
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }        
        makeWall(map,3);
        System.out.println(max);
    }
    public static void bfs(int[][] orgmap){
        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = orgmap[i][j];
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==2){
                    q.add(new int[] {i,j});
                }
            }
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            for(int i=0;i<4;i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0||ny<0||nx>=N||ny>=M){continue;}
                if(map[nx][ny]==0){
                    map[nx][ny] = 2;
                    q.add(new int[] {nx,ny});
                }
            }
        }
        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==0){
                    cnt++;
                }
            }
        }
        max = Math.max(max,cnt);
    }
    public static void makeWall(int[][] map, int cnt){
        if(cnt==0){
            bfs(map);
            return;
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==0){
                    map[i][j]=1;
                    makeWall(map,cnt-1);
                    map[i][j]=0;
                }
            }
        }
    }
}