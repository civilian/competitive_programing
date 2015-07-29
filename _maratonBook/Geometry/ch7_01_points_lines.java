package maratonBook.Geometry;

/*FALTARIA TENER LOS METODOS DE LINE(LOCAL),
 CON LINE DE JAVA PERO SE PUEDEN HACER CON EL METODO POINST TO LINE(constante)
 Y LUEGO MANDARLO A EL METODO
 */

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ch7_01_points_lines {

	static double INF = 1e9;
	static double EPS = 1e-9;
	static double PI = Math.PI; // important constant; alternative #define PI
								// (2.0 * acos(0.0))

	// Math.acos(-1.0);

	// double DEG_to_RAD(double d) { return d * PI / 180.0; }

	// double RAD_to_DEG(double r) { return r * 180.0 / PI; }

	// boolean areSame(Point p1, Point p2) { // integer version
	// return p1.x == p2.x && p1.y == p2.y; } // precise comparison
	boolean areSame(Point p1, Point p2) { // integer version
		return p1.equals(p2); // precise comparison
	}

	boolean areSame(Point2D p1, Point2D p2) { // floating point version
		// use EPS when testing equality of two floating points
		return Math.abs(p1.getX() - p2.getX()) < EPS
				&& Math.abs(p1.getY() - p2.getY()) < EPS;
	}

	// double dist(Point2D p1, Point2D p2) { // Euclidean distance
	// // hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
	// return Math.hypot(p1.getX() - p2.getX(), p1.getY() - p2.getY()); } //
	// return double
	static double dist(Point2D p1, Point2D p2) { // Euclidean distance
		// hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
		return p1.distance(p2); // return double
	}

	// rotate p by theta degrees CCW w.r.t origin (0, 0)
	static Point2D rotate(Point2D p, double theta) {
		// rotation matrix R(theta) = [cos(theta) -sin(theta)]
		// [sin(theta) cos(theta)]
		double rad = Math.toRadians(theta); // must work in radian
		return new Point2D.Double(p.getX() * Math.cos(rad) - p.getY()
				* Math.sin(rad), p.getX() * Math.sin(rad) + p.getY()
				* Math.cos(rad));
	}

	// the answer is stored in the third parameter (pass byref)
	static void pointsToLine(Point2D p1, Point2D p2, line l) {
		double a = p1.getX();
		if (p1.getX() == p2.getX()) { // vertical line is handled nicely here
			l.a = 1.0;
			l.b = 0.0;
			l.c = -p1.getX();
		} else {
			l.a = -(double) (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());
			l.b = 1.0;
			l.c = -(double) (l.a * p1.getX()) - (l.b * p1.getY());
		}
	}

	// not needed since we will use the more robust form: ax + by + c = 0 (see
	// above)
	// struct line2 { double m, c; }; // another way to represent a line
	//
	// int pointsToLine(Point2D p1, Point2D p2, line2 *l) {
	// if (p1.x == p2.x) { // special case: vertical line
	// l->m = INF; // l contains m = INF and c = x_value
	// l->c = p1.x; // to denote vertical line x = x_value
	// return 0; // we need this return variable to differentiate result
	// }
	// else {
	// l->m = (double)(p1.y - p2.y) / (p1.x - p2.x);
	// l->c = p1.y - l->m * p1.x;
	// return 1; // l contains m and c of the line equation y = mx + c
	// } }

	static boolean areParallel(line l1, line l2) { // check coefficient a + b
		return (Math.abs(l1.a - l2.a) < EPS) && (Math.abs(l1.b - l2.b) < EPS);
	}

	static boolean areSame(line l1, line l2) { // also check coefficient c
		return areParallel(l1, l2) && (Math.abs(l1.c - l2.c) < EPS);
	}

	// REVISAR PORQUE SE CAMBIO P.X POR X Y Y Y LUEGO SE PUSO
	// returns true (+ intersection Point2D) if two lines are intersect
	static boolean areIntersect(line l1, line l2, Point2D p) {
		double x = 0.0, y = 0.0;
		if (areSame(l1, l2))
			return false; // all points intersect
		if (areParallel(l1, l2))
			return false; // no intersection
		// solve system of 2 linear algebraic equations with 2 unknowns
		x = (double) (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
		if (Math.abs(l1.b) > EPS) // test for vertical line
			y = -(l1.a * x + l1.c) / l1.b; // avoid div by zero
		else
			// this is another special case in geometry problem...
			y = -(l2.a * x + l2.c) / l2.b;

		p.setLocation(x, y);
		return true;
	}

	static vec toVector(Point2D p1, Point2D p2) { // convert 2 points to vector
		return new vec(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	static vec scaleVector(vec v, double s) { // s = [<1 ... 1 ... >1]
		return new vec(v.x * s, v.y * s);
	} // shorter v same v longer v

	static Point2D translate(Point2D p, vec v) { // translate p according to v
		Point2D out = new Point2D.Double();
		out.setLocation(p.getX() + v.x, p.getY() + v.y);
		return out;
	}

	// convert Point2D and gradient/slope to line
	static void pointSlopeToLine(Point2D p, double m, line l) {
		l.a = -m; // always -m
		l.b = 1; // always 1
		l.c = -((l.a * p.getX()) + (l.b * p.getY()));
	} // compute this

	// REVISAR EL SETLOCATION
	static void closestPoint(line l, Point2D p, Point2D ans) {
		line perpendicular = new line(); // perpendicular to l and pass through
											// p
		if (Math.abs(l.b) < EPS) { // special case 1: vertical line
			ans.setLocation(-(l.c), p.getY());
			return;
		}

		if (Math.abs(l.a) < EPS) { // special case 2: horizontal line
			ans.setLocation(p.getX(), -(l.c));
			return;
		}

		pointSlopeToLine(p, 1 / l.a, perpendicular); // normal line
		// intersect line l with this perpendicular line
		// the intersection point is the closest point
		areIntersect(l, perpendicular, ans);
	}

	// returns the distance from p to the line defined by
	// two points A and B (A and B must be different)
	// the closest point is stored in the 4th parameter (byref)
	static double distToLine(Point2D p, Point2D A, Point2D B, Point2D c) {
		// formula: cp = A + (p-A).(B-A) / |B-A| * (B-A)
		double scale = (double) ((p.getX() - A.getX()) * (B.getX() - A.getX()) + (p
				.getY() - A.getY()) * (B.getY() - A.getY()))
				/ ((B.getX() - A.getX()) * (B.getX() - A.getX()) + (B.getY() - A
						.getY()) * (B.getY() - A.getY()));
		c.setLocation(A.getX() + scale * (B.getX() - A.getX()), A.getY()
				+ scale * (B.getY() - A.getY()));
		return dist(p, c);
	} // Euclidean distance between p and *c

	// returns the distance from p to the line segment ab defined by
	// two points A and B (still OK if A == B)
	// the closest point is stored in the 4th parameter (byref)
	static double distToLineSegment(Point2D p, Point2D A, Point2D B, Point2D c) {
		if ((B.getX() - A.getX()) * (p.getX() - A.getX())
				+ (B.getY() - A.getY()) * (p.getY() - A.getY()) < EPS) {
			c.setLocation(A.getX(), A.getY()); // closer to A
			return dist(p, A);
		} // Euclidean distance between p and A
		if ((A.getX() - B.getX()) * (p.getX() - B.getX())
				+ (A.getY() - B.getY()) * (p.getY() - B.getY()) < EPS) {
			c.setLocation(B.getX(), B.getY()); // closer to B
			return dist(p, B);
		} // Euclidean distance between p and B
		return distToLine(p, A, B, c);
	} // run distToLine as above

	static double cross(Point2D p, Point2D q, Point2D r) { // cross product
		return (r.getX() - q.getX()) * (p.getY() - q.getY())
				- (r.getX() - q.getY()) * (p.getX() - q.getX());
	}

	// // another variant
	// int area2(Point2D p, Point2D q, Point2D r) { // returns 'twice' the area
	// of this triangle A-B-c
	// return p.x * q.y - p.y * q.x +
	// q.x * r.y - q.y * r.x +
	// r.x * p.y - r.y * p.x;
	// }

	// note: to accept collinear points, we have to change the `> 0'
	// returns true if point r is on the left side of line pq
	static boolean ccw(Point2D p, Point2D q, Point2D r) {
		return cross(p, q, r) > 0;
	}

	// returns true if point r is on the same line as the line pq
	static boolean collinear(Point p, Point2D q, Point2D r) {
		return Math.abs(cross(p, q, r)) < EPS;
	}

	public static void main(String[] args) {
		ArrayList<Point> P = new ArrayList<Point>();
		P.add(new Point(2, 2));
		P.add(new Point(4, 3));
		P.add(new Point(2, 4));
		P.add(new Point(6, 6));
		P.add(new Point(2, 6));
		P.add(new Point(6, 5));

		/*
		 * // the positions of these 5 points (0-based indexing) 6 P4 P3 5 P5 4
		 * P2 3 P1 2 P0 1 0 1 2 3 4 5 6
		 */

		double d = dist(P.get(0), P.get(5));
		System.out.printf("Euclidean distance between P[0] and P[5] = %.2f\n",
				d); // should be 5.000

		// line equations
		line l1 = new line(), l2 = new line(), l3 = new line(), l4 = new line();

		pointsToLine(P.get(0), P.get(1), l1);
		System.out.printf("%.2f * x + %.2f * y + %.2f = 0.00\n", l1.a, l1.b,
				l1.c); // should be -0.50 * x + 1.00 * y - 1.00 = 0.00

		pointsToLine(P.get(0), P.get(2), l2); // a vertical line, not a problem
												// in "ax + by + c = 0"
												// representation
		System.out.printf("%.2f * x + %.2f * y + %.2f = 0.00\n", l2.a, l2.b,
				l2.c); // should be 1.00 * x + 0.00 * y - 2.00 = 0.00

		// tests
		pointsToLine(P.get(2), P.get(3), l3);
		System.out.printf("l1 & l2 are parallel? %b\n", areParallel(l1, l2)); // no
		System.out.printf("l1 & l3 are parallel? %b\n", areParallel(l1, l3)); // yes,
																				// l1
																				// (P[0]-P[1])
																				// and
																				// l3
																				// (P[2]-P[3])
																				// are
																				// parallel

		pointsToLine(P.get(2), P.get(4), l4);
		System.out.printf("l1 & l2 are the same? %b\n", areSame(l1, l2)); // no
		System.out.printf("l2 & l4 are the same? %b\n", areSame(l2, l4)); // yes,
																			// l2
																			// (P[0]-P[2])
																			// and
																			// l4
																			// (P[2]-P[4])
																			// are
																			// the
																			// same
																			// line
																			// (note,
																			// they
																			// are
																			// two
																			// different
																			// line
																			// segments,
																			// but
																			// same
																			// line)

		Point2D p12 = new Point2D.Double(0.0, 0.0);
		boolean res = areIntersect(l1, l2, p12); // yes, l1 (P[0]-P[1]) and l2
													// (P[0]-P[2]) are intersect
													// at (2.0, 2.0)
		System.out.printf("l1 & l2 are intersect? %b, at (%.2f, %.2f)\n", res,
				p12.getX(), p12.getY());

		// other distances
		Point2D p023 = new Point2D.Double(0.0, 0.0);
		d = distToLine(P.get(0), P.get(2), P.get(3), p023);
		System.out
				.printf("Closest point from P[0] to line         (P[2]-P[3]): (%.2f, %.2f), dist = %.2f\n",
						p023.getX(), p023.getY(), d);
		Point2D ans = new Point2D.Double(0.0, 0.0);
		closestPoint(l3, P.get(0), ans);
		System.out
				.printf("Closest point from P[0] to line V2      (P[2]-P[3]): (%.2f, %.2f), dist = %.2f\n",
						ans.getX(), ans.getY(), dist(P.get(0), ans));
		d = distToLineSegment(P.get(0), P.get(2), P.get(3), p023);
		System.out
				.printf("Closest point from P[0] to line SEGMENT (P[2]-P[3]): (%.2f, %.2f), dist = %.2f\n",
						p023.getX(), p023.getY(), d);

		System.out.printf("P[0], P[2], P[3] are collinear? %b\n",
				collinear(P.get(0), P.get(2), P.get(3))); // no
		System.out.printf("P[0], P[2], P[4] are collinear? %b\n",
				collinear(P.get(0), P.get(2), P.get(4))); // yes

		System.out.printf("P[0], P[2], P[3] form A left turn? %b\n",
				ccw(P.get(0), P.get(2), P.get(3))); // no
		System.out.printf("P[0], P[3], P[2] form A left turn? %b\n",
				ccw(P.get(0), P.get(3), P.get(2))); // yes

		// example of sorting points based on its x-coordinate (then if tie, by
		// its y-coordinate)
		Comparator<Point> comp = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o2.x == o1.x)
					return o2.y - o1.y;
				else
					return o2.x - o1.x;
			}
		};

		Collections.sort(P, comp); // comparison operator is defined
		for (int i = 0; i < (int) P.size(); i++)
			System.out.printf("(%d, %d)\n", P.get(i).x, P.get(i).y);

		/*
		 * // the positions of these 6 points E<-- 4 3 B D<-- 2 A C 1 -4-3-2-1 0
		 * 1 2 3 4 5 6 -1 -2 F<-- -3
		 */

		// translation
		Point2D A = new Point2D.Double(2.0, 2.0);
		Point2D B = new Point2D.Double(4.0, 3.0);
		vec v = toVector(A, B); // imagine there is an arrow from A to B (see
								// the diagram above)
		Point2D C = new Point2D.Double(3.0, 2.0);
		Point2D D = translate(C, v); // D will be located in coordinate (3.0 +
										// 2.0, 2.0 + 1.0) = (5.0, 3.0)
		System.out.printf("D = (%.2f, %.2f)\n", D.getX(), D.getY());
		Point2D E = translate(C, scaleVector(v, 0.5)); // E will be located in
														// coordinate (3.0 + 1/2
														// * 2.0, 2.0 + 1/2 *
														// 1.0) = (4.0, 2.5)
		System.out.printf("E = (%.2f, %.2f)\n", E.getX(), E.getY());

		// rotation
		System.out.printf("B = (%.2f, %.2f)\n", B.getX(), B.getY()); // B =
																		// (4.0,
																		// 3.0)
		Point2D F = rotate(B, 90); // rotate B by 90 degrees COUNTER clockwise,
									// F = (-3.0, 4.0)
		System.out.printf("F = (%.2f, %.2f)\n", F.getX(), F.getY());
		Point2D G = rotate(B, 180); // rotate B by 180 degrees COUNTER
									// clockwise, G = (-4.0, -3.0)
		System.out.printf("G = (%.2f, %.2f)\n", G.getX(), G.getY());
	}

}

class line {
	double a, b, c;
}; // a way to represent a line

class vec {
	double x, y; // similar to Point2D

	vec(double _x, double _y) {
		x = _x;
		y = _y;
	}
};