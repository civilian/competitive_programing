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

#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)

using namespace std;


int main() {
    ios_base::sync_with_stdio(false);
    freopen("lectura.in.txt", "r", stdin);
    freopen("lectura.out.txt", "w", stdout);
    
    stringstream parser;
    //Parsear un string
    string l="(alpha+omega)^2" , a, b;
    int e;
    REP(i,0,l.size()){
        if( l[i] == '(' || l[i] == ')' 
            || l[i] == '+' || l[i] == '^' ){
            l[i]=' ';
        }
    }
//        D(l);
    parser.clear();
    
    parser << l;
    parser >> a;//alpha
    parser >> b;//omega
    parser >> e;//2
    cout << a << b << e <<endl;
    //Leer lineas enteras en string
    while(getline(cin,l, '\n')){
        cout<<l<<" se ve"<<endl;
    }
    //Leer lineas enteras en char *
//    string l;
    char T[1000];
    while(gets(T)){
        cout<<T<<" se ve"<<endl;
    }
    //freopen("d_in.txt", "r", stdin);
    //freopen("d_out.txt", "w", stdout);
}

