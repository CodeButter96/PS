class UserSolution {
    int map[][];
    int map_size;
    int visited[][];
    int[] dx = {0,1,-1,0};
    int[] dy = {1,0,0,-1};
    
    final int queue_size = 100;
    int[][] queue;
    int size, front, rear;

    void enqueue(int x,int y){
        if(size>=queue_size){
            System.out.println("Queue Overflow");
            return;
        }
        else{
            size++;
            rear = (rear+1) % queue_size;
            queue[rear][0] = x;
            queue[rear][1] = y;
        }
    }

    int[] deque(){
        if(size==0){
            return new int[]{0,0};
        }
        else{
            size--;
            front = (front+1)%queue_size;
            return queue[front];
        }
    }

    void bfs_init(int map_size, int map[][]) {
        this.map = map;
        this.map_size = map_size;
    }

    int bfs(int x1, int y1, int x2, int y2) {
        queue = new int[queue_size][2];
        size = 0; front = -1; rear = -1;        
        visited = new int[map_size][map_size];

        enqueue(y1-1, x1-1);        
        visited[y1-1][x1-1]++;
        
        while(size!=0){
            int[] cur = deque();
            int curx = cur[0]; int cury = cur[1];
            //System.out.println("Deque x:"+curx+" y:"+cury);            
            if(curx==(y2-1)&&cury==(x2-1)){
                return visited[curx][cury]-1;
            }
            
            for(int i=0;i<4;i++){
                int nx = curx+dx[i];
                int ny = cury+dy[i];
                //System.out.println("check x:"+nx+" y:"+ny);
                if(nx<0||ny<0||nx>=map_size||ny>=map_size){
                    continue;
                }
                if(visited[nx][ny]!=0||map[nx][ny]==1){
                    continue;
                }
                        
                visited[nx][ny] = visited[curx][cury]+1;
                enqueue(nx, ny);
                
                //System.out.println("Enque x:"+nx+" y:"+ny);
            }
        }
    	return -1;
    }	
}