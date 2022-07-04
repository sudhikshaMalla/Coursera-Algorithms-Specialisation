package src.divideAndConquerSortingSearching;

public class RedBlackTree {

    private static Node tree = null;
    private static boolean leftLeftRotation = false;
    private static boolean rightRightRotation = false;
    private static boolean leftRightRotation = false;
    private static boolean rightLeftRotation = false;

    public static void insert(int data)
    {
        if(tree ==null)
        {
            tree = new Node(data);
            tree.colour = 'B';
        }
        else
            tree = insertHelp(tree,data);
    }

    private static Node insertHelp(Node root, int data)
    {
        boolean redRedConflict = false;

        if(root==null)
            return(new Node(data));
        else if(data<root.data)
        {
            root.left = insertHelp(root.left, data);
            root.left.parent = root;
            if(root!=tree)
            {
                if(root.colour=='R' && root.left.colour=='R')
                    redRedConflict = true;
            }
        }
        else
        {
            root.right = insertHelp(root.right,data);
            root.right.parent = root;
            if(root!=tree)
            {
                if(root.colour=='R' && root.right.colour=='R')
                    redRedConflict = true;
            }
        }

        root = rotateNode(root);

        if(redRedConflict)
            handleRedRedConflict(root);

        return root;
    }

    private static void handleRedRedConflict(Node root) {
        if(root.parent.right == root)
        {
            if(root.parent.left==null || root.parent.left.colour=='B')
            {
                if(root.left!=null && root.left.colour=='R')
                    rightLeftRotation = true;
                else if(root.right!=null && root.right.colour=='R')
                    leftLeftRotation = true;
            }
            else
            {
                root.parent.left.colour = 'B';
                root.colour = 'B';
                colorParentRed(root);
            }
        }
        else
        {
            if(root.parent.right==null || root.parent.right.colour=='B')
            {
                if(root.left!=null && root.left.colour=='R')
                    rightRightRotation = true;
                else if(root.right!=null && root.right.colour=='R')
                    leftRightRotation = true;
            }
            else
            {
                root.parent.right.colour = 'B';
                root.colour = 'B';
                colorParentRed(root);
            }
        }
    }

    private static void colorParentRed(Node root) {
        if(root.parent!=tree)
            root.parent.colour = 'R';
    }

    private static Node rotateNode(Node root) {
        if(leftLeftRotation)
        {
            root = rotateLeft(root);
            root.colour = 'B';
            root.left.colour = 'R';
            leftLeftRotation = false;
        }
        else if(rightRightRotation)
        {
            root = rotateRight(root);
            root.colour = 'B';
            root.right.colour = 'R';
            rightRightRotation = false;
        }
        else if(rightLeftRotation)
        {
            root.right = rotateRight(root.right);
            root.right.parent = root;
            root = rotateLeft(root);
            root.colour = 'B';
            root.left.colour = 'R';

            rightLeftRotation = false;
        }
        else if(leftRightRotation)
        {
            root.left = rotateLeft(root.left);
            root.left.parent = root;
            root = rotateRight(root);
            root.colour = 'B';
            root.right.colour = 'R';
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