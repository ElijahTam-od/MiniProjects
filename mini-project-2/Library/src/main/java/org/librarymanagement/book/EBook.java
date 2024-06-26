package org.librarymanagement.book;


import lombok.Getter;

@Getter
public class EBook extends Book{
    private float sizeInMB;

    public EBook(String author, String title, String ISBN, float sizeInMB) {
        super(author, title, ISBN, "EBook");
        this.sizeInMB = sizeInMB;
    }

    public void setSizeInMB(float sizeInMB) {
        if (sizeInMB < 0) {
            throw new IllegalArgumentException("Size in MB cannot be negative.");
        }
        this.sizeInMB = sizeInMB;
    }
}
