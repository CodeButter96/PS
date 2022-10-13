import java.util.HashMap;
import java.util.HashSet;

class UserSolution
{
    class List{
        String name;
        int length;
        int[] values;
        List parent;
        boolean child;
        HashSet<String> children;
        HashMap<Integer,Integer> changes;
        public List(int length, int[] listValue, String name) {
            this.name = name;
            this.child = false;
            this.length = length;
            this.values = listValue.clone();
        }
        public List(List parent, String name) {
            this.children = new HashSet<>();
            this.name = name;
            this.child = true;
            this.parent = parent;
            this.changes = new HashMap<>();
        }
    }

    HashMap<String,List> hm;
	public void init()
	{
        hm = new HashMap<>();
	}

	public void makeList(char mName[], int mLength, int mListValue[])
	{
        String name = new String(mName);
        name = name.split("\0")[0];
        List newList = new List(mLength, mListValue,name);
        hm.put(name, new List(newList,name));
        //hm.put(name, newList);
	}

	public void copyList(char mDest[], char mSrc[], boolean mCopy)
	{
        String srcName = new String(mSrc);
        srcName = srcName.split("\0")[0];
        String dstName = new String(mDest);
        dstName = dstName.split("\0")[0];
        if(mCopy){//Deep Copy
            List src = hm.get(srcName);
            List srcList = new List(src,srcName);
            List dstList = new List(src,dstName);
            hm.put(dstName, dstList);
            hm.put(src.name, srcList);
            for(String s : src.children){
                hm.put(s, srcList);
            }
        }else{//주소만 복사
            hm.put(dstName, hm.get(srcName));
            hm.get(srcName).children.add(dstName);
        }
	}

	public void updateElement(char mName[], int mIndex, int mValue)
	{
        String name = new String(mName);
        name = name.split("\0")[0];
        List element = hm.get(name);
        if(element.child){
            element.changes.put(mIndex, mValue);
        }else{
            element.values[mIndex] = mValue;
        }
	}

	public int element(char mName[], int mIndex)
	{
        String name = new String(mName);
        name = name.split("\0")[0];
        List element = hm.get(name);
            while(true){
                if(!element.child){
                        return element.values[mIndex];
                }
                if(element.changes.containsKey(mIndex)){
                    return element.changes.get(mIndex);
                }
                element = element.parent;
            }
	}
}