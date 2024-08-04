package library.service;


import library.model.Library;
import library.repository.BookDao;
import library.repository.LibraryDao;
import library.repository.mem.MemBookDao;
import library.repository.mem.MemLibraryDao;
import library.service.impl.LibraryServiceBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class LibraryServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find libraries!");

        ApplicationContext context = new AnnotationConfigApplicationContext("library");
        LibraryService service = context.getBean(LibraryService.class);

        List<Library> libraries = service.getAllLibraries();
        System.out.println(libraries.size() + " libraries found:");
        libraries.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);
    }
}
