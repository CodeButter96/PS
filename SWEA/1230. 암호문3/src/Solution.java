import java.util.Scanner;
import java.io.FileInputStream;
import java.util.LinkedList;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            LinkedList<String> codes = new LinkedList<>();
            for(int i=0;i<N;i++){
                codes.add(sc.next());
            }
            int M = sc.nextInt();
            for(int i=0;i<M;i++){
                char s = sc.next().charAt(0);
                switch(s){
                    case 'I':{
                        int x = sc.nextInt();
                        int y = sc.nextInt();
                        int z = x+y;
                        for(int j=x;j<z;j++){
                            codes.add(j,sc.next());
                        }
                    }
                    break;
                    case 'D':{
                        int x = sc.nextInt();
                        int y = sc.nextInt();
                        for(int j=0;j<y;j++){
                            codes.remove(x);
                        }
                    }
                    break;                        
                    case 'A':{
                        int y = sc.nextInt();
                        for(int j=0;j<y;j++){
                            codes.add(sc.next());
                        }
                    }
                }
            }
            System.out.print("#"+Integer.toString(test_case)+" ");
            for(int i=0;i<10;i++){
                System.out.print(codes.get(i)+" ");
            }            
            System.out.println();
		}
	}
}