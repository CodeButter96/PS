import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

class UserSolution {    
    class memory{
        int addr;
        int size;
        memory(int addr, int size){
            this.addr = addr;
            this.size = size;
        }
        @Override
        public boolean equals(Object o){
            return (this.addr==((memory)o).addr&&this.size==((memory)o).size);
        }
    }

    TreeSet<memory> freeMem;//size순으로 정렬, size가 같으면 addr순으로    
    TreeMap<Integer,Integer> usedMem;//addr순으로 정렬
    int max;
	
    public void init(int N) {
        //System.out.println("init N: "+N);
        max = N;
        freeMem = new TreeSet<>(new Comparator<memory>() {
            @Override
            public int compare(UserSolution.memory o1, UserSolution.memory o2) {
                int diff = -o1.size+o2.size;
                if(diff==0){
                    return (-o1.addr+o2.addr);
                }
                return diff;
            }            
        });
        usedMem = new TreeMap<>();
        freeMem.add(new memory(0, N));
		return;
	}

	public int allocate(int mSize) {
        //System.out.println("allocate mSize: "+mSize);
        memory it = freeMem.lower(new memory(-1, mSize));
        if(it == null) { 
            //System.out.println("allocate fail");
            return -1;}
        int size = it.size, addr = it.addr;
        freeMem.remove(it);
        usedMem.put(addr, mSize);
        if(size > mSize) freeMem.add(new memory(addr+mSize, size-mSize));
        //System.out.println("allocated at "+addr+", size: "+mSize);
        return addr;
	}

	public int release(int mAddr) {
        //System.out.println("release mAddr: "+mAddr);
        if(!usedMem.containsKey(mAddr)){            
            //System.out.println("release fail");
            return -1;
        }
        //System.out.println(usedMem.get(mAddr));
        int size = usedMem.remove(mAddr); 
        //System.out.println(size);
        int res = size;

        int afterSize = 0;
        Entry<Integer,Integer> after = usedMem.higherEntry(mAddr);
        if(after!=null){
            afterSize = after.getKey() - (mAddr+size);
        }else if(mAddr+size<max){
            afterSize = max - (mAddr+size);
        }
        if(afterSize>0){
            freeMem.remove(new memory(mAddr+size, afterSize));
            size += afterSize;
        }

        int beforeSize = 0;        
        Entry<Integer,Integer> before = usedMem.lowerEntry(mAddr);
        if(before!=null){
            beforeSize = mAddr - (before.getKey()+before.getValue());
        }else if(mAddr>0){
            beforeSize = mAddr;
        }
        if(beforeSize>0){
            freeMem.remove(new memory(mAddr - beforeSize, beforeSize));
            size += beforeSize;
            mAddr -= beforeSize;
        }
        freeMem.add(new memory(mAddr, size));        
        //System.out.println("Addr: "+mAddr+" Size: "+size+" released");
        return res;
	}
    
}
