import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

class UserSolution
{
    class Soldier{
        int id;
        int team;
        public Soldier(int id, int team, int score) {
            this.id = id;
            this.team = team;
        }
    }
    
    public void calScore(){
        newScore = new HashMap<>();
        for(int i=-4;i<=4;i++){
            newScore.put(i, new HashMap<>());
            for(int j=1;j<=5;j++){
                int value;
                if(j+i>5){
                    value = 5;
                }else if(j+i<1){
                    value = 1;
                }else{
                    value = i+j;
                }
                newScore.get(i).put(j, value);
            }
        }
    }

    HashMap<Integer,HashMap<Integer,Integer>> newScore;
    HashMap<Integer,Soldier> soldiers;
    ArrayList<HashMap<Integer,LinkedList<Soldier>>> DB;
	public void init()
	{
        calScore();
        soldiers = new HashMap<>();
        DB = new ArrayList<>();
        for(int i=0;i<=5;i++){
            DB.add(new HashMap<>());
            for(int j=1;j<=5;j++){
                DB.get(i).put(j,new LinkedList<>());
            }
        }
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
        Soldier newSoldier = new Soldier(mID, mTeam, mScore);
        DB.get(mTeam).get(mScore).add(newSoldier);
        soldiers.put(mID, newSoldier);
	}
	
	public void fire(int mID)
	{
        Soldier target = soldiers.get(mID);
        soldiers.remove(mID);
        HashMap<Integer,LinkedList<Soldier>> team = DB.get(target.team);
        for(LinkedList<Soldier> ll : team.values()){
            /*if(ll.contains(target)){//O(n)
                ll.remove(target);
                break;
            }*/
        }
	}

	public void updateSoldier(int mID, int mScore)
	{ 
        Soldier target = soldiers.get(mID);
        HashMap<Integer,LinkedList<Soldier>> team = DB.get(target.team);
        for(LinkedList<Soldier> ll : team.values()){
            /*if(ll.contains(target)){//O(n)
                ll.remove(target);
                break;
            }*/
        }
        DB.get(target.team).get(mScore).add(target);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
        HashMap<Integer,LinkedList<Soldier>> team = DB.get(mTeam);
        HashMap<Integer,Integer> scoreMap = newScore.get(mChangeScore);        
        
        if(mChangeScore>0){
            for(int i=5;i>=1;i--){
                if(i==scoreMap.get(i)){
                    continue;
                }
                //team.get(scoreMap.get(i)).addAll(team.get(i));//O(n)
                //team.put(i, new LinkedList<>());
            }
        }else if(mChangeScore<0){
            for(int i=1;i<=5;i++){
                if(i==scoreMap.get(i)){
                    continue;
                }
                //team.get(scoreMap.get(i)).addAll(team.get(i));
                //team.put(i, new LinkedList<>());
            }
        }
	}
	
	public int bestSoldier(int mTeam)
	{
        
        int id = 0;        
        HashMap<Integer,LinkedList<Soldier>> team = DB.get(mTeam);
        for(int i=5;i>0;i--){
            if(team.get(i).size()==1){
                id = team.get(i).getFirst().id;
                break;
            }else if(team.get(i).size()>1){
                int max = 0;
                LinkedList<Soldier> ll = team.get(i);
                for(Soldier s : ll){
                    if(s.id>max){max = s.id;}
                }
                id = max;
                break;
            }
        }
        return id;
	}
}