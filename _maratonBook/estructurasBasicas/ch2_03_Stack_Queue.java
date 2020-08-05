package maratonBook.estructurasBasicas;

import java.util.*;

class ch2_03_Stack_Queue {

	public static void main(String[] args) {
		Stack<Character> s = new Stack<Character>();

		System.out.println(s.isEmpty()); // currently s is empty, true
		System.out.println("==================");
		s.push('a');
		s.push('b');
		s.push('c');
		// stack is LIFO, thus the content of s is currently like this:
		// c <- top
		// b
		// a
		System.out.println(s.peek()); // output 'c'
		s.pop(); // pop topmost
		System.out.println(s.peek()); // output 'b'
		System.out.println(s.empty()); // currently s is not empty, false
		System.out.println("==================");

		// PARA QUEUE OPTIMIZADA
		Stack<Character> s1 = (Stack<Character>) s.clone();

		// QUEUE is abstract, must be instantiated with LinkedList
		// (special case for Java Queue)
		Queue<Character> q = new LinkedList<Character>();
		System.out.println(q.isEmpty()); // currently q is empty, true
		System.out.println("==================");
		while (!s.isEmpty()) { // stack s still has 2 more items
			q.offer(s.peek()); // enqueue 'b', and then 'a' (the method name in
								// Java Queue for push/enqueue operation is
								// 'offer')
			s.pop();
		}

		System.out.println(q.peek()); // prints 'b'
		// in Java, it is harder to see the back of the queue...

		// QUEUE OPTIMIZADA Uva 11624
		head = tail = 0;
		System.out.println(!(head < tail)); // currently qf is empty
		System.out.println("QUEUE OPTIMIZADA");
		while (!s1.isEmpty()) { // stack s still has 2 more items
			qf[tail++] = s1.peek(); // enqueue 'b', and then 'a'
			s1.pop();
		}
		System.out.println(!(head < tail)); //qf no esta vacia
		System.out.println(qf[head]); // prints 'b' peak
		System.out.println(qf[head++]); // prints 'b' pop
		System.out.println(qf[head++]); // prints 'a' pop

	}

	static int head, tail;
	static int MAX_STATES = 1000;
	// static State[] qf = new State[MAX_STATES];
	static Character[] qf = new Character[MAX_STATES];// Debe ser iniciliazada
														// con la cantidad
														// maxima de estados

	static class State {
		int a; /* y lo que sea demas */
	}
}
