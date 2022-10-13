import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static public int[] getPi(String s){
        int n = s.length();
        int[] pi = new int[n];
        int j =0;
        for(int i=1;i<n;i++){
            while(j>0&&s.charAt(j)!=s.charAt(i)){
                j = pi[j-1];
            }
            if(s.charAt(j)==s.charAt(i)){
                pi[i] = ++j;
            }
        }
        return pi;
    }
    static public int KMP(String s, String pattern){
        int cnt = 0;
        int j=0;
        int[] pi = getPi(pattern);
        for(int i=0;i<s.length();i++){
            while(j>0&&s.charAt(i)!=pattern.charAt(j)){
                j = pi[j-1];
            }
            if(s.charAt(i)==pattern.charAt(j)){
                if(j==pattern.length()-1){
                    cnt++;
                    j = pi[j];
                    continue;
                }else{
                    j++;
                }
            }
        }
        return cnt;
    }
	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/sample_input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		

		for(int test_case = 1; test_case <= T; test_case++)
		{
            String B = sc.next();
            String S = sc.next();
			int answer = KMP(B, S);
            System.out.println("#"+test_case+" "+answer);
		}
	}
}