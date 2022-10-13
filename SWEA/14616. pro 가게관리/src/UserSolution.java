import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

class UserSolution {
    HashMap<Integer,Product> products;
    class Product{
        int totalQuantity;
        TreeSet<BuyOrder> buyInfoByPrice;
        public Product(){
            totalQuantity = 0;
            buyInfoByPrice = new TreeSet<>(new Comparator<BuyOrder>() {
                @Override
                public int compare(BuyOrder o1, BuyOrder o2) {
                    if(o1.price==o2.price){
                        return o1.bId-o2.bId;
                    }else{
                        return o1.price-o2.price;
                    }
                }                
            });
        }
    }    
    HashMap<Integer,BuyOrder> buyInfoByID;    
    HashMap<Integer,SellOrder> sellInfoByID;
    class BuyOrder{
        int bId;
        int pId;
        int price;
        int orgQuantity;
        int quantity;
        public BuyOrder(int dealId, int pId, int price, int quantity) {
            this.pId = pId;
            this.bId = dealId;
            this.price = price;
            this.orgQuantity = quantity;
            this.quantity = quantity;
        }
    }
    class SellOrder{
        int sId;
        boolean ref;
        HashMap<Integer,Integer> sellHistory;
        public SellOrder(int dealId, HashMap<Integer,Integer> sellHistory) {
            this.sId = dealId;
            this.sellHistory = sellHistory;
            this.ref = false;
        }
    }
	public void init() {
        products = new HashMap<>(); 
        buyInfoByID = new HashMap<>();   
        sellInfoByID = new HashMap<>();  
		return;
	}

	public int buy(int bId, int mProduct, int mPrice, int mQuantity) {
    //30,000번 이하, 구매후에 보유중인 mProduct의 총 개수 반환
        Product thisProduct;
        if(!products.containsKey(mProduct)){
            thisProduct = new Product();
            products.put(mProduct, thisProduct);
        }else{
            thisProduct = products.get(mProduct);
        }        
        thisProduct.totalQuantity+=mQuantity;

        BuyOrder thisDeal = new BuyOrder(bId,mProduct,mPrice,mQuantity);
        buyInfoByID.put(bId, thisDeal);
        thisProduct.buyInfoByPrice.add(thisDeal);

		return thisProduct.totalQuantity;
	}

	public int cancel(int bId) {
    //구매ID가 bID인 구매를 취소, bID로 구매한 상품 수량이 모두 남아있을 경우에만 취소 가능, 실패하면 -1 반환
    //bID를 판매한적 있어도 환불로 다시 재고가 되었으면 환불 가능
        if(buyInfoByID.containsKey(bId)){
            BuyOrder thisOrder = buyInfoByID.get(bId);
            if(thisOrder.quantity==thisOrder.orgQuantity){
                Product thisProduct = products.get(thisOrder.pId);
                thisProduct.totalQuantity-=thisOrder.quantity;
                thisProduct.buyInfoByPrice.remove(thisOrder);
                buyInfoByID.remove(bId);
                return thisProduct.totalQuantity;
            }
        }
        return -1;
	}

	public int sell(int sId, int mProduct, int mPrice, int mQuantity) {
    //30,000번 이하, mQuantity보다 작으면 -1반환, 싸게 구매한것부터 판매(같으면 bID가 작은것부터), 총 판매수익 반환
        if(!products.containsKey(mProduct)){
            return -1;
        }
        Product thisProduct = products.get(mProduct);
        if(thisProduct.totalQuantity<mQuantity){
            return -1;
        }else{
            int profit = 0;
            HashMap<Integer,Integer> sellHistory = new HashMap<>();
            while(mQuantity>0){
                BuyOrder thisDeal = thisProduct.buyInfoByPrice.first();
                if(thisDeal.quantity>=mQuantity){
                    sellHistory.put(thisDeal.bId, mQuantity);
                    profit+=(mPrice-thisDeal.price)*mQuantity;
                    thisProduct.totalQuantity-=mQuantity;
                    thisDeal.quantity-=mQuantity; mQuantity = 0;                    
                }else{
                    sellHistory.put(thisDeal.bId, thisDeal.quantity);
                    profit+=(mPrice-thisDeal.price)*thisDeal.quantity;
                    thisProduct.totalQuantity-=thisDeal.quantity;
                    mQuantity-=thisDeal.quantity;
                    thisDeal.quantity = 0;
                }
                if(thisDeal.quantity==0){thisProduct.buyInfoByPrice.pollFirst();}
            }
            sellInfoByID.put(sId, new SellOrder(sId, sellHistory));
            return profit;
        }
	}

	public int refund(int sId) {
    //sId로 판매한 상품 환불, 환불해준 상품 개수 반환, 환불한 상품은 재고에 쌓임
    //sId로 판매한 적이 없거나 이미 환불한 ID면 실패하고 -1 반환
        if(!sellInfoByID.containsKey(sId)){
            return -1;
        }
        SellOrder thisDeal = sellInfoByID.get(sId);
        if(thisDeal.ref){
            return -1;
        }
        HashMap<Integer,Integer> sellHistory = thisDeal.sellHistory;
        int cnt = 0;
        for(int i : sellHistory.keySet()){
            BuyOrder refDeal = buyInfoByID.get(i);
            boolean put = false;
            if(refDeal.quantity==0){
                put = true;
            }
            refDeal.quantity+=sellHistory.get(i);
            products.get(refDeal.pId).totalQuantity+=sellHistory.get(i);
            if(put)products.get(refDeal.pId).buyInfoByPrice.add(refDeal);
            cnt+=sellHistory.get(i);
        }
        thisDeal.ref = true;
        sellHistory.clear();
        
        
		return cnt;
	}
}