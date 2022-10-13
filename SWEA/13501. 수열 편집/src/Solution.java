import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/sample_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{  
            int N = sc.nextInt();
            int M = sc.nextInt();
            int L = sc.nextInt();
            LinkedList<Integer> list = new LinkedList<>();
            for(int i=0;i<N;i++){
                list.add(sc.nextInt());
            }
            for(int i=0;i<M;i++){
                char s = sc.next().charAt(0);
                switch(s){
                    case 'I':{
                        int x = sc.nextInt();
                        int y = sc.nextInt();
                        list.add(x,y);
                    }
                    break;
                    case 'D':{
                        int x = sc.nextInt();
                        list.remove(x);
                    }
                    break;                        
                    case 'C':{
                        int x = sc.nextInt();
                        int y = sc.nextInt();
                        list.set(x,y);
                    }
                }
            }
            int answer;
            if(list.size()<=L)
                answer = -1;
            else
                answer = list.get(L);
            System.out.println("#"+Integer.toString(test_case)+" "+Integer.toString(answer));
		}
	}
}