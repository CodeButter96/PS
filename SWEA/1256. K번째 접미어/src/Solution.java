import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;
import java.io.FileInputStream;



class Solution
{
    static class TrieNode{
        boolean is_terminal;
        boolean visited;
        String str;
        TreeMap<Character, TrieNode> child;
        TrieNode(){
            this.child = new TreeMap<>();
        }
    }

    static class Trie{
        TrieNode rootNode;

        Trie(){
            rootNode = new TrieNode();
            rootNode.str="";
        }

        void insert(String str){
            TrieNode thisNode = this.rootNode;
            for(int i=0;i<str.length();i++){
                if(thisNode.child.containsKey(str.charAt(i))){
                    thisNode = thisNode.child.get(str.charAt(i));
                }else{
                    thisNode.child.put(str.charAt(i), new TrieNode());                    
                    thisNode = thisNode.child.get(str.charAt(i));
                }
                //thisNode = thisNode.child.computeIfAbsent(str.charAt(i), c -> new TrieNode());
            }
            thisNode.is_terminal = true;
        }
    }

    static String DFS(Trie Trie, int K){
        String s = "none";
        Stack<TrieNode> stack = new Stack<>();
        stack.push(Trie.rootNode);
        Trie.rootNode.visited = true;

        
        int count = 0;
        while(!stack.isEmpty()){
            TrieNode thisNode = stack.pop();
            if(thisNode.is_terminal){
                count++;
                if(count==K)
                    s = thisNode.str;
            }
            for(char c : thisNode.child.descendingKeySet()){
                if(!thisNode.child.get(c).visited){
                    stack.push(thisNode.child.get(c));
                    thisNode.child.get(c).str = thisNode.str + c;     
                    thisNode.child.get(c).visited = true;
                }
            }
        }
        return s;
    }

	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

        int K;
        String s;
		for(int test_case = 1; test_case <= T; test_case++)
		{   
            K = sc.nextInt();
            s = sc.next();
            Trie trie = new Trie();
            for(int i=0;i<s.length();i++){
                trie.insert(s.substring(i));
            }
            
            System.out.println("#"+test_case+" "+DFS(trie,K));            
            

		}
	}
}