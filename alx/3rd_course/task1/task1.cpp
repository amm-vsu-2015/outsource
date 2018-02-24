#include <math.h>
#include <iostream>

using namespace std;

class Rectangle {

private:
  int x1, y1, x2, y2;

public:
  Rectangle(int X1, int Y1, int X2, int Y2) {
    x1 = X1; y1 = Y1;
    x2 = X2; y2 = Y2;
  }

  int square() {
    int a = (int) abs(x1 - x2);
    int b = (int) abs(y1 - y2);

    return (a * b);
  }
};

int main()
{
  setlocale(LC_ALL, "Russian");

  int x1, y1, x2, y2;

  cout << "Введите координаты прямоугольника: (x1, y1, x2, y2)\n";
  cin >> x1 >> y1 >> x2 >> y2;

  Rectangle* rect = new Rectangle(x1, y1, x2, y2);

  cout <<  "Площадь равна: " << rect->square() << '\n';

  cin.get();
  return 0;
}
