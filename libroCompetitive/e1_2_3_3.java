package libroCompetitive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class e1_2_3_3 {
	
	public static void main(String[] args) {
		System.out.println(diaFecha(2012, 1, 15));//doming
		System.out.println(diaFecha(2010, 8,9));//lun
	}
	
	static String diaFecha(int anho, int mes, int dia){
		String[] dias={"", "domig", "lun","mar","mier","jueve","friday","saturday"};//dias comienzan en 1
		GregorianCalendar dat=new GregorianCalendar(anho,mes-1,dia);//mes comienza en 0 tonces toca correrlo
		return dias[dat.get(GregorianCalendar.DAY_OF_WEEK)];//monday
	}
}
