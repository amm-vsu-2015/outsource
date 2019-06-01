
/**

	Задача: 16.
	Класс-родитель: Работник, содержащий базовую информацию о человеке (имя, оклад, год рождения).
  Класс-потомок: Работник компании. наследует все свойства и добавляет поле "должность"

*/

#include <iostream>
#include <ctime>
#include <iomanip>
#include <sstream>
#include <string.h>
// #include <windows>

using namespace std;

class Employer {
protected:
  string name;
  double salary;
  int year;

public:
  Employer(string NAME, double SALARY, int YEAR) {
    if (SALARY < 0) {
      cout << "Оклад не может быть отрицательным!" << endl;
      exit(0);
    }

    if (YEAR < 0) {
      cout << "Год рождения не может быть отрицательным!" << endl;
      exit(0);
    }

    name = NAME;
    salary = SALARY;
    year = YEAR;
  }
};

class CompanyEmployer: protected Employer {
protected:
  string position;

public:
  CompanyEmployer(string NAME, double SALARY, int YEAR, string POSITION): Employer(NAME, SALARY, YEAR) {
    position = POSITION;
  }

  string getEmployer() {
    ostringstream stream;
    stream << fixed << setprecision(2) << salary;
    string salary_str = stream.str();
    return (name + " от " + to_string(year) + " г.р. работает на позиции " + position + ", оклад " + salary_str + " руб.");
  }

  string getPosition() {
    return position;
  }

  void updatePrice() {
      salary = salary * 1.2;
  }
};

int main() {
  setlocale(LC_ALL, "");
  time_t seconds = time(NULL);
  tm timeinfo = * localtime(&seconds);

  int employer_count = 0;
  int idx_employer = 0;

  cout << "Сколько работников хотите добавить? ";
  cin >> employer_count;

  CompanyEmployer* employers[employer_count-1];

  while (employer_count--) {
    cout << "Введите информацию о новом работнике." << endl;

    string name, position;
    double salary;
    int year;

    cout << "Фамилия: ";
    cin.get();
    getline(cin, name);

    cout << "Позиция: ";
    getline(cin, position);

    cout << "Год рождения: ";
    cin >> year;

    cout << "Оклад: ";
    cin >> salary;

    employers[idx_employer++] = new CompanyEmployer(name, salary, year, position);
    cout << endl;
  }

  cout << endl << "Список работников: " << endl;

  for (int idx_i = 0; idx_i < idx_employer; idx_i++) {
    if (employers[idx_i]->getPosition() == "Программист") {
      employers[idx_i]->updatePrice();
    }
  }

  for (int idx_i = 0; idx_i < idx_employer; idx_i++) {
    cout << idx_i + 1 << ". " <<  employers[idx_i]->getEmployer() << '\n';
  }

  return 0;
}
