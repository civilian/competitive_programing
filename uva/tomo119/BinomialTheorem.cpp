//Uva 11955 
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

int64 C[60][60];

void init(int N) 
{ 
   memset(C,0,sizeof(C)); 
   for (int i=0; i<=N; i++) { 
       C[i][0] = 1; 
       for (int j=1; j<=i; j++) { 
           C[i][j] = C[i-1][j] + C[i-1][j-1]; 
       } 
       C[i][i] = 1;
   } 
}

void raise_to(string b, int e){
//    cout<< b << "e" << e ;
    if(e){
        if( e == 1 ){
            cout << b;
            return;
        }
        else{
            cout<< b << "^" << e ;
//            printf("%s^%d",b,e);
            return;
        }
    }
    return;
}

int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("BinomialTheorem.txt", "r", stdin);
//    freopen("BinomialTheorem_out.txt", "w", stdout);
    
    init(57);
    
    int tc, e, expA;
    cin>>tc;
    string a,b,l;
    stringstream parser;
    REP(idCase,1,tc+1){
        cin.get();
        getline(cin,l);
        
        REP(i,0,l.size()){
            if( l[i] == '(' || l[i] == ')' 
                || l[i] == '+' || l[i] == '^' ){
                l[i]=' ';
            }
        }
//        D(l);
        parser.clear();
        
        parser << l;
        parser >> a;
        parser >> b;
        parser >> e;
        
        cout<< "Case " << idCase << ": ";
        
//        D(e);
//        D(a);
//        D(b);

        raise_to(a,e);
        expA=e-1;
        
        REP(expB,1,e){
            cout << "+";
            
            cout << C[e][expB];
            cout << "*";
            raise_to(a,expA);
            
            cout << "*";
            raise_to(b,expB);
            
            expA--;
        }
        
        cout << "+";
//        raise_to(a,0);
        raise_to(b,e);
        cout << endl;
    }
}

