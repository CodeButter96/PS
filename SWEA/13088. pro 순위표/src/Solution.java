import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Result {
    public int current_rank = 0, best_rank = 0, worst_rank = 0;
    
    Result () {

    }

    Result (int current, int best, int worst) {
        current_rank = current;
        best_rank = best;
        worst_rank = worst;
    }

}

class Solution {

    private static UserSolution usersolution = new UserSolution();
    private static BufferedReader br;
    private static StringTokenizer line;
    private final static int INIT					= 0;
    private final static int NEW_PLAYER				= 1;
    private final static int NEW_PROBLEM			= 2;
    private final static int CHANGE_PROBLEM_SCORE 	= 3;
    private final static int ATTEMPT_PROBLEM		= 4;
    private final static int GET_RANK 		        = 5;
    public static long[] timer = new long[5];

    private static void stringToArray(char dst[], String s){
        for(int i=0;i<s.length();i++) dst[i] = s.charAt(i);	
        dst[s.length()] = 0;
    }

    private static int run (int tc_result) throws Exception 
    {

        StringTokenizer line = new StringTokenizer(br.readLine(), " ");
        int N;
        N = Integer.parseInt(line.nextToken());
        usersolution.init();

        for (int i = 0; i < N; ++i) {

            line = new StringTokenizer(br.readLine(), " ");
            char name[] = new char[64], player_name[] = new char[64], problem_name[] = new char[64];
            int score, new_score, attempt_result;
            int current_rank_ans, best_rank_ans, worst_rank_ans;
            Result res;

            int cmd = Integer.parseInt(line.nextToken());

            switch (cmd) {
                case NEW_PLAYER:{
                    long beforeTime = System.currentTimeMillis();
                    stringToArray(name, line.nextToken());
                    usersolution.newPlayer(name);
                    long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                    long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
                    timer[0] += secDiffTime;
                    break;
                }
                    

                case NEW_PROBLEM:{
                    long beforeTime = System.currentTimeMillis();
                    stringToArray(name, line.nextToken());
                    score = Integer.parseInt(line.nextToken());
                    usersolution.newProblem(name, score);
                    long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                    long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
                    timer[1] += secDiffTime;
                    break;
                }
                    

                case CHANGE_PROBLEM_SCORE:{
                    long beforeTime = System.currentTimeMillis();
                    stringToArray(name, line.nextToken());
                    new_score = Integer.parseInt(line.nextToken());
                    usersolution.changeProblemScore(name, new_score);
                    long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                    long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
                    timer[2] += secDiffTime;
                    break;
                }
                    

                case ATTEMPT_PROBLEM:{
                    long beforeTime = System.currentTimeMillis();
                    stringToArray(player_name, line.nextToken());
                    stringToArray(problem_name, line.nextToken());
                    attempt_result = Integer.parseInt(line.nextToken());
                    usersolution.attemptProblem(player_name, problem_name, attempt_result);
                    long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                    long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
                    timer[3] += secDiffTime;
                    break;
                }
                    

                case GET_RANK:{
                    long beforeTime = System.currentTimeMillis();
                    stringToArray(player_name, line.nextToken());
                    res = usersolution.getRank(player_name);
                    current_rank_ans = Integer.parseInt(line.nextToken());
                    best_rank_ans = Integer.parseInt(line.nextToken());
                    worst_rank_ans = Integer.parseInt(line.nextToken());
                    if ( !(res.current_rank == current_rank_ans && res.best_rank == best_rank_ans && res.worst_rank == worst_rank_ans) ) {
                        tc_result = 0;
                    }
                    long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                    long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
                    timer[4] += secDiffTime;
                }
                    

                    break;
            }
        }
        
        usersolution.destroy();

        return tc_result;
    }
    
    public static void main(String[] args) throws Exception {

        System.setIn(new java.io.FileInputStream("res/sample_input25.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(line.nextToken());
        int Ans = Integer.parseInt(line.nextToken());
        for (int testcase = 1; testcase <= TC; ++testcase) {
            System.out.println("#" + testcase + " " + run(Ans));
            System.out.println("NEW_PLAYER: "+timer[0]+" NEW_PROBLEM: "+timer[1]
            +" CHANGE_PROBLEM_SCORE: "+timer[2]+" ATTEMPT_PROBLEM: "+timer[3]+" GET_RANK: "+timer[4]);
        }
        
    }
}