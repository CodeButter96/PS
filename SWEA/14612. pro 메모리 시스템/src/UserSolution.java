import java.util.ArrayList;
import java.util.TreeSet;

class UserSolution {
    public static int binarySearch(ArrayList<memory> array,  int addr) {
        int left = 0;
        int right = array.size()-1;
        int mid = 0;
        while(left<=right){
            //System.out.println("left: "+left+" right: "+right);
            mid = (left+right)/2;
            //System.out.println("mid: "+mid);
            if(addr>array.get(mid).addr){
                left = mid+1;
            }else if(addr<array.get(mid).addr){
                right = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static int binarySearch2(ArrayList<memory> array,  int addr) {
        int left = 0;
        int right = array.size()-1;
        int mid = 0;
        while(left<=right){
            //System.out.println("left: "+left+" right: "+right);
            mid = (left+right)/2;
            //System.out.println("mid: "+mid);
            if(addr>array.get(mid).addr){
                left = mid+1;
            }else if(addr<array.get(mid).addr){
                right = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static int sizelowerBound(ArrayList<memory> array,  int size) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (size <= array.get(mid).size) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    public static int sizeUpperBound(ArrayList<memory> array, int size) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (size < array.get(mid).size) {                
                high = mid;
            } else {                
                low = mid + 1;
            }
        }
        return low;
    }
    public static int addrUpperBound(int l, int h, ArrayList<memory> array, int addr) {
        int low = l;
        int high = h;
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (addr < array.get(mid).addr) {                
                high = mid;
            } else {                
                low = mid + 1;
            }
        }
        return low;
    }
    public static int addrlowerBound(int l, int h, ArrayList<memory> array,  int addr) {
        int low = l;
        int high = h;
        while (low < high) {
            final int mid = low + (high - low)/2;
            if (addr <= array.get(mid).addr) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    class memory{
        int addr;
        int size;
        memory(int addr, int size){
            this.addr = addr;
            this.size = size;
        }
    }

    ArrayList<memory> freeMem;//size순으로 정렬, size가 같으면 addr순으로    
    ArrayList<memory> usedMem;//addr순으로 정렬
	public void init(int N) {
        System.out.println("init N: "+N);
        freeMem = new ArrayList<>();
        usedMem = new ArrayList<>();
        freeMem.add(new memory(0, N));
		return;
	}

	public int allocate(int mSize) {
        System.out.println("allocate mSize: "+mSize);
        if(freeMem.size()==0){
            System.out.println("fail");
            System.out.println("allocate failed");
            return -1;
        }else{
            int LB = sizelowerBound(freeMem, mSize);
            int UB = sizeUpperBound(freeMem, mSize);
            if(LB==freeMem.size()){
                System.out.println("allocate failed");
                return -1;
            }else{
                memory target = freeMem.get(LB);
                int addrUB = addrUpperBound(0, usedMem.size(), usedMem, target.addr);
                usedMem.add(addrUB, new memory(target.addr, mSize));
                freeMem.remove(LB);
                if(target.size>mSize){
                    int remain = target.size - mSize;
                    int sizeLB = sizelowerBound(freeMem, remain);
                    int sizeUB = sizeUpperBound(freeMem, remain);
                    if(sizeLB!=sizeUB){
                        //int addrLB = addrlowerBound(sizeLB, sizeUB, freeMem, target.addr+mSize);
                        addrUB = addrUpperBound(sizeLB, sizeUB, freeMem, target.addr+mSize);
                        freeMem.add(addrUB, new memory(target.addr+mSize, remain));
                    }else{
                        freeMem.add(sizeUB, new memory(target.addr+mSize, remain)); 
                    }
                    int idx = binarySearch(freeMem, target.addr+mSize+remain);
                    if(idx!=-1){
                        
                    } 
                }                
                System.out.println("allocated at "+target.addr);
                return target.addr;
            }
        }
	}

	public int release(int mAddr) {
        System.out.println("release mAddr: "+mAddr);
        int idx = binarySearch(usedMem, mAddr);
        if(idx!=-1){
            if(idx>0){
                int gap = mAddr - usedMem.get(idx-1).addr+usedMem.get(idx-1).size;
                if(gap!=0){
                    int sizeLB = sizelowerBound(freeMem, gap);
                    int sizeUB = sizeUpperBound(freeMem, gap);
                    if(sizeLB!=sizeUB){//여러개 존재
                        for(int i=sizeLB;i<sizeUB;i++){
                            if(freeMem.get(i).addr+gap==mAddr){
                                freeMem.remove(i);
                                mAddr -= gap;
                                break;
                            }
                        }
                        int addrUB = addrUpperBound(sizeLB, sizeUB, freeMem, target.addr);
                        freeMem.add(addrUB, new memory(target.addr, target.size));
                    }else{//하나만 존재
                        freeMem.add(sizeUB, new memory(target.addr, target.size));
                    } 
                    
                }
            }
            if(idx+1<usedMem.size()){                
                int gap = usedMem.get(idx+1).addr+usedMem.get(idx+1).size - mAddr;
                if(gap!=0){

                }
            }
            memory target = usedMem.get(idx);
            int sizeLB = sizelowerBound(freeMem, target.size);
            int sizeUB = sizeUpperBound(freeMem, target.size);
            if(sizeLB!=sizeUB){
                //int addrLB = addrlowerBound(sizeLB, sizeUB, freeMem, target.addr+mSize);
                int addrUB = addrUpperBound(sizeLB, sizeUB, freeMem, target.addr);
                freeMem.add(addrUB, new memory(target.addr, target.size));
            }else{
                freeMem.add(sizeUB, new memory(target.addr, target.size));
            } 
            usedMem.remove(idx);
            System.out.println("release success");
            return target.addr;
        }else{
            System.out.println("release failed");
            return -1;
        }
	}
    void merge(int index){
        memory newFree = freeMem.get(index);
        int addrUB = addrUpperBound(0, usedMem.size(), usedMem, newFree.addr);
        if(addrUB!=0){
            memory tmp = usedMem.get(addrUB-1);
            int freeSize = newFree.addr-(tmp.addr+tmp.size);
            int UB = sizeUpperBound(freeMem, freeSize);
            int LB = sizelowerBound(freeMem, freeSize);
            if(LB+1!=UB){//하나만 존재
                freeMem.remove(LB);
            }else{//여러개 존재
                for(int i=LB;i<UB;i++){
                    if(freeMem.get(i).addr+freeMem.get(i).size==newFree.addr){
                        
                        break;
                    }
                }
            }
        }
        if(addrUB!=usedMem.size()){            
            memory tmp = usedMem.get(addrUB);
            if(tmp.addr==addr+size){

            }
        }
    }
}
