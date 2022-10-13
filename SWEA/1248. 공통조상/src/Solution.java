import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static class Node{
        int index;
        Node parent;
        Node left;
        Node right;        
        Node(int index){
            this.index = index;
            parent = null;
            left = null;
            right = null;
        }
    }
    static public int size(Node n){
        int count = 1;
        if(n.left!=null){
            count+=size(n.left);
        }
        if(n.right!=null){
            count+=size(n.right);            
        }
        return count;
    }
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int V = sc.nextInt();
            int E = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            Node[] nodes = new Node[V+1];
            boolean[] visited = new boolean[V+1];
            for(int i=1;i<=V;i++){
                nodes[i] = new Node(i);
            }
            for(int i=0;i<E;i++){
                int parent = sc.nextInt();
                int child = sc.nextInt();
                if(nodes[parent].left==null){
                    nodes[parent].left=nodes[child];
                }else{
                    nodes[parent].right=nodes[child];
                }
                nodes[child].parent = nodes[parent];
            }
            Node common = nodes[1];
            while(true){
                if(a!=1){
                    Node parent = nodes[a].parent;
                    if(visited[parent.index]){
                        common = parent;
                        break;
                    }
                    visited[parent.index] = true;
                    a = parent.index;
                }
                if(b!=1){
                    Node parent = nodes[b].parent;
                    if(visited[parent.index]){
                        common = parent;
                        break;
                    }
                    visited[parent.index] = true;
                    b = parent.index;
                }
            }
            System.out.println("#"+test_case+" "+common.index+" "+size(common));
            
		}
	}
}