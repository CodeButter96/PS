import java.io.*;
import java.util.*;
class Point {
    int x,y;
    int dist;
    public Point(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}
public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int size = 2;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int x=0,y=0;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==9){
                    x = i;
                    y = j;
                }
            }
        }
        Point fish = bfs(x, y);
        int cnt = 0;
        int time = 0;
        while(fish.x!=-1){
            time += fish.dist;
            map[x][y] = 0;
            x = fish.x;
            y = fish.y;
            cnt++;
            if(cnt==size){
                size++;
                cnt = 0;
            }
            map[x][y] = 0;
            fish = bfs(x,y);
        }
        System.out.println(time);
    }

    static Point bfs(int x, int y){
        visited = new boolean[N][N];
        TreeSet<Point> ts = new TreeSet<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2){
                if(p1.x<p2.x){
                    return -1;
                }else if(p1.x>p2.x){
                    return 1;
                }
                else{
                    return p1.y-p2.y;
                }
            }
        });
        boolean found = false;
        int minDist = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y,0));
        visited[x][y] = true;
        while(!q.isEmpty()){
            Point cur = q.poll();
            if(map[cur.x][cur.y]!=0&&map[cur.x][cur.y]<size){
                if(!found){
                    found = true;
                    minDist = cur.dist;
                    ts.add(cur);
                }
                else if(cur.dist==minDist){
                    ts.add(cur);
                }
                else{
                    break;
                }
            }
            for(int dir=0;dir<4;dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx<0||ny<0||nx>=N||ny>=N||visited[nx][ny]||map[nx][ny]>size){continue;}
                q.add(new Point(nx, ny, cur.dist+1));
                visited[nx][ny] = true;
            }
        }
        if(ts.isEmpty()){
            return new Point(-1, -1, 0);
        }
        return ts.first();
    }
}
