public class UserSolution {
    
    class User{
        int uID;
        int income;
        public User(int uID, int income) {
            this.uID = uID;
            this.income = income;
        }
        public int comp(User o2){
            if(this.income>o2.income){
                return 1;
            }else if(this.income<o2.income){
                return -1;
            }else{
                if(this.uID<o2.uID){
                    return 1;
                }else if(this.uID>o2.uID){
                    return -1;
                }else{
                    return 0;
                }                    
            }
        }        
    }

    User[] users;
    int size;

	public void init() {
        users = new User[100001];
        size = 0;
	}

    User pop(User[] data,int size){
        User max = data[1];
        data[1] = data[size--];
        for(int i=1;(i*2)<=size;){
            int larger = data[i*2].comp(data[i*2+1])>0?i*2:i*2+1;
            if(data[larger].comp(data[i])>0){
                User tmp = data[larger];
                data[larger] = data[i];
                data[i] = tmp;
                i = larger;
            }else break;
        }
        return max;
    }
	
	public void addUser(int uID, int income) {
        //System.out.println("add uID: "+uID+" income: "+income);
        users[++size] = new User(uID, income);
        for(int i = size;i/2!=0&&users[i/2].comp(users[i])<0;i/=2){
            User tmp = users[i/2];
            users[i/2] = users[i];
            users[i] = tmp;
        }
	}
	
	int getTop10(int[] result) {
        //User[] tmp = new User[size+1];
        User[] tmp = users.clone();
        //for(int i=0;i<size+1;i++){
        //    tmp[i] = users[i];
        //}
        if(size>=10){
            int tmpSize = size;
            for(int i=0;i<10;i++){
                result[i] = pop(tmp,tmpSize--).uID;
                //System.out.println(result[i]);
            }
            return 10;
        }else{
            int tmpSize = size;
            for(int i=0;i<size;i++){
                result[i] = pop(tmp,tmpSize--).uID;
                //System.out.println(result[i]);
            }
            return size;
        }
	}
}