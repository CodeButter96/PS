import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int r, c;
    static int d;//0: 북쪽, 1: 동쪽, 2: 남쪽, 3: 서쪽
    static int[][] room;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int cnt;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                room[i][j] = Integer.parseInt(st.nextToken());//0: 빈칸, 1: 벽
            }
        }
        cnt = 0;
        clean(r,c,d);
        System.out.println(cnt);
    }

    static void clean(int x, int y, int dir){
        if(room[x][y]==0){
            room[x][y]=-1;
            cnt++;
        }
        boolean flag = false;
        int nx, ny;
        for(int i=0;i<4;i++){
            nx = x + dx[i];
            ny = y + dy[i];
            if(room[nx][ny]==0){flag = true;}
        }
        if(!flag){
            nx = x - dx[dir];
            ny = y - dy[dir];
            if(room[nx][ny]!=1){
                clean(nx,ny,dir);
            }else{
                return;
            }
        }else{
            while(true){
                dir = (dir+3)%4; //반시계방향으로 90도 회전
                nx = x + dx[dir];
                ny = y + dy[dir];
                if(room[nx][ny]==0){
                    clean(nx,ny,dir);
                    break;
                }
            }
        }
    }
}
