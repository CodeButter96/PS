import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	private final static int CMD_INIT				= 1;
	private final static int CMD_HIRE				= 2;
	private final static int CMD_FIRE				= 3;
	private final static int CMD_UPDATE_SOLDIER		= 4;
	private final static int CMD_UPDATE_TEAM		= 5;
	private final static int CMD_BEST_SOLDIER		= 6;
	
	private final static UserSolution usersolution = new UserSolution();

	static long[] time;
	static String[] funcName = new String[] {"INIT","HIRE","FIRE","UP_SOL","UP_TEAM","BEST"};
	
	private static boolean run(BufferedReader br) throws Exception
	{
		StringTokenizer st;
		
		int numQuery;

		int mID, mTeam, mScore, mChangeScore;
	
		int userAns, ans;
	
		boolean isCorrect = false;

		long beforeTime, afterTime, secDiffTime;
		time = new long[6];

		numQuery = Integer.parseInt(br.readLine());
		
		for (int q = 0; q < numQuery; ++q)
		{
			st = new StringTokenizer(br.readLine(), " ");

			int cmd;
			cmd = Integer.parseInt(st.nextToken());
			
			switch(cmd)
			{
			case CMD_INIT:
				beforeTime = System.currentTimeMillis();
				usersolution.init();
				isCorrect = true;
				afterTime = System.currentTimeMillis();
				secDiffTime = (afterTime - beforeTime);
				time[0]+=secDiffTime;
				break;
			case CMD_HIRE:
				beforeTime = System.currentTimeMillis();
				mID = Integer.parseInt(st.nextToken());
				mTeam = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.hire(mID, mTeam, mScore);
				afterTime = System.currentTimeMillis();
				secDiffTime = (afterTime - beforeTime);
				time[1]+=secDiffTime;
				break;
			case CMD_FIRE:
				beforeTime = System.currentTimeMillis();
				mID = Integer.parseInt(st.nextToken());
				usersolution.fire(mID);
				afterTime = System.currentTimeMillis();
				secDiffTime = (afterTime - beforeTime);
				time[2]+=secDiffTime;
				break;
			case CMD_UPDATE_SOLDIER:
				beforeTime = System.currentTimeMillis();
				mID = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.updateSoldier(mID, mScore);
				afterTime = System.currentTimeMillis();
				secDiffTime = (afterTime - beforeTime);
				time[3]+=secDiffTime;
				break;
			case CMD_UPDATE_TEAM:
				beforeTime = System.currentTimeMillis();
				mTeam = Integer.parseInt(st.nextToken());
				mChangeScore = Integer.parseInt(st.nextToken());
				usersolution.updateTeam(mTeam, mChangeScore);
				afterTime = System.currentTimeMillis();
				secDiffTime = (afterTime - beforeTime);
				time[4]+=secDiffTime;
				break;
			case CMD_BEST_SOLDIER:
				beforeTime = System.currentTimeMillis();
				mTeam = Integer.parseInt(st.nextToken());
				userAns = usersolution.bestSoldier(mTeam);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans) {
					isCorrect = false;
				}
				afterTime = System.currentTimeMillis();
				secDiffTime = (afterTime - beforeTime);
				time[5]+=secDiffTime;
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
	
		System.setIn(new java.io.FileInputStream("res/sample_25_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= 15; ++testcase)
		{
			int score = run(br) ? MARK : 0;
            System.out.print("#" + testcase + " " + score);
			for(int i=0;i<6;i++){
				System.out.print(" "+funcName[i]+" "+time[i]);
			}System.out.println();
		}

		br.close();
	}
}