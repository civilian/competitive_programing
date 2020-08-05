#include <cstdlib>
#include <cassert>
#include <cmath>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

const double PI = 3.14159;
const double tol = 1e-8;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int t;
  cin >> t;
  
  cout.precision(2);
  cout.setf(ios::fixed);
  
  for (int tc = 1; tc <= t; tc++) {
    int a, b;
    cin >> a >> b;
    
    int c = a-b;
    
    double total_area = PI * a * a;
    double up_area = PI * b * b;
    double low_area = PI * c * c;
    
    double side_area = .5 * (total_area - up_area - low_area);
    
    cout << "Taijitu #" << tc << ": yin " << (low_area+side_area+tol) << ", ";
    cout << "yang " << (up_area+side_area+tol) << "\n\n";
  }
  
  return 0;
}
