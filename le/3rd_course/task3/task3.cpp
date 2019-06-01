
/**

  Задача: 7.
	Класс-родитель: Круг с возможностью подсчета площади и методом кратного увеличения.
  Класс-потомок: Внутренний круг, создающий кольцо и переопределяющий метод площади и увеличения.

  Вывести информацию о круге и кольце.
  Увеличить их в 1.5 раза и вывести информацию снова.

	Входные данные для проверки:
	-> Circle(3)
  -> Ring(4, 3)

	Проверить можно на вольфраме.

*/

#include <iostream>
#include <math.h>
#include <iomanip>
#include <sstream>

using namespace std;

class Circle {
protected:
  double radius;

public:
  Circle (double rad) {
    radius = rad;
  }

  virtual double area() {
    return (M_PI * pow(radius, 2));
  }

  virtual void increase(double value) {
    radius *= abs(value);
  }

  string information() {
    ostringstream stream;
    stream << fixed << setprecision(2) << radius;
    string radius_str = stream.str();

    return ("Area is " + to_string(area()) + " with radius " + radius_str);
  }
};

class Ring : public Circle {
private:
  double inner_radius;

public:
  Ring(int outer_rad, int inner_rad) : Circle(outer_rad) {
    inner_radius = inner_rad;
  }

  virtual double area() {
    double outer_area = Circle::area();
    double inner_area = (M_PI * pow(inner_radius, 2));
    return (outer_area - inner_area);
  }

  virtual void increase(double value) {
    Circle::increase(value);
    inner_radius *= value;
  }
};

int main() {
  setlocale(LC_ALL, "Russian");
  double outter_radius, inner_radius, radius, increase;

  // Circle
  cout << endl << "[ Конфигурация окружности ]" << endl << endl;
  cout << "Введите радиус: ";
  cin >> radius;

  Circle* circle = new Circle(radius);
  cout << "Информация о созданной окружности: " << circle->information() << endl << endl;

  cout << "Введите значение мультипликатора (во сколько раз увеличить радиус окружности): ";
  cin >> increase;

  circle->increase(increase);
  cout << "Информация об окружности после увеличения: " << circle->information() << endl;

  // Ring
  cout << endl << "[ Конфигурация кольца ]" << endl;

  outter_radius = radius;

  cout << "Внешний радиус кольца равен: " << outter_radius << endl;
  cout << "Введите внутренний радиус кольца:  ";
  cin >> inner_radius;

  outter_radius = abs(outter_radius);
  inner_radius = abs(inner_radius);

  if (inner_radius > outter_radius) {
    cout << endl << "Смена внешнего и внутреннего радиуса..." << endl;
    swap(inner_radius, outter_radius);
  }

  cout << endl;
  cout << "Внешний радиус кольца равен: " << outter_radius << endl;
  cout << "Внутренний радиус кольца равен: " << inner_radius << endl;

  Ring* ring = new Ring(outter_radius, inner_radius);

  cout << endl;
  cout << "Информация о созданном кольце: " << ring->information() << endl;
  cout << "Введите значение мультипликатора (во сколько раз увеличить радиус кольца): ";
  cin >> increase;

  ring->increase(increase);

  cout << "Информация о созданном кольце после увеличения: " << ring->information() << endl;

  int stop;
  cin >> stop;
  return 0;
}
