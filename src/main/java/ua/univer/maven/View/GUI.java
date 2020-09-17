package ua.univer.maven.View;

import ua.univer.maven.CheckStaff.Check;
import ua.univer.maven.DAO.BookDAO;
import ua.univer.maven.DAO.UserDAO;
import ua.univer.maven.Model.*;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class GUI {

    private JFrame frame;
    private JTextField textFieldE;
    private JTextField textFieldP;
    private JFrame frameOp;
    private JFrame frameOp3;
    private JFrame frameOp4;
    private JFrame frameOp5;
    private JFrame frameOp5_1;
    private JFrame frameOp6;
    private JFrame frameOp7;
    private JFrame frameOp8;
    private JFrame frameOp12;
    private JFrame frameOp4C;
    private JFrame frameOp5C;
    private JTextField chooseOP;

    Check check = new Check();
    IbookDAO ibookDAO = new BookDAO();
    IUserDAO userDAO = new UserDAO();
    JTextArea textArea1;
    int login_c;
    int result;
    String buffer;
    String email;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUI() throws Exception {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public int Login(String email, String password) {
        try {
            userDAO = new UserDAO();
            List<User> userLists = userDAO.getAllUsers();
            for (User user : userLists) {
                if (user.getUser_email().equals(email) && user.getPassword().equals(password) && user.getUserabil().equals("admin")) {
                    login_c = 1;
                }
                if (user.getUser_email().equals(email) && user.getPassword().equals(password) && user.getUserabil().equals("customer")) {
                    login_c = 2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login_c;
    }

    private void initialize() {
        frame = new JFrame("Library");
        frame.setBounds(100, 100, 648, 413);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);


        textFieldE = new JTextField();
        textFieldE.setBounds(260, 136, 150, 20);
        panel.add(textFieldE);
        textFieldE.setColumns(10);


        JLabel jLabelE = new JLabel("Email");
        jLabelE.setBounds(210, 136, 120, 20);
        panel.add(jLabelE);

        textFieldP = new JTextField();
        textFieldP.setColumns(10);
        textFieldP.setBounds(260, 166, 150, 20);
        panel.add(textFieldP);

        JLabel jLabelP = new JLabel("Password");
        jLabelP.setBounds(180, 166, 120, 20);
        panel.add(jLabelP);

        JButton btnLogIn = new JButton("Log in");
        btnLogIn.addActionListener(e -> {
            try {
                email = textFieldE.getText();
                result = Login(textFieldE.getText(), textFieldP.getText());
                if (result == 1) {
                    frame.setVisible(false);
                    initializeAdmin();
                    System.out.println(result);
                }
                if (result == 2) {
                    frame.setVisible(false);
                    initializeCustomer();
                    System.out.println(result);
                }
                textFieldE.setText("");
                textFieldP.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        btnLogIn.setBounds(285, 200, 89, 23);
        panel.add(btnLogIn);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 632, 22);
        panel.add(menuBar);
    }

    public void initializeAdmin() {
        frameOp = new JFrame("Library");
        frameOp.setVisible(true);
        frameOp.setBounds(100, 100, 800, 500);

        JPanel panel = new JPanel();
        frameOp.add(panel);
        textArea1 = new JTextArea();


        JScrollPane sp = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(250, 40, 500, 300);
        panel.add(sp);
        frameOp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frameOp.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);


        JButton btnOp1 = new JButton("Show all users");
        btnOp1.setBounds(30, 40, 150, 23);
        panel.add(btnOp1);
        btnOp1.addActionListener(e -> {
            textArea1.setText("");
            for (User current : userDAO.getAllUsers()) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "ID :" + current.getUser_id() + "\n\r" + "Email :" + current.getUser_email() + "\n\r" + "Phone number :" + current.getPhone_number() + "\n\r" + "Type :" + current.getUserabil() + "\n\r");
            }
        });

        JButton btnOp2 = new JButton("Show all books");
        btnOp2.setBounds(30, 70, 150, 23);
        panel.add(btnOp2);
        btnOp2.addActionListener(e -> {
            textArea1.setText("");
            for (Book current : ibookDAO.getAllBooks()) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "Name :" + current.getName() + "\n\r" + "ID :" + current.getId() + "\n\r" + "Amount :" + current.getNumber_of_books() + "\n\r" + "Author ID :" + current.getAuthor_id() + "\n\r");
            }
        });


        JButton btnOp3 = new JButton("Add new book");
        btnOp3.setBounds(30, 100, 150, 23);
        panel.add(btnOp3);
        btnOp3.addActionListener(e -> {
            initializeOp3();
        });

        JButton btnOp4 = new JButton("Delete book");
        btnOp4.setBounds(30, 130, 150, 23);
        panel.add(btnOp4);
        btnOp4.addActionListener(e -> {
            initializeOp4();
        });

        JButton btnOp5 = new JButton("Set book");
        btnOp5.setBounds(30, 160, 150, 23);
        panel.add(btnOp5);
        btnOp5.addActionListener(e -> {
            initializeOp5();
        });

        JButton btnOp6 = new JButton("Book by id");
        btnOp6.setBounds(30, 190, 150, 23);
        panel.add(btnOp6);
        btnOp6.addActionListener(e -> {
            initializeOp6();
        });

        JButton btnOp7 = new JButton("User by transId");
        btnOp7.setBounds(30, 220, 150, 23);
        panel.add(btnOp7);
        btnOp7.addActionListener(e -> {
            initializeOp7();
        });


        JButton btnOp8 = new JButton("User by book");
        btnOp8.setBounds(30, 250, 150, 23);
        panel.add(btnOp8);
        btnOp8.addActionListener(e -> {
            initializeOp8();
        });

        JButton btnOp9 = new JButton("30 days off");
        btnOp9.setBounds(30, 280, 150, 23);
        panel.add(btnOp9);
        btnOp9.addActionListener(e -> {
            textArea1.setText("");
            for (User current : check.GetIdsHCH()) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "ID :" + current.getUser_id() + "\n\r" + "Email :" + current.getUser_email() + "\n\r" + "Phone number :" + current.getPhone_number() + "\n\r" + "Type :" + current.getUserabil() + "\n\r");
            }
        });

        JButton btnOp10 = new JButton("Taken books");
        btnOp10.setBounds(30, 310, 150, 23);
        panel.add(btnOp10);
        btnOp10.addActionListener(e -> {
            textArea1.setText("");
            for (TakenBook current : ibookDAO.showTakenBooks(ibookDAO.getIDsOfTakenBooks())) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "User UD :" + current.getUser_id() + "\n\r" + "Transaction ID :" + current.getTransaction_id() + "\n\r" + "Date :" + current.getDate() + "\n\r" + "Book ID :" + current.getBook_id() + "\n\r");
            }
        });

        JButton btnOp11 = new JButton("Available books");
        btnOp11.setBounds(30, 340, 150, 23);
        panel.add(btnOp11);
        btnOp11.addActionListener(e -> {
            textArea1.setText("");
            for (Book current : ibookDAO.getAvailableBooks()) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "Name :" + current.getName() + "\n\r" + "ID :" + current.getId() + "\n\r" + "Amount :" + current.getNumber_of_books() + "\n\r" + "Author ID :" + current.getAuthor_id() + "\n\r");
            }
        });

        JButton btnOp12 = new JButton("holding length");
        btnOp12.setBounds(30, 370, 150, 23);
        panel.add(btnOp12);
        btnOp12.addActionListener(e -> {
            initializeOp12();
        });

        JLabel jLabelChoose = new JLabel("Choose option :");
        jLabelChoose.setBounds(30, 10, 120, 20);
        panel.add(jLabelChoose);


    }

    public void initializeOp3() {
        frameOp3 = new JFrame();
        frameOp3.setBounds(100, 100, 300, 200);
        frameOp3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp3.setVisible(true);
        JPanel panelOp3 = new JPanel();
        frameOp3.getContentPane().add(panelOp3, BorderLayout.CENTER);
        panelOp3.setLayout(null);

        JLabel jLabelName = new JLabel("Book name :");
        jLabelName.setBounds(20, 40, 90, 20);
        panelOp3.add(jLabelName);

        JTextField jTextFieldName = new JTextField();
        jTextFieldName.setBounds(120, 40, 110, 20);
        panelOp3.add(jTextFieldName);

        JLabel jLabelNumberOfBooks = new JLabel("Amount :");
        jLabelNumberOfBooks.setBounds(20, 70, 90, 20);
        panelOp3.add(jLabelNumberOfBooks);

        JTextField jTextFieldNumberOfBooks = new JTextField();
        jTextFieldNumberOfBooks.setBounds(120, 70, 110, 20);
        panelOp3.add(jTextFieldNumberOfBooks);

        JLabel jLabelAuthorID = new JLabel("Author ID :");
        jLabelAuthorID.setBounds(20, 100, 90, 20);
        panelOp3.add(jLabelAuthorID);

        JTextField jTextFieldAuthorID = new JTextField();
        jTextFieldAuthorID.setBounds(120, 100, 110, 20);
        panelOp3.add(jTextFieldAuthorID);

        JButton add = new JButton("Add");
        add.setBounds(130, 130, 70, 25);
        panelOp3.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ibookDAO.addBook(new Book(jTextFieldName.getText(), ibookDAO.getMaxBookId(), Integer.parseInt(jTextFieldAuthorID.getText()), Integer.parseInt(jTextFieldNumberOfBooks.getText())));
                JOptionPane.showMessageDialog(null, "Done!");
                frameOp3.setVisible(false);
            }
        });
    }

    public void initializeOp4() {
        frameOp4 = new JFrame();
        frameOp4.setBounds(100, 100, 200, 160);
        frameOp4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp4.setVisible(true);
        JPanel panelOp4 = new JPanel();
        frameOp4.getContentPane().add(panelOp4, BorderLayout.CENTER);
        panelOp4.setLayout(null);

        JLabel jLabelID = new JLabel("Book id :");
        jLabelID.setBounds(10, 40, 90, 20);
        panelOp4.add(jLabelID);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setBounds(80, 40, 110, 20);
        panelOp4.add(jTextFieldID);

        JButton delete = new JButton("Delete");
        delete.setBounds(60, 80, 90, 25);
        panelOp4.add(delete);
        delete.addActionListener(e -> {
            ibookDAO.removeBook(Integer.parseInt(jTextFieldID.getText()));
            JOptionPane.showMessageDialog(null, "Done!");
            frameOp4.setVisible(false);
        });
    }

    public void initializeOp5() {
        frameOp5 = new JFrame();
        frameOp5.setBounds(100, 100, 200, 160);
        frameOp5.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp5.setVisible(true);
        JPanel panelOp5 = new JPanel();
        frameOp5.getContentPane().add(panelOp5, BorderLayout.CENTER);
        panelOp5.setLayout(null);

        JLabel jLabelID = new JLabel("Book id :");
        jLabelID.setBounds(10, 40, 90, 20);
        panelOp5.add(jLabelID);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setBounds(80, 40, 110, 20);
        panelOp5.add(jTextFieldID);


        JButton Go = new JButton("Go");
        Go.setBounds(60, 80, 90, 25);
        panelOp5.add(Go);
        Go.addActionListener(e -> {
            frameOp5.setVisible(false);
            initializeOp5_1();
            buffer = jTextFieldID.getText();
            frameOp5.setVisible(false);
        });
    }

    public void initializeOp5_1() {
        frameOp5_1 = new JFrame();
        frameOp5_1.setBounds(100, 100, 300, 200);
        frameOp5_1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp5_1.setVisible(true);
        JPanel panelOp5_1 = new JPanel();
        frameOp5_1.getContentPane().add(panelOp5_1, BorderLayout.CENTER);
        panelOp5_1.setLayout(null);

        JLabel jLabelName = new JLabel("Book name :");
        jLabelName.setBounds(20, 40, 90, 20);
        panelOp5_1.add(jLabelName);

        JTextField jTextFieldName = new JTextField();
        jTextFieldName.setBounds(120, 40, 110, 20);
        panelOp5_1.add(jTextFieldName);

        JLabel jLabelNumberOfBooks = new JLabel("Amount :");
        jLabelNumberOfBooks.setBounds(20, 70, 90, 20);
        panelOp5_1.add(jLabelNumberOfBooks);

        JTextField jTextFieldNumberOfBooks = new JTextField();
        jTextFieldNumberOfBooks.setBounds(120, 70, 110, 20);
        panelOp5_1.add(jTextFieldNumberOfBooks);

        JLabel jLabelAuthorID = new JLabel("Author ID :");
        jLabelAuthorID.setBounds(20, 100, 90, 20);
        panelOp5_1.add(jLabelAuthorID);

        JTextField jTextFieldAuthorID = new JTextField();
        jTextFieldAuthorID.setBounds(120, 100, 110, 20);
        panelOp5_1.add(jTextFieldAuthorID);

        JButton add = new JButton("Set");
        add.setBounds(130, 130, 70, 25);
        panelOp5_1.add(add);
        add.addActionListener(e -> {
            ibookDAO.setBook(new Book(jTextFieldName.getText(), Integer.parseInt(buffer), Integer.parseInt(jTextFieldAuthorID.getText()), Integer.parseInt(jTextFieldNumberOfBooks.getText())));
            frameOp5_1.setVisible(false);
            JOptionPane.showMessageDialog(null, "Done!");
        });
    }

    public void initializeOp6() {
        frameOp6 = new JFrame();
        frameOp6.setBounds(100, 100, 200, 160);
        frameOp6.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp6.setVisible(true);
        JPanel panelOp6 = new JPanel();
        frameOp6.getContentPane().add(panelOp6, BorderLayout.CENTER);
        panelOp6.setLayout(null);

        JLabel jLabelID = new JLabel("Book id :");
        jLabelID.setBounds(10, 40, 90, 20);
        panelOp6.add(jLabelID);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setBounds(80, 40, 110, 20);
        panelOp6.add(jTextFieldID);

        JButton Go1 = new JButton("Go");
        Go1.setBounds(60, 80, 90, 25);
        panelOp6.add(Go1);

        Go1.addActionListener(e -> {
            textArea1.setText("");
            for (Book current : ibookDAO.getBooksById(Integer.parseInt(jTextFieldID.getText()))) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "Name :" + current.getName() + "\n\r" + "ID :" + Integer.parseInt(jTextFieldID.getText()) + "\n\r" + "Amount :" + current.getNumber_of_books() + "\n\r" + "Author ID :" + current.getAuthor_id() + "\n\r");
                frameOp6.setVisible(false);
            }
        });
    }

    public void initializeOp7() {
        frameOp7 = new JFrame();
        frameOp7.setBounds(100, 100, 250, 160);
        frameOp7.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp7.setVisible(true);
        JPanel panelOp7 = new JPanel();
        frameOp7.getContentPane().add(panelOp7, BorderLayout.CENTER);
        panelOp7.setLayout(null);

        JLabel jLabelID = new JLabel("Transaction id :");
        jLabelID.setBounds(10, 40, 110, 20);
        panelOp7.add(jLabelID);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setBounds(120, 40, 110, 20);
        panelOp7.add(jTextFieldID);

        JButton Go2 = new JButton("Go");
        Go2.setBounds(60, 80, 90, 25);
        panelOp7.add(Go2);

        Go2.addActionListener(e -> {
            textArea1.setText("");
            for (User current : userDAO.getUserByTransId(Integer.parseInt(jTextFieldID.getText()))) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "ID :" + current.getUser_id() + "\n\r" + "Email :" + current.getUser_email() + "\n\r" + "Phone number :" + current.getPhone_number() + "\n\r" + "Type :" + current.getUserabil() + "\n\r");
                frameOp7.setVisible(false);
            }
        });
    }

    public void initializeOp8() {
        frameOp8 = new JFrame();
        frameOp8.setBounds(100, 100, 250, 160);
        frameOp8.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp8.setVisible(true);
        JPanel panelOp8 = new JPanel();
        frameOp8.getContentPane().add(panelOp8, BorderLayout.CENTER);
        panelOp8.setLayout(null);

        JLabel jLabelID = new JLabel("Book id :");
        jLabelID.setBounds(10, 40, 110, 20);
        panelOp8.add(jLabelID);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setBounds(120, 40, 110, 20);
        panelOp8.add(jTextFieldID);

        JButton Go3 = new JButton("Go");
        Go3.setBounds(60, 80, 90, 25);
        panelOp8.add(Go3);

        Go3.addActionListener(e -> {
            textArea1.setText("");
            for (User current : userDAO.getUserByBook(jTextFieldID.getText())) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "ID :" + current.getUser_id() + "\n\r" + "Email :" + current.getUser_email() + "\n\r" + "Phone number :" + current.getPhone_number() + "\n\r" + "Type :" + current.getUserabil() + "\n\r");
                frameOp8.setVisible(false);
            }
        });
    }

    public void initializeOp12() {
        frameOp12 = new JFrame();
        frameOp12.setBounds(100, 100, 300, 200);
        frameOp12.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp12.setVisible(true);
        JPanel panelOp12 = new JPanel();
        frameOp12.getContentPane().add(panelOp12, BorderLayout.CENTER);
        panelOp12.setLayout(null);

        JLabel jLabelName = new JLabel("User ID :");
        jLabelName.setBounds(20, 40, 90, 20);
        panelOp12.add(jLabelName);

        JTextField jTextFieldUserID = new JTextField();
        jTextFieldUserID.setBounds(120, 40, 110, 20);
        panelOp12.add(jTextFieldUserID);

        JLabel jLabelBookID = new JLabel("Book ID :");
        jLabelBookID.setBounds(20, 70, 90, 20);
        panelOp12.add(jLabelBookID);

        JTextField jTextFieldBookID = new JTextField();
        jTextFieldBookID.setBounds(120, 70, 110, 20);
        panelOp12.add(jTextFieldBookID);

        JButton add = new JButton("Go");
        add.setBounds(130, 100, 70, 25);
        panelOp12.add(add);
        textArea1.setText("");
        add.addActionListener(e -> {
            textArea1.setText("User ID :" + jTextFieldUserID.getText() + "\r\n" + "Book ID :" + jTextFieldBookID.getText() + "\n\r" + "Holding length :" + check.LengthOfHoldingCheck(Integer.parseInt(jTextFieldUserID.getText()), Integer.parseInt(jTextFieldBookID.getText())));
            frameOp12.setVisible(false);
        });
    }

    /*
    Customer view
    */
    public void initializeCustomer() {
        frameOp = new JFrame("Library");
        frameOp.setVisible(true);
        frameOp.setBounds(100, 100, 800, 500);
        frameOp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frameOp.add(panel);
        textArea1 = new JTextArea();


        JScrollPane sp = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(250, 40, 500, 300);
        panel.add(sp);
        frameOp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frameOp.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton btnOp1 = new JButton("Available books");
        btnOp1.setBounds(30, 40, 150, 23);
        panel.add(btnOp1);
        btnOp1.addActionListener(e -> {
            textArea1.setText("");
            for (User current : userDAO.getAllUsers()) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "ID :" + current.getUser_id() + "\n\r" + "Email :" + current.getUser_email() + "\n\r" + "Phone number :" + current.getPhone_number() + "\n\r" + "Type :" + current.getUserabil() + "\n\r");
            }
        });

        JButton btnOp2 = new JButton("Show all books");
        btnOp2.setBounds(30, 70, 150, 23);
        panel.add(btnOp2);
        btnOp2.addActionListener(e -> {
            textArea1.setText("");
            for (Book current : ibookDAO.getAllBooks()) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "Name :" + current.getName() + "\n\r" + "ID :" + current.getId() + "\n\r" + "Amount :" + current.getNumber_of_books() + "\n\r" + "Author ID :" + current.getAuthor_id() + "\n\r");
            }
        });

        JButton btnOp6 = new JButton("Book by id");
        btnOp6.setBounds(30, 100, 150, 23);
        panel.add(btnOp6);
        btnOp6.addActionListener(e -> {
            initializeOp6();
        });

        JButton btnOp4C = new JButton("Book by name");
        btnOp4C.setBounds(30, 130, 150, 23);
        panel.add(btnOp4C);
        btnOp4C.addActionListener(e -> {
            initializeOp4c();
        });

        JButton btnOp5C = new JButton("Take book");
        btnOp5C.setBounds(30, 160, 150, 23);
        panel.add(btnOp5C);
        btnOp5C.addActionListener(e -> {
            initializeOp5c();
        });

        JLabel jLabelChoose = new JLabel("Choose option :");
        jLabelChoose.setBounds(30, 10, 120, 20);
        panel.add(jLabelChoose);
    }

    public void initializeOp4c() {
        frameOp4C = new JFrame();
        frameOp4C.setBounds(100, 100, 200, 160);
        frameOp4C.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp4C.setVisible(true);
        JPanel panelOp4C = new JPanel();
        frameOp4C.getContentPane().add(panelOp4C, BorderLayout.CENTER);
        panelOp4C.setLayout(null);

        JLabel jLabelID = new JLabel("Book name :");
        jLabelID.setBounds(10, 40, 90, 20);
        panelOp4C.add(jLabelID);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setBounds(90, 40, 110, 20);
        panelOp4C.add(jTextFieldID);

        JButton Go1 = new JButton("Go");
        Go1.setBounds(60, 80, 90, 25);
        panelOp4C.add(Go1);

        Go1.addActionListener(e -> {
            textArea1.setText("");
            for (Book current : ibookDAO.getBooksByName(jTextFieldID.getText())) {
                buffer = textArea1.getText();
                textArea1.setText(buffer + "Name :" + current.getName() + "\n\r" + "ID :" + current.getId() + "\n\r" + "Amount :" + current.getNumber_of_books() + "\n\r" + "Author ID :" + current.getAuthor_id() + "\n\r");
                frameOp6.setVisible(false);
            }
        });
    }

    public void initializeOp5c() {
        frameOp5C = new JFrame();
        frameOp5C.setBounds(100, 100, 200, 160);
        frameOp5C.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameOp5C.setVisible(true);
        JPanel panelOp5C = new JPanel();
        frameOp5C.getContentPane().add(panelOp5C, BorderLayout.CENTER);
        panelOp5C.setLayout(null);

        JLabel jLabelID = new JLabel("Book ID :");
        jLabelID.setBounds(10, 40, 90, 20);
        panelOp5C.add(jLabelID);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setBounds(90, 40, 110, 20);
        panelOp5C.add(jTextFieldID);

        JButton add = new JButton("Take");
        add.setBounds(60, 80, 90, 25);
        panelOp5C.add(add);
        add.addActionListener(e -> {
            System.out.println(email);
            ibookDAO.takeBook(Integer.parseInt(jTextFieldID.getText()), userDAO.getUserIdByUserL(email), userDAO.getTransactionId());
            frameOp5C.setVisible(false);
            JOptionPane.showMessageDialog(null, "Done!");
        });
    }
}