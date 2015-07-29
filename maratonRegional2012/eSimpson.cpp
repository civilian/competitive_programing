#include <cstdlib>
#include <cstring>
#include <cmath>
#include <iostream>
#include <vector>
#include <algorithm>

#define D(x) cout << #x << " = " << (x) << endl;

using namespace std;

typedef long long int64;

int W, D, A, K;
vector<int64> polinomios[4];

double sum(int i, double x) { // i polinomio, x valor
    double s = 0;
    double pot = 1;
    for(int j = 0; j <= K; j++) {
        s += polinomios[i][j] * pot;
        pot *= x;
    }
    return s;
}

double eval_fun(int i, double x) {
    double p = sum(i, x);
    double q = sum(i+1, x);

    if(! q) return 0;
    return (p / q);
}

double calc_integral(int j, int intervalos, double d) {
    double a = 0 , b = W, f_tmp; // a inicio intervalo b fin intervalo
    const int N = 10 ; // 10^6 generalmente 
    int val;
    double s = 0;
    double h = (b - a) / N;//en cuanto lo vamos a partir
    for (int i=0; i<=N; ++i) {
	   double x = a + h * i;
	   f_tmp = eval_fun(x, j);
	   if(f_tmp < -d) continue;
	   val = ((i==0 || i==N) ? 1 : ((i&1)==0) ? 2 : 4);
	   s +=  f_tmp * val;
    }
//    D(s);
    s *= h / 3;
}

double search() {
    double lo = 0, hi = D;
    for(int i = 0; i <= 100; i++) {
        double mid = (hi + lo) / 2;
        double v = calc_integral(0, 1000, mid) - calc_integral(2, 1000, mid);
        if(v <= A) hi = mid;
        else lo = mid;
    }
    return calc_integral(0, 1000, lo) - calc_integral(2, 1000, lo);
}

int main() {
#ifdef LOCAL
    freopen("e.in", "r", stdin);
    freopen("e.out", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif
    cout.precision(5);
    cout.setf(ios::fixed);
    while(cin >> W >> D >> A >> K) {
        for(int i = 0; i < 4; i++) {
            polinomios[i].clear();
            for(int j = 0; j <= K; j++) {
                int64 x; cin >> x;
                polinomios[i].push_back(x);
            }
        }
        cout << search() << endl;
    }
}
