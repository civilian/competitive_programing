package maratonBook;

/*Utilidades para manejar fechas en java recordar siempre el primer dia es 
 * domingo el 1, y el enero es mes 0 
 * 
 * Peligro si se pone una fecha mal se acepta pero por ejemplo el mes 13 del
 * 2011 es febrero del 2012
 * 
 * Tambien tener mucho cuidado con las comparaciones aunque es mucho mas 
 * facil que implementalas ahi que poner cuidado porque la comparacion 
 * se hace teniendo en cuenta la hora el minuto el segundo y etc..*/

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class Dates {
	public static void main(String[] args) {
		System.out.println(diaFecha(2012, 1, 15));// doming
		System.out.println(diaFecha(2010, 8, 9));// lun
		GregorianCalendar date = new GregorianCalendar(2011, 13, 15);

		System.out.println(imprimirFecha(date));// 02 15 2012
	}

	static String diaFecha(int anho, int mes, int dia) {
		String[] dias = { "", "domig", "lun", "mar", "mier", "jueve", "friday",
				"saturday" };// dias comienzan en 1
		GregorianCalendar dat = new GregorianCalendar(anho, mes - 1, dia);// mes
																			// comienza
																			// en
																			// 0
																			// tonces
																			// toca
																			// correrlo
		return dias[dat.get(GregorianCalendar.DAY_OF_WEEK)];// monday
	}

	private static String imprimirFecha(GregorianCalendar date) {
		SimpleDateFormat formato = new SimpleDateFormat("MM dd yyyy");
		Date fecha = date.getTime();
		return formato.format(fecha);
	}

	public static GregorianCalendar restarFechasDias(GregorianCalendar fch,
			int dias) {
		fch.add(fch.DATE, -dias);
		return fch;
	}

	public static GregorianCalendar sumarSemanasFecha(GregorianCalendar fch,
			int semanas) {
		fch.add(fch.WEEK_OF_YEAR, semanas);
		return fch;
	}

}

class Mes implements Comparable<GregorianCalendar> {
	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	int diaIni;
	int mesIni;

	int diaFin;
	int mesFin;
	GregorianCalendar ini;
	GregorianCalendar fin;

	public Mes(int diaIni, int mesIni, int diaFin, int mesFin) {
		this.diaIni = diaIni;
		this.mesIni = mesIni;
		this.diaFin = diaFin;
		this.mesFin = mesFin;
		ini = new GregorianCalendar(1900, mesIni, diaIni, 0, 0);// el date tiene
																// problemas con
																// la
																// comparacion
		fin = new GregorianCalendar(1900, mesFin, diaFin, 23, 59);
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
