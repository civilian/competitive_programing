/*Uva 12528 @autor: afruizc 
from: http://asdfcoding.wordpress.com/2013/06/22/12528-environment-protection-uva/
*/

#include <cstdio>
#include <cmath>
 
double W, D, A, K;
double p1[10], q1[10], p2[10], q2[10];
 
double eval(double x, double line) { // Evaluation using horner's rule :D
    double num1, den1, num2, den2;
    num1 = num2 = den1 = den2 = 0.0;
    for (int i=K ; i>=0 ; i--) {
        num1 = num1*x + p1[i];
        den1 = den1*x + q1[i];
        num2 = num2*x + p2[i];
        den2 = den2*x + q2[i];
    }
 
    double integral1, integral2;
    integral1 = num1 / den1;
    integral2 = num2 / den2;
 
    if (line > integral1) {
        return 0;
    }
    else if (line < integral2) {
        return integral1 - integral2;
    }
    else return integral1 - line;
}
 
int main() {
#ifdef LOCAL
    freopen("EnvironmentProtection.in.txt", "r", stdin);
    freopen("EnvironmentProtection.out.txt", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
#endif
    while (scanf("%lf %lf %lf %lf", &W, &D, &A, &K) == 4) {
        for (int i=0 ; i<=K ; i++) {
            scanf("%lf", &p1[i]);
        }
 
        for (int i=0 ; i<=K ; i++) {
            scanf("%lf", &q1[i]);
        }
 
        for (int i=0 ; i<=K ; i++) {
            scanf("%lf", &p2[i]);
        }
 
        for (int i=0 ; i<=K ; i++) {
            scanf("%lf", &q2[i]);
        }
 
        const double EPS = 1e-4;
        double x, low, high;
        low = -D;
        high = 0.0;
        for (int i=0 ; i<22 ; i++) {
            double totalArea = 0.0;
            double x = (low + high) / 2.0;
            for (double a=0.0 ; a+EPS-1e-5<W ; a += EPS) {
                double b = a + EPS;
                totalArea += ((b - a) / 6.0) * (eval(a, x) + (4 * eval((a+b) / 2.0, x)) + eval(b, x));
            }
 
            if (totalArea < A) {
                high = x;
            }
            else {
                low = x;
            }
        }
 
        printf("%.5lf\n", -low);
    }
}
  

