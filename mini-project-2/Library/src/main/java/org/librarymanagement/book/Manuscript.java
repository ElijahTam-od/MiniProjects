package org.librarymanagement.book;

public class Manuscript extends Book{
    private String coverType;

    public Manuscript(String author, String title, String ISBN, String coverType) {
        super(author, title, ISBN, "Manuscript");
        this.coverType = coverType;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }
}
