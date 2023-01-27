package javabasic.stack;

public class Node {
    private int item;
    private Node node;

    public Node(int item) {
        this.item = item;
        this.node = null;
    }

    protected void linkNode(Node node) {//가르킬 노드를 지정
        this.node = node;
    }
    protected int getData() {
        return this.item;
    }
    protected Node getNextNode() { //다음 노드를 리턴
        return this.node;
    }
}
