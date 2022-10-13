import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public static void main(String[] args){
        Solution my = new Solution();
        int[] arr = {7,4,5,6};
        my.solution(2,10,arr);
    }
    class Pair{
        int index;
        int weight;
        Pair(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int num = truck_weights.length;        
        Queue <Pair> q = new LinkedList<Pair>();
        int[] dist = new int[num];
        
        for(int i=0;i<num;i++){
            q.add(new Pair(i,truck_weights[i]));
            dist[i] = -1;
        }
        
        while(!q.isEmpty()){
            answer++;
            int num_trucks = 0;
            int sum_weight = 0;
            boolean crossed = false;
            for(Pair truck : q){
                num_trucks++;
                sum_weight+=truck.weight;
                if((num_trucks>bridge_length)||(sum_weight>weight)){
                    break;
                }
                if(dist[truck.index]==-1){
                    dist[truck.index] = bridge_length;
                    break;
                }
                else if(dist[truck.index]==1){                                        
                    dist[truck.index]--;
                    crossed = true;
                    num_trucks--;
                    sum_weight-=truck.weight;
                }
                else{
                    dist[truck.index]--;
                }                
                
            }
            if(crossed){
                q.poll();
            }            
            System.out.print(answer);            
            System.out.print(" ");
            for(int i=0;i<num;i++){
                System.out.print(dist[i]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        
        return answer;
    }
}