package org.simpleform.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.simpleform.Entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

