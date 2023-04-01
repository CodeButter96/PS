import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R,S;
    static char[][] pic;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        pic = new char[R][S];
        for(int i=0;i<R;i++){
            String s = br.readLine();
            pic[i] = s.toCharArray();
        }

        drop(check());

        for(int i=0;i<R;i++){
            for(int j=0;j<S;j++){
                sb.append(pic[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    static int check(){
        int drop = Integer.MAX_VALUE;
        for(int y=0;y<S;y++){
            boolean hasStar = false;
            int starEnd = 0;
            int ground = 0;
            for(int x=0;x<R;x++){
                if(pic[x][y]=='X'){
                    starEnd = x;
                    hasStar = true;
                }else if(pic[x][y]=='#'){
                    ground = x;
                    break;
                }
            }
            if(hasStar){
                drop = Math.min(drop,ground - starEnd - 1);
            }
        }
        return drop;
    }

    static void drop(int num){
        for(int y=0;y<S;y++){
            for(int x=R-2;x>=0;x--){
                if(pic[x][y]=='X'){
                    pic[x+num][y]='X';
                    pic[x][y]='.';
                }
            }
        }
    }
}