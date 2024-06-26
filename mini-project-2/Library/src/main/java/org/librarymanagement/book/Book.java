package org.librarymanagement.book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public abstract class Book implements BookInterface {
    @Setter
    private String author;
    @Setter
    private String title;
    @Setter
    private String ISBN;
    private final String Type;
}

