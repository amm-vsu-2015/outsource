#include <iostream>
//#include <stdafx>
#include <cmath>
#include "string"
#include "sstream"
#include "omp.h"

int x = 10;
int n = 5;

double f = 1 + n * x;

void staticomp()
{
  int res = 1;
  double sum = 0;

  #pragma omp for schedule (static, 6)

  for (int i = 2; i <= n; i++)
  {
    res = res * n;
    sum = sum + (n * (n - 1) * pow(x, n)) / res;
  }

  sum += f;
  std::cout << "Сумма: " << sum << "\n";
}

void dynamicomp()
{
  int res = 1;
  double sum = 0;

  #pragma omp for schedule (dynamic, 6)
  for (int i = 2; i <= n; i++)
  {
    res = res * n;
    sum = sum + (n * (n - 1) * pow(x, n)) / res;
  }

  sum += f;
  std::cout << "Сумма: " << sum << "\n";
}

void guidedomp()
{
  int res = 1;
  double sum = 0;

  #pragma omp for schedule (guided, 6)
  for (int i = 2; i <= n; i++)
  {
    res = res * n;
    sum = sum + (n * (n - 1) * pow(x, n)) / res;
  }
  sum += f;
  std::cout << "Сумма: " << sum << "\n";
}

int main()
{
  double start_time, end_time;
  double time, time1, time2, time3;
  int x;
  double sum = 0;

  start_time = omp_get_wtime();

  int res = 1;

  for (int i = 2; i <= n; i++)
  {
    res = res * n;
    sum = sum + (n * (n - 1) * pow(x, n)) / res;
  }

  std::cout << "Сумма: " << sum << "\n";

  end_time = omp_get_wtime();
  time = end_time - start_time;

  start_time = omp_get_wtime();

  #pragma omp parallel private(x)
  {
    staticomp();
    end_time = omp_get_wtime();
    time1 = end_time - start_time;

    dynamicomp();
    end_time = omp_get_wtime();
    time2 = end_time - start_time;

    guidedomp();
    end_time = omp_get_wtime();
    time3 = end_time - start_time;
}

  std::cout << "Время на замер времени непараллельного " << time << "\n";
  std::cout << "Время на замер времени static " << time1 << "\n";
  std::cout << "Время на замер времени dynamic " << time2 << "\n";
  std::cout << "Время на замер времени guided " << time3 << "\n";

  // system ("Pause");
  return 0;
}
