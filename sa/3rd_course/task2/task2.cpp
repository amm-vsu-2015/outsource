
/**

	Определить площадь треугольника, образованного вектором и точкой.
	Класс-родитель: вектор с (x1, y1, x2, y2)
	Класс-потомок: треугольник, включающий вектор и точку (x3, y3)

	Входные данные для проверки:
	-> (x1, y1, x2, y2) 1 1 4 4
	   (x3, y3) 2 3

	Проверить можно на вольфраме (вводить с точностью до тысячных)

*/

#include <iostream>
#include <math.h>
using namespace std;

class Vector {
protected:
	int x1, y1, x2, y2;

public:
	Vector(int X1, int Y1, int X2, int Y2) {
		x1 = X1; y1 = Y1;
		x2 = X2; y2 = Y2;
	}
};

class Triangle : protected Vector {
private:
	int x3, y3;

	double findLineByCoordinates(int x1, int y1, int x2, int y2) {
		int baseline_x = abs(x1 - x2);
		int baseline_y = abs(y1 - y2);

		double vector_length = sqrt(pow(baseline_x, 2) + pow(baseline_y, 2));

		std::cout << "Length of line: " << vector_length << '\n';
		return vector_length;
	}

public:
	Triangle(int X1, int Y1, int X2, int Y2, int X3, int Y3) : Vector(X1, Y1, X2, Y2) {
		x3 = X3; y3 = Y3;
	}

	void area() {
		double line_a = findLineByCoordinates(x1, y1, x2, y2);
		double line_b = findLineByCoordinates(x1, y1, x3, y3);
		double line_c = findLineByCoordinates(x2, y2, x3, y3);

		double small_s = (line_a + line_b + line_c) / 2;
		double area = sqrt(small_s * (small_s - line_a) * (small_s - line_b) * (small_s - line_c));
		std::cout << "Area: " << area << '\n';
	}
};

int main()
{
	setlocale(LC_ALL, "Russian");

	int x1, y1, x2, y2, x3, y3;

	std::cout << "Write vector and point coordinates: " << '\n';

	std::cout << "for vector: (x1, y1, x2, y2) ";
	std::cin >> x1 >> y1 >> x2 >> y2;

	std::cout << "for point: (x, y) ";
	std::cin >> x3 >> y3;

	Triangle* triangle = new Triangle(x1, y1, x2, y2, x3, y3);

	triangle->area();

	int stop;
	cin >> stop;
  return 0;
}
