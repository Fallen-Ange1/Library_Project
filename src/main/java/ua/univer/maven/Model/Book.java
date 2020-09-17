package ua.univer.maven.Model;


import java.util.Date;

public class Book {
    private String name;
    private int id;
    private int author_id;
    private int number_of_books;

    public Book(String name, int id, int author_id, int number_of_books) {
        this.name = name;
        this.id = id;
        this.author_id = author_id;
        this.number_of_books = number_of_books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor(int author) {
        this.author_id = author_id;
    }

    public int getNumber_of_books() {
        return number_of_books;
    }

    public void setNumber_of_books(int number_of_books) {
        this.number_of_books = number_of_books;
    }

    @Override
    public String toString() {
        return "Book name : " + '\"' + name + '\"' +
                ", Book id :" + id +
                ", Author id=" + author_id +
                ", Number of book =" + number_of_books;
    }
}