package emp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class EmployeeDAO {

    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
         //   session.save(employee);  -- No more use of save because it old methods
            session.persist(employee);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEmployee(Employee employee) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
        //  session.delete(employee); --- old one
            session.remove(employee);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public Optional<Employee> getEmployeeById(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (Optional.ofNullable(session.get(Employee.class, id)));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateEmployeeById(long id, Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Optional<Employee> optionalEmployee = getEmployeeById(id);
            if(optionalEmployee.isPresent()){
                optionalEmployee.get().setName(employee.getName());
                optionalEmployee.get().setLastName(employee.getLastName());
                optionalEmployee.get().setEmail(employee.getEmail());

         //     session.update(optionalEmployee.get());    -- it is old one the second is new.
        //        session.persist(optionalEmployee.get());
        //      Use merge to update the detached entity
                session.getTransaction().begin();
                session.merge(optionalEmployee.get());
                session.getTransaction().commit();
            }else {
                System.out.println("No Employee found with id: " +id);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
