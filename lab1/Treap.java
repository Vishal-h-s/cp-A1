import java.util.Random;

class Node {
    int key, priority;
    Node left, right;

    public Node(int key) {
        this.key = key;
        this.priority = new Random().nextInt(); // Assign a random priority
        this.left = null;
        this.right = null;
    }
}

public class Treap {
    private Node root;

    // Rotate right
private Node rotateRight(Node parent) {
    Node child = parent.left;
    Node temp = child.right;

    // Perform rotation
    child.right = parent;
    parent.left = temp;

    return child;
}

// Rotate left
private Node rotateLeft(Node parent) {
    Node child = parent.right;
    Node temp = child.left;

    // Perform rotation
    child.left = parent;
    parent.right = temp;

    return child;
}

    // Insert a key into the Treap
    public Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        // BST insertion
        if (key < node.key) {
            node.left = insert(node.left, key);

            // Heap property violation
            if (node.left.priority > node.priority) {
                node = rotateRight(node);
            }
        } else if (key > node.key) {
            node.right = insert(node.right, key);

            // Heap property violation
            if (node.right.priority > node.priority) {
                node = rotateLeft(node);
            }
        }

        return node;
    }

    // Delete a key from the Treap
    public Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }

        // BST deletion
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // Node to be deleted found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Rotate to maintain heap property
            if (node.left.priority > node.right.priority) {
                node = rotateRight(node);
                node.right = delete(node.right, key);
            } else {
                node = rotateLeft(node);
                node.left = delete(node.left, key);
            }
        }

        return node;
    }

    // Search for a key in the Treap
    public boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.key) {
            return true;
        } else if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // Wrapper for insert
    public void insert(int key) {
        root = insert(root, key);
    }

    // Wrapper for delete
    public void delete(int key) {
        root = delete(root, key);
    }

    // Wrapper for search
    public boolean search(int key) {
        return search(root, key);
    }

    // In-order traversal
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("Key: " + node.key + ", Priority: " + node.priority);
            inOrder(node.right);
        }
    }

    public void printInOrder() {
        inOrder(root);
    }

    public static void main(String[] args) {
        Treap treap = new Treap();

        // Insert keys
        treap.insert(50);
        treap.insert(30);
        treap.insert(20);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);

        // Print in-order traversal
        System.out.println("In-order traversal of the Treap:");
        treap.printInOrder();

        // Search for a key
        System.out.println("\nSearch for key 40: " + treap.search(40));
        System.out.println("Search for key 90: " + treap.search(90));

        // Delete a key
        System.out.println("\nDeleting key 50...");
        treap.delete(50);

        // Print in-order traversal after deletion
        System.out.println("In-order traversal after deletion:");
        treap.printInOrder();
    }
}