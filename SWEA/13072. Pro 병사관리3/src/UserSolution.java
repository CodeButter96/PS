import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
class UserSolution
{
    
    int[][] soldiers;
    List<ArrayList<LinkedList<Integer>>> teams;   
    boolean[] updated = new boolean[5];
	public void init()
	{
        soldiers = new int[100001][2];
        teams = new ArrayList<ArrayList<LinkedList<Integer>>>();
        for(int i=0;i<5;i++){
            teams.add(new ArrayList<LinkedList<Integer>>());
            for(int j=0;j<5;j++){
                teams.get(i).add(new LinkedList<Integer>());
            }
        }
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
        for(int i=0;i<5;i++){
            if(updated[i]){
                for(int j=0;j<5;j++){
                    for(int k : teams.get(i).get(j)){
                        soldiers[k][1] = j+1;
                    }
                }                
                updated[i] = false;
            }
        }    
        //System.out.println("hire mID:"+mID+" mTeam:"+mTeam+" mScore:"+mScore);
        soldiers[mID][0] = mTeam;
        soldiers[mID][1] = mScore;
        teams.get(mTeam-1).get(mScore-1).add(mID);
	}
	
	public void fire(int mID)
	{       
        for(int i=0;i<5;i++){
            if(updated[i]){
                for(int j=0;j<5;j++){
                    for(int k : teams.get(i).get(j)){
                        soldiers[k][1] = j+1;
                    }
                }                
                updated[i] = false;
            }
        }    
        //System.out.println("fire mID:"+mID);
        LinkedList<Integer> team = teams.get(soldiers[mID][0]-1).get(soldiers[mID][1]-1);     
        team.remove(team.indexOf(mID));
        soldiers[mID][0] = 0;
        soldiers[mID][1] = 0;
	}

	public void updateSoldier(int mID, int mScore)
	{
        for(int i=0;i<5;i++){
            if(updated[i]){
                for(int j=0;j<5;j++){
                    for(int k : teams.get(i).get(j)){
                        soldiers[k][1] = j+1;
                    }
                }                
                updated[i] = false;
            }
        }    
        int team = soldiers[mID][0];
        fire(mID);
        hire(mID, team, mScore);
        //System.out.println("update mID:"+mID+" mScore:"+mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
        //System.out.println("update mTeam:"+mTeam+" mChangeScore:"+mChangeScore);
        int[] newscore = new int[5];
        for(int i=1;i<=5;i++){
            if(i+mChangeScore>5){
                newscore[i-1] = 5;
            }else if(i+mChangeScore<1){
                newscore[i-1] = 1;
            }else{
                newscore[i-1] = i + mChangeScore;
            }
        }        
        ArrayList<LinkedList<Integer>> tmp = new ArrayList<LinkedList<Integer>>();
        for(int i=0;i<5;i++){
            tmp.add(new LinkedList<Integer>());
        }    
        for(int i=0;i<5;i++){
            tmp.get(newscore[i]-1).addAll(teams.get(mTeam-1).get(i));
            //for(int j : teams.get(mTeam-1).get(i)){
            //    soldiers[j][1] = newscore[i];
            //}
        }
        teams.remove(mTeam-1);
        teams.add(mTeam-1,tmp);
        updated[mTeam-1] = true;
	}
	
	public int bestSoldier(int mTeam)
	{
        for(int i=0;i<5;i++){
            if(updated[i]){
                for(int j=0;j<5;j++){
                    for(int mID : teams.get(i).get(j)){
                        soldiers[mID][1] = j+1;
                    }
                }                
                updated[i] = false;
            }
        }        
        
        int maxId = 0;
        //System.out.println("bestSoldier mTeam:"+mTeam);
        LinkedList<Integer> tmp = new LinkedList<>();
        for(int i=5;i>0;i--){
            if(teams.get(mTeam-1).get(i-1).size()!=0){
                tmp = teams.get(mTeam-1).get(i-1);
                break;
            }
        }
        if(tmp.size()==1){
            maxId = tmp.getFirst();
        }
        else{
            for(int i : tmp){
                if(i>maxId){
                    maxId = i;
                }
            }
        }
        //System.out.println("bestSoldier is mID "+maxId); 
        return maxId;       
        
	}
}