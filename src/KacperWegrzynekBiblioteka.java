import java.util.*;

class Ksiazka {
    private String tytul;
    private String autor;
    private boolean wypozyczona;

    public Ksiazka(String tytul, String autor) {
        this.tytul = tytul;
        this.autor = autor;
        this.wypozyczona = false;
    }

    public String getTytul() {
        return tytul;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isWypozyczona() {
        return wypozyczona;
    }

    public void wypozycz() {
        if (!wypozyczona) {
            wypozyczona = true;
            System.out.println("Książka została wypożyczona.");
        } else {
            System.out.println("Książka jest już wypożyczona.");
        }
    }

    public void zwroc() {
        if (wypozyczona) {
            wypozyczona = false;
            System.out.println("Książka została zwrócona.");
        } else {
            System.out.println("Książka nie była wypożyczona.");
        }
    }

    @Override
    public String toString() {
        return "Tytuł: " + tytul + ", Autor: " + autor + ", Status: " + (wypozyczona ? "Niedostępna" : "Dostępna");
    }
}

class Biblioteka {
    private Map<Integer, Ksiazka> ksiazki;
    private int licznikIdKsiazki;

    public Biblioteka() {
        ksiazki = new HashMap<>();
        licznikIdKsiazki = 1;
    }

    public void dodajKsiazke(String tytul, String autor) {
        ksiazki.put(licznikIdKsiazki++, new Ksiazka(tytul, autor));
        System.out.println("Książka została dodana.");
    }

    public void dodajLosowaKsiazke() {
        String[] tytuly = {"Cień Przeszłości", "Pod Gwiazdami Wieczności", "Ostatnia Pieśń Świtu", "Sekrety Kamiennego Ogrodu", "Labirynt Bez Końca"};
        String[] autorzy = {"Anna Kowalska", "Jakub Zieliński", "Magdalena Nowak", "Piotr Kamiński", "Katarzyna Wiśniewska"};
        Random random = new Random();

        String losowyTytul = tytuly[random.nextInt(tytuly.length)];
        String losowyAutor = autorzy[random.nextInt(autorzy.length)];
        dodajKsiazke(losowyTytul, losowyAutor);
    }

    public void wyswietlKsiazki() {
        if (ksiazki.isEmpty()) {
            System.out.println("Brak dostępnych książek w bibliotece.");
        } else {
            for (Map.Entry<Integer, Ksiazka> wpis : ksiazki.entrySet()) {
                System.out.println(wpis.getKey() + ". " + wpis.getValue());
            }
        }
    }

    public void wypozyczKsiazke(int id) {
        Ksiazka ksiazka = ksiazki.get(id);
        if (ksiazka != null) {
            ksiazka.wypozycz();
        } else {
            System.out.println("Nieprawidłowe ID książki.");
        }
    }

    public void zwrocKsiazke(int id) {
        Ksiazka ksiazka = ksiazki.get(id);
        if (ksiazka != null) {
            ksiazka.zwroc();
        } else {
            System.out.println("Nieprawidłowe ID książki.");
        }
    }
}

public class KacperWegrzynekBiblioteka {
    public static void main(String[] args) {
        Biblioteka biblioteka = new Biblioteka();
        Scanner scanner = new Scanner(System.in);

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
                wybor = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy typ danych. Wprowadź liczbę.");
                continue;
            }

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
                    return;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }
}
