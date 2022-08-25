package uz.itm.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.appjparelationships.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);
}
