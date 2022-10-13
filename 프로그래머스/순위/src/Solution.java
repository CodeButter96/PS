import java.util.*;

class Solution {
    static ArrayList<LinkedList<Integer>> adjList;
    static int[] cnt;
    static boolean[] visited;
    public static void dfs(int n){
        visited[n] = true;
        cnt[n]++;
        for(int i : adjList.get(n)){
            if(!visited[i]){
                dfs(i);
            }
        }
    }
    public static int solution(int n, int[][] results) {        
        adjList = new ArrayList<>();
        cnt = new int[n+1];
        
        for(int i=0;i<=n;i++){
            adjList.add(new LinkedList<>());
        }
        for(int i=0;i<results.length;i++){
            adjList.get(results[i][0]).add(results[i][1]);
        }
        for(int i=1;i<=n;i++){
            visited = new boolean[n+1];
            dfs(i);
        }  
        int answer = 0;
        return answer;
    }
}