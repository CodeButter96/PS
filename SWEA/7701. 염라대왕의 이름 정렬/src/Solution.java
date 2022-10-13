import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
    static String[] names;
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/s_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            System.out.println("#"+test_case);
            int N = sc.nextInt();
            names = new String[N];
            for(int i=0;i<N;i++){
                names[i] = sc.next();
            }
            Arrays.sort(names, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    // TODO Auto-generated method stub
                    if(o1.length()==o2.length())return o1.compareTo(o2);
                    else return o1.length()-o2.length();
                }                
            });
            System.out.println(names[0]);
            for(int i=1;i<N;i++){
                if(!names[i].equals(names[i-1]))System.out.println(names[i]);
            }
		}
	}
}