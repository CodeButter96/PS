public class UserSolution {
	boolean visited[];
    //int adjMatrix[][];
    int adjList[][];
    int king;
    boolean found;


	public void dfs_init(int N, int[][] path) {
        //adjMatrix = new int[100][100];
        adjList = new int[100][5];
        for(int i=0;i<path.length;i++){
            int a = path[i][0];
            int b = path[i][1];
            /*if(a!=0||b!=0){                
                System.out.println(" "+a+" "+b+" ");
            }*/
            //adjMatrix[a][b] = 1;
            int j = 0;
            while(adjList[a][j]!=0){
                j++;
            }
            adjList[a][j] = b;
            //System.out.println("adjList["+a+"]["+j+"]="+b);
        }
        
	}
    
    public int dfs(int N) {        
        visited = new boolean[100];
        king = N;
        found = false;        
        //System.out.println("dfs"+N+"start");
        dfsRecursion(N);
        //System.out.println("N "+N+" "+"king "+king);
        //System.out.println(king > N ? king:-1);
		return king > N ? king:-1;
	}

    public void dfsRecursion(int N) {
        visited[N] = true;
        if(N>king){
            king = N;
            found = true;
        }
        else{
            for(int next = 0; next < 5; next++){
                //System.out.println(next);
                if(adjList[N][next]!=0&&!visited[adjList[N][next]]){
                //if(!visited[next]&&adjMatrix[N][next]!=0){
                    //System.out.println("dfs"+adjList[N][next]);
                    dfsRecursion(adjList[N][next]);
                    if(found)
                        break;
                }
            }
        }
        
	}
}
