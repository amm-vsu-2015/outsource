#include <stdio.h>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
#include <omp.h>
#include "math.h"

using namespace std;


void print(string label, double value) {
  stringstream s;
  s << fixed << setprecision(9) << value;
  cout << label << s.str() << endl;
}

int main(int argc, char *argv[]) {

  int** array1 = new int*[5];
  int* array2 = new int[5];

  array1[0] = array2;
  array1[0][0] = 21;

  std::cout << "a: " << *(*(array1)) << '\n';

  return 0;
}
