package ms.libraries.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Libraries")
public class Library implements Serializable {


    @ElementCollection
    @CollectionTable(name="BOOK_LIBRARY",joinColumns = @JoinColumn(name = "library_id"))
    @Column(name = "book_id")
    public Set<Integer> books ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String logo;
    @Transient
    private List<String> bookNames = new ArrayList<>();



    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
