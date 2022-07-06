package src.divideAndConquerSortingSearching;

public class RedBlackTree {

    public static final char BLACK = 'B';
    public static final char RED = 'R';
    private static Node tree = null;
    private static boolean leftLeftRotation = false;
    private static boolean rightRightRotation = false;
    private static boolean leftRightRotation = false;
    private static boolean rightLeftRotation = false;

    public static void insert(int data)
    {
        if(tree == null)
        {
            tree = new Node(data);
            tree.colour = BLACK;
        }
        else
            tree = insertNonRoot(tree,data);
    }

    private static Node insertNonRoot(Node root, int data)
    {
        boolean redRedConflict = false;

        if(root==null)
            return(new Node(data));
        else if(data<root.data)
        {
            root.left = insertNonRoot(root.left, data);
            root.left.parent = root;
            if(root!=tree)
            {
                if(root.colour== RED && root.left.colour== RED)
                    redRedConflict = true;
            }
        }
        else
        {
            root.right = insertNonRoot(root.right,data);
            root.right.parent = root;
            if(root!=tree)
            {
                if(root.colour== RED && root.right.colour== RED)
                    redRedConflict = true;
            }
        }

        root = rotateNode(root);

        if(redRedConflict)
            handleConflict(root);

        return root;
    }

    private static void handleConflict(Node root) {
        if(root.parent.right == root)
        {
            if(root.parent.left==null || root.parent.left.colour== BLACK)
            {
                if(root.left!=null && root.left.colour== RED)
                    rightLeftRotation = true;
                else if(root.right!=null && root.right.colour== RED)
                    leftLeftRotation = true;
            }
            else
            {
                root.parent.left.colour = BLACK;
                root.colour = BLACK;
                colorParentRed(root);
            }
        }
        else
        {
            if(root.parent.right==null || root.parent.right.colour== BLACK)
            {
                if(root.left!=null && root.left.colour== RED)
                    rightRightRotation = true;
                else if(root.right!=null && root.right.colour== RED)
                    leftRightRotation = true;
            }
            else
            {
                root.parent.right.colour = BLACK;
                root.colour = BLACK;
                colorParentRed(root);
            }
        }
    }

    private static void colorParentRed(Node root) {
        if(root.parent!=tree)
            root.parent.colour = RED;
    }

    private static Node rotateNode(Node root) {
        if(leftLeftRotation)
        {
            root = rotateLeft(root);
            root.colour = BLACK;
            root.left.colour = RED;
            leftLeftRotation = false;
        }
        else if(rightRightRotation)
        {
            root = rotateRight(root);
            root.colour = BLACK;
            root.right.colour = RED;
            rightRightRotation = false;
        }
        else if(rightLeftRotation)
        {
            root.right = rotateRight(root.right);
            root.right.parent = root;
            root = rotateLeft(root);
            root.colour = BLACK;
            root.left.colour = RED;

            rightLeftRotation = false;
        }
        else if(leftRightRotation)
        {
            root.left = rotateLeft(root.left);
            root.left.parent = root;
            root = rotateRight(root);
            root.colour = BLACK;
            root.right.colour = RED;
            leftRightRotation = false;
        }
        return root;
    }

    private static Node rotateLeft(Node node)
    {
        Node x = node.right;
        Node y = x.left;
        x.left = node;
        node.right = y;
        node.parent = x;
        if(y!=null)
            y.parent = node;
        return x;
    }

    private static Node rotateRight(Node node)
    {
        Node x = node.left;
        Node y = x.right;
        x.right = node;
        node.left = y;
        node.parent = x;
        if(y!=null)
            y.parent = node;
        return x;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,3,5,7,8,2,9};
        for(int i=0;i<9;i++)
        {
            insert(arr[i]);
            tree.printTree();
        }
    }
}

class Node
{
    int data;
    Node left;
    Node right;
    char colour;
    Node parent;

    Node(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
        this.colour = 'R';
        this.parent = null;
    }

    public void printTree() {
        inOrder(this);
        System.out.println();
    }

    private void inOrder(Node root) {
        if(root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }
}