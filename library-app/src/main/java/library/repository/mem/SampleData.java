package library.repository.mem;

import library.model.Library;
import library.model.Writer;
import library.model.Book;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<Library> libraries = new ArrayList<>();

    static List<Writer> writers = new ArrayList<>();

    static List<Book> books = new ArrayList<>();

    static {

        Writer martin = new Writer(1, "George R. R.", "Martin");
        Writer nisioisin = new Writer(2, "Nisio", "Isin");
        Writer king = new Writer(3, "Stephen", "King");
        Writer rowling = new Writer(4, "J.K.", "Rowling");

        Book gameOfThrones = new Book(1, "A Game of Thrones", "https://upload.wikimedia.org/wikipedia/en/9/93/AGameOfThrones.jpg", martin, (float) 4.1);
        Book stormOfSwords = new Book(2, "A Storm of Swords", "https://upload.wikimedia.org/wikipedia/en/2/24/AStormOfSwords.jpg", martin, (float) 4.7);

        Book bakemonogatari = new Book(3, "Bakemonogatari", "https://upload.wikimedia.org/wikipedia/en/4/4a/Bakemonogatari_Up.png", nisioisin, (float) 4.6);
        Book katanagatari = new Book(4, "Katanagatari", "https://upload.wikimedia.org/wikipedia/en/a/a9/Katanagatari_volume_one.jpg", nisioisin, (float) 4.0);

        Book shining = new Book(5, "The Shining", "https://upload.wikimedia.org/wikipedia/commons/0/09/The_Shining_%281977%29_front_cover%2C_first_edition.jpg", king, (float) 4.8);
        Book it = new Book(6, "It", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/It_%281986%29_front_cover%2C_first_edition.jpg/800px-It_%281986%29_front_cover%2C_first_edition.jpg", king, (float) 4.7);

        Book philosopherStone = new Book(7, "Harry Potter and the Philosopher's Stone", "https://upload.wikimedia.org/wikipedia/en/6/6b/Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg", rowling, (float) 4.2);
        Book prisonerOfAzkaban = new Book(8, "Harry Potter and the Prisoner of Azkaban", "https://ecsmedia.pl/c/noc-listopadowa-b-iext64788740.jpg", rowling, (float) 4.4);

        bind(gameOfThrones, martin);
        bind(stormOfSwords, martin);

        bind(bakemonogatari, nisioisin);
        bind(katanagatari, nisioisin);

        bind(shining, king);
        bind(it, king);

        bind(philosopherStone, rowling);
        bind(prisonerOfAzkaban, rowling);

        Library britishLibrary = new Library(1, "British Library", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/BritishLibrary.svg/800px-BritishLibrary.svg.png");
        Library libraryOfCongress = new Library(2, "Library of Congress", "https://upload.wikimedia.org/wikipedia/commons/b/be/Logo_of_the_United_States_Library_of_Congress.svg");
        Library dietLibrary = new Library(4, "National Diet Library", "https://www.ndl.go.jp/default/images/logo.png");
        Library bn = new Library(3, "Biblioteka Narodowa", "https://upload.wikimedia.org/wikimedia/pl/7/7a/Biblioteka_Narodowa.jpg");

        bind(britishLibrary, prisonerOfAzkaban);
        bind(britishLibrary, gameOfThrones);

        bind(libraryOfCongress, stormOfSwords);
        bind(libraryOfCongress, shining);

        bind(dietLibrary, bakemonogatari);
        bind(dietLibrary, katanagatari);
        bind(dietLibrary, philosopherStone);

        bind(bn, stormOfSwords);
        bind(bn, it);
        bind(bn, prisonerOfAzkaban);
        bind(bn, gameOfThrones);

        writers.add(martin);
        writers.add(nisioisin);
        writers.add(king);
        writers.add(rowling);

        books.add(gameOfThrones);
        books.add(stormOfSwords);
        books.add(bakemonogatari);
        books.add(katanagatari);
        books.add(shining);
        books.add(it);
        books.add(philosopherStone);
        books.add(prisonerOfAzkaban);

        libraries.add(britishLibrary);
        libraries.add(libraryOfCongress);
        libraries.add(dietLibrary);
        libraries.add(bn);
    }

    private static void bind(Library c, Book m) {
        c.addBook(m);
        m.addLibrary(c);
    }

    private static void bind(Book m, Writer d) {
        d.addBook(m);
        m.setWriter(d);
    }

}
