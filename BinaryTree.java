import java.util.Random;

public class BinaryTree {

    private class Node {
        public String id;
        public Node left;
        public Node right;
    }

    private Node root;
    private long elapsed;
    private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public void create(int levels) {
        System.out.printf("Start Building Tree with %d levels\n", levels);
        startTimer();
        root = buildTree(levels);
        long duration = stopTimer();
        System.out.printf("Done Building Tree in %f seconds.\n", duration/1000.0);
    }

    public void walk() {
        System.out.println("Start Walking Tree");
        startTimer();
        visit(root);
        long duration = stopTimer();
        System.out.printf("Done Walking Tree in %f seconds.\n", duration/1000.0);        
    }
    
    private Node buildTree(int level) {
        if ( level == 0 ) {
            return null;
        } else {
            Node n = new Node();
            n.id = generateId(25);
            n.left = buildTree(level-1);
            n.right = buildTree(level-1);
            return n;
        }
    }

    private void visit(Node n) {
        if ( n != null ) {
            n.id.toLowerCase();
            visit(n.left);
            visit(n.right);
        }
    }

    private String generateId(int length) {
        String result = new String();
        Random rnd = new Random(System.currentTimeMillis());
        for ( int i=0; i < length; i++ ) {
            result += chars.charAt(rnd.nextInt(chars.length()));
        }
        return result;
    }

    private void startTimer() { elapsed = System.currentTimeMillis(); }
    private long stopTimer() { return System.currentTimeMillis() - elapsed; }

    public static void  main(String[] args) {
        int levels = 24;
        System.out.println(args.length);
        if ( args.length > 0 ) {
            levels = Integer.parseInt(args[0]);
        }
        BinaryTree bt = new BinaryTree();
        bt.create(levels);
        bt.walk();
    }

}