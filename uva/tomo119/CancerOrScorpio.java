package uva.tomo119;

/*Uva 11947*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CancerOrScorpio {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
//		 input = new BufferedReader(new FileReader("CancerOrScorpio"));
		readln();

		int numCases = nextInt();
		GregorianCalendar prueba = new GregorianCalendar(0, 0, 0);
		GregorianCalendar date = new GregorianCalendar();
		SimpleDateFormat f=new SimpleDateFormat("MMddyyyy");
		
		for (int casesId = 0; casesId<numCases; casesId++) {
			readln();
//			prueba.add(Calendar.DATE, 1);
			
//			String l = f.format(prueba.getTime());
//			String l = "01160001";
//			System.out.println(l);
			String l=next();
			int mes = Integer.parseInt(l.substring(0, 2));
			int dia = Integer.parseInt(l.substring(2, 4));
			int anho = Integer.parseInt(l.substring(4, l.length()));

			date.set(anho, mes - 1, dia,12,30);

			date.add(GregorianCalendar.WEEK_OF_YEAR, 40);

			// dbg("anho " ,anho, "mes ", mes, " dia ", dia);
			// dbg( "anho " ,date.get(date.YEAR), "mes ", date.get(date.MONTH),
			// " dia ", date.get(date.DAY_OF_MONTH));

			SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");

			Date fecha = date.getTime();
			// dbg(formato.format(fecha));
			String salida=String.format("%d %s %s\n", casesId + 1, formato.format(fecha),
					signoFecha(date));
			System.out.print(salida);
//			String compara=Main.main(casesId+1, l);
			
//			if(!compara.equals(salida)){
//				dbg(compara," salida ", salida);
//				break;
//			}
		}

	}

	static String[] signos = { "aquarius", "pisces", "aries", "taurus",
			"gemini", "cancer", "leo", "virgo", "libra", "scorpio",
			"sagittarius", "capricorn", "capricorn" };

	static Fecha[] fechas = new Fecha[13];
	static {
		fechas[12] = new Fecha(1, 0, 20, 0);// machete??
		fechas[0] = new Fecha(21, 0, 19, 1);
		fechas[1] = new Fecha(20, 1, 20, 2);
		fechas[2] = new Fecha(21, 2, 20, 3);
		fechas[3] = new Fecha(21, 3, 21, 4);
		fechas[4] = new Fecha(22, 4, 21, 5);
		fechas[5] = new Fecha(22, 5, 22, 6);
		fechas[6] = new Fecha(23, 6, 21, 7);
		fechas[7] = new Fecha(22, 7, 23, 8);
		fechas[8] = new Fecha(24, 8, 23, 9);
		fechas[9] = new Fecha(24, 9, 22, 10);
		fechas[10] = new Fecha(23, 10, 22, 11);
		fechas[11] = new Fecha(23, 11, 31, 11);
	}

	static String signoFecha(GregorianCalendar date) {
		int index = -1;
		for (int i = 0; i < fechas.length; i++) {
			if (fechas[i].compareTo(date) == 0) {
				index = i;
				break;
			}
		}
		return signos[index];
	}
}

class Fecha implements Comparable<GregorianCalendar> {
	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	int diaIni;
	int mesIni;

	int diaFin;
	int mesFin;
	GregorianCalendar ini;
	GregorianCalendar fin;

	public Fecha(int diaIni, int mesIni, int diaFin, int mesFin) {
		this.diaIni = diaIni;
		this.mesIni = mesIni;
		this.diaFin = diaFin;
		this.mesFin = mesFin;
		ini = new GregorianCalendar(1900, mesIni, diaIni,0,0);//el date tiene muchos problemas con la comparacion
		fin = new GregorianCalendar(1900, mesFin, diaFin,23,59);
	}

	@Override
	public int compareTo(GregorianCalendar o) {
		ini.set(o.YEAR, o.get(o.YEAR));
		fin.set(o.YEAR, o.get(o.YEAR));
		if (o.compareTo(ini) >= 0 && o.compareTo(fin) <= 0)
			return 0;

		return -1;// poner cuidado

	}

}

//class Main {
//	public static String main(int TC, String linea) {
//		Calendar cal = Calendar.getInstance();
//		char[] s = linea.toCharArray();
//		int MM = (s[0] - '0') * 10 + s[1] - '0';
//		int DD = (s[2] - '0') * 10 + s[3] - '0';
//		int YY = (((s[4] - '0') * 10 + s[5] - '0') * 10 + s[6] - '0') * 10
//				+ s[7] - '0';
//
//		cal.set(YY, MM - 1, DD);
//		cal.add(Calendar.DATE, 40 * 7); // advance 40*7 days
//
//		// get the new date
//		MM = cal.get(Calendar.MONTH) + 1;
//		DD = cal.get(Calendar.DATE);
//		YY = cal.get(Calendar.YEAR);
//
//		String ZZ = "capricorn"; // starts from capricorn's zodiac
//		if (cal.compareTo(new GregorianCalendar(YY, 0, 21)) >= 0)
//			ZZ = "aquarius";
//		if (cal.compareTo(new GregorianCalendar(YY, 1, 20)) >= 0)
//			ZZ = "pisces";
//		if (cal.compareTo(new GregorianCalendar(YY, 2, 21)) >= 0)
//			ZZ = "aries";
//		if (cal.compareTo(new GregorianCalendar(YY, 3, 21)) >= 0)
//			ZZ = "taurus";
//		if (cal.compareTo(new GregorianCalendar(YY, 4, 22)) >= 0)
//			ZZ = "gemini";
//		if (cal.compareTo(new GregorianCalendar(YY, 5, 22)) >= 0)
//			ZZ = "cancer";
//		if (cal.compareTo(new GregorianCalendar(YY, 6, 23)) >= 0)
//			ZZ = "leo";
//		if (cal.compareTo(new GregorianCalendar(YY, 7, 22)) >= 0)
//			ZZ = "virgo";
//		if (cal.compareTo(new GregorianCalendar(YY, 8, 24)) >= 0)
//			ZZ = "libra";
//		if (cal.compareTo(new GregorianCalendar(YY, 9, 24)) >= 0)
//			ZZ = "scorpio";
//		if (cal.compareTo(new GregorianCalendar(YY, 10, 23)) >= 0)
//			ZZ = "sagittarius";
//		if (cal.compareTo(new GregorianCalendar(YY, 11, 23)) >= 0)
//			ZZ = "capricorn";
//
//		return String.format("%d %02d/%02d/%04d %s\n", TC, MM, DD, YY, ZZ);
//	}
//}
