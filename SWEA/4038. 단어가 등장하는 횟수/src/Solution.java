import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static public int RabinKarp(String s, String p){
        int cnt = 0;
        int n = s.length();
        int m = p.length();
        int sHash = 0, pHash = 0, base = 1, c=2, mod = 10000007;
        int sHash2 = 0, pHash2 = 0, base2 = 1, c2 = 33;
        
        for(int i=m-1;i>=0;i--){
            sHash = (sHash + s.charAt(i)*base)%mod;
            sHash2 += s.charAt(i)*base2;
            pHash = (pHash + p.charAt(i)*base)%mod;
            pHash2 += p.charAt(i)*base2;
            if(i!=0){
                base*=c; base2*=c2;
            }
        }

        for(int i=0;i<n-m+1;i++){
            if(i>0){
                //int a = (sHash - s.charAt(i-1)*base)*c;
                //sHash = ((sHash - s.charAt(i-1)*base)*c) + s.charAt(m-1+i);  
                sHash = (sHash - s.charAt(i-1)*base)*c +  s.charAt(m-1+i);  
                sHash2 = (sHash2 - s.charAt(i-1)*base2)*c2 +  s.charAt(m-1+i);              
            }
            if(pHash==sHash){
                if(pHash2==sHash2){
                    cnt++;
                }
                /*for(int j=0;j<m;j++){
                    if(s.charAt(i+j)!=p.charAt(j)){
                        cnt--;
                        break;
                    }
                }*/
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
			int answer = RabinKarp(B, S);
            System.out.println("#"+test_case+" "+answer);
		}
	}
}