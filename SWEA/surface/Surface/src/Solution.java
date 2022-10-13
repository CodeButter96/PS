import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s=br.readLine().split(" ");
        int x = Integer.parseInt(s[0]);

        for(int i=0;i<x;i++){
            s=br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);            
            int[][] map = new int[n][n];
            int[] dx = {-1,1,0,0};
            int[] dy = {0,0,-1,1};
            int max = 0;

            for(int j=0;j<n;j++){                
                s=br.readLine().split(" ");
                for(int k=0;k<n;k++){
                    map[j][k] = Integer.parseInt(s[k]);
                    if(map[j][k]>max)
                        max = map[j][k];
                }
            }

            for(int j=0;j<max;j++){
                for(int k=0;k<n;k++){
                    for(int l=0;l<n;l++){
                        map[k][l] -= 1;
                    }                
                }                
                int[][] region = new int[n][n];
                for(int k=0;k<n;k++){
                    for(int l=0;l<n;l++){
                        if(map[k][l] > 0){//침수안됨
                            Queue<int[]> q = new LinkedList<>();
                            q.add(new int[] {k,l});
                            while(!q.isEmpty()){
                                int now[] = q.poll();
                                int nowX = now[0];
                                int nowY = now[1];
            
                                for(int m=0;m<4;m++){
                                    int nextX = nowX +dx[m];
                                    int nextY = nowY +dy[m];
            
                                    if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= n)
                                        continue;
                                    if(region[nextX][nextY] != 0 || map[nextX][nextY] <= 0)
                                        continue;
                                }
                            }
                        }
                        else{//침수됨

                        }
                    }                
                }                 
            }
            
            
        }
    }
}
