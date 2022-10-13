import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private final static int CMD_INIT				= 1;
	private final static int CMD_ADD				= 2;
	private final static int CMD_REMOVE				= 3;
	private final static int CMD_NUMBER_OF_CROSS	= 4;
	private final static int CMD_PARTICIPANT		= 5;
	
	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br, int TC) throws Exception
	{
		StringTokenizer st;

		int numQuery;

		int mX, mY, mID;

		int userAns, ans;

		boolean isCorrect = false;

		numQuery = Integer.parseInt(br.readLine());

		for (int q = 0; q < numQuery; ++q)
		{
			st = new StringTokenizer(br.readLine(), " ");

			int cmd;
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd)
			{
			case CMD_INIT:
                //if(TC==10){
                //    System.out.println("INIT");
                //}
				usersolution.init();
				isCorrect = true;
				break;
			case CMD_ADD:
				mX = Integer.parseInt(st.nextToken());
				mY = Integer.parseInt(st.nextToken());
                //if(TC==10){
                //    System.out.println("ADD"+" mX: "+mX+" mY: "+mY);
                //}
				usersolution.add(mX, mY);
				break;
			case CMD_REMOVE:
				mX = Integer.parseInt(st.nextToken());
				mY = Integer.parseInt(st.nextToken());
                //if(TC==10){
                //    System.out.println("REMOVE"+" mX: "+mX+" mY: "+mY);
                //}
				usersolution.remove(mX, mY);
				break;
			case CMD_NUMBER_OF_CROSS:
				mID = Integer.parseInt(st.nextToken());        
				userAns = usersolution.numberOfCross(mID);
				ans = Integer.parseInt(st.nextToken());
                //if(TC==10){
                //    System.out.println("CROSS"+" mID: "+mID);
                //}
				if (userAns != ans)
				{
					isCorrect = false;
                    System.out.println("Cross is Wrong!");
				}
				break;
			case CMD_PARTICIPANT:            
				mX = Integer.parseInt(st.nextToken());
				mY = Integer.parseInt(st.nextToken());
				userAns = usersolution.participant(mX, mY);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
				{
					isCorrect = false;
                    if(TC==10){
                        System.out.println("@Participant "+mX+" "+mY+" "+userAns+" "+ans);
                    }
                    System.out.println("@Participant "+mX+" "+mY+" "+userAns+" "+ans);
				}
				break;
			default:
				isCorrect = false;
				break;
			}
		}
		return isCorrect;
	}

	public static void main(String[] args) throws Exception
	{
		int TC, MARK;
	
		System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        
        //실험할 코드 추가
		for (int testcase = 1; testcase <= TC; ++testcase)
		{
            
        
			int score = run(br,testcase) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
            //System.out.println("add: "+usersolution.time[0]+" remove: "+usersolution.time[1]+" cross: "+usersolution.time[2]+" participant: "+usersolution.time[3]);
		}
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("실행시간(ms) : "+secDiffTime);

		br.close();
	}
}
