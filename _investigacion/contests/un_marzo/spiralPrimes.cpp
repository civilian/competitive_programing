#include <iostream>
#include <cmath>
#include <map>
#include <utility> 
#define EPS 1e-7
using namespace std;
typedef long long LL;
bool IsPrimeSlow (LL x)
{
  if(x<=1) return false;
  if(x<=3) return true;
  if (!(x%2) || !(x%3)) return false;
  LL s=(LL)(sqrt((double)(x))+EPS);
  for(LL i=5;i<=s;i+=6)
  {
    if (!(x%i) || !(x%(i+2))) return false;
  }
  return true;
}

map<pair<int,int>, int> spiralPrimes;

void createPrimes(){
  int end = 1;
  bool xadd = true;
  bool xsub = false;
  bool yadd = false;
  bool ysub = false;
  int x = 0;
  int y = 0;
  for(int i=2; i<10000;i++){
    if(IsPrimeSlow(i)){
      pair<int,int> pos;
      pos = make_pair(x,y);
      spiralPrimes[pos] = i;
      if(xadd){
        x++;
        if(x==end){
          xadd = false;
          yadd = true;
        }

      }else if(yadd){
        y++;
        if(y==end){
          yadd = false;
          xsub = true;
        }
      }else if(xsub){
        x--;
        if(x==(end*-1)){
          xsub = false;
          ysub = true;
        }
      }
      else if(ysub){
        y--;
        if(y==(end*-1)){
          ysub = false;
          xadd = true;
          end++;
        }
      }
    }
  }

}

int main(){
  createPrimes();
  int test = 1;
  while(true){
    int a,b;
    cin >> a;

    if(a == -999)
      break;

    if(a !=  -999 && test > 1)
      cout << endl ;
    cin >> b;

    pair<int,int> pos;
    pos = make_pair(a,b);

    cout <<"Case "<<test<<": The prime at location ("
         <<a<<","<<b<<") is "<<spiralPrimes[pos]<<"."<< endl;
    test++;
  }
  return 0;
}
