//klasa wykorzystująca obiekt DAO do łączenia się z bazą danych, rozwiązanie podzadania 3

import java.util.List;

public class RozwiazanieZad4 {
    public static void main(String[] args) {
        KsiazkaDAO ksiazkaDB = new KsiazkaDAO();
        AutorDAO autorDB = new AutorDAO();
        AutorKsiazkaDAO autorKsiazkaDAO = new AutorKsiazkaDAO();

        Ksiazka ksiazka = new Ksiazka();
        ksiazka.setTytul("tytul ksiazki z zadania4");

        Autor autor = new Autor();
        autor.setImie("Nowy");
        autor.setNazwisko("Autor");

        //dodaje nowa ksiazke i pobieram ostatnio dodana ksiazke metoda getLastBook
        System.out.println("Dodaje nowa ksiazke");
        ksiazkaDB.newBook(ksiazka);
        ksiazka = ksiazkaDB.getLastBook();
        System.out.println("Dodano nowa ksiazke: ");
        System.out.println(ksiazka.toString());

        //dodaje nowego autora i pobieram ostatnio dodanego autora metoda getLastAutor
        System.out.println("Dodaje nowego autora");
        autorDB.newAutor(autor);
        autor = autorDB.getLastAutor();
        System.out.println("Dodano nowego autora: ");
        System.out.println(autor.toString());

        //tworzymy powiazanie miedzy wczesniej dodanymi obiektami w bazie danych
        autorKsiazkaDAO.newAutorKsiazka(autor, ksiazka);

        //Znajdujemy wszystkie ksiazki przykladowego autora i wypisujemy do konsoli
        Autor szukanyAutor = autorDB.findById(1);
        List<Ksiazka> ksiazkiAutora = autorDB.findAllBooksAutors(szukanyAutor);
        System.out.println("Wszystkie ksiazki " + szukanyAutor.getImie() + " " + szukanyAutor.getNazwisko() + ":");
        for (Ksiazka ksiazkaautora : ksiazkiAutora) {
            System.out.println(ksiazkaautora.toString());
        }

        //sprawdzamy czy Josua Bloch jest autorem Java. Techniki zaawansowane.
        Autor josuaBlooh = autorDB.findById(1); //pobieramy autora Josua Blood ma id 1
        String tytulszukany = "Java. Techniki zaawansowane.";
        boolean wynikSprawdzania = autorDB.checkAutorBooks(josuaBlooh, tytulszukany);
        if (wynikSprawdzania) {
            System.out.println(josuaBlooh.getImie() + " " + josuaBlooh.getNazwisko() + " jest autorem ksiazki "+tytulszukany);
        }else{
            System.out.println(josuaBlooh.getImie() + " " + josuaBlooh.getNazwisko() + " nie jest autorem ksiazki "+tytulszukany);
        }
        //sprawdzamy czy Cay S. Horstmann jest autorem Java. Techniki zaawansowane.
        Autor cayHorstmann = autorDB.findById(2); //pobieramy autora Cay S. Horstmann ma id 1
        boolean wynikSprawdzania2 = autorDB.checkAutorBooks(cayHorstmann, tytulszukany);
        if (wynikSprawdzania2) {
            System.out.println(cayHorstmann.getImie() + " " + cayHorstmann.getNazwisko() + " jest autorem ksiazki "+tytulszukany);
        }else{
            System.out.println(cayHorstmann.getImie() + " " + cayHorstmann.getNazwisko() + " nie jest autorem ksiazki "+tytulszukany);
        }
    }
}
