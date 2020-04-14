#include <stdio.h>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
#include <omp.h>
#include "math.h"

using namespace std;

int** getArray(int from, int to) {
  int** newArray = new int*[100];
  for (int i = 0; i < 100; ++i) newArray[i] = new int[100];

  #pragma omp parallel for schedule(dynamic)
  for (int i = 0; i < 100; i++) {
    for (int j = 0; j < 100; j++) {
      newArray[i][j] = rand() % (to - from + 1) + from;
    }
  }
  return newArray;
}

void removeArray(int** array) {
  for (int i = 0; i < 100; ++i) {
    delete[] array[i];
    array[i] = NULL;
  }

  delete[] array;
  array = NULL;
}

int** maxValuedArrayFrom(int** firstArray, int** secondArray, int** thirdArray) {
  int** newArray = new int*[100];
  for (int i = 0; i < 100; ++i) newArray[i] = new int[100];

  #pragma omp parallel for schedule(dynamic)
  for (int i = 0; i < 100; i++) {
    for (int j = 0; j < 100; j++) {
      newArray[i][j] = max(firstArray[i][j], max(secondArray[i][j], thirdArray[i][j]));
    }
  }
  return newArray;
}

int getMinFromArray(int** array) {
  int min_value;
  #pragma omp parallel for schedule(dynamic)
  for (int i = 0; i < 100; i++) {
    for (int j = 0; j < 100; j++) {
      min_value = min(min_value, array[i][j]);
    }
  }
  return min_value;
}

int main(int argc, char *argv[]) {
  double start_time, end_time, delta_time;
  double max_time = 0;
  double min_time = 100;
  int min_value = 0;

  for (int i = 0; i < 1000; i++) {
    start_time = omp_get_wtime();
    int** firstArray = getArray(1, 10000);
    int** secondArray = getArray(1, 1000);
    int** thirdArray = getArray(1, 5000);
    int** resultArray = maxValuedArrayFrom(firstArray, secondArray, thirdArray);
    int min = getMinFromArray(resultArray);
    end_time = omp_get_wtime();
    delta_time = end_time - start_time;

    if (delta_time > max_time) max_time = delta_time;
    if (delta_time < min_time) min_time = delta_time;

    if (min > min_value) min_value = min;

    removeArray(firstArray);
    removeArray(secondArray);
    removeArray(thirdArray);
    removeArray(resultArray);
  }

  printf("answer: %d\n", min_value);
  printf("minimal time: %2.15f\n", min_time);
  printf("maximal time: %2.15f\n", max_time);
  printf("middle time: %2.15f\n", min_time + ((max_time - min_time)/2));

  return 0;
}
