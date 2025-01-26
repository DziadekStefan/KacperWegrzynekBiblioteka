import java.util.*;
// Klasa reprezentująca książkę w bibliotece
class Ksiazka {
    private String tytul;  // Tytuł książki
    private String autor;  // Autor książki
    private boolean wypozyczona;  // Status wypożyczenia

    // Konstruktor inicjalizujący książkę
    public Ksiazka(String tytul, String autor) {
        this.tytul = tytul;
        this.autor = autor;
        this.wypozyczona = false;  // Domyślnie książka nie jest wypożyczona
    }

    public String getTytul() {
        return tytul;
    }

    public String getAutor() {
        return autor;
    }

    // Sprawdzenie, czy książka jest wypożyczona
    public boolean isWypozyczona() {
        return wypozyczona;
    }

    // Metoda wypożyczająca książkę
    public void wypozycz() {
        if (!wypozyczona) {
            wypozyczona = true;  // Zmiana statusu na wypożyczony
            System.out.println("Książka została wypożyczona.");
        } else {
            System.out.println("Książka jest już wypożyczona.");
        }
    }

    // Metoda zwracająca książkę
    public void zwroc() {
        if (wypozyczona) {
            wypozyczona = false;  // Zmiana statusu na dostępny
            System.out.println("Książka została zwrócona.");
        } else {
            System.out.println("Książka nie była wypożyczona.");
        }
    }

    // Nadpisanie metody toString dla czytelniejszego wyświetlania informacji o książce
    @Override
    public String toString() {
        return "Tytuł: " + tytul + ", Autor: " + autor + ", Status: " + (wypozyczona ? "Niedostępna" : "Dostępna");
    }
}

// Klasa reprezentująca bibliotekę
class Biblioteka {
    private Map<Integer, Ksiazka> ksiazki;  // Przechowuje książki z przypisanymi ID
    private int licznikIdKsiazki;  // Licznik ID książek

    // Konstruktor inicjalizujący bibliotekę
    public Biblioteka() {
        ksiazki = new HashMap<>();  // Inicjalizacja mapy na książki
        licznikIdKsiazki = 1;  // Początkowe ID książki
    }

    // Dodawanie książki do biblioteki
    public void dodajKsiazke(String tytul, String autor) {
        ksiazki.put(licznikIdKsiazki++, new Ksiazka(tytul, autor));  // Dodanie książki z unikalnym ID
        System.out.println("Książka została dodana.");
    }

    // Dodawanie losowej książki do biblioteki
    public void dodajLosowaKsiazke() {
        String[] tytuly = {"Cień Przeszłości", "Pod Gwiazdami Wieczności", "Ostatnia Pieśń Świtu", "Sekrety Kamiennego Ogrodu", "Labirynt Bez Końca"};
        String[] autorzy = {"Anna Kowalska", "Jakub Zieliński", "Magdalena Nowak", "Piotr Kamiński", "Katarzyna Wiśniewska"};
        Random random = new Random();

        // Losowanie tytułu i autora
        String losowyTytul = tytuly[random.nextInt(tytuly.length)];
        String losowyAutor = autorzy[random.nextInt(autorzy.length)];
        dodajKsiazke(losowyTytul, losowyAutor);  // Dodanie książki
    }

    // Wyświetlanie wszystkich książek w bibliotece
    public void wyswietlKsiazki() {
        if (ksiazki.isEmpty()) {
            System.out.println("Brak dostępnych książek w bibliotece.");
        } else {
            // Iteracja po mapie książek
            for (Map.Entry<Integer, Ksiazka> wpis : ksiazki.entrySet()) {
                System.out.println(wpis.getKey() + ". " + wpis.getValue());
            }
        }
    }

    // Wypożyczenie książki na podstawie ID
    public void wypozyczKsiazke(int id) {
        Ksiazka ksiazka = ksiazki.get(id);  // Pobranie książki z mapy
        if (ksiazka != null) {
            ksiazka.wypozycz();  // Wypożyczenie książki
        } else {
            System.out.println("Nieprawidłowe ID książki.");
        }
    }

