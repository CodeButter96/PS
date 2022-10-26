import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();        
        sc.close();
        HashSet<Integer> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N, 0));
        while(!q.isEmpty()){
            Node now = q.poll();
            if(now.num==1){
                System.out.println(now.cnt);
                for(int i : now.route){
                    System.out.print(i+" ");
                }
                break;
            }
            if(now.num<1){continue;}
            if(visited.contains(now.num)){continue;}
            visited.add(now.num);
            //System.out.println(now.num);
            if(now.num%3==0){
                q.add(new Node(now.num/3, now.cnt,now.route));
            }
            if(now.num%2==0){
                q.add(new Node(now.num/2, now.cnt, now.route));
            }
            q.add(new Node(now.num-1, now.cnt, now.route));
        }
    }

    public static class Node{
        int num;
        int cnt;
        ArrayList<Integer> route;
        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
            route = new ArrayList<>();
            route.add(num);
        }
        public Node(int num, int cnt, ArrayList<Integer> route) {
            this.num = num;
            this.cnt = cnt+1;
            this.route = (ArrayList<Integer>)route.clone();
            this.route.add(num);
        }
    }
}
