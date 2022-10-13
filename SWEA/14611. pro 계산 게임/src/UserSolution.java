import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

class UserSolution {
    class Sum{
        int sum;
        int jokerCnt;
        public Sum(int sum, int jokerCnt) {
            this.sum = sum;
            this.jokerCnt = jokerCnt;
        }
    }
    ArrayDeque<Sum> sumList; 
    ArrayList<ArrayDeque<Integer>> results;   
    ArrayDeque<Integer> table;
    int joker;

    void init(int mJoker, int mNumbers[]) {
        table = new ArrayDeque<>();
        sumList = new ArrayDeque<>();
        results = new ArrayList<>();
        joker = mJoker%20;
        for(int i : mNumbers){             
            table.addLast(i);
        }
        for(int i=0;i<20;i++){
            results.add(new ArrayDeque<>());
        }
        List<Integer> tmp = new ArrayList<>(table);
        int sum = 0;
        int jokerCnt = 0;
        for(int j=0;j<4;j++){
            int num = tmp.get(j);
            if(num==-1){jokerCnt++;}
            else{sum+=num;}
        }
        sumList.add(new Sum(sum, jokerCnt));
        for(int i=0;i<20;i++){
            results.get(i).add((sum+(jokerCnt*i))%20);
        }

        int num = tmp.get(0);
        if(num==-1){jokerCnt--;}
        else{sum-=num;}
        num = tmp.get(4);
        if(num==-1){jokerCnt++;}
        else{sum+=num;}
        sumList.add(new Sum(sum, jokerCnt));
        for(int i=0;i<20;i++){
            results.get(i).add((sum+(jokerCnt*i))%20);
        }
    }

    void putCards(int mDir, int mNumbers[]) {
        if(mDir==0){//왼쪽에 나열
            for(int i=4;i>=0;i--){
                table.addFirst(mNumbers[i]);
            }            
            List<Integer> tmp = new ArrayList<>(table);

            for(int i=4;i>=0;i--){
                int sum = sumList.getFirst().sum;
                int jokerCnt = sumList.getFirst().jokerCnt;
                int num = tmp.get(i+4);
                if(num==-1){jokerCnt--;}
                else{sum-=num;}
                num = tmp.get(i);
                if(num==-1){jokerCnt++;}
                else{sum+=num;}
                sumList.addFirst(new Sum(sum, jokerCnt));
                for(int j=0;j<20;j++){
                    results.get(j).addFirst((sum+(jokerCnt*j))%20);
                }
            }
        }else{//오른쪽에 나열
            for(int i : mNumbers){
                table.addLast(i);
            }
            List<Integer> tmp = new ArrayList<>(table);
            for(int i=tmp.size()-8;i<tmp.size()-3;i++){
                int sum = sumList.getLast().sum;
                int jokerCnt = sumList.getLast().jokerCnt;
                int num = tmp.get(i-1);
                if(num==-1){jokerCnt--;}
                else{sum-=num;}
                num = tmp.get(i+3);
                if(num==-1){jokerCnt++;}
                else{sum+=num;}
                sumList.add(new Sum(sum, jokerCnt));
                for(int j=0;j<20;j++){
                    results.get(j).add((sum+(jokerCnt*j))%20);
                }
            }
        }
    }

    int findNumber(int mNum, int mNth, int ret[]) {
        int answer = 0;
        ArrayDeque<Integer> copy = results.get(joker).clone();
        int idx = 0;
        while(!copy.isEmpty()){
            if(copy.pollFirst()==mNum){
                mNth--;
                if(mNth==0){
                    List<Integer> cards = new ArrayList<>(table);
                    for(int j=0;j<4;j++){
                        ret[j] = cards.get(idx+j);
                    }
                    answer = 1;
                    break;
                }
            }
            idx++;
        }
        return answer;
    }

    void changeJoker(int mValue) {
        joker = mValue%20;
    }
}