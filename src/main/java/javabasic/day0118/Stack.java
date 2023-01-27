package javabasic.day0118;

import javabasic.model.UserDTO;

import java.util.ArrayList;

public class Stack {
    ArrayList<Integer> stack = new ArrayList<>();

    public void push(int data) {
        stack.add(data);
    }

    public void pop() {
        if (stack.size()>0){
            System.out.println(stack.get(stack.size() - 1));
            stack.remove(stack.size() - 1);
        }else {
            System.out.println("입력된 값이 없습니다.");
        }
    }

    public int peek() {
        System.out.println(stack.get(stack.size()-1));
        return stack.get(stack.size()-1);
    }

    public static void main(String[] args) {
        Stack stackManager = new Stack();
        stackManager.push(1);
        stackManager.push(2);
        stackManager.push(3);
        stackManager.push(4);
        stackManager.push(5);
        for (int item : stackManager.stack) {
            System.out.println(item);
        }
        stackManager.pop();
        int popItem2 = stackManager.peek();
    }
}
