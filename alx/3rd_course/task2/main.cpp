#include <iostream>
#include <ctime>
// #include <windows>

using namespace std;

class Date {
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
    dd = DD; mm = MM; yy = YY;
  }

  int getYear() {
    return yy;
  }

  Date* getDate() {
    return new Date(dd, mm, yy);
  }

  int daysFromDate(Date* fromDate) {
    if (fromDate->mm < this->mm || (fromDate->mm == this->mm && fromDate->dd <= this->dd)) {
      int daysCount = (12 - (this->mm - fromDate->mm)) * 30;

      if (fromDate->dd < this->dd) {
        daysCount += 30 - this->dd + fromDate->dd;
      } else {
        daysCount += fromDate->dd - this->dd;
      }

      return daysCount;
    } else {
      return (fromDate->mm - this->mm) * 30 + (fromDate->dd - this->dd);
    }
  }

  virtual string toString() {
    return (to_string(dd) + "." + to_string(mm) + "." + to_string(yy));
  }
};

class Friend : public Date {
private:
  string fullname;
  string phone;

public:
  Friend(string FULLNAME, string PHONE, int DD, int MM, int YY): Date(DD, MM, YY) {
    fullname = FULLNAME;
    phone = PHONE;
  }

  virtual string toString() {
    return (fullname + " родился " + Date::toString() + ".");
  }

};

int main() {
  setlocale(LC_ALL, "");
  time_t seconds = time(NULL);
  tm timeinfo = * localtime(&seconds);

  int friends_count = 0;
  int idx_friends = 0;

  cout << "Сколько друзей хотите добавить? ";
  cin >> friends_count;

  Friend* friends[friends_count-1];

  while (friends_count--) {
    cout << "Введите информацию о новом друге." << endl;

    string fullname, phone;
    int day, month, year;

    cout << "Полное имя: ";
    cin.get();
    getline(cin, fullname);

    cout << "Телефон: ";
    getline(cin, phone);

    cout << "День рождения (через пробел ДД ММ ГГГГ): ";
    cin >> day >> month >> year;

    friends[idx_friends++] = new Friend(fullname, phone, day, month, year);
    cout << endl;
  }

  cout << endl << "Список друзей: " << endl;

  for (int idx_i = 0; idx_i < idx_friends; idx_i++) {
    cout << idx_i + 1 << ". " << friends[idx_i]->toString() << endl;

    Friend* nearestBirthday = 0;

    if (idx_friends > 1) {
      if (idx_i + 1 == idx_friends) {
        nearestBirthday = friends[0];
      } else {
        nearestBirthday = friends[idx_i + 1];
      }

      cout << "   До ближайшего дня рождения: " << friends[idx_i]->daysFromDate(nearestBirthday->getDate()) << " дней."<< endl;
    }
  }

  return 0;
}
