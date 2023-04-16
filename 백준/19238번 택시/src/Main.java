import java.io.*;
import java.util.*;

class Point{
    int x,y;
    int dist;
    public Point(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    static int N,M,F;
    static int[][] map;
    static int[][] cus;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        cus = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int startx = Integer.parseInt(st.nextToken())-1;
        int starty = Integer.parseInt(st.nextToken())-1;

        HashMap<Integer,int[]> hm = new HashMap<>();

        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken())-1;
            int sy = Integer.parseInt(st.nextToken())-1;
            cus[sx][sy] = i;
            int ex = Integer.parseInt(st.nextToken())-1;
            int ey = Integer.parseInt(st.nextToken())-1;
            //cus[ex][ey] = -i;
            hm.put(i, new int[] {sx,sy,ex,ey});
        }

        for(int i=0;i<M;i++){
            Point c = bfs(startx,starty);
            if(c==null||F<c.dist){
                F = -1;
                break;
            }
            F -= c.dist;
            int idx = cus[c.x][c.y];
            int[] cInfo = hm.get(cus[c.x][c.y]);
            int dist = bfs2(c.x, c.y, cInfo[2], cInfo[3]);
            if(dist==-1||F<dist){
                F = -1;
                break;
            }
            F += dist;
            startx = cInfo[2];
            starty = cInfo[3];
            cus[c.x][c.y] = 0;
        }
        System.out.println(F);
    }

    //손님 찾기
    static Point bfs(int x, int y){
        int min = Integer.MAX_VALUE;
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Point(x,y,0));
        visited[x][y] = true;
        TreeSet<Point> ts = new TreeSet<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2){
                if(p1.x<p2.x){
                    return -1;
                }else if(p1.x>p2.x){
                    return 1;
                }else{
                    return p1.y-p2.y;
                }
            }
        });
        while(!q.isEmpty()){
            Point cur = q.poll();
            if(cus[cur.x][cur.y]>0){
                min = Math.min(min,cur.dist);
                if(min<cur.dist){break;}
                else{
                    ts.add(cur);
                }
                // return cur;
            }
            for(int dir=0;dir<4;dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx<0||ny<0||nx>=N||ny>=N||map[nx][ny]==1||visited[nx][ny]){continue;}
                q.add(new Point(nx, ny,cur.dist+1));
                visited[nx][ny] = true;
            }
        }
        if(ts.isEmpty()){
            return null;
        }else{
            return ts.first();
        }
    }

    //목적지까지 거리 계산
    static int bfs2(int sx, int sy, int ex, int ey){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Point(sx,sy,0));
        visited[sx][sy] = true;
        while(!q.isEmpty()){
            Point cur = q.poll();
            if(cur.x==ex&&cur.y==ey){return cur.dist;}
            for(int dir=0;dir<4;dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx<0||ny<0||nx>=N||ny>=N||map[nx][ny]==1||visited[nx][ny]){continue;}
                q.add(new Point(nx, ny, cur.dist+1));
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}
