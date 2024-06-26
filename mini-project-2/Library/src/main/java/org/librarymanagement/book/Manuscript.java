package org.librarymanagement.book;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Manuscript extends Book{
    private String coverType;

    public Manuscript(String author, String title, String ISBN, String coverType) {
        super(author, title, ISBN, "Manuscript");
        this.coverType = coverType;
    }

}
