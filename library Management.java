import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String bname;
    int bid;
    boolean bkflag;

    public Book(String bname, int bid) {
        this.bname = bname;
        this.bid = bid;
        this.bkflag = true; // Book is available when created
    }
}

class Student {
    String sname;
    String spass;
    int sid;
    int rbook; // ID of the borrowed book; 0 means no book borrowed

    public Student(String sname, String spass, int sid) {
        this.sname = sname;
        this.spass = spass;
        this.sid = sid;
        this.rbook = 0;
    }
}

public class Main {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static int bookCount = 1, bookId = 101, studentCount = 1, studentId = 1001;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Admin\n2.Student\n3.Exit\n\nEnter your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    admin();
                    break;
                case 2:
                    student();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("\nInvalid choice");
            }
            System.out.println("\n         Thank you        ");
        }
    }

    static void viewBooks() {
        System.out.println("\n");
        for (Book book : books) {
            System.out.printf("Book name: %s\t", book.bname);
            System.out.printf("Book Id : %-10d\t", book.bid);
            if (book.bkflag) {
                System.out.println("Available");
            } else {
                System.out.println("Not Available");
            }
        }
        System.out.println("\n");
    }

    static void admin() {
        System.out.println("Enter user name :");
        String a = scanner.next();
        System.out.println("Enter password :");
        String b = scanner.next();

        if (a.equals("Library") && b.equals("Mylibrary")) {
            while (true) {
                System.out.println("\n----------> ADMIN <------------\n\n");
                System.out.println("1.Add Book\n2.View Book\n3.Logout\n\nEnter your choice : ");
                int n = scanner.nextInt();

                switch (n) {
                    case 1:
                        System.out.println("\nHow many Books to add :");
                        int m = scanner.nextInt();
                        for (int i = 0; i < m; i++) {
                            System.out.println("\nEnter Book name :");
                            String bookName = scanner.next();
                            books.add(new Book(bookName, bookId++));
                            System.out.println("Your Book id is : " + (bookId - 1));
                        }
                        break;
                    case 2:
                        viewBooks();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("\nInvalid choice...\n");
                }
            }
        } else {
            System.out.println("\nUser name or password is wrong...\n\n");
        }
    }

    static void student() {
        while (true) {
            System.out.println("\n-------------> STUDENT LOGIN <--------------\n\n");
            System.out.println("1.Sign In\n2.Sign Up\n3.Exit\n\nEnter your choice : ");
            int n = scanner.nextInt();

            switch (n) {
                case 1:
                    System.out.println("\nEnter user id: ");
                    int a = scanner.nextInt();
                    System.out.println("Enter password: ");
                    String b = scanner.next();

                    if (a >= 1001 && a - 1001 < students.size() &&
                            students.get(a - 1001).sid == a &&
                            students.get(a - 1001).spass.equals(b)) {

                        while (true) {
                            System.out.println("1.View Book\n2.Lend Book\n3.Return Book\n4.Log out\n\nEnter your choice : ");
                            int m = scanner.nextInt();

                            switch (m) {
                                case 1:
                                    viewBooks();
                                    break;
                                case 2:
                                    System.out.println("Enter Book name: ");
                                    String bookName = scanner.next();
                                    System.out.println("Enter Book id : ");
                                    int bookId = scanner.nextInt();

                                    if (students.get(a - 1001).rbook == 0 &&
                                            bookId >= 101 && bookId - 101 < books.size() &&
                                            books.get(bookId - 101).bkflag) {

                                        students.get(a - 1001).rbook = bookId;
                                        books.get(bookId - 101).bkflag = false;
                                        System.out.println(books.get(bookId - 101).bname + " Lend Successfully...");
                                    } else if (students.get(a - 1001).rbook != 0) {
                                        System.out.println("Already you have Lend a book...");
                                    } else {
                                        System.out.println("Book Not Available...");
                                    }
                                    break;
                                case 3:
                                    int e = students.get(a - 1001).rbook;
                                    if (e != 0 && books.get(e - 101).bkflag == false) {
                                        System.out.printf("Book name : %8s\n", books.get(e - 101).bname);
                                        books.get(e - 101).bkflag = true;
                                        students.get(a - 1001).rbook = 0;
                                        System.out.println("Return Successfully..");
                                    } else {
                                        System.out.println("You haven't borrowed any book...");
                                    }
                                    break;
                                case 4:
                                    return;
                                default:
                                    System.out.println("Invalid choice...\n");
                            }
                        }
                    } else {
                        System.out.println("\nUser id or password is wrong...\n\n");
                    }
                    break;
                case 2:
                    System.out.println("\nEnter Member name :");
                    String sname = scanner.next();
                    System.out.println("Enter Password :");
                    String spass = scanner.next();
                    students.add(new Student(sname, spass, studentId++));
                    System.out.println("Your Membership id is : " + (studentId - 1));
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nInvalid choice...\n");
            }
        }
    }
}
