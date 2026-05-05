error id: file:///C:/Users/malte/Documents/GitHub/Projekt-UAP/BinarySearchTree/BST.java:java/util/Comparator#
file:///C:/Users/malte/Documents/GitHub/Projekt-UAP/BinarySearchTree/BST.java
empty definition using pc, found symbol in pc: java/util/Comparator#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 274
uri: file:///C:/Users/malte/Documents/GitHub/Projekt-UAP/BinarySearchTree/BST.java
text:
```scala
import java.util.Comparator;

public class BinarySearchTree {
    BinaryNode root;
    int size;
    private Comparator<Integer> comparator;

    public BinarySearchTree() {
        comparator = (e1, e2) -> e1.compareTo(e2);
    }

    public BinarySearchTree(Com@@parator<Integer> comparator) {
        this.comparator = comparator;
    }

    public boolean add(Integer e) {
        int oldSize = size;
        root = add(root, e);
        return size > oldSize;
    }

    private BinaryNode add(BinaryNode node, Integer e) {
        if (node == null) {
            size++;
            return new BinaryNode(e);
        }
        int cmp = comparator.compare(e, node.element);
        if (cmp < 0)
            node.left = add(node.left, e);
        else if (cmp > 0)
            node.right = add(node.right, e);
        else
            return node;

        updateHeight(node);
        return balance(node);
    }

    public boolean contains(Integer e) {
        return contains(root, e);
    }

    private boolean contains(BinaryNode node, Integer e) {
        if (node == null) return false;
        int cmp = comparator.compare(e, node.element);
        if (cmp < 0)
            return contains(node.left, e);
        else if (cmp > 0)
            return contains(node.right, e);
        else
            return true;
    }

    private int nodeHeight(BinaryNode n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(BinaryNode n) {
        n.height = 1 + Math.max(nodeHeight(n.left), nodeHeight(n.right));
    }

    private int balanceFactor(BinaryNode n) {
        return nodeHeight(n.left) - nodeHeight(n.right);
    }

    private BinaryNode balance(BinaryNode n) {
        int bf = balanceFactor(n);
        if (bf > 1) {
            if (balanceFactor(n.left) < 0)
                n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (bf < -1) {
            if (balanceFactor(n.right) > 0)
                n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    private BinaryNode rotateRight(BinaryNode y) {
        BinaryNode x = y.left;
        y.left = x.right;
        x.right = y;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private BinaryNode rotateLeft(BinaryNode x) {
        BinaryNode y = x.right;
        x.right = y.left;
        y.left = x;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    public int height() { return nodeHeight(root); }

    public int size() { return size; }

    public void clear() { root = null; size = 0; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        return sb.toString();
    }

    private void inorder(BinaryNode node, StringBuilder sb) {
        if (node == null) return;
        inorder(node.left, sb);
        sb.append(node.element).append("\n");
        inorder(node.right, sb);
    }

    static class BinaryNode {
        Integer element;
        BinaryNode left;
        BinaryNode right;
        int height = 1;

        private BinaryNode(Integer element) {
            this.element = element;
        }
    }
}


```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/Comparator#