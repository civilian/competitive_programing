/*en los que son ordenamientos de acceso random que queden
 * solo para arrays[] porque si no su complejidad se dispara
 * para los que son locales como merge o quick se puede hacer con
 * iterator, aunque queda por probar
 * 
 * -Cuando se quiera hacer para tipo variable se hace con tipo de 
 * arreglo Comparable[] y en vez de menor o mayor se usa el compare to
 * con el 0.//asi ya esta*/
package maratonBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Sorting {

	private static void dbg(Object... out) {
		System.out.println(Arrays.deepToString(out));
	}

	public static void main(String[] args) {
		ArrayList<Integer> lista = new ArrayList<Integer>(8000);
		ArrayList<Integer> copy = new ArrayList<Integer>(8000);
		for (int i = 0; i < 3500; i++) {
			lista.add((int) (Math.random() * 1000));
		}

		copy = (ArrayList<Integer>) lista.clone();

		// System.out.println(lista.toString());

		// Collections.sort(lista);
		Integer[] aLista = (Integer[]) lista.toArray(new Integer[lista.size()]);

		// dbg("copy",copy.size() );
		// dbg("lista",lista);
		long ini = System.nanoTime();
		Integer[] abuuble = (Integer[]) copy.toArray(new Integer[copy.size()]);
		long cambios = bubbleSort(abuuble);
		dbg("abuuble", Arrays.toString(abuuble), "cambios=", cambios);
		System.out.println(Arrays.equals(abuuble, aLista));
		System.out.println(System.nanoTime() - ini);

		Integer[] ainsertion = (Integer[]) copy
				.toArray(new Integer[copy.size()]);
		insertionSort(ainsertion);
		dbg("ainsertion", Arrays.toString(ainsertion));
		System.out.println(Arrays.equals(ainsertion, aLista));

		Integer[] aselections = (Integer[]) copy.toArray(new Integer[copy
				.size()]);
		selectionSort(aselections);
		dbg("aselections", Arrays.toString(aselections));
		System.out.println(Arrays.equals(aselections, aLista));

		Integer[] amerge = (Integer[]) copy.toArray(new Integer[copy.size()]);
		mergesortIni(amerge);
		dbg("amerge", Arrays.toString(amerge), "cambios=", swapsMerge);
		System.out.println(Arrays.equals(amerge, aLista));

		/* Prueba para el merge sort contando los swaps */
		Integer[] prueb = new Integer[500];
		int i = 500;
		for (int j = 0; j < prueb.length; j++) {
			prueb[j] = i--;
		}
		System.out.println(Arrays.toString(prueb));
		mergesortIni(prueb);
		dbg("pruebaCambios swap", swapsMerge);// 124750

		Integer[] aquick = (Integer[]) copy.toArray(new Integer[copy.size()]);
		quickSort(aquick, 0, aquick.length - 1);
		dbg("aquick", Arrays.toString(aquick));
		System.out.println(Arrays.equals(aquick, aLista));

		Integer[] aheap = (Integer[]) copy.toArray(new Integer[copy.size()]);
		heapsort(aheap);
		dbg("aheap", Arrays.toString(aheap));
		System.out.println(Arrays.equals(aheap, aLista));

		Integer[] aheapEasy = (Integer[]) copy
				.toArray(new Integer[copy.size()]);
		heapsortEasy(aheapEasy);
		dbg("aheapEasy", Arrays.toString(aheapEasy));
		System.out.println(Arrays.equals(aheapEasy, aLista));

		Integer[] aCounting = (Integer[]) copy
				.toArray(new Integer[copy.size()]);
		countingSort(aCounting);
		dbg("aCounting", Arrays.toString(aCounting));
		System.out.println(Arrays.equals(aCounting, aLista));

		Integer[] aRadix = (Integer[]) copy.toArray(new Integer[copy.size()]);
		radix_sort(aRadix, 4/* el segundo es la cantidad de bits para la mascara */);
		dbg("aRadix", Arrays.toString(aRadix));
		System.out.println(Arrays.equals(aRadix, aLista));

		multiFieldSorting();
	}

	/*
	 * the values "bubble up" to their punto solo hace intercambios de
	 * adjacentes y asi ordena, estos intercambios se pueden contar, (aunque
	 * tambien se pueden contar con mergesort)
	 */
	private static long bubbleSort(Comparable[] A) {/* es opcional que sea int */
		boolean swapped = true;// si no mueve nada en todo el
		// while es porque ya todo esta ordenado
		long swaps = 0;// para contar los swaps, es opcional
		for (int i = A.length; i > 0 && swapped; i--) {
			swapped = false;
			for (int j = 0; j < i - 1; j++) {
				if (A[j].compareTo(A[j + 1]) > 0) // change ">" to < for
				// descending sort
				{
					Comparable temp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = temp;
					swapped = true;
					swaps++;
				}
			}

		}
		return swaps;
		// TODO Auto-generated method stub
	}

	/*
	 * hace que el valor se mueva hasta su punto de "insersion" temporal
	 * moviendo los demas. Nota: hay una implementacion que utiliza binary
	 * search para buscar el lugar a donde insertar
	 */
	static void insertionSort(Comparable[] arr) {
		int i, j;
		Comparable newValue;
		for (i = 1; i < arr.length; i++) {
			newValue = arr[i];
			j = i;
			while (j > 0 && arr[j - 1].compareTo(newValue) > 0) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = newValue;
		}
	}

	/*
	 * Mas eficiente que el clasico pero utiliza el mismo concepto de buscar por
	 * el objeto mas pequenno en el arreglo y luego pasarlo al principio,
	 * "seleccionandolo"
	 */
	public static void selectionSort(Comparable[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = i; // Index of smallest remaining value.
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIndex].compareTo(array[j]) > 0) {
					minIndex = j;// Remember index of new minimum
				}
			}
			if (minIndex != i) {
				// ... Exchange current element with smallest remaining.
				Comparable temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
	}

	/* MERGE SORT */
	/*
	 * Merge sort parte el arreglo en dos hasta que este sea de 1 elemento,
	 * luego los une dejandolos ordenados como un pila de cartas
	 */
	static long swapsMerge;
	static Integer[] valuesMerge;
	static int sizeMerge;

	static Comparable[] mergesortIni(Comparable[] data) {

		swapsMerge = 0;
		valuesMerge = (Integer[]) data;
		sizeMerge = data.length;

		mergesort(0, sizeMerge - 1);
		return valuesMerge;
	}

	static void mergesort(int low, int hi) {
		if (low < hi) {
			int mid = (low + hi) / 2;
			mergesort(low, mid);

			mergesort(mid + 1, hi);

			merge(low, mid, hi);
		}

	}

	static void merge(int low, int mid, int hi) {
		int[] helper = new int[sizeMerge];

		for (int i = low; i <= hi; i++) {
			helper[i] = valuesMerge[i];
		}

		int i = low;
		int j = mid + 1;
		int k = low;

		while (i <= mid && j <= hi) {
			if (helper[i] <= helper[j]) {// con comparable .compareTo <= 0
				valuesMerge[k] = helper[i];
				i++;
			} else {
				swapsMerge += j - k;
				valuesMerge[k] = helper[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			valuesMerge[k] = helper[i];
			k++;
			i++;
		}
		helper = null;
	}

	/* MERGE SORT END */

	/* QUICKSORT */
	/*
	 * Hace el ordenamiento escogiendo un pivote e intercambiando de lado a lado
	 * los menores y mayores que ese y llamandose recursivamente y se queda con
	 * el ultimo i cuando i>j para volver ha hacer la recusion
	 */
	static void quickSort(Comparable arr[], int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}

	static int partition(Comparable arr[], int left, int right) {
		int i = left, j = right;
		Comparable tmp;
		Comparable pivot = arr[(left + right) / 2];

		/* PARA RANDOMICO */
		// int size=right-left;//con estas tres lineas se vuelve
		// //quicksort random para "mejorar" el posible n^2
		// int pos=(int) Math.round(Math.random()*size);
		// pivot = arr[left+pos];

		while (i <= j) {
			while (arr[i].compareTo(pivot) < 0)
				i++;
			while (arr[j].compareTo(pivot) > 0)
				j--;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		;

		return i;
	}

	/* QUICKSORT END */

	/*
	 * HEAPSORT***************************************************** Basicamente
	 * llena un heap y luego devuelve la cabeza, que siempre por propiedades de
	 * el heap es el menor de el arreglo
	 */

	// Complicado, pero visible todo

	/**
	 * Standard heapsort.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 */
	public static void heapsort(Comparable[] a) {
		for (int i = a.length / 2; i >= 0; i--)
			/* buildHeap */
			percDown(a, i, a.length);
		for (int i = a.length - 1; i > 0; i--) {
			swapReferences(a, 0, i); /* deleteMax */
			percDown(a, 0, i);
		}
	}

	/**
	 * Internal method for heapsort.
	 * 
	 * @param i
	 *            the index of an item in the heap.
	 * @return the index of the left child.
	 */
	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	/**
	 * Internal method for heapsort that is used in deleteMax and buildHeap.
	 * 
	 * @param a
	 *            an array of Comparable items.
	 * @index i the position from which to percolate down.
	 * @int n the logical size of the binary heap.
	 */
	private static void percDown(Comparable[] a, int i, int n) {
		int child;
		Comparable tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
				child++;
			if (tmp.compareTo(a[child]) < 0)
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}

	/**
	 * Method to swap to elements in an array.
	 * 
	 * @param a
	 *            an array of objects.
	 * @param index1
	 *            the index of the first object.
	 * @param index2
	 *            the index of the second object.
	 */
	public static final void swapReferences(Object[] a, int index1, int index2) {
		Object tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	/* HEAPSORT END**************************************************** */
	/* HEAPSORT FACIL */
	static void heapsortEasy(Comparable[] array) {
		PriorityQueue heap = new PriorityQueue();
		for (Comparable e : array) {
			heap.add(e);
		}
		int i = 0;
		while (!heap.isEmpty()) {
			array[i] = (Comparable) heap.poll();
			i++;
		}
	}

	/* HEAPSORT FACIL END */

	/* COUNTING SORT */
	/*
	 * Se usa cuando no se sabe cual es el mayor y cual es el menor y puede
	 * hacer la ordenada un poco mas eficiente recortando el intervalo
	 */

	static void countingSort(Integer[] a) {
		Integer max = Integer.MIN_VALUE;
		Integer min = Integer.MAX_VALUE;
		for (Integer e : a) {
			if (e > max) {
				max = e;
			} else if (e < min) {
				min = e;
			}
		}
		countingSort(a, min, max);
	}

	public static void countingSort(Integer[] a, int low, int high) {
		int[] counts = new int[high - low + 1];
		// this will hold all possible values, from low to high
		for (int x : a)
			counts[x - low]++;
		// - low so the lowest possible value is always 0

		int current = 0;
		/*
		 * Este for se repite hasta que se alcanze el high sin importar que haya
		 * en la lista por lo que no es bueno para numeros grandes
		 */
		for (int i = 0; i < counts.length; i++) {
			Arrays.fill(a, current, current + counts[i], i + low);
			// fills counts[i] elements of value i + low in current
			current += counts[i]; // leap forward by counts[i] steps
		}
	}

	/* RADIX SORT */
	/*
	 * 2-bit radix {6, 9, 1, 4, 15, 12, 5, 6, 7, 11} {4, 12} {9, 1, 5} {6, 6}
	 * {15, 7, 11} //mismos 2 bits finales {1} {4, 5, 6, 6, 7} {9, 11} {12, 15}
	 */// mismos 2 bits n-2 y n-1.
		// uno le puede mandar lo que sea de bits pero utiliza 2^bits de
		// memoria.
		// takes an array and the number of bits used as the key in each
		// iteration.
	public static void radix_sort(Integer[] a, int bits) {

		Integer[] b = new Integer[a.length];
		Integer[] b_orig = b;

		int rshift = 0;

		for (int mask = ~(-1 << bits); mask != 0; mask <<= bits, rshift += bits) {
			// to store the result of each iteration.
			int[] cntarray = new int[1 << bits];
			// We count each key value.
			for (int p = 0; p < a.length; ++p) {
				int key = (a[p] & mask) >> rshift;
				++cntarray[key];
			}

			// we sum up how many elements there are with lower key values, for
			// each key.
			for (int i = 1; i < cntarray.length; ++i)
				cntarray[i] += cntarray[i - 1];

			// values in cntarray are used as indexes for storing the values in
			// b. b will
			// then be completely sorted on this iteration's key. Elements with
			// the same
			// key value are stored in their original internal order
			for (int p = a.length - 1; p >= 0; --p) {// por el for alrevez
				int key = (a[p] & mask) >> rshift;
				--cntarray[key];
				b[cntarray[key]] = a[p];
			}

			Integer[] temp = b;
			b = a;
			a = temp;
		}

		// to the output
		if (a == b_orig)
			System.arraycopy(a, 0, b, 0, a.length);
	}

	/* RADIX SORT END */

	static void multiFieldSorting() {
		cumpleannos[] prueba = new cumpleannos[3];
		prueba[0] = new cumpleannos(26, 12, 1989, "oscar");
		prueba[1] = new cumpleannos(26, 12, 1989, "copy");
		prueba[2] = new cumpleannos(24, 9, 1960, "c3po");
		Comparator<cumpleannos> compara = new Comparator<cumpleannos>() {
			@Override
			public int compare(cumpleannos o1, cumpleannos o2) {
				if (o1.mes != o2.mes)
					return Integer.signum(o2.mes - o1.mes);
				// corregido ahora o2-o1 de menor a mayor
				else if (o1.anno != o2.anno)
					return Integer.signum(o2.anno - o1.anno);
				else
					return o1.nombre.compareTo(o2.nombre);
			}
		};

		Arrays.sort(prueba, compara);
		// [cumple[dia=24, mes=9, anno=1960, nombre=c3po], cumple[dia=26, mes=12,
		// anno=1989, nombre=copy], cumple[dia=26, mes=12, anno=1989,
		// nombre=oscar]]
		dbg(prueba);

	}

	static class cumpleannos {
		@Override
		public String toString() {
			return "cumple[dia=" + dia + ", mes=" + mes + ", anno=" + anno
					+ ", nombre=" + nombre + "]";
		}

		public cumpleannos(int dia, int mes, int anno, String nombre) {
			this.dia = dia;
			this.mes = mes;
			this.anno = anno;
			this.nombre = nombre;
		}

		int dia, mes, anno;
		String nombre;
	}
}
