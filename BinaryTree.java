import java.util.Random;

public class BinaryTree {

    public class Node {
        public String id;
        public Node left;
        public Node right;

        public Node() {
            id = generateId(25);
            left = right = null;
        }
    }

    private Node root;
    private long elapsed;
    private long count;
    private Random rnd = new Random(System.currentTimeMillis());
    private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public void insertNodes(int nodes) {
        System.out.printf("Start Building Tree with %d Nodes\n", nodes);
        startTimer();
        for (int i=0; i < nodes; i++) {
            Node n = new Node();
            root = insert(root, n);
        }
        long duration = stopTimer();
        System.out.printf("Done Building Tree in %f seconds.\n", duration/1000.0);  
    }

    private Node insert(Node root, Node n) {
        if ( root == null ) {
            root = n;
            return root;
        } else {
            if (n.id.compareTo(root.id) > 0) {
                root.right = insert(root.right, n);
            } else {
                root.left = insert(root.left, n);
            }
            return root;
        }
    }

    public void walk(boolean show) {
        count = 0;
        System.out.println("Start Walking Tree");
        startTimer();
        visit(root, show);
        long duration = stopTimer();
        System.out.printf("Done Walking Tree, saw %d Nodes in %f seconds.\n", count, duration/1000.0);        
    }
    
    private void visit(Node n, boolean show) {
        if ( n != null ) {
            visit(n.left, show);
            count++;
            if (show) {
                System.out.println(n.id);
            }
            visit(n.right, show);
        }
    }

    private String generateId(int length) {
        String result = new String();
        for ( int i=0; i < length; i++ ) {
            result += chars.charAt(rnd.nextInt(chars.length()));
        }
        return result;
    }

    public void startTimer() { elapsed = System.currentTimeMillis(); }
    public long stopTimer() { return System.currentTimeMillis() - elapsed; }

    public static void  main(String[] args) {
        int nodes = 1000000;
        boolean showNames = false;
        if ( args.length > 0 ) {
            nodes = Integer.parseInt(args[0]);  // Number of Nodes
        }
        if ( args.length > 1 ) {                // Whether to show Node names while walking
            showNames = true;
        }
        BinaryTree bt = new BinaryTree();
        bt.insertNodes(nodes);
        bt.walk(showNames);
    }

}