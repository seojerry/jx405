package javabasic.stack;

public class NodeManager {
    Node top; //가장 최근에 들어온 노드를 가리킴

    public NodeManager() {
        this.top = null;
    }
    public void push(int data) {
        Node node = new Node(data);    //노드를 생성
        node.linkNode(top);    //새로 생성된 노드가 top이 가르키고 있는 노드를 맄크로 연결하게 함
        top = node;    //top의 값을 가장 최근에 생성된 node로 바꿈
    }
    public void pop() {
        top = top.getNextNode(); // 현재 top이 가리키고 있는 노드를 가리키게 함
    }
    public int peek() {
        return top.getData();
    }
}