    // Zwrot książki na podstawie ID
    public void zwrocKsiazke(int id) {
        Ksiazka ksiazka = ksiazki.get(id);  // Pobranie książki z mapy
        if (ksiazka != null) {
            ksiazka.zwroc();  // Zwrot książki
        } else {
            System.out.println("Nieprawidłowe ID książki.");
        }
    }
}

// Główna klasa programu
public class KacperWegrzynekBiblioteka {
    public static void main(String[] args) {
        Biblioteka biblioteka = new Biblioteka();  // Utworzenie obiektu biblioteki
        Scanner scanner = new Scanner(System.in);  // Inicjalizacja skanera do odczytu danych

        // Wyświetlenie instrukcji użytkownika
        System.out.println("Program stworzył: Kacper Węgrzynek");
        System.out.println("");
        System.out.println("Krótka instrukcja do obsługiwania programu");
        System.out.println("Po wybraniu opcji 1 można dodać książkę poprzez podanie w konsoli jej tytułu, a następnie jej autora.");
        System.out.println("Po wybraniu opcji 2 do bazy książek biblioteki zostanie dodana losowa książka.");
        System.out.println("Po wybraniu opcji 3 wyświetlona zostanie lista książek jakie są dostepne, lub niedostepne w bibliotece na ten moment");
        System.out.println("Po wybraniu opcji 4 należy wpisać numer książki, którą chce się wypozyczyć po czym jej status zmieni się na niedostępny");
        System.out.println("Po wybraniu opcji 5 należy wpisać numer książki, którą chce się zwrócić po czym jej statsu zmieni się na dostępny");
        System.out.println("Po wybraniu opcji 6 program zostanie wyłączony");
        System.out.println("");

        // Pętla główna programu
        while (true) {
            System.out.println("Program Zarządzania Biblioteką");
            System.out.println("1. Dodaj książkę");
            System.out.println("2. Dodaj losową książkę");
            System.out.println("3. Wyświetl książki");
            System.out.println("4. Wypożycz książkę");
            System.out.println("5. Zwróć książkę");
            System.out.println("6. Wyjdź");
            System.out.print("Wybierz opcję: ");

            int wybor;
            try {
                wybor = Integer.parseInt(scanner.nextLine());  // Pobranie wyboru użytkownika
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy typ danych. Wprowadź liczbę.");
                continue;
            }
            // Obsługa wyborów użytkownika
            switch (wybor) {
                case 1:
                    System.out.print("Podaj tytuł książki: ");
                    String tytul = scanner.nextLine();
                    System.out.print("Podaj autora książki: ");
                    String autor = scanner.nextLine();
                    biblioteka.dodajKsiazke(tytul, autor);
                    break;
                case 2:
                    biblioteka.dodajLosowaKsiazke();
                    break;
                case 3:
                    biblioteka.wyswietlKsiazki();
                    break;
                case 4:
                    biblioteka.wyswietlKsiazki();
                    System.out.print("Podaj numer książki do wypożyczenia: ");
                    int idWypozyczenia;
                    try {
                        idWypozyczenia = Integer.parseInt(scanner.nextLine());
                        biblioteka.wypozyczKsiazke(idWypozyczenia);
                    } catch (NumberFormatException e) {
                        System.out.println("Nieprawidłowy numer książki. Wprowadź poprawny numer.");
                    }
                    break;
                case 5:
                    biblioteka.wyswietlKsiazki();
                    System.out.print("Podaj numer książki do zwrotu: ");
                    int idZwrotu;
                    try {
                        idZwrotu = Integer.parseInt(scanner.nextLine());
                        biblioteka.zwrocKsiazke(idZwrotu);
                    } catch (NumberFormatException e) {
                        System.out.println("Nieprawidłowy numer książki. Wprowadź poprawny numer.");
                    }
                    break;
                case 6:
                    System.out.println("Zamykanie programu. Miłego dnia!");
                    scanner.close();
                    return; // Wyjście z programu
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }
}
