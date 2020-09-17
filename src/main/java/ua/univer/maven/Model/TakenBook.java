package ua.univer.maven.Model;

public class TakenBook {
    private int user_id;
    private int transaction_id;
    private String Date;
    private int book_id;

    public TakenBook(int user_id, int transaction_id, String Date, int book_id) {
        this.user_id = user_id;
        this.transaction_id = transaction_id;
        this.Date = Date;
        this.book_id = book_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    @Override
    public String toString() {
        return "TakenBook{" +
                "user_id=" + user_id +
                ", transaction_id=" + transaction_id +
                ", Date='" + Date + '\'' +
                ", book_id=" + book_id +
                '}';
    }
}
