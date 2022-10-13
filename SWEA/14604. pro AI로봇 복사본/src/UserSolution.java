import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

class UserSolution
{    
    int n;
    class Robot{
        int rID;
        int intel;
        int status=0;
        int returnedTime=0;
        public Robot(int rID, int intel) {
            this.rID = rID;
            this.intel = intel;
        }
        public Robot(Robot robot) {
            this.rID = robot.rID;
            this.intel = robot.intel;
            this.status = robot.status;
            this.returnedTime = robot.returnedTime;
        }
        public int getIntel(int cTime){
            return intel + cTime - returnedTime;
        }
    }
    Robot[] robotCache;
    ArrayList<Stack<Integer>> work;
    ArrayList<Integer> workStartTime;

    void r2c(int rID, int cTime, int intel){
        robotCache[rID].returnedTime = cTime;
        robotCache[rID].intel = intel;
        robotCache[rID].status = 0;

        qHigh.add(new Robot(robotCache[rID]));
        qLow.add(new Robot(robotCache[rID]));
    }

    //ArrayList<Robot> robots;
    PriorityQueue<Robot> qHigh;
    PriorityQueue<Robot> qLow;

	public void init(int N)
	{
        n = N;
        robotCache = new Robot[N+1];
        work = new ArrayList<>();
        workStartTime = new ArrayList<>();
        workStartTime.add(0);
        work.add(new Stack<>());
        //robots = new ArrayList<>();
        qHigh = new PriorityQueue<>(new Comparator<Robot>() {
            @Override
            public int compare(Robot r1, Robot r2){
                int intel1 = r1.intel - r1.returnedTime;
                int intel2 = r2.intel - r2.returnedTime;
                if(intel1>intel2){
                    return -1;
                }else if(intel1<intel2){
                    return 1;
                }else{
                    if(r1.rID<r2.rID){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            }
        });
        qLow = new PriorityQueue<>(new Comparator<Robot>() {
            @Override
            public int compare(Robot r1, Robot r2){
                int intel1 = r1.intel - r1.returnedTime;
                int intel2 = r2.intel - r2.returnedTime;
                if(intel1>intel2){
                    return 1;
                }else if(intel1<intel2){
                    return -1;
                }else{
                    if(r1.rID<r2.rID){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            }
        });

        for(int i=1;i<=n;i++){
            robotCache[i] = new Robot(i, 0);
            //robotCache[i].rID = i;
            r2c(i, 0, 0);
        }
	}

	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{
        work.add(new Stack<>());
        workStartTime.add(0);
        int sumRobotId = 0;
        for(int i=1;i<=mNum;i++){
            int robotId = -1;
            while(robotId<0){
                Robot robot;
                if(mOpt==0){
                    robot = qHigh.poll();
                }else{
                    robot = qLow.poll();
                }
                if(robotCache[robot.rID].status!=0){continue;}
                if(robotCache[robot.rID].returnedTime==robot.returnedTime){
                    robotId = robot.rID;
                }
            }
            robotCache[robotId].status = wID;
            work.get(wID).push(robotId);
            workStartTime.set(wID, cTime);
            sumRobotId += robotId;
        }

        return sumRobotId;
	}

	public void returnJob(int cTime, int wID)
	{
        while(!work.get(wID).isEmpty()){
            int robotId = work.get(wID).pop();
            if(robotCache[robotId].status==wID){
                r2c(robotId, cTime, robotCache[robotId].getIntel(workStartTime.get(wID)));
            }
        }
	}

	public void broken(int cTime, int rID)
	{
        if(robotCache[rID].status==0||robotCache[rID].status==-1){return;}
        robotCache[rID].status = -1;
	}

	public void repair(int cTime, int rID)
	{
        if(robotCache[rID].status!=-1){return;}
        r2c(rID, cTime, 0);
	}

	public int check(int cTime, int rID)
	{
        if(robotCache[rID].status==-1){return 0;}
        if(robotCache[rID].status==0){
            return robotCache[rID].getIntel(cTime);
        }
        return -robotCache[rID].status;
	}
}