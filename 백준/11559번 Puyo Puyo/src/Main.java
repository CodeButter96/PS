import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static char[][] field = new char[12][6];
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<12;i++){
            field[i] = br.readLine().toCharArray();
        }
        int cnt = 0;
        while(pop()>0){
            cnt++;
        }
        System.out.println(cnt);
    }

    static int pop(){
        boolean[][] visited = new boolean[12][6];
        ArrayList<int[]> coordinatesToPop = new ArrayList<>();
        for(int x=0;x<12;x++){
            for(int y=0;y<6;y++){
                if(!visited[x][y]&&field[x][y]!='.'){
                    ArrayList<int[]> coordinates = bfs(x, y, visited);
                    if(coordinates.size()>=4){
                        coordinatesToPop.addAll(coordinates);
                    }
                }
            }
        }
        for(int[] coordinate : coordinatesToPop){
            field[coordinate[0]][coordinate[1]] = '.';
        }
        drop();
        return coordinatesToPop.size();
    }

    static void drop(){
        for(int y=0;y<6;y++){
            int cnt = 0;
            for(int x=11;x>=0;x--){
                if(field[x][y]=='.'){cnt++;}
                else if(cnt!=0){
                    field[x+cnt][y] = field[x][y];
                    field[x][y]='.';
                }
            }
        }
    }

    static ArrayList<int[]> bfs(int x, int y, boolean[][] visited){
        char color = field[x][y];
        ArrayList<int[]> arrayList = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y});
        arrayList.add(new int[] {x,y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            for(int dir=0;dir<4;dir++){
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if(nx<0||ny<0||nx>=12||ny>=6||visited[nx][ny]){continue;}
                if(field[nx][ny]==color){
                    q.add(new int[] {nx,ny});
                    arrayList.add(new int[] {nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return arrayList;
    }

    static void print(){
        for(int i=0;i<12;i++){
            for(int j=0;j<6;j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
