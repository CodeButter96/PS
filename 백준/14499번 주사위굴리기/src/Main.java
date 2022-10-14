import java.io.*;

public class Main {
    public static int[] dice = {4,3,5,2,6};//왼쪽 오른쪽 아래 위 반대편
    public static int num = 1;//현재 윗면의 인덱스
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        int x = Integer.parseInt(inputs[2]);
        int y = Integer.parseInt(inputs[3]);
        int K = Integer.parseInt(inputs[4]);
        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            inputs = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        int[] move = new int[K];
        inputs = br.readLine().split(" ");
        for(int i=0;i<K;i++){
            move[i] = Integer.parseInt(inputs[i])-1;
        }
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        //동 서 북 남

        int[] numbers = {0,0,0,0,0,0};
        for(int i=0;i<K;i++){
            int nx = x+dx[move[i]];
            int ny = y+dy[move[i]];       
            if(nx<0||ny<0||nx>=N||ny>=M){
                continue;
            }
            x = nx; y= ny;     
            num = roll(move[i]);
            if(map[x][y]==0){
                map[x][y] = numbers[dice[4]-1];
            }else{
                numbers[dice[4]-1] = map[x][y];
                map[x][y] = 0;
            }
            System.out.println(numbers[num-1]);

        }
    }

    public static int roll(int dir){
        if(dir==0){
            int tmp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[1];
            dice[1] = num;
            num = tmp;
        }else if(dir==1){
            int tmp = dice[0];
            dice[0] = num;
            num = dice[1];
            dice[1] = dice[4];
            dice[4] = tmp;
        }else if(dir==2){
            int tmp = dice[3];
            dice[3] = num;
            num = dice[2];
            dice[2] = dice[4];
            dice[4] = tmp;
        }else{
            int tmp = dice[4];
            dice[4] = dice[2];
            dice[2] = num;
            num = dice[3];
            dice[3] = tmp;
        }
        return num;
    }
}
