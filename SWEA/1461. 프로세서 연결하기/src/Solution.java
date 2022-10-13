import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static class Core{
        int x,y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] cell;
    static List<Core> coreList;
    static int minWireLength, maxCore;
    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};

	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

        

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            cell = new int[N][N];
            coreList = new ArrayList<>();
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    int input = sc.nextInt();                    
                    if(input==1){
                        cell[i][j] = input;
                        if(i!=0&&j!=0&&i!=N-1&&j!=N-1)
                            coreList.add(new Core(i, j));
                    }
                }
            }

            maxCore = Integer.MIN_VALUE;
            minWireLength = Integer.MAX_VALUE;

            dfs(0,0,0);
            System.out.println("#"+test_case+" "+minWireLength);
		}
	}

    static void dfs(int index, int coreCnt, int wireCnt){
        if(index == coreList.size()){
            if(maxCore < coreCnt){
                maxCore = coreCnt;
                minWireLength = wireCnt;
            }
            else if(maxCore == coreCnt){
                minWireLength = Math.min(wireCnt, minWireLength);
            }
            return;
        }

        int x = coreList.get(index).x;
        int y = coreList.get(index).y;

        for(int i=0;i<4;i++){
            int count = 0;
            int nx = x, ny = y;
            while(true){
                nx += dx[i];
                ny += dy[i];
                
                if(nx<0 || ny<0 || nx>=N || ny>=N){
                    break;
                }

                if(cell[nx][ny]==1){
                    count = 0;
                    break;
                }

                count++;
            }

            int fill_x = x;
            int fill_y = y;

            for(int j=0;j<count;j++){
                fill_x += dx[i];
                fill_y += dy[i];
                cell[fill_x][fill_y] = 1;
            }

            if(count==0){
                dfs(index+1, coreCnt, wireCnt);
            }
            else{
                dfs(index+1, coreCnt+1, wireCnt+count);

                fill_x = x;
                fill_y = y;

                for(int j=0;j<count;j++){
                    fill_x += dx[i];
                    fill_y += dy[i];
                    cell[fill_x][fill_y] = 0;
                }
            }
        }
    }
}