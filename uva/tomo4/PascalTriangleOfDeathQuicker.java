package uva.tomo4;

//Uva 485
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class PascalTriangleOfDeathQuicker {

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) {
		ArrayList<BigInteger> fila = new ArrayList<BigInteger>(1000);
		ArrayList<BigInteger> filaAux = new ArrayList<BigInteger>(1000);
		filaAux.add(0, BigInteger.ONE);
		fila.add(0, BigInteger.ONE);
		int tamannoFila = 0;
		BigInteger masGrande = BigInteger.TEN;
		masGrande = masGrande.pow(60);
		boolean kill = false;
		// StringBuilder salida = new StringBuilder();
		BigInteger tmp;

		while (true) {
			tamannoFila++;
			int ultimo = tamannoFila - 1;
			for (int i = 0; i < tamannoFila; i++) {
				if (i == 0) {
					output.append(fila.get((int) i).toString());
					continue;
				} else if (i == ultimo) {
					fila.add((int) i, BigInteger.ONE);
					filaAux.add((int) i, BigInteger.ONE);
				} else {
					tmp = filaAux.get(i).add(filaAux.get(i - 1));
					fila.set(i, tmp);
					if (tmp.compareTo(masGrande) > 0) {
						kill = true;
					}
				}
				output.append(" " + fila.get((int) i).toString());
			}

			// System.out.println(fila.toString());
			// salida.append("\n");
			output.println();
			// salida = new StringBuilder();
			if (kill) {// System.out.print(salida);
				break;
			}
			filaAux = (ArrayList<BigInteger>) fila.clone();
			// System.out.println(filaAux.toString());
		}
		output.close();
	}

}
