
#include <iostream>
#include <math.h>
#include <iomanip>
#include <sstream>

using namespace std;

class Triangle {
protected:
  double a, b, c;

public:
  Triangle(double A, double B, double C) {
    a = A; b = B; c = C;
  }

  virtual string information() {
    return "info: perimeter = " + to_string(perimeter()) + " and area = " + to_string(area()) + ".";
  }

  virtual double perimeter() {
    return a + b + c;
  }

  virtual double area() {
    double semi_p = perimeter() / 2.0;
    return sqrt(semi_p * (semi_p - a) * (semi_p - b) * (semi_p - c));
  }
};

class Rectangle: public Triangle {
private:
  double diagE, diagF;

protected:
  double d;

public:
  Rectangle(double A, double B, double C, double D, double E, double F): Triangle(A, B, C) {
    d = D; diagE = E; diagF = F;
  }

  virtual double perimeter() {
    return Triangle::perimeter() + d;
  }

  virtual double area() {
    return sqrt((4 * pow(diagE, 2) * pow(diagF, 2) - pow((pow(b, 2) + pow(d, 2) - pow(a, 2) - pow(c, 2)), 2)) / 16);
  }

};

int main() {
  setlocale(LC_ALL, "Russian");

  double a = 7;
  double b = 12;
  double c = 6;
  double d = 14;

  Triangle* triangle = new Triangle(a, b, c);

  double diagE = sqrt(pow(a, 2) + pow(b, 2));
  double diagF = sqrt(pow(c, 2) + pow(d, 2));
  Triangle* rect = new Rectangle(a, b, c, d, diagE, diagF);

  std::cout << "triangle:" << '\n';
  std::cout << triangle->information() << '\n';

  std::cout << "rectangle:" << '\n';
  std::cout << rect->information() << '\n';

  int stop;
  cin >> stop;
  return 0;
}
