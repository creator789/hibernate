import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {
        Employee emp = new Employee("John", "Smith");
        Transaction tx = null;
        Long id = null;
        try(Session session = HibernateUtils.getSession()) {
            tx = session.beginTransaction();
            id = (Long) session.save(emp);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }

        // Changes made on detached object
        emp.setFirstName("Johnson");

        try(Session session = HibernateUtils.getSession()) {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, 1L);
            session.merge(emp);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
