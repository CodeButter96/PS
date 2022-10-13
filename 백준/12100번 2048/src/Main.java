import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {    
    public static int N;     
    public static int max;         
    
    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());     
        int[][] board = new int[N][N];
        max = 0;   
        for(int i=0;i<N;i++){               
            String inputs[] = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(inputs[j]);
                if(board[i][j]!=0){
                    max = Math.max(max,board[i][j]);
                }
            }
        }
        for(int i=0;i<4;i++){
            dfs(i,0,board);
        }
        System.out.println(max);
        br.close();
    }

    public static void dfs(int dir, int cnt, int[][] board){
        if(cnt==5){
            for(int i=0;i<N;i++){               
                for(int j=0;j<N;j++){
                    max = Math.max(max,board[i][j]);
                }
            }
            return;
        }
        for(int i=0;i<4;i++){
            dfs(i,cnt+1,move(dir,board));
        }
    }

    public static int[][] move(int dir, int[][] orgBoard){
        int[][] board = new int[N][N];
        for(int i = 0; i < N; i++)
            board[i] = orgBoard[i].clone();
        boolean[][] visited = new boolean[N][N];
        if(dir == 0){
            for(int i=0;i<N;i++){               
                for(int j=1;j<N;j++){
                    if(board[i][j]==0){continue;}
                    int x = i;
                    int y = j;
                    while(y>0){//왼쪽으로 쭉 이동
                        if(board[x][y-1]==0){//비어있어서 움직일 수 있음
                            board[x][y-1] = board[x][y];
                            board[x][y] = 0;
                            y--;
                        }else if(board[x][y-1]==board[x][y]&&!visited[x][y-1]){//같은 숫자여서 합쳐짐
                            board[x][y-1] *=2;
                            visited[x][y-1] = true;
                            //max = Math.max(max,board[x][y-1]);
                            board[x][y] = 0;
                            break;
                        }else{//다른 숫자여서 못움직임
                            break;
                        }
                    }
                }
            }
        }else if(dir == 1){
            for(int i=0;i<N;i++){               
                for(int j=N-2;j>=0;j--){
                    if(board[i][j]==0){continue;}
                    int x = i;
                    int y = j;
                    while(y<N-1){//오른쪽으로 쭉 이동
                        if(board[x][y+1]==0){//비어있어서 움직일 수 있음
                            board[x][y+1] = board[x][y];
                            board[x][y] = 0;
                            y++;
                        }else if(board[x][y+1]==board[x][y]&&!visited[x][y+1]){//같은 숫자여서 합쳐짐
                            board[x][y+1] *=2;
                            visited[x][y+1] = true;
                            //max = Math.max(max,board[x][y+1]);
                            board[x][y] = 0;
                            break;
                        }else{//다른 숫자여서 못움직임
                            break;
                        }
                    }
                }
            }
        }else if(dir == 2){
            for(int j=0;j<N;j++){               
                for(int i=1;i<N;i++){
                    if(board[i][j]==0){continue;}
                    int x = i;
                    int y = j;
                    while(x>0){//위로 쭉 이동
                        if(board[x-1][y]==0){//비어있어서 움직일 수 있음
                            board[x-1][y] = board[x][y];
                            board[x][y] = 0;
                            x--;
                        }else if(board[x-1][y]==board[x][y]&&!visited[x-1][y]){//같은 숫자여서 합쳐짐
                            board[x-1][y] *=2;
                            visited[x-1][y] = true;
                            //max = Math.max(max,board[x-1][y]);
                            board[x][y] = 0;
                            break;
                        }else{//다른 숫자여서 못움직임
                            break;
                        }
                    }
                }
            }
        }else{
            for(int j=0;j<N;j++){               
                for(int i=N-2;i>=0;i--){
                    if(board[i][j]==0){continue;}
                    int x = i;
                    int y = j;
                    while(x<N-1){//아래로 쭉 이동
                        if(board[x+1][y]==0){//비어있어서 움직일 수 있음
                            board[x+1][y] = board[x][y];
                            board[x][y] = 0;
                            x++;
                        }else if(board[x+1][y]==board[x][y]&&!visited[x+1][y]){//같은 숫자여서 합쳐짐
                            board[x+1][y] *=2;
                            visited[x+1][y] = true;
                            //max = Math.max(max,board[x+1][y]);
                            board[x][y] = 0;
                            break;
                        }else{//다른 숫자여서 못움직임
                            break;
                        }
                    }
                }
            }
        }
        return board;
    }
}
