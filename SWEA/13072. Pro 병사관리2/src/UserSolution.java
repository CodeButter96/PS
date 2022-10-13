public class UserSolution {

    static class Node {
        int team, id;
        Node prev, next;

        public Node(int team, int id) { this.team = team; this.id = id; }
    }

    private static final int MAX_ID = 100_000;
    private static final int MAX_TEAM = 5;
    private static final int MAX_SCORE = 5;

    private Node[] soldiers;
    private Node[][] teamList; // 원형 더블 연결 리스트(순환 구조)

    public void init() {
        soldiers = new Node[MAX_ID + 1];
        teamList = new Node[MAX_TEAM + 1][MAX_SCORE + 1];

        for (int team = 1; team <= MAX_TEAM; team++) {
            for (int score = 1; score <= MAX_SCORE; score++) {
                // 각 팀마다, 스코어마다 리스트(헤더) 생성
                Node head = new Node(team, -1);
                head.prev = head;
                head.next = head;
                teamList[team][score] = head;
            }
        }
    }

    public void hire(int mID, int mTeam, int mScore) {
        Node newNode = new Node(mTeam, mID);

        soldiers[mID] = newNode;

        Node list = teamList[mTeam][mScore];

        Node tail = list.prev;
        newNode.prev = tail;
        newNode.next = list;
        tail.next = newNode;
        list.prev = newNode;
    }

    public void fire(int mID) {
        Node node = soldiers[mID];
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
        soldiers[mID] = null;
    }

    public void updateSoldier(int mID, int mScore) {
        Node node = soldiers[mID];
        node.prev.next = node.next;
        node.next.prev = node.prev;

        Node list = teamList[node.team][mScore];
        Node tail = list.prev;

        tail.next = node;
        node.prev = tail;
        node.next = list;
        list.prev = node;
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        if (mChangeScore == 0)
            return;

        if (mChangeScore > 0) { // 점수 올리기
            for (int fromScore = MAX_SCORE - 1; fromScore >= 1; fromScore--) {
                int toScore = Math.min(fromScore + mChangeScore, 5);

                moveList(mTeam, fromScore, toScore);
            }
        } else { // 점수 내리기
            for (int fromScore = 2; fromScore <= MAX_SCORE; fromScore++) {
                int toScore = Math.max(fromScore + mChangeScore, 1);

                moveList(mTeam, fromScore, toScore);
            }
        }
    }

    public int bestSoldier(int mTeam) {
        int maxId = 0;

        for (int score = MAX_SCORE; score >= 1; score--) {
            Node list = teamList[mTeam][score];

            if (list.next.id == -1) // empty list
                continue;

            Node node = list.next;

            while (node.id != -1) {
                if (maxId < node.id)
                    maxId = node.id;

                node = node.next;
            }

            break;
        }

        return maxId;
    }

    private void moveList(int team, int fromScore, int toScore) {
        Node fromList = teamList[team][fromScore];
        Node fromHead = fromList.next;
        Node fromTail = fromList.prev;

        if (fromHead.id == -1) // empty list
            return;

        Node toList = teamList[team][toScore];
        Node toTail = toList.prev;

        // to score list 의 꼬리에 이어 붙이기
        toTail.next = fromHead;
        fromHead.prev = toTail;
        fromTail.next = toList;
        toList.prev = fromTail;

        // from score list clear
        fromList.prev = fromList;
        fromList.next = fromList;
    }
}