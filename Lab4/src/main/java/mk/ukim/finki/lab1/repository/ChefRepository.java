package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChefRepository extends JpaRepository<Chef, Long> {

}
