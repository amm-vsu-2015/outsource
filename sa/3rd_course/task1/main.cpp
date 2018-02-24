#include <iostream>
#include <ctime>
// #include <windows>

using namespace std;
class Date {

  // Конструктор по умолчанию, устанавливает текущую дату

private:
  int dd;
  int mm;
  int yy;

  public:

    Date() {
      time_t seconds = time(NULL);
      tm timeinfo = * localtime(&seconds);
      dd = timeinfo.tm_mday;
      mm = timeinfo.tm_mon + 1;
      yy = timeinfo.tm_year + 1900;
    }

  // Конструктор с параметрами
  Date(int DD, int MM, int YY) {
    // Нужно проверить, корректна ли дата?
    if (YY < 0) {
      std::cout << "Год не может быть отрицательным!" << std::endl;
      std::cout << "Во избежание ошибки установлен 2000 год!" << std::endl;
      YY = 2000;
    }

    if ((MM < 1) | (MM > 12)) {
      std::cout << "Неверно задан месяц! " << std::endl;
      std::cout << "Во избежание ошибки установлен январь!" << std::endl;
      MM = 1;
    }

    if ((DD < 1) | (DD > getNumberOfDays(MM, YY))) { // Вызываем метод, возвращающий количество дней в месяце
      std::cout << "Неверно задано число месяца! " << std::endl;
      std::cout << "Во избежание ошибки установлено 1 число" << std::endl;
      DD = 1;
    }

    dd = DD;
    mm = MM;
    yy = YY;
  }

  void isLeap() { // проверка равенства числа и месяца
    if ((1 - (yy % 4 + 2) % (yy % 4 + 1)) * ((yy % 100 + 2) % (yy % 100 + 1)) + (1 - (yy % 400 + 2) % (yy % 400 + 1))) {
      cout << "Високосный год: " << yy << endl;
    } else {
      cout << "Невисокосный год: " << yy << endl;
    }
  }

  void increaseDays(int days_count) {
    int daysOfCurrentMonth = getNumberOfDays(mm, yy);

    if ((dd + days_count) > daysOfCurrentMonth) {
      dd += (days_count - daysOfCurrentMonth);
      mm++;
    } else {
      dd += days_count;
    }

    if (mm > 12) {
      // если месяц больше 12, то сделать его 1 и увеличить год
      yy++; mm = 1;
    }
  }

  //Метод получения информации о Дате - конвертация в строку

  string toString() {
    return (std::to_string(dd) + "." + std::to_string(mm) + "." + std::to_string(yy));
  }

  protected:

    // Метод вычисления количества дней в месяце MM для года YY
    int getNumberOfDays(int MM, int YY) {
      int leap = (1 - (YY % 4 + 2) % (YY % 4 + 1)) * ((YY % 100 + 2) % (YY % 100 + 1)) + (1 - (YY % 400 + 2) % (YY % 400 + 1));
      return 28 + ((MM + (MM / 8)) % 2) + 2 % MM + ((1 + leap) / MM) + (1 / MM) - (leap / MM);
    }

};

int main() {
  setlocale(LC_ALL, "");

  std::cout << "Введите день, месяц и год через пробел:\n";

  int DD, MM, YY;
  std::cin >> DD >> MM >> YY;

  Date myDate = Date(DD, MM, YY);
  std::cout << "Вы ввели: " << myDate.toString() << std::endl;

  myDate.isLeap();
  myDate.increaseDays(5);

  std::cout << "Прибавили 5 дней: " << myDate.toString()  << std::endl;


  return 0;
}
