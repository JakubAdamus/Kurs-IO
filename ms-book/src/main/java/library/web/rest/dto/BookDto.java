package library.web.rest.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String cover;
    private int writerId;
    private float rating;
}
