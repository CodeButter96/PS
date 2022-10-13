import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

class UserSolution
{
    class Robot{
        int rID;
        int intel;
        int status=0;
        int trainStart=0;
        int wID;
        public Robot(int rID, int intel) {
            this.rID = rID;
            this.intel = intel;
        }
    }
    int N;
    ArrayList<Robot> robots;
    TreeSet<Robot> center;
    TreeSet<Robot> centerLow;
    HashMap<Integer,Robot> broken;
    ArrayList<HashMap<Integer,Robot>> work;
    int workCnt;
	public void init(int n)
	{
        workCnt = 0;
        N = n;
        robots = new ArrayList<>();
        broken = new HashMap<>();
        work = new ArrayList<>();
        work.add(new HashMap<>());//0번 Index에 더미 work 추가
		center = new TreeSet<>(new Comparator<Robot>() {
            @Override
            public int compare(Robot r1, Robot r2){
                if(r1.intel-r1.trainStart>r2.intel-r2.trainStart){return -1;}
                else if(r1.intel-r1.trainStart<r2.intel-r2.trainStart){return 1;}
                else{
                    if(r1.rID<r2.rID){return -1;}
                    else{return 1;}
                }
            }
        });

        centerLow = new TreeSet<>(new Comparator<Robot>() {
            @Override
            public int compare(Robot r1, Robot r2){
                if(r1.intel-r1.trainStart>r2.intel-r2.trainStart){return 1;}
                else if(r1.intel-r1.trainStart<r2.intel-r2.trainStart){return -1;}
                else{
                    if(r1.rID<r2.rID){return -1;}
                    else{return 1;}
                }
            }
        });

        robots.add(new Robot(-1, 0));
        for(int i=1;i<=N;i++){
            Robot thisRobot = new Robot(i, 0);
            robots.add(thisRobot);
            center.add(thisRobot);
            centerLow.add(thisRobot);
        }
	}

	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{
        workCnt++;
        int sum = 0;
        HashMap<Integer,Robot> thisWork = new HashMap<>();
        if(mOpt==0){//높은지능 우선방식
            for(int i=0;i<mNum;i++){
                Robot thisRobot = center.pollFirst();
                thisRobot.status = 1;
                thisRobot.wID = wID;
                thisRobot.intel += (cTime - thisRobot.trainStart);
                sum += thisRobot.rID;
                thisWork.put(thisRobot.rID,thisRobot);
            }
        }else{//낮은지능 우선방식
            for(int i=0;i<mNum;i++){
                Robot thisRobot = center.pollLast();
                thisRobot.status = 1;
                thisRobot.wID = wID;
                thisRobot.intel += (cTime - thisRobot.trainStart);
                sum += thisRobot.rID;
                thisWork.put(thisRobot.rID,thisRobot);
            }
        }
        work.add(thisWork);
		return sum;
	}

	public void returnJob(int cTime, int wID)
	{
		HashMap<Integer,Robot> thisWork = work.get(wID);
        int n = thisWork.size();
        for(int i : thisWork.keySet()){
            Robot thisRobot = thisWork.get(i);
            thisRobot.status = 0;
            thisRobot.trainStart = cTime;
            center.add(thisRobot);
            //thisWork.remove(i);
        }
        thisWork.clear();
	}

	public void broken(int cTime, int rID)
	{
        for(HashMap<Integer,Robot> hMap : work){
            if(hMap.containsKey(rID)){
                Robot thisRobot = hMap.get(rID);
                thisRobot.status = 2;
                broken.put(rID, thisRobot);
                hMap.remove(rID);
                break;
            }
        }		
	}

	public void repair(int cTime, int rID)
	{
		if(broken.containsKey(rID)){
            Robot thisRobot = broken.get(rID);
            thisRobot.status = 0;
            thisRobot.intel = 0;
            thisRobot.trainStart = cTime;
            center.add(thisRobot);
            broken.remove(rID);
        }
	}

	public int check(int cTime, int rID)
	{
        Robot thisRobot = robots.get(rID);
        if(thisRobot.status==0){//센터
            return thisRobot.intel + cTime - thisRobot.trainStart;
        }else if(thisRobot.status==1){//일
            return (thisRobot.wID*(-1));
        }else{//고장
            return 0;
        }
	}
}