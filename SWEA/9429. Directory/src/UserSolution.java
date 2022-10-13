class UserSolution {

    private final static int NAME_MAXLEN   = 6;
    private final static int PATH_MAXLEN   = 1999;    

    class Node{
        char[] name;
        int count;
        Node parent;
        Node[] children;
        Node(char[] name){
            this.name = name;
        }
    }

    Node[] node_pool;
    Node root;
    int nodeCnt;

    //    The below commented methods are for your reference. If you want
//    to use it, uncomment these methods.
//   
    int mstrcmp(char[] a, char[] b) {
        int i;
        for (i = 0; a[i] != '\0'; i++) {
            if (a[i] != b[i])
                return a[i] - b[i];
        }
        return a[i] - b[i];
    }

    int mstrncmp(char[] a, char[] b, int len) {
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i])
                return a[i] - b[i];
        }
        return 0;
    }

    int mstrlen(char[] a) {
        int len = 0;

        while (a[len] != '\0')
            len++;

        return len;
    }

    void mstrcpy(char[] dest, char[] src) {
        int i = 0;
        while (src[i] != '\0') {
            dest[i] = src[i];
            i++;
        }
        dest[i] = src[i];
    }

    void mstrncpy(char[] dest, char[] src, int len) {
        for (int i = 0; i < len; i++) {
            dest[i] = src[i];
        }
        dest[len] = '\0';
    }


    void init(int n) {
        root = new Node(new char[] {'/'});
        node_pool = new Node[n];
        nodeCnt = 0;
    }

    void cmd_mkdir(char[] path, char[] name) {
        int i=1;
        Node parent = root;
        while(i<path.length){
            int j=0;
            char[] tmp = new char[NAME_MAXLEN];
            while(path[i]!='/'){
                tmp[j++] = path[i++];
            }
            // / /aa/ /aa/cc/
            for(int k=0;k<parent.children.length;k++){
                
            }

        }
    }

    void cmd_rm(char[] path) {

    }

    void cmd_cp(char[] srcPath, char[] dstPath) {

    }

    void cmd_mv(char[] srcPath, char[] dstPath) {

    }

    int cmd_find(char[] path) {

        return 0;
    }
}