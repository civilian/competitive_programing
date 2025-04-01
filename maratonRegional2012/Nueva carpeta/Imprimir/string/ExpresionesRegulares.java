package maratonBook.string;

import java.io.*;
import java.util.Scanner;

/*Lenguaje:
 STATEMENT = ACTION | STATEMENT , ACTION
 ACTION = ACTIVE_LIST VERB ACTIVE_LIST
 ACTIVE_LIST = ACTOR | ACTIVE_LIST and ACTOR
 ACTOR = NOUN | ARTICLE NOUN
 ARTICLE = a | the
 NOUN = tom | jerry | goofy | mickey | jimmy | dog | cat | mouse
 VERB = hate | love | know | like | VERBs
 */
/*Pruebas:
 the dog and a cat know goofy
 jimmy kills tom
 goofy hate mouse jerry
 tom hates jerry , jimmy hates tom
 bla bla

 R/SI PERTENCE
 NO PERTENCE
 NO PERTENCE
 SI PERTENCE
 NO PERTENCE
 */
class ExpresionesRegulares {
	static final String VERB = "(hate|love|know|like)s?";// estaba * pero eso
															// podria tener s
															// infinitas
	static final String NOUN = "(tom|jerry|goofy|mickey|jimmy|dog|cat|mouse)";
	static final String ARTICLE = "(a|the)";
	static final String ACTOR = "(" + NOUN + "|" + ARTICLE + " " + NOUN + ")";
	static final String ACTIVE_LIST = "(" + ACTOR + " and )*" + ACTOR;
	static final String ACTION = ACTIVE_LIST + " " + VERB + " " + ACTIVE_LIST;
	static final String STATEMENT = ACTION + "( , " + ACTION + ")*";

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		String riddle;
		while ((riddle = sc.nextLine()) != null) {
			// There is always space before and after a comma (,). Therefore, it
			// is optional to clean up input with
			// replaceAll() and trim().
			System.out.println(riddle.replaceAll(" +", " ").trim()
					.matches(STATEMENT) ? "SI PERTENCE" : "NO PERTENCE");
		}
	}
}