package general;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Arrays;

public class DibujarPoligonos extends Applet {
  Image disco;
  public void init() {
    this.setSize(400,300);
//    disco=getImage(getDocumentBase(), "disco.gif");
  }

  	private static void dbg(Object...o) {
  		System.out.println(Arrays.deepToString(o));
		// TODO Auto-generated method stub

	}
   public void paint(Graphics g){
	   
	    dbg(p.xpoints);
	    dbg(p.ypoints);
	   g.setColor(Color.RED);
	   g.fillPolygon(p);
  }
   
   public Polygon p=new Polygon();
   
   public void dibuja (Polygon p) {
	   this.p=p;
	   
		// creas una ventana
		Frame ventana = new Frame ("titulo");
		// creas una instancia de tu applet
		DibujarPoligonos applet = new DibujarPoligonos();
		applet.p=p;
		// agrega el applet a la ventana
		ventana.add (applet);
		dbg(p.xpoints);
	    dbg(p.ypoints);
		
		// llamas el método init del applet
		applet.init ();
		// le das tamaño a la ventana
		ventana.setSize (300, 300);
		// muestras la ventana
		ventana.setVisible (true);
		applet.repaint();
	}
}
