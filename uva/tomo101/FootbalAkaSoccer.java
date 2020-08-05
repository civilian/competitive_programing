package uva.tomo101;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class FootbalAkaSoccer {
	static BufferedReader input;
	
	static StringTokenizer _stk;
	static String readln() throws IOException{
		String l=input.readLine();
		if(l!=null) _stk=new StringTokenizer(l,"#@",true);
		return l;
	}
	
	static String next(){return _stk.nextToken();}
	static int nextInt(){return Integer.parseInt(next());}
	
	static void dbg(Object... o){
		System.out.println(Arrays.deepToString(o));
	}
	

	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
//		input=new BufferedReader(new InputStreamReader(System.in));
		input=new BufferedReader(new FileReader("FootbalAkaSoccer"));
		readln();

		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out, "ISO-8859-1")));
		
		int tornaments=nextInt();
		
		for (int casesId = 0; casesId < tornaments; casesId++) {
			String torneo=readln();
//			if(torneo.length()>100) torneo=torneo.substring(0,100);
			readln();
			int iequipos=nextInt();
			
			//leo los nombre de los equipos creando los equipos del 
			//torneo en un hashmap
			EquipoTorneo[] tabla=new EquipoTorneo[iequipos];
			HashMap<String, Integer> mapper=new HashMap<String, Integer>();
			
			for (int i = 0; i < iequipos; i++) {
				String nombre=readln();

				mapper.put(nombre, i);
//				if(nombre.length()>30) nombre=nombre.substring(0,30);
				tabla[i]=new EquipoTorneo(nombre);
			}
			//Luego leo los "partidos" y segun eso tengo que cambiar los de arriba
			readln();
			int partidos=nextInt();
			for (int i = 0; i < partidos; i++) {
				readln();
				String equipoA=next();
				next();//separador #
				int goalsA=nextInt();
				next();//separador at
				int goalsB=nextInt();
				next();//separator #
				String equipoB=next();
				
				EquipoTorneo A=tabla[mapper.get(equipoA)];
				EquipoTorneo B=tabla[mapper.get(equipoB)];
				boolean empate=false;
				if(goalsA<goalsB){//gano B
					EquipoTorneo equipoTmp=A;
					A=B;
					B=equipoTmp;//el perdedor queda en B
//					dbg("ganoB",A,B);
					int tmp=goalsA;
					goalsA=goalsB;
					goalsB=tmp;
				}else if (goalsA==goalsB) {
					
					empate=true;
					A.points+=1;
					B.points+=1;
					
					A.gamesPlayed++;
					B.gamesPlayed++;
					
					A.ties++;
					B.ties++;
					
					A.goalsScored+=goalsA;
					B.goalsScored+=goalsB;
					
					A.goalsAgaist+=goalsB;
					B.goalsAgaist+=goalsA;
//					dbg("empate",A,B);
				}if(!empate){
					
					A.points+=3;
	//				B.points;
					
					A.gamesPlayed++;
					B.gamesPlayed++;
					
					A.wins++;
					
					B.loses++;
					
					A.goalsScored+=goalsA;
					B.goalsScored+=goalsB;
					
					A.goalsAgaist+=goalsB;
					B.goalsAgaist+=goalsA;
//					dbg("noEmpate",A,B);
				}
			}
//			EquipoTorneo[] equiposLuego=(EquipoTorneo[]) tabla.values().toArray(new EquipoTorneo[tabla.size()]);
			EquipoTorneo[] equiposLuego=tabla;
			
			for (int i = 0; i < equiposLuego.length; i++) {
				equiposLuego[i].calcularDiferenciaDeGoles();
//				System.out.println(equiposLuego[i]);
			}
//			System.out.println();
			
			Comparator<EquipoTorneo> compa= new Comparator<EquipoTorneo>() {
				@Override//1 es va primero -1 es va segundo
				public int compare(EquipoTorneo o1, EquipoTorneo o2) {
					if(o1.points!=o2.points)
						return Integer.signum(o2.points-o1.points);
					else if (o1.wins!=o2.wins)
						return Integer.signum(o2.wins-o1.wins);
					else if(o1.globalDiference!=o2.globalDiference)
						return Integer.signum(o2.globalDiference-o1.globalDiference);
					else if(o1.goalsScored!=o2.goalsScored)
						return Integer.signum(o2.goalsScored-o1.goalsScored);
					else if(o1.gamesPlayed!=o2.gamesPlayed)
						return Integer.signum(o1.gamesPlayed-o2.gamesPlayed);
					else{return o1.name.compareToIgnoreCase(o2.name);}//los nombre siempre van a ser diferentes
						
				}
			};
			
			Arrays.sort(equiposLuego, compa);
			output.println(torneo);
			for (int i = 0; i < equiposLuego.length; i++) {
				
				output.printf("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n",i+1,equiposLuego[i].name,equiposLuego[i].points,equiposLuego[i].gamesPlayed, equiposLuego[i].wins, equiposLuego[i].ties, equiposLuego[i].loses, equiposLuego[i].globalDiference, equiposLuego[i].goalsScored, equiposLuego[i].goalsAgaist);
//				System.out.println(equiposLuego[i]);
			}
			
			if(casesId!=tornaments-1)output.println();
			
		}
		output.close();
//		System.out.println((int)'a');
//		System.out.println((int)'A');
//		System.out.println("Z".compareTo("a"));
		
	}
}
class EquipoTorneo{


	int rank, points, gamesPlayed, wins, ties, loses, 
	globalDiference, goalsScored, goalsAgaist;
	String name;
	
	//el rank se imprime cuando los este mostrando
	//y los goal diferencen se cacula cuando se acabne los partidos
	
	public EquipoTorneo(String name) {
		this.name=name;
		// TODO Auto-generated constructor stub
	}

	public void calcularDiferenciaDeGoles() {
		globalDiference=goalsScored-goalsAgaist;
		// TODO Auto-generated method stub
	}
	
	@Override
	public String toString() {
		return "EquipoTorneo "+name+ " [rank=" + rank + ", points=" + points
				+ ", gamesPlayed=" + gamesPlayed + ", wins=" + wins + ", ties="
				+ ties + ", loses=" + loses + ", globalDiference="
				+ globalDiference + ", goalsScored=" + goalsScored
				+ ", goalsAgaist=" + goalsAgaist + "]";
	}
}
