package CHelper.src.algo;

public class segmentTree {
    /* Tree */
    private static int cur;
    private static int[] arr;
    private static int size;

    public static void treeArrayInt(int length) {
        size = length;
        arr = new int[length];
        cur = -1;
    }

    public static int getLeft(int parent) {
        return arr[parent * 2 + 1];
    }

    public static int getRight(int parent) {
        return arr[parent * 2 + 2];
    }

    public static int get(int node) {
        return arr[node];
    }

    public static void set(int node, int Value) {
        arr[node] = Value;
    }

    public static int parent(int node){
        if(node%2==0)
            return (node-2)/2;
        return (node-1)/2;
    }

    /*SEG*/
    public segmentTree(int length){
        size = 2*math.nextPowerOf2(length)-1;
        treeArrayInt(size);
    }

    public static void print(){
        int j=0;
        for(int i=0; i<size; i++){
            System.out.print(arr[i]+" ");
            if(i==j) {
                System.out.println();
                j = 2*(j+1);
            }
        }
    }

    
    /* SUM TREE */
    public static void sumTree(int[] arr){
        // Set Last Level of Tree
        int j=0;
        for(int i=size/2; i<size/2+arr.length-1; i++) {
            set(i, arr[j]);
            j++;
        }

        //Construct Tree
        int start = size/4;
        while(start>=1) {
            for (int i = start; i <= 2*start; i++) {
                set(i, getLeft(i)+getRight(i));
            }
            start/=2;
        }
        set(0, getLeft(0)+getRight(0));
    }

    public static void sumTreeUpdate(int index, int value){
        index = (size/2)+index;
        if(get(index)!=value) {
            set(index, value);
            int parent = parent(index);

            while (parent > 0) {
                set(parent, getLeft(parent) + getRight(parent));
                parent = parent(parent);
            }
            set(0, getLeft(0) + getRight(0));
        }
    }

    private static int sumTreeGetUtil(int ss, int se, int qs, int qe, int si)
    {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se)
            return get(si);

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return sumTreeGetUtil(ss, mid, qs, qe, 2 * si + 1) + sumTreeGetUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    public static int sumTreeGet(int StartIndex, int EndIndex){
        return sumTreeGetUtil(0, size/2, StartIndex, EndIndex, 0);
    }

    /* MAX TREE */
    public static void maxTree(int[] arr){
        // Set Last Level of Tree
        int j=0;
        for(int i=size/2; i<size/2+arr.length-1; i++) {
            set(i, arr[j]);
            j++;
        }

        //Construct Tree
        int start = size/4;
        while(start>=1) {
            for (int i = start; i <= 2*start; i++) {
                set(i, Math.max(getLeft(i), getRight(i)));
            }
            start/=2;
        }
        set(0, Math.max(getLeft(0), getRight(0)));
    }

    public static void maxTreeUpdate(int index, int value){
        index = (size/2)+index;
        if(get(index)!=value) {
            set(index, value);
            int parent = parent(index);

            while (parent > 0) {
                set(parent, Math.max(getLeft(parent), getRight(parent)));
                parent = parent(parent);
            }
            set(0, Math.max(getLeft(0), getRight(0)));
        }
    }

    private static int maxTreeGetUtil(int ss, int se, int qs, int qe, int si)
    {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se)
            return get(si);

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return Math.max(maxTreeGetUtil(ss, mid, qs, qe, 2 * si + 1), maxTreeGetUtil(mid + 1, se, qs, qe, 2 * si + 2));
    }

    public static int maxTreeGet(int StartIndex, int EndIndex){
        return sumTreeGetUtil(0, size/2, StartIndex, EndIndex, 0);
    }
}
