#include "stdafx.h"
#include "iostream"
#include "string"
#include "sstream"
#include "omp.h"
#include "math.h"

using namespace std;

double f(double alpha, double beta, double x){
  return sin(alpha + beta * x) + cos(alpha * x - beta);
}

int main() {
  int x0 = 0;
  int z = 10000;
  double s = 0;

  double start_time, end_time, tick;
  start_time = omp_get_wtime();
  
  #pragma omp parallel for schedule(dynamic)
  for (int x = x0; x <= z; x++) {
    s += f(x0, z/10, x/10);
  }
  double d = 0.1 * s;

  end_time = omp_get_wtime();
  tick = omp_get_wtick();

  cout « d;

  cout « "\nTime " « end_time « " - " « start_time « " = " « end_time - start_time « "\n";
  cout « "The exact time " « tick « "\n";
  system("Pause");
  return 0;
}
