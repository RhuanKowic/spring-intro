package main.java.com.example.classes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import main.java.com.example.classes.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {
  List<Class> findByTaught(boolean taught);
  List<Class> findByTitleContaining(String title);
}
