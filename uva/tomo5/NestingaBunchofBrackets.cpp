//Uva 551

#include <cstdio>
#include <cstdlib>
#include <cstring>
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

string sopen="1([{<";
string sclose="2)]}>"; //asi respeteando las posiciones

stack <char> expr;
bool no;


int main() {
    freopen("NestingaBunchofBrackets.txt", "r", stdin);
    freopen("NestingaBunchofBrackets_out.txt", "w", stdout);

//    
    string linea;
    int who,i,j;
    char simb, tmp;
    bool open;
    while(cin>>linea){
        while(!expr.empty()) expr.pop();
//        D(expr.size());
        
        no=false;
        
        for(j=i=0; i<linea.size();i++){
            open= false;
            simb=linea[i];
            j++;
            if(simb== '1' || simb== '2')
                continue;
                
            if( simb=='('){
                if(i<linea.size()-1){
            	   tmp=linea[i+1];
                   if(tmp=='*' ){
                        simb='1';
                        i++;
                    }
                }
             }else if ( simb=='*'){
                if(i<linea.size()-1){
        	       tmp=linea[i+1];
                   if(tmp==')' ){
                        simb='2';
                        i++;
                    }
                }
            }

            if( (who= sopen.find(simb))!= string::npos ){
	           open=true;
            }else if( (who= sclose.find(simb))!= string::npos){
            	open=false;
            }else {
            	continue;
            }
//            D(simb);
//            D(open);
            if(open){
            	expr.push(sclose[who]);
            } else if(expr.size()==0 || simb!= expr.top()){
           		no=true;
           		break;
            } else if(expr.size()>0 && simb == expr.top()){
                expr.pop();
//                D("matc");
//                D(simb);
//                D(expr.size());
           		no=false;
            } 
        }
//        D(expr.size());
//        D(no);
        if(no){
        	printf("NO %d\n", j);
        } else if(expr.size() != 0 ){
            printf("NO %d\n", j+1);
        } else {
        	printf("YES\n");
        }
    }
    return 0;
}

