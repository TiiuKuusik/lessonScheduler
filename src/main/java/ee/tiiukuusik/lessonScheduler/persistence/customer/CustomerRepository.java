package ee.tiiukuusik.lessonscheduler.persistence.customer;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where upper(c.email) = upper(:email)")
    Optional<Customer> findCustomerBy(String email);

    boolean existsByEmail(@NotNull @Size(max = 100) String email);

    boolean existsByPhone(@NotNull @Size(max = 50) String phone);
}
