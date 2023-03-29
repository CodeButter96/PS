import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static ArrayList<int[]> iceberg;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        //visited = new boolean[N][M];
        iceberg = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0){
                    iceberg.add(new int[] {i,j});
                }
            }
        }
        int cnt = 0;
        int numIce = numIceberg();
        while(numIce<2){
            if(numIce==0){
                System.out.println(0);
                return;
            }
            melt();
            cnt++;
            numIce = numIceberg();
        }
        System.out.println(cnt);
    }

    static void melt(){
        ArrayList<Integer> seaSideCnt = new ArrayList<>();
        for(int[] loc : iceberg){
            int x = loc[0];
            int y = loc[1];
            int sea = 0;
            for(int dir=0;dir<4;dir++){
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(map[nx][ny]==0){
                    sea++;
                }
            }
            seaSideCnt.add(sea);
        }
        for(int i=0;i<iceberg.size();i++){
            int[] loc = iceberg.get(i);
            int x = loc[0];
            int y = loc[1];
            map[x][y] = map[x][y]>=seaSideCnt.get(i)?map[x][y]-seaSideCnt.get(i):0;
        }
    }

    static int numIceberg(){
        // for(int i=0;i<N;i++){
        //     for(int j=0;j<M;j++){
        //         System.out.print(map[i][j]);
        //     }
        //     System.out.println();
        // }
        visited = new boolean[N][M];
        int cnt = 0;
        for(int[] loc : iceberg){
            int x = loc[0];
            int y = loc[1];
            if(map[x][y]!=0&&!visited[x][y]){
                //System.out.println("Unvisited:"+x+" "+y);
                bfs(x,y);
                cnt++;
            }
        }
        //System.out.println("Number of Iceberg: "+cnt);
        return cnt;
    }

    static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            for(int dir=0;dir<4;dir++){
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if(map[nx][ny]!=0&&!visited[nx][ny]){
                    q.add(new int[] {nx,ny});
                    visited[nx][ny]=true;
                }
            }
        }
    }
}
