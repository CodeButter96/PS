import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        class Beads{
            int rx,ry,bx,by;
            int cnt;
            Beads(int rx, int ry, int bx, int by, int cnt){
                this.rx = rx;
                this.ry = ry;
                this.bx = bx;
                this.by = by;
                this.cnt = cnt;
            }
        }
        //System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String input = br.readLine();
        //String inputs[] = input.split(" ");
        

        //int TC = Integer.parseInt(inputs[0]);
        for (int testcase = 1; testcase <= 1; testcase++)
        {
            String input = br.readLine();
            String inputs[] = input.split(" ");
            int N = Integer.parseInt(inputs[0]), M = Integer.parseInt(inputs[1]);
            char[][] board = new char[N][M];
            boolean[][][] visited = new boolean[2][N][M];
            int[] red = new int[2];
            int[] blue = new int[2];
            int[] dx = {0,0,1,-1};
            int[] dy = {1,-1,0,0};
            char c;
            for(int i=0;i<N;i++){
                input = br.readLine();
                for(int j=0;j<M;j++){
                    c = input.charAt(j);
                    if(c=='R'){
                        red[0] = i;
                        red[1] = j;
                        board[i][j] = '.';
                    }else if(c=='B'){
                        blue[0] = i;
                        blue[1] = j;
                        board[i][j] = '.';
                    }
                    board[i][j] = c;
                }
            }
            Queue<Beads> q = new LinkedList<>();
            Beads start = new Beads(red[0], red[1], blue[0], blue[1],0);
            q.add(start);
            boolean found = false;
            while(!q.isEmpty()&&!found){
                Beads cur = q.poll();
                int rx = cur.rx;
                int ry = cur.ry;
                int bx = cur.bx;
                int by = cur.by;
                int cnt = cur.cnt;
                visited[0][rx][ry] = true;
                visited[1][bx][by] = true;
                if(cur.cnt>10){
                    found = false;
                    break;
                }
                
                for(int i=0;i<4;i++){
                    int nrx = rx;
                    int nry = ry;
                    int nbx = bx;
                    int nby = by;
                    while(board[nrx+dx[i]][nry+dy[i]]!='#'){
                        nrx += dx[i];
                        nry += dy[i];
                        if(board[nrx][nry]=='O'){
                            break;
                        }
                    }
                    while(board[nbx+dx[i]][nby+dy[i]]!='#'){
                        nbx += dx[i];
                        nby += dy[i];
                        if(board[nbx][nby]=='O'){
                            break;
                        }
                    }
                    if(board[nbx][nby]=='O'){continue;}
                    if(nrx==nbx&&nry==nby){
                        if(i==0){
                            if(ry>by){
                                nby--;
                            }else{
                                nry--;
                            }
                        }
                        else if(i==1){
                            if(ry>by){
                                nry++;
                            }else{
                                nby++;
                            }
                        }
                        else if(i==2){
                            if(rx>bx){
                                nbx--;
                            }else{
                                nrx--;
                            }
                        }
                        else if(i==3){
                            if(rx>bx){
                                nrx++;
                            }else{
                                nbx++;
                            }
                        }
                    }
                    if(board[nrx][nry]=='O'){
                        System.out.println(cnt+1);
                        found = true;
                        break;      
                    }
                    if(visited[0][nrx][nry]==true&&visited[1][nbx][nby]==true){continue;}
                    q.add(new Beads(nrx, nry, nbx, nby,cnt+1));
                }
            }
            if(!found){System.out.println(-1);}
        }
        
        br.close();
    }
}
