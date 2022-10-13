import java.util.ArrayList;

class UserSolution
{   
    public static int upperBound(ArrayList<horizon> array, int value) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (value < array.get(mid).y) {                
                high = mid;
            } else {                
                low = mid + 1;
            }
        }
        return low;
    }

    public static  int binarySearch(ArrayList<horizon> array, int value) {
        int low = 0;
        int high = array.size() - 1;
 
        while (low <= high) {
            int mid = low + (high - low)/2;
 
            if (value > array.get(mid).y)
                low = mid + 1;
            else if (value < array.get(mid).y)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1);
    }

    class horizon{
        int y;
        int dir;
        horizon(int y, int dir){
            this.y = y;
            this.dir = dir;
        }
    }

    ArrayList<ArrayList<horizon>> map;

	public void init()
	{
        map = new ArrayList<>();
        for(int i=0;i<101;i++){
            map.add(new ArrayList<>());
        }
	}

	public void add(int mX, int mY)
	{   
        int UB = upperBound(map.get(mX), mY);
        map.get(mX).add(UB, new horizon(mY, 1));
        UB = upperBound(map.get(mX+1), mY);
        map.get(mX+1).add(UB, new horizon(mY, -1));
        
	}

	public void remove(int mX, int mY)
	{
        int idx = binarySearch(map.get(mX), mY);
        map.get(mX).remove(idx);
        idx = binarySearch(map.get(mX+1), mY);
        map.get(mX+1).remove(idx);
	}

	public int numberOfCross(int mID)
	{
        int x = mID;
        int y = 0;
        int cnt = 0;
        while(y!=1000000000){                   
            ArrayList<horizon> keyList = map.get(x);
            int left = 0;
            int right = keyList.size();
            int mid;
            while(left<right){
                mid = (left+right)/2;
                if(keyList.get(mid).y>y){
                    right = mid;
                }else{
                    left = mid+1;
                }
            }
            if(right<keyList.size()){
                x+=map.get(x).get(right).dir;
                cnt++;
                y = keyList.get(right).y;
            }else{
                y = 1000000000;
            }
        }
		return cnt;
	}

	public int participant(int mX, int mY)
	{
        int x = mX;
        int y = mY;
        while(y!=0){             
            ArrayList<horizon> keyList = map.get(x);
            if(keyList.size()==0){
                y=0;                
            }else if(keyList.size()==1){     
                if(keyList.get(0).y<y){
                    x+=keyList.get(0).dir;
                    y = keyList.get(0).y;
                }else{
                    y=0;
                }                
            }
            else{
                int left = 0;
                int right = keyList.size();
                int mid;
                while(left+1<right){
                    mid = (left+right)/2;
                    if(keyList.get(mid).y<y){
                        left = mid;
                    }else{
                        right = mid;                        
                    }
                }

                int next = left;
                if(next==0){
                    if(keyList.get(next).y<y){                        
                        x+=keyList.get(next).dir;
                        y = keyList.get(next).y;
                    }else{
                        y = 0;
                    }
                }else if(next<keyList.size()){
                    x+= keyList.get(next).dir;
                    y = keyList.get(next).y;
                }else if(next==keyList.size()){
                    x+= keyList.get(next-1).dir;
                    y = keyList.get(next-1).y;
                }
                else{
                    y = 0;
                }
            }            
        }
		return x;
	}
}
