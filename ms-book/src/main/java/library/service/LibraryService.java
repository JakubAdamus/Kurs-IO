package library.service;

import library.model.Library;
import library.model.Book;

import java.util.List;

public interface LibraryService {
    Library getLibraryById(int id);

    List<Library> getAllLibraries();

    List<Library> getLibrariesByBook(Book m);

    List<Book> getBooksInLibrary(Library c);

    Library addLibrary(Library library);
}
