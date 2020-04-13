#include <stdio.h>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
#include <omp.h>
#include "math.h"

using namespace std;

int** getArray(int from, int to) {
  int** newArray = new int*[1000];
  for (int i = 0; i < 1000; ++i) newArray[i] = new int[1000];

  #pragma omp parallel for schedule(static)
  for (int i = 0; i < 1000; i++) {
    for (int j = 0; j < 1000; j++) {
      newArray[i][j] = rand() % (to - from + 1) + from;
    }
  }
  return newArray;
}

int** multiplyArrays(int** firstArray, int** secondArray) {
  int** newArray = new int*[1000];
  for (int i = 0; i < 1000; ++i) newArray[i] = new int[1000];

  #pragma omp parallel for schedule(static)
  for (int i = 0; i < 1000; i++) {
    for (int j = 0; j < 1000; j++) {
      newArray[i][j] = firstArray[i][j] * secondArray[i][j];
    }
  }
  return newArray;
}

int getMinFromArray(int** array) {
  int min_value = 10000000;
  #pragma omp parallel for schedule(guided)
  for (int i = 0; i < 1000; i++) {
    for (int j = 0; j < 1000; j++) {
      if (min_value > array[i][j]) {
        min_value = array[i][j];
      }
    }
  }
  return min_value;
}

int main(int argc, char *argv[]) {
  double start_time, end_time, delta_time;
  double max_time = 0;
  double min_time = 100;
  double min_value = 0;

  for (int i = 0; i < 1000; i++) {
    start_time = omp_get_wtime();
    int** firstArray = getArray(1000, 3000);
    int** secondArray = getArray(500, 15000);
    int** thirdArray = multiplyArrays(firstArray, secondArray);
    int min = getMinFromArray(thirdArray);
    end_time = omp_get_wtime();
    delta_time = end_time - start_time;

    if (delta_time > max_time) max_time = delta_time;
    if (delta_time < min_time) min_time = delta_time;

    if (min > min_value) min_value = min;
  }

  printf("answer: %2.15f", min_value);
  printf("minimal time: %2.15f", min_time);
  printf("maximal time: %2.15f", max_time);
  printf("middle time: %2.15f", min_time + ((max_time - min_time)/2));

  return 0;
}
