package emp;

import java.util.Comparator;
import java.util.Optional;

public class Main {

    /*
    // ORM -- Object-relational-mapping or ORM is the programming
    // technique to map application domain model objects to the relational database tables.
    // Hibernate is a java-based ORM tool -- that provides a framework for mapping application --
    // -- domain objects to relational table.
    // JPA -- java persistence API -- hibernate is implementation of JPA
    // Hibernate, EclipseLinks ----
    // Hibernate : SessionFactory;
    // Hibernate : Session
    // Hibernate : Transaction

    // SessionFactory : is an interface that provides methods to create and manage Hibernate

    // Session Object: It is a thread-safe and immutable object that provides a cache of immutable
    // objects, which is used to create new * Session * objects.

    // Hibernate : Session
    // This Session is an interface that represents a single unit of work with the database.
    // It is responsible for managing the persistence operations.
    //The session interface provides methods for querying, saving updating and deleting objects in the database.
    // behind the scenes, the Hibernate session wraps a JDBC a java.sql.Connection and acts as a factory for
    // org.hibernate.Transaction instances.

    //Hibernate Transaction
    // The Transaction is an interface that represents a database transaction.
    // It provides methods to control the transaction, such as commit(), rollback(), and begin().
    // The transaction interface also provides methods to set the transaction isolation level and check  --
    // -- the status of the transaction.
    */

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();
        Employee employee1 = new Employee("John","Doe","john.doe@example");
        Employee employee2 = new Employee("Roshni", "Sharma","Roshni.sharma@example");
        Employee employee3 = new Employee("sham", "Ajay","ajay.sham@example");
        Employee employee4 = new Employee("Oppenheimer", "boss","bossoppen@example");
        Employee employee5 = new Employee("Jack", "Poul","jack.poul@example");
        dao.addEmployee(employee1);
        dao.addEmployee(employee2);
        dao.addEmployee(employee3);
        dao.addEmployee(employee4);
        dao.addEmployee(employee5);

    //  dao.deleteEmployee(employee2);

        Optional<Employee> employee = dao.getEmployeeById(2);

        if(employee.isPresent()) {
            System.out.println("Employee found: " + employee.get());
        }else {
            System.out.println("Employee not found ");
        }

       // System.out.println(dao.getAllEmployees());

        System.out.println("-------- list of employees sorted by firstname ---");
        dao.getAllEmployees().stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(System.out::println);

        System.out.println("------- list of employees sorted by lastName -------");
        dao.getAllEmployees().stream()
                .sorted(Comparator.comparing(Employee::getId))
                .forEach(System.out::println);

        System.out.println(" -----------------------");

        dao.updateEmployeeById(2,new Employee("Roshni","khan","roshni.khan@example.com"));
        dao.getAllEmployees().stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(System.out::println);

    }


}
