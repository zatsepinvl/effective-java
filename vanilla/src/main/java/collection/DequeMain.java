package collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeMain {
    public static void main(String[] args) {
        Deque<Integer> queue = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 5; i++) {
            queue.add(i);
            stack.push(i);
        }

        System.out.println("Queue: " + queue);
        System.out.println("Stack: " + stack);
        System.out.println();

        System.out.print("Queue: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.pop() + ", ");
        }
        System.out.println();


        System.out.print("Stack: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.poll() + ", ");
        }
    }
}
