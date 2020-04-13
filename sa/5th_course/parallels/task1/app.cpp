#include <stdio.h>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
#include <omp.h>
#include "math.h"

using namespace std;

const double x = 0.3;
const double L = 1;
const int N = 50000;

double f(int a, int n) {
  return a * cos((n * x * M_PI)/L);
}

double* getArray(int from, int to) {
  double* newArray = new double[N];
  #pragma omp parallel for schedule(guided)
  for (int i = 0; i < N; i++) {
    newArray[i] = rand() % (to - from + 1) + from;
  }
  return newArray;
}

double summary(double* array) {
    double sum = array[0];
    #pragma omp parallel for schedule(guided) reduction(+:sum)
    for(int n = 1; n < N; n++) {
      sum += f(array[n], n);
    }

    return sum;
}

int main(int argc, char *argv[]) {
  double t_start, t_end, t_diff;

  double max_time = 0;
  double min_time = 10;

  for (int index = 0; index < 50000; index++) {
    t_start = omp_get_wtime();
    double* array = getArray(1, 50000);
    double result = summary(array);
    t_end = omp_get_wtime();
    t_diff = t_end - t_start;

    if (t_diff > max_time) max_time = t_diff;
    if (t_diff < min_time) min_time = t_diff;
  }

  printf("%2.15f\n", min_time);
  printf("%2.15f\n", max_time);
  printf("%2.15f\n", max_time - min_time);

  return 0;
}
