package ee.tiiukuusik.lessonscheduler.persistence.customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  @Query("select c from Customer c where upper(c.email) = upper(:email)")
  Optional<Customer> findCustomerBy(String email);


}
