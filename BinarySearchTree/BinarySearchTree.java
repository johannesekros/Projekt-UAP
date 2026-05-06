import java.util.Comparator;

public class BinarySearchTree {
    BinaryNode root;
    int size;
    private Comparator<Integer> comparator;

    //Vet ej varför vi har två konstruktorer här men det var så i pfk labben jaja
    public BinarySearchTree() {
        comparator = (e1, e2) -> e1.compareTo(e2);
    }
    public BinarySearchTree(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    //INSERT, lägger till ett element i listan!!!
    public boolean add(Integer e) {
        int oldSize = size;
        root = add(root, e); //anropa hjälpmetod
        return size > oldSize; //checka så den blivit större
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

    //SÖKNING, kollar om ett element finns i listan!!
    public boolean contains(Integer e) {
        return contains(root, e); //anropa hjälpismetod
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

    //REMOVE, tar bort ett element om det finns i listan!!
    public boolean remove(Integer e) {
        int oldSize = size;
        root = remove(root, e); //anroooop av hjälpppppmetod
        return size < oldSize; //checka så storleken minskat annars gick det inte
    }
    private BinaryNode remove(BinaryNode node, Integer e) {
        if (node == null) return null;

        int cmp = comparator.compare(e, node.element);
        if (cmp < 0)
            node.left = remove(node.left, e);
        else if (cmp > 0)
            node.right = remove(node.right, e);
        else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            BinaryNode smallest = findMin(node.right);
            node.element = smallest.element;
            size++;
            node.right = remove(node.right, smallest.element);
        }
        updateHeight(node);
        return balance(node);
    }
    //Behövs för borttagningen
    private BinaryNode findMin(BinaryNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    //Höjdrelaterade metoder
    private int nodeHeight(BinaryNode n) {
        return n == null ? 0 : n.height;
    }
    private void updateHeight(BinaryNode n) {
        n.height = 1 + Math.max(nodeHeight(n.left), nodeHeight(n.right));
    }
    private int balanceFactor(BinaryNode n) {
        return nodeHeight(n.left) - nodeHeight(n.right);
    }

    //Privata metoder för balansering av träd
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

    //Dessa tre talar för sig själva
    public int height() { 
        return nodeHeight(root); 
    }
    public int size() { 
        return size; 
    }
    public void clear() { 
        root = null; size = 0; 
    }

    //toString med inorder som ser till att listan skrivs ut enligt sorteringen
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();//Stringbuilder hahaha! Klassiker!
        inorder(root, sb);
        return sb.toString();
    }
    private void inorder(BinaryNode node, StringBuilder sb) {
        if (node == null) return;
        inorder(node.left, sb);
        sb.append(node.element).append("\n");
        inorder(node.right, sb);
    }

    //Nodekrafs
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