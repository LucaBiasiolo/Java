package esercizi.datastructures;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class BinarySearchTree {

    public int value;
    public int depth;
    public BinarySearchTree left;
    public BinarySearchTree right;

    public BinarySearchTree(int value, int depth) {
        this.value = value;
        this.depth = depth;
        this.left = null;
        this.right = null;
    }

    public BinarySearchTree(int value) {
        this.value = value;
        this.depth = 1;
        this.left = null;
        this.right = null;
    }

    // Define insert() below

    public void insert(int value){
        if (value < this.value){
            if (this.left == null){
                this.left = new BinarySearchTree(value,this.depth+ 1);
                System.out.printf("Tree node %d added to the left of %d at depth %d \n", value, this.value, this.depth + 1);
            } else{
                this.left.insert(value);
            }
        } else{
            if (this.right == null){
                this.right = new BinarySearchTree(value, this.depth+ 1);
                System.out.printf("Tree node %d added to the right of %d at depth %d \n", value, this.value, this.depth + 1);
            } else{
                this.right.insert(value);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree root = new BinarySearchTree(100);
        root.insert(50);
        root.insert(125);
        root.insert(75);
        root.insert(25);
        // Insert values below:
        IntStream intStream = IntStream.of(1,2,3,4,5,6,7,8,9,10);
        intStream.forEach(root::insert);
    }
}