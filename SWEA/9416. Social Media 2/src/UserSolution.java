import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class UserSolution {
    class Post{
        int pID;
        int uID;
        int timestamp;
        int like=0;
        public Post(int pID, int uID, int timestamp) {
            this.pID = pID;
            this.uID = uID;
            this.timestamp = timestamp;
        }
    }
    boolean[][] follow;
    Post[] posts;
    int postCnt;

	public void init(int N)
	{
        follow= new boolean[N+1][N+1];
        for(int i=1;i<=N;i++){
            follow[i][i] = true;
        }
        posts = new Post[100001];
        postCnt = 0;
	}

	public void follow(int uID1, int uID2, int timestamp)
	{
        follow[uID1][uID2] = true;
	}

	public void makePost(int uID, int pID, int timestamp)
	{
        Post newPost = new Post(pID, uID, timestamp);
        //hm.put(pID, newPost);
        posts[pID] = newPost;
        postCnt++;
	}

	public void like(int pID, int timestamp)
	{
        //hm.get(pID).like++;
        posts[pID].like++;
	}

	public void getFeed(int uID, int timestamp, int pIDList[])
	{
        PriorityQueue<Post> pq = new PriorityQueue<>(new Comparator<Post>(){
            @Override
            public int compare(Post p1, Post p2){
                if(timestamp - p1.timestamp<=1000){
                    if(timestamp - p2.timestamp>1000){//p1만 1000초 이내
                        return -1;
                    }else{//둘다 1000초 이내
                        if(p1.like>p2.like){
                            return -1;
                        }else if(p1.like<p2.like){
                            return 1;
                        }else{
                            return -p1.timestamp+p2.timestamp;
                        }
                    }
                }else{
                    if(timestamp - p2.timestamp<=1000){//p2만 1000초 이내
                        return 1;
                    }else{//둘다 1000초 밖
                        return -p1.timestamp+p2.timestamp;
                    }
                }
            }
        });
        
        int n = 0;
        for(int i = postCnt;i>0;i--){
            Post p = posts[i];
            if(n>=10){
                if(timestamp - p.timestamp>1000){break;}
            }
            if(follow[uID][p.uID]){
                pq.add(p);
                n++;
            }
        }
        /*for(int i=1;i<=postCnt;i++){
            if(n==10)break;
            Post p = posts[i];
            if(follow[uID][p.uID]){
                pq.add(p);
                n++;
            }
        }*/
        
        n = pq.size()>10?10:pq.size();
        for(int i=0;i<n;i++){
            pIDList[i] = pq.poll().pID;
        }

	}
}