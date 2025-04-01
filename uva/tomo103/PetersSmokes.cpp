//Uva 10346.

#include <cstdlib>//atoi rand
#include <cstdio>//printf
#include <iostream>//cin cout
#include <string>
#include <climits>
#include <vector>
#include <map>
#include <utility>
#include <algorithm>//organizar busqueda binaria

#define mp make_pair

using namespace std;

int n, k, fumados, tmp;

int main(int argc, char *argv[])
{
//    freopen("PeterSmokes.txt", "r", stdin);
//    freopen("PeterSmokes_out.txt", "w", stdout);
    while (scanf("%d%d", &n, &k)== 2) {
        fumados = 0;
        tmp = 1;
        while (true) {
            if (tmp <= 0) {
                break;
            }
            tmp = n / k;
            n = n - (k * tmp);
            fumados += (k * tmp);
            n += tmp;
        }
       fumados += n;
       printf("%d\n", fumados);
//        cout<<fumados<<endl;
    }
    return 0;
}
