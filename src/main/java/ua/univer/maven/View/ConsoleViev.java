package ua.univer.maven.View;

import ua.univer.maven.CheckStaff.Check;
import ua.univer.maven.DAO.BookDAO;
import ua.univer.maven.DAO.UserDAO;
import ua.univer.maven.Model.Book;
import ua.univer.maven.Model.IUserDAO;
import ua.univer.maven.Model.IbookDAO;
import ua.univer.maven.Model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleViev {

    public static void run() {

        System.out.println("Hi there , you're in actual library");
        System.out.println("Enter your email , please");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String email = br.readLine();
            System.out.println("Enter your password");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            String password = br2.readLine();
            IUserDAO userDAO = new UserDAO();
            List<User> userLists = userDAO.getAllUsers();
            IbookDAO ibookDAO = new BookDAO();
            Check check = new Check();
            for (User user : userLists) {
                if (user.getUser_email().equals(email) && user.getPassword().equals(password) && user.getUserabil().equals("admin")) {
                    System.out.println("Hi ," + user.getUserabil() + " choose option : ");
                    System.out.println("1) Show all users,");
                    System.out.println("2) Show all books,");
                    //this
                    System.out.println("3) Add new book,");
                    System.out.println("4) Delete book,");
                    System.out.println("5) Set book,");
                    System.out.println("6) Get book by id,");
                    //this
                    System.out.println("7) Get user by transId,");
                    System.out.println("8) Get user by book,");
                    System.out.println("9) Get users that hold his book longer that 30 days,");
                    System.out.println("10) Show taken books,");
                    System.out.println("11) Show available books,");
                    System.out.println("12) Check length of holding book");
                    BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
                    int i = Integer.parseInt(br3.readLine());
                    switch (i) {
                        case (1):
                            IUserDAO userDAO1 = new UserDAO();
                            List<User> userLists1 = userDAO.getAllUsers();
                            for (User user1 : userLists) {
                                System.out.println(user1);
                            }
                            break;
                        case (2):
                            List<Book> bookList = ibookDAO.getAllBooks();
                            for (Book book1 : bookList) {
                                System.out.println(book1);
                            }
                            break;
                        case (3):
                            System.out.println("Input name :");
                            BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
                            String s1 = br4.readLine();
                            System.out.println("Input author id :");
                            BufferedReader br6 = new BufferedReader(new InputStreamReader(System.in));
                            int integer = Integer.parseInt(br6.readLine());
                            System.out.println("Input number of book");
                            BufferedReader br66 = new BufferedReader(new InputStreamReader(System.in));
                            int integer2 = Integer.parseInt(br66.readLine());
                            ibookDAO.addBook(new Book(s1, ibookDAO.getMaxBookId(), integer, integer2));
                            System.out.println("Add successful");
                            break;
                        case (4):
                            BufferedReader br8 = new BufferedReader(new InputStreamReader(System.in));
                            int i4 = Integer.parseInt(br8.readLine());
                            ibookDAO.removeBook(i4);
                            break;
                        case (5):
                            System.out.println("Input name :");
                            BufferedReader br9 = new BufferedReader(new InputStreamReader(System.in));
                            String s3 = br9.readLine();
                            System.out.println("Input id :");
                            BufferedReader br10 = new BufferedReader(new InputStreamReader(System.in));
                            int i3 = Integer.parseInt(br10.readLine());
                            System.out.println("Input author id :");
                            BufferedReader br11 = new BufferedReader(new InputStreamReader(System.in));
                            int integer3 = Integer.parseInt(br11.readLine());
                            System.out.println("Input number of book");
                            BufferedReader br111 = new BufferedReader(new InputStreamReader(System.in));
                            int integer4 = Integer.parseInt(br111.readLine());
                            ibookDAO.addBook(new Book(s3, i3, integer3, integer4));
                            System.out.println("Set successful");
                            break;
                        case (6):
                            System.out.println("Input id :");
                            BufferedReader br13 = new BufferedReader(new InputStreamReader(System.in));
                            int id = Integer.parseInt(br13.readLine());
                            System.out.println(ibookDAO.getBooksById(id).toString());
                            break;
                        case (7):
                            System.out.println("Input transaction id  :");
                            BufferedReader br16 = new BufferedReader(new InputStreamReader(System.in));
                            Integer i6 = Integer.parseInt(br16.readLine());
                            System.out.println(userDAO.getUserByTransId(i6));
                            break;
                        case (8):
                            System.out.println("Input book name  :");
                            BufferedReader br17 = new BufferedReader(new InputStreamReader(System.in));
                            String bname = br17.readLine();
                            System.out.println(userDAO.getUserByBook(bname));
                            break;
                        case (9):
                            System.out.println(check.GetIdsHCH() + "/n/r");
                            break;
                        case (10):
                            System.out.println("Taken books :");
                            System.out.println(ibookDAO.showTakenBooks(ibookDAO.getIDsOfTakenBooks()) + "\n\r");
                            break;
                        case (11):
                            System.out.println("Available books :");
                            System.out.println(ibookDAO.getAvailableBooks());
                            break;
                        case (12):
                            System.out.println("Input user id :");
                            BufferedReader bufferedReader18 = new BufferedReader(new InputStreamReader(System.in));
                            int us_id = Integer.parseInt(bufferedReader18.readLine());
                            System.out.println("Input book_id");
                            BufferedReader bufferedReader19 = new BufferedReader(new InputStreamReader(System.in));
                            int book_id = Integer.parseInt(bufferedReader19.readLine());
                            System.out.println("Holding length :");
                            System.out.println(check.LengthOfHoldingCheck(us_id, book_id));
                            break;
                    }
                } else {
                    if (user.getUser_email().equals(email) && user.getPassword().equals(password) && user.getUserabil().equals("customer")) {
                        System.out.println("Hi ," + user.getUserabil() + " choose option : ");
                        //add new
                        System.out.println("1) Show available books,");
                        System.out.println("2) Show all books ,");
                        System.out.println("3) Get book by id,");
                        System.out.println("4) Get book by name,");
                        System.out.println("5) Take book");
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                        int i = Integer.parseInt(br1.readLine());
                        switch (i) {
                            case (1):
                                System.out.println("Available books :");
                                System.out.println("+---------------------------------------------------------------+");
                                for (Book current : ibookDAO.getAvailableBooks()) {

                                    System.out.println("|" + current + " |");
                                    System.out.println("+---------------------------------------------------------------+");

                                }
                                break;
                            case (2):
                                List<Book> bookList = ibookDAO.getAllBooks();
                                System.out.println("+---------------------------------------------------------------+");
                                for (Book book1 : bookList) {
                                    System.out.println("|" + book1 + " |");
                                    System.out.println("+---------------------------------------------------------------+");
                                }
                                break;
                            case (3):
                                System.out.println("Input id :");
                                BufferedReader br13 = new BufferedReader(new InputStreamReader(System.in));
                                int id = Integer.parseInt(br13.readLine());
                                for (Book current : ibookDAO.getBooksById(id)) {
                                    System.out.println(current);
                                }
                                break;
                            case (4):
                                System.out.println("Input name :");
                                BufferedReader br15 = new BufferedReader(new InputStreamReader(System.in));
                                String name = br15.readLine();
                                for (Book current : ibookDAO.getBooksByName(name)) {
                                    System.out.println(current);
                                }
                                break;
                            case (5):
                                System.out.println("Enter id of the book that you want to take , please :");
                                BufferedReader br16 = new BufferedReader(new InputStreamReader(System.in));
                                int idb = Integer.parseInt(br16.readLine());
                                ibookDAO.takeBook(idb, userDAO.getUserIdByUserL(email), userDAO.getTransactionId());
                                System.out.println("Done!");
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
