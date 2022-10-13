import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static String[] code = {"0001101","0011001","0010011","0111101","0100011","0110001",
                                  "0101111","0111011","0110111","0001011"};
    
    public static int[] decrypt(String s){
        int num[] = new int[8];
        int index = 0;
        for(int i=0;i<s.length();i+=7){
            String tmp = s.substring(i,i+7);
            for(int j=0;j<code.length;j++){
            	if(tmp.equals(code[j])){
                    num[index++]=j;
                    break;
                }
            }
        }
        return num;
    }
    
    public static boolean isValid(int[] num){
        int sum = 0;
        for(int i=0;i<num.length;i++){
            if(i%2==0)
                sum+=(num[i]*3);
            else
                sum+=num[i];
        }
        if(sum%10==0)
            return true;
        return false;
    }
    
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);        
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            String code = "";
            for(int i=0;i<n;i++){
                String s = sc.next();
                if(code.isEmpty()){
                    for(int j = m - 1; j>=0;j--){
                        if(s.charAt(j)=='1'){
                            code = s.substring(j-55,j+1);
                            break;
                        }
                    }
                }
            }
            int[] num = decrypt(code);
            if(isValid(num)){
                int sum = 0;
                for(int i=0;i<num.length;i++){
                    sum += num[i];
                }
                System.out.println("#"+test_case+" "+sum);
            }
            else{
                System.out.println("#"+test_case+" 0");
            }
                
            
		}
	}
}