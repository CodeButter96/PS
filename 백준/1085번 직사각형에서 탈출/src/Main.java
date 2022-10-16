import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y,0});
        boolean[][] visited = new boolean[w+1][h+1];
        int answer = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0]==0||cur[1]==0||cur[0]==w||cur[1]==h){
                answer = cur[2];
                break;
            }
            for(int i=0;i<4;i++){
                int nx = cur[0]+dx[i];
                int ny = cur[1]+dy[i];
                if(nx<0||ny<0||nx>w||ny>h){continue;}
                if(visited[nx][ny]){continue;}
                q.add(new int[] {nx,ny,cur[2]+1});
                visited[nx][ny] = true;
            }
        }
        System.out.println(answer);
    }
}
