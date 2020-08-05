package maratonBook.matematica;

import java.util.HashMap;

public class FibonacciNoNecesario {
	
	static HashMap<Long, Long> fibo = new HashMap<Long, Long>();
	static {
		fibo.put(0L, 0L);
		fibo.put(1L, 1L);
	}

	static Long fibRecursivo(Long n) {
		Long salida = fibo.get(n);
		if (salida == null) {
			salida = fibRecursivo(n - 1) + fibRecursivo(n - 2);
			fibo.put(n, salida);
		}
		return fibo.get(n);
	}

}
