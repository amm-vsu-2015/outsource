
/**

	Задача: 14.
	Класс-родитель: Книга, включающая параметры имени, года и автора.
  Класс-потомок: Магазин, определяющий цену книги (умеет уменьшать цену для книг, что более 5 лет)

*/

#include <iostream>
#include <ctime>
#include <iomanip>
#include <sstream>
#include <string.h>
// #include <windows>

using namespace std;

class Book {
protected:
  string name;
  string author;
  int year;

public:
  Book() {
    name = "noname";
    author = "unknown";
    year = 0;
  }

  Book(string NAME, string AUTHOR, int YEAR) {
    if (YEAR < 0) {
      cout << "Year can't be negative!" << endl;
      cout << "Set default year as 2000!" << endl;
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
  Store(string NAME, string AUTHOR, int YEAR, double PRICE): Book(NAME, AUTHOR, YEAR) {
    price = PRICE;
  }

  string getBook() {
    ostringstream stream;
    stream << fixed << setprecision(2) << price;
    string price_str = stream.str();
    return (name + " from " + author + ", published in " + to_string(year) + " year, price equals $" + price_str);
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

  cout << "How much books to add? ";
  cin >> books_count;

  Store* books[books_count-1];

  while (books_count--) {
    cout << "Write information about new book." << endl;

    string name, author;
    int year, price;

    cout << "name: ";
    cin.get();
    getline(cin, name);

    cout << "author: ";
    getline(cin, author);

    cout << "published in: ";
    cin >> year;

    cout << "price: ";
    cin >> price;

    books[idx_books++] = new Store(name, author, year, price);
    cout << endl;
  }

  cout << endl << "List of books: " << endl;

  for (int idx_i = 0; idx_i < idx_books; idx_i++) {
    if ((currentYear - books[idx_i]->getYear()) >= 5) {
      books[idx_i]->updatePrice();
    }
  }


  for (int idx_i = 0; idx_i < idx_books; idx_i++) {
    cout << idx_i + 1 << ". " <<  books[idx_i]->getBook() << '\n';
  }

  return 0;
}
