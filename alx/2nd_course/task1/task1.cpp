
/**

	Входные данные для проверки:

	Для 10 чисел:

	6 7 2 1 120 3 6 672 74 5
	6 2 1 2 4 6 78 672 19 3

	=> min: 5.



*/

#include <iostream>
#include <cmath>
using namespace std;



int main()
{

	setlocale(LC_ALL, "Russian");

	int const COUNT = 30;
	int x[COUNT], y[COUNT];

	cout << "Введите значения элементов первой последовательности х: ";

	for (int i = 0; i < COUNT; i++) {
		cin >> x[i];
	}

	cout << "Введите значения элементов второй последовательности y: ";
	for (int i = 0; i < COUNT; i++) {
		cin >> y[i];
	}

	int min = INT_MAX;

	for (int i = 0, k = 0; i < COUNT; i++) {
		k = 0;
		while ((k < COUNT) && (x[i] != y[k])) k++;

		if ((k >= COUNT) && (min > x[i])) {
			min = x[i];
		}
	}

	std::cout << "min: " << min << '\n';
	int stop;
	cin >> stop;
  return 0;
}
