import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class UserSolution {
    int N, K, M, B;
    int[] resPrice;
    int[][][] area; 
    int[][] block;

    class Offer{
        String cName;
        int mR1, mC1, mR2, mC2;
        int profit;
        boolean cancel;
        public Offer(String cName, int mR1, int mC1, int mR2, int mC2, int profit) {
            this.cName = cName;
            this.mR1 = mR1;
            this.mC1 = mC1;
            this.mR2 = mR2;
            this.mC2 = mC2;
            this.profit = profit;
            cancel = false;
        }
    }
    PriorityQueue<Offer> pq;
    HashMap<String,Offer> hm;

    void init(int n, int k, int m, int mResPrice[])
    {
        N = n; K = k; M = m;
        B = N/K;
        resPrice = new int[M];
        for(int i=0;i<M;i++){resPrice[i] = mResPrice[i];}
        area = new int[N][N][M];
        block = new int[B][B];
        hm = new HashMap<>();
        pq = new PriorityQueue<>(new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                // TODO Auto-generated method stub
                return -o1.profit + o2.profit;
            }            
        });
    }

    void destroy()
    {

    }

    void update(int mBlockR, int mBlockC, int mCost, int mResInfo[][])
    {
        block[mBlockR][mBlockC] = mCost;
        int areaR = mBlockR*K, areaC = mBlockC*K;
        for(int i=0;i<K;i++){
            for(int j=0;j<K;j++){
                int x = areaR+i, y = areaC+j;
                int resInfo = mResInfo[i][j];
                for(int k=M-1;k>=0;k--){
                    if(resInfo==0){
                        for(int l=k;l>=0;l--){
                            area[x][y][k] = 0;
                        }
                        break;
                    }
                    area[x][y][k] = resInfo%2;
                    resInfo/=2;
                }                
            }
        }
    }

    int netProfit(int mR1, int mC1, int mR2, int mC2){
        //개발비용 =  구역 * 단위면적당 비용의 합
        int cost = 0;
        for(int i = mR1;i<=mR2;i++){
            int x = i/K;
            for(int j = mC1;j<=mC2;j++){
                cost += block[x][j/K];
            }
        }
        //발생수익 = 구역의 자원갯수*가격의 합
        int profit = 0;
        for(int i = mR1;i<=mR2;i++){
            for(int j = mC1;j<=mC2;j++){
                for(int k=0;k<M;k++){
                    profit += area[i][j][k]*resPrice[k];
                }                
            }
        }
        return profit - cost;
    }

    int participate(char mCompany[], int mR1, int mC1, int mR2, int mC2)
    {
        String cName = new String(mCompany);
        int estimate = netProfit(mR1, mC1, mR2, mC2);
        Offer thisOffer = new Offer(cName, mR1, mC1, mR2, mC2, estimate);
        hm.put(cName, thisOffer);
        pq.add(thisOffer);
        if(estimate>=0){return estimate;}
        return -1;
    }

    void cancel(char mCompany[])
    {
        String cName = new String(mCompany);
        hm.get(cName).cancel = true;
    }

    int calcProfit(int mTop)
    {
        return -2;
    }
}