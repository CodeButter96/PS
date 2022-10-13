import java.util.ArrayList;
import java.util.HashMap;

class UserSolution {
    class Player{
        String name;
        int score;
        int wrongScore;
        int rank[];
        HashMap<String,Integer> results;
        public Player(String name, int score, int wrongScore) {
            this.name = name;
            this.score = score;
            this.wrongScore = wrongScore;
            this.results = new HashMap<>();
            this.rank = new int[3];
        }
        /*public int getRank(){
            //int curScore = thisPlayer.score;
            int bestScore = fullScore - wrongScore;
            int curRank = 1, BestRank = 1, WorstRank = 1;
            for(String player : players.keySet()){
                if(player.equals(name)){continue;}
                if(bestScore<players.get(player).score){
                    BestRank++;
                    curRank++;
                }           
                else if(score<players.get(player).score){
                    curRank++;
                }
                if(score<fullScore - players.get(player).wrongScore){
                    WorstRank++;
                }
            }
            return curRank;
        }*/
    }
    class Problem{
        int score;
        ArrayList<String> players;
        public Problem(int score) {
            this.score = score;
            this.players = new ArrayList<>();
        }
    }
    HashMap<String,Player> players;
    HashMap<String,Problem> problems;
    ArrayList<Player> curRank;
    ArrayList<Player> bestRank;
    ArrayList<Player> worstRank;

    

    int fullScore;

    void init () {
        players = new HashMap<>();
        problems = new HashMap<>();
        curRank = new ArrayList<>();
        bestRank = new ArrayList<>();
        worstRank = new ArrayList<>();
        fullScore = 0;
    }

    void destroy () {

    }

    void newPlayer(char name[]) {
        String playerName = new String(name);
        Player thisPlayer = new Player(playerName, 0, 0);
        int curRank = 1, WorstRank = 1;
        for(String player : players.keySet()){
            if(0<players.get(player).score){
                curRank++;
            }
            if(0<fullScore - players.get(player).wrongScore){
                WorstRank++;
            }
        }
        thisPlayer.rank[0] = curRank;
        thisPlayer.rank[1] = 1;
        thisPlayer.rank[2] = WorstRank;
        for(Player player : players.values()){
            if(player.score<fullScore){
                player.rank[2]++;
            }
        }
        players.put(playerName, thisPlayer);
        //기존의 플레이어들 curRank, bestRank는 변화X WorstRank만 변화
        //worstRank때 새로운플레이어는 다 맞는경우
        //새로운 플레이어의 curRank 구하기, bestRank는 1등, worstRank는 다 틀리는경우
    }

    void newProblem(char name[], int score) {
        String problemName = new String(name);
        problems.put(problemName, new Problem(score));
        fullScore += score;
        //curRank는 변화x
        //bestRank는 변화
        for(Player player1 : players.values()){
            for(Player player2 : players.values()){
                if(fullScore-player1.score<player2.score){
                    player1.rank[2]++;
                }
            }
        }
        //WorstRank도 변화
    }

    void changeProblemScore(char name[], int new_score) {        
        String problemName = new String(name);
        Problem thisProblem = problems.get(problemName);
        for(String playerName : thisProblem.players){
            Player thisPlayer = players.get(playerName);
            if(thisPlayer.results.get(problemName)==1){
                thisPlayer.score = thisPlayer.score - thisProblem.score + new_score;
            }else{
                thisPlayer.wrongScore = thisPlayer.wrongScore - thisProblem.score + new_score;
            }
        }
        fullScore = fullScore - thisProblem.score + new_score;
        thisProblem.score = new_score;
    }

    void attemptProblem(char player_name[], char problem_name[], int attempt_result) {        
        String playerName = new String(player_name);
        String problemName = new String(problem_name);
        Player thisPlayer = players.get(playerName);
        Problem thisProblem = problems.get(problemName);
        thisPlayer.results.put(problemName, attempt_result);
        if(attempt_result==1){
            thisPlayer.score = thisPlayer.score + thisProblem.score;
        }else{
            thisPlayer.wrongScore = thisPlayer.wrongScore + thisProblem.score;         
        }
        thisProblem.players.add(playerName);
    }

    Result getRank(char player_name[]) {      
        String playerName = new String(player_name);
        Player thisPlayer = players.get(playerName);
        int curScore = thisPlayer.score;
        int bestScore = fullScore - thisPlayer.wrongScore;
        int curRank = 1, BestRank = 1, WorstRank = 1;
        for(String player : players.keySet()){
            if(player.equals(playerName)){continue;}
            if(bestScore<players.get(player).score){
                BestRank++;
                curRank++;
            }           
            else if(curScore<players.get(player).score){
                curRank++;
            }
            if(curScore<fullScore - players.get(player).wrongScore){
                WorstRank++;
            }
        }
        Result res = new Result(curRank,BestRank,WorstRank);
        return res;
    }

}