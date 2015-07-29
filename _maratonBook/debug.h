#ifndef _DEBUG_H
#define _DEBUG_H

#include <iostream>
#include <vector>
#include <map>
#include <utility>
#include <set>

#ifndef D
#define D(x) cerr << #x << " = " << (x) << endl;
#endif

template<typename T,typename U> inline
std::ostream& operator<<(std::ostream& os, const std::pair<T,U>& z){
    return ( os << "(" << z.first << ", " << z.second << ",)" );
}
template<typename T> inline
std::ostream& operator<<(std::ostream& os, const std::vector<T>& z){
    os << "[ ";
    for(int i=0; i < z.size(); i++) os << z[i] << ", " ;
    return ( os << "]" << endl);
}
template<typename T> inline
std::ostream& operator<<(std::ostream& os, const std::set<T>& z){
    os << "set( ";
    for(__typeof((z).begin()) p = (z).begin(); p!=(z).end(); ++p)
        os << (*p) << ", " ;
    return ( os << ")" << endl);
}
template<typename T,typename U> inline
std::ostream& operator<<(std::ostream& os, const std::map<T,U>& z){
    os << "{ ";
    for(__typeof((z).begin()) p = (z).begin(); p!=(z).end(); ++p)
        os << (p->first) << ": " << (p->second) << ", " ;
    return ( os << "}" << endl);
}

#endif
