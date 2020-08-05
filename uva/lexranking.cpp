#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <iomanip>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>
#include <numeric>
#include <complex>

#define D(x) cout << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;


int N;

string original;
string letras;

int ord['z'+1];

long long factorial[21];

long long numPalabras(int L, int C) {
    long long res=0;
    for(int l=1; l<=L; l++) {
        res += factorial[C]/factorial[C-l];
    }
    return res;
}

long long rankingPalabra() {
    long long res = 0;
    for(int i=0; i<N; i++) {
        res += ord[original[i]]+1; //numero de palabras de 1 letra que hay antes de la actual
        res += ord[original[i]]*numPalabras(N-i-1,N-i-1);
        
        for(int m=original[i]; m<='z'; m++) ord[m]--;
    }
    return res;
}



int main() {
    ios_base::sync_with_stdio(false);
    
    factorial[0]=1;
    for(int i=1; i<=20; i++) factorial[i]=factorial[i-1]*i;
    
    cin>>original;
    letras = original;
    sort(ALL(letras));
    N = letras.length();
    
    REP(i,0,N) ord[letras[i]]=i;
    
    cout<<rankingPalabra()<<endl;
    cin>>original;
    //freopen("d_in.txt", "r", stdin);
    //freopen("d_out.txt", "w", stdout);
}

