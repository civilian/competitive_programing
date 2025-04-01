#include <cstdlib>
#include <cstring>
#include <cmath>
#include <iostream>
#include <vector>
#include <algorithm>

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
    
    return (p / q);
}

double calc_integral(int j, int intervalos, double d) {
    double h = W / (double)intervalos;
    double s = 0;
    
    double altP, altA, xini, xfin;
    xini = 0;
    xfin = h;
    for(int i = 0; i < intervalos; i++) {
        altP = eval_fun(j, xini);
        altA = eval_fun(j, xfin);
        altA = max(altP, altA);
        if(altP < -d) continue;
        altP = min(altP, altA);
        if(altA < -d) continue;
        double tmp = altA * h;
        tmp += h * (altP - altA) / 2.;
        s += -tmp;
        xini = xfin;
        xfin += h;
    }
    return s;
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
            for(int j = 0; j <= K; j++) {
                int64 x; cin >> x;
                polinomios[i].push_back(x);
            }
        }
        cout << search() << endl;
    }
}
