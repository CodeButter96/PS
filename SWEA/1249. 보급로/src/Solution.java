import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static class Node{
        int x;
        int y;
        int dist;
        Node(int x,int y,int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int answer = 0;
            int N = sc.nextInt();
            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];
            PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2){
                    return n1.dist-n2.dist;
                }  
            });

            for(int i=0;i<N;i++){
                String[] s = sc.next().split("");
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(s[j]);
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dist[0][0] = 0;
            pq.add(new Node(0, 0, 0));
            while(!pq.isEmpty()){
                Node cur = pq.poll();
                int x = cur.x;
                int y = cur.y;
                //System.out.println("("+x+","+y+")");
                if(x==N-1&&y==N-1){
                    answer = dist[x][y];
                    break;
                    //return;
                }
                if(dist[x][y]<cur.dist){
                    continue;
                }
                for(int i=0;i<4;i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx<0||ny<0||nx>=N||ny>=N){continue;}
                    if(dist[x][y]+map[nx][ny]<dist[nx][ny]){
                        dist[nx][ny] = dist[x][y]+map[nx][ny];
                        pq.offer(new Node(nx, ny, dist[nx][ny]));
                    }
                }
            }
            System.out.println("#"+test_case+" "+answer);            
		}
	}
}
