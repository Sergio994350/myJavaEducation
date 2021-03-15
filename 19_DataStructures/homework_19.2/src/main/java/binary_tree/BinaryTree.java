package binary_tree;

import java.util.ArrayList;

public class BinaryTree {
    private Node root;
    ArrayList<Node> listNode = new ArrayList<>();

    public void addNode(String data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        Node node = root;
        while (true) {
            if (data.compareTo(node.getData()) < 0) {
                if (node.getLeft() == null) {
                    node.setLeft(new Node(data));
                    return;
                } else {
                    node = node.getLeft();
                }
            } else if (data.compareTo(node.getData()) >= 0) {
                if (node.getRight() == null) {
                    node.setRight(new Node(data));
                    return;
                } else {
                    node = node.getRight();
                }
            } else {
                return;
            }
        }
    }

    public boolean isContains(String data) {
        return false;
    }

    public Node getRoot() {
        return root;
    }

    public ArrayList<Node> searchNodes(Node startNode) {
        if (startNode
                != null) {
            searchNodes(startNode.getLeft());
            listNode.add(startNode);
            searchNodes(startNode.getRight());
        }
        return listNode;
    }
}
