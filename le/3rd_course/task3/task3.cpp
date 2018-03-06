
/**

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
    radius *= value;
  }

  string information() {
    std::ostringstream stream;
    stream << std::fixed << std::setprecision(2) << radius;
    std::string radius_str = stream.str();

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

  double outter_radius = 4, inner_radius = 3, increase = 1.5;

  Circle* circle = new Circle(outter_radius);
  Ring* ring = new Ring(outter_radius, inner_radius);

  // std::cout << '\n' << "set multiply value: " << increase << '\n';

  // Circle
  std::cout << "[ Circle ]";
  std::cout << '\n' << "information about circle: " << circle->information();
  circle->increase(increase);
  std::cout << '\n' << "increase in " << increase << "...";
  std::cout << '\n' << "information about increased circle: " << circle->information() << '\n';


  // Ring
  std::cout << '\n' << "[ Ring ]";
  std::cout << '\n' << "information about ring:   " << ring->information();
  ring->increase(increase);
  std::cout << '\n' << "increase in " << increase << "...";
  std::cout << '\n' << "information about increased ring:   " << ring->information() << '\n';

  /*
  double outter_radius, inner_radius, increase;

  std::cout << "Write radius of outter circle: ";
  std::cin >> outter_radius;

  std::cout << "Write radius of inner circle:  ";
  std::cin >> inner_radius;

  outter_radius = abs(outter_radius);
  inner_radius = abs(inner_radius);

  if (inner_radius > outter_radius) {
    swap(inner_radius, outter_radius);
  }

  std::cout << '\n';
  std::cout << "Radius of circle: " << outter_radius << '\n';

  std::cout << '\n';
  std::cout << "Outter radius of ring: " << outter_radius << '\n';
  std::cout << "Inner radius of ring:  " << inner_radius << '\n';

  std::cout << '\n';

  Circle* circle = new Circle(outter_radius);
  Ring* ring = new Ring(outter_radius, inner_radius);

  std::cout << '\n' << "information about circle: " << circle->information();
  std::cout << '\n' << "information about ring:   " << ring->information() << '\n';

  std::cout << '\n' << "increase by: ";
  std::cin >> increase;

  increase = abs(increase);

  circle->increase(increase);
  ring->increase(increase);

  std::cout << '\n' << "information about circle: " << circle->information();
  std::cout << '\n' << "information about ring:   " << ring->information() << '\n';
  */

  int stop;
  std::cin >> stop;
  return 0;
}
