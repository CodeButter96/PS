import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        class Block{
            int x;
            int y;
            int value;
            Block(int x, int y, int value){
                this.x = x;
                this.y = y;
                this.value = value;
            }
        }
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());     
        int board[][] = new int[N][N];        
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        ArrayList<Block> blocks = new ArrayList<>();
        for(int i=0;i<N;i++){               
            String inputs[] = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(inputs[j]);
                if(board[i][j]!=0){
                    blocks.add(new Block(i, j, board[i][j]));
                }
            }
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<N;i++){      
                if(j+dx[i]<0||j+dx[i]>=N){continue;}         
                for(int k=0;k<N;j++){
                    if(k+dy[i]<0||k+dy[i]>=N){continue;}  
                    if(board[j][k]!=0){
                        int x = j;
                        int y = k;
                        while(x+dx[i]>=0&&x+dx[i]<N&&y+dy[i]>=0&&y+dy[i]<N){
                            x+=dx[i];
                            y+=dy[i];
                            if(board[x][y]!=0){
                                if(board[x][y]==board[j][k]){
                                    board[x][y]*=2;
                                    board[j][k]=0;
                                }else{
                                    x-=dx[i];
                                    y-=dy[i];
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i=0;i<N;i++){               
            for(int j=0;j<N;j++){
            }
        }
        for(int i=0;i<N;i++){               
            for(int j=N-1;j>=0;j--){
            }
        }
        for(int j=0;j<N;j++){               
            for(int i=0;i<N;i++){
            }
        }
        for(int j=0;j<N;j++){               
            for(int i=N-1;i>=0;i--){
            }
        }
        
        for(int i=0;i<4;i++){
            for(Block b : blocks){
                int x = b.x;
                int y = b.y;
                while(x+dx[i]>=0&&x+dx[i]<N&&y+dy[i]>=0&&y+dy[i]<N){
                    x+=dx[i];
                    y+=dy[i];
                }
            }
        }
        

        br.close();
    }
}
