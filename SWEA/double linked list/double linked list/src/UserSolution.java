class Node {
	public int data;
	public Node prev;
	public Node next;

	public Node(int data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}

public class UserSolution {
	
	private final static int MAX_NODE = 10000;

	private Node[] node = new Node[MAX_NODE];
	private int nodeCnt = 0;
	private Node head;
    
	public Node getNode(int data) {
		node[nodeCnt] = new Node(data);
		return node[nodeCnt++];
	}

	public void init() {
    	nodeCnt = 0;
    	head = new Node(-1);
	}
	
	public void addNode2Head(int data) {
    	Node newnode = getNode(data);
        if(head.next!=null){
            head.next.prev = newnode;
        	newnode.next = head.next;
        }
        newnode.prev = head;
        head.next = newnode;
	}

	public void addNode2Tail(int data) {
    	Node prev = head;
        while(prev.next!=null){
            prev = prev.next;
        }
        Node newnode = getNode(data);
        prev.next = newnode;
        newnode.prev = prev;
	}

	public void addNode2Num(int data, int num) {
		int count = 1;
    	Node prev = head;
        while(prev.next!=null && count++<num){
            prev = prev.next;
        }
        Node newnode = getNode(data);            
        newnode.next = prev.next;
        newnode.prev = prev;
        prev.next = newnode;
	}
	
	public int findNode(int data) {
		int count = 1;
    	Node prev = head;
        while(prev.next!=null && prev.next.data!=data){
            prev = prev.next;
            count++;
        }
        if(prev.next!=null){
            return count;
        }
        return -1;
	}
	
	public void removeNode(int data) {
		Node prev = head;
        while(prev.next!=null && prev.next.data!=data){
            prev = prev.next;
        }        
        if(prev.next!=null){
            if(prev.next.next!=null){
                prev.next.next.prev = prev;
                prev.next = prev.next.next; 
            }else{
                prev.next = null;
            }           
                       
        	nodeCnt--;
        }
	}

	public int getList(int[] output) {
		Node prev = head;
        int idx = 0;
        while(prev.next!=null){            
            prev = prev.next;
            output[idx++] = prev.data;
        }
		return nodeCnt;
	}
	
	public int getReversedList(int[] output) {
		Node prev = head;
        int idx = 0;
        while(prev.next!=null){            
            prev = prev.next;
            output[nodeCnt-1-idx++] = prev.data;
        }
		return nodeCnt;
	}
}