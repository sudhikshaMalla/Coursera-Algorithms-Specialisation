package src.divideAndConquerSortingSearching;

public class BinarySearchTree {

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(30);
        tree.insert(50);
        tree.insert(15);
        tree.insert(20);
        tree.insert(10);
        tree.insert(40);
        tree.insert(60);

        tree.printTree();

    }
}

class Node{
    Node left;
    int val;
    Node right;
    Node(int val){
        this.val=val;
    }
}

class BST {
    Node root;

    public BST() {
        root = null;
    }

    public void insert(int value) {
        if(root == null) {
            root = new Node(value);
            return;
        }

        Node root = this.root;
        while(root != null) {
            if(value < root.val) {
                if(root.left == null) {
                    root.left = new Node(value);
                    break;
                }
                else {
                    root = root.left;
                }
            }
            else {
                if(root.right == null) {
                    root.right = new Node(value);
                    break;
                }
                else {
                    root = root.right;
                }
            }
        }
    }

    public void printTree() {
        inorder(root);
    }

    private void inorder(Node root) {
        if(root!=null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

}