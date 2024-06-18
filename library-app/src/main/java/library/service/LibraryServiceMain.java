package library.service;


import library.model.Library;
import library.repository.BookDao;
import library.repository.LibraryDao;
import library.repository.mem.MemBookDao;
import library.repository.mem.MemLibraryDao;
import library.service.impl.LibraryServiceBean;

import java.util.List;

public class LibraryServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find libraries!");

        LibraryDao libraryDao = new MemLibraryDao();
        BookDao bookDao = new MemBookDao();

        LibraryService service = new LibraryServiceBean(libraryDao, bookDao);

        List<Library> libraries = service.getAllLibraries();
        System.out.println(libraries.size() + " libraries found:");
        libraries.forEach(System.out::println);
    }
}
