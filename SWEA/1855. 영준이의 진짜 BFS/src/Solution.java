import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
    static class Node{
        int index;
        int parent;
        int depth;
        LinkedList<Integer> child;        
        //int child[];
        public Node(int index) {
            this.index = index;
            child = new LinkedList<>();
            //child = new int[2];
        }
    }
    static int N;
    static Node[] nodes;
    static boolean[] visited;    
    static boolean[] visited2;

	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/sample_input.txt"));

		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            nodes = new Node[N];
            visited = new boolean[N];    
            for(int i=0;i<N;i++){
                nodes[i] = new Node(i);  
            }            
            for(int i=2;i<N+1;i++){
                int parent = sc.nextInt();
                nodes[i-1].parent = parent;  
                nodes[parent-1].child.add(i);              
            }
            
            System.out.println("#"+test_case+" "+bfs());          

			
		}
	}

    static int bfs(){
        int cnt = 0;
        int now, before;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        now = 0;
        visited[0] = true;
        while(!q.isEmpty()){
            before = now;
            now = q.poll();
            int l = lca(before,now);
            cnt+=l;            
            //System.out.println("before "+before+" now "+now+" dist "+l+" cnt "+cnt);
            //System.out.println("before: "+before+" now: "+now);
            for(int child : nodes[now-1].child){
                nodes[child-1].depth = nodes[now-1].depth + 1;
                if(!visited[child-1]){
                    q.add(child);
                    visited[child-1] = true;
                }
            }
        }
        return cnt;
    }

    static int lca(int before, int now){
                
        visited2 = new boolean[N];
        if(before==0){
            return 0;
        }else if(before==1){
            return 1;
        }         
        int dist = 0;
        int dista = 0;
        int distb = 0;
        int bb = before;
        int nn = now;
        int common = 1;
        visited2[now-1] = true;        
        visited2[before-1] = true;
        while(true){
            if(now!=1){                
                //System.out.println("now "+now);
                int parent = nodes[now-1].parent;
                if(visited2[parent-1]){
                    common = parent;
                    break;
                }
                visited2[parent-1] = true;
                now = parent;
            }
            if(before!=1){
                //System.out.println("before "+before);
                int parent = nodes[before-1].parent;
                if(visited2[parent-1]){
                    common = parent;
                    break;
                }
                visited2[parent-1] = true;
                before = parent;
            }
            
        }
        dista = nodes[bb-1].depth - nodes[common-1].depth;
        distb = nodes[nn-1].depth - nodes[common-1].depth;        
        //System.out.println("dista "+dista+" distb "+distb);
        //System.out.println("dist "+before+" "+now+" "+dist);
        dist = dista+distb;        
        //System.out.println("before "+bb+" now "+nn+" common "+common+" dista "+dista+" distb "+distb);


        return dist;
    }
}