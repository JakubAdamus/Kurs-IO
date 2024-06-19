package library.service;

import library.model.Library;
import library.model.Writer;
import library.model.Book;

import java.util.List;

public interface BookService {


    List<Book> getAllBooks();

    List<Book> getBooksByWriter(Writer w);

    List<Book> getBooksInLibrary(Library l);

    Book getBookById(int id);

    Book addBook(Book m);


    List<Writer> getAllWriters();

    Writer getWriterById(int id);

    Writer addWriter(Writer d);
}
