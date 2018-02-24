#include <iostream>
#include <ctime>
#include <iomanip>
#include <sstream>
// #include <std::string.h>
// #include <windows>

using namespace std;

class Book {
protected:
  std::string name;
  std::string author;
  int year;

public:
  Book() {
    name = "noname";
    author = "unknown";
    year = 0;
  }

  Book(std::string NAME, std::string AUTHOR, int YEAR) {
    if (YEAR < 0) {
      std::cout << "Год не может быть отрицательным!" << std::endl;
      std::cout << "Во избежание ошибки установлен 2000 год!" << std::endl;
      YEAR = 2000;
    }

    name = NAME;
    author = AUTHOR;
    year = YEAR;
  }
};

class Store: protected Book {
protected:
  double price;

public:
  Store(std::string NAME, std::string AUTHOR, int YEAR, double PRICE): Book(NAME, AUTHOR, YEAR) {
    price = PRICE;
  }

  string getBook() {
    std::ostringstream stream;
    stream << std::fixed << std::setprecision(2) << price;
    std::string price_str = stream.str();
    return (name + " from " + author + ", published in " + to_string(year) + " for $" + price_str);
  }

  int getYear() {
    return year;
  }

  void updatePrice() {
      price = price * 0.8;
  }
};

int main() {
  setlocale(LC_ALL, "");
  time_t seconds = time(NULL);
  tm timeinfo = * localtime(&seconds);

  int currentYear = timeinfo.tm_year + 1900;
  int books_count = 0;
  int idx_books = 0;

  std::cout << "Сколько книг хотите добавить? ";
  std::cin >> books_count;

  Store* books[books_count-1];

  while (books_count--) {
    std::cout << "Введите информацию о новой книге." << endl;

    string name, author;
    int year, price;

    std::cout << "Название: ";
    std::cin >> name;

    std::cout << "Автор: ";
    std::cin >> author;

    std::cout << "Год издания: ";
    std::cin >> year;

    std::cout << "Цена: ";
    std::cin >> price;

    books[idx_books++] = new Store(name, author, year, price);
  }

  for (int idx_i = 0; idx_i < idx_books; idx_i++) {
    if ((currentYear - books[idx_i]->getYear()) >= 5) {
      books[idx_i]->updatePrice();
    }
  }


  for (int idx_i = 0; idx_i < idx_books; idx_i++) {
    std::cout << books[idx_i]->getBook() << '\n';
  }

  return 0;
}
