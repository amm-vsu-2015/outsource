
/**

	Класс-родитель: Фильм, расчитывающий стоимость билета в зависимости от параметров
  Класс-потомок: Мультфильм, переопределяющий расчеты стоимости

  Создать два фильма и мультик.

	Входные данные для проверки:
	-> вводятся из программы

	Проверить можно на вольфраме.

*/

#include <iostream>
#include <math.h>
#include <iomanip>
#include <sstream>
using namespace std;

class Film {
protected:
	string name;
	string producer;
	int minutes;
	int artists;

public:
	Film(string NAME, string PRODUCER, int MINUTES, int ARTISTS) {
		name = NAME;
		producer = PRODUCER;
		minutes = MINUTES;
		artists = ARTISTS;
	}

	virtual double price() {
		double length_comission = minutes * 20;
		double artist_comission = artists * 30;

		double price = (length_comission + artist_comission);

		if (producer == "Steven Spielberg" || producer == "James Cameron") {
			price *= 2;
		}

		return price;
	}

	string information() {
		std::ostringstream stream;
    stream << std::fixed << std::setprecision(2) << price();
    std::string price_str = stream.str();

		string result = "Film: " + name + "\n";

		result += "Produced by " + producer + "\n";
		result += "With the participation of " + to_string(artists) + " artists.\n";
		result += "Budget is $" + price_str;

    return result;
	}
};

class Multfilm : public Film {
public:
	Multfilm(string NAME, string PRODUCER, int MINUTES, int ARTISTS) : Film(NAME, PRODUCER, MINUTES, ARTISTS) { }

	virtual double price() {
		double length_comission = minutes * 25;
		double artist_comission = artists * 10;
		double price = (length_comission + artist_comission);

		return price;
	}
};

int main()
{
	setlocale(LC_ALL, "Russian");

	Film* first_film = new Film("Interstellar", "Christopher Nolan", 134, 64);
	std::cout << first_film->information() << '\n' << '\n';

	Film* second_film = new Film("Men in Black", "Steven Spielberg", 146, 121);
	std::cout << second_film->information() << '\n' << '\n';

	Film* first_mult = new Multfilm("Frozen", "Peter Vecho", 146, 121);
	std::cout << first_mult->information() << '\n' << '\n';

	int stop;
	cin >> stop;
  return 0;
}
