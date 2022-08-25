package uz.itm.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.itm.appjparelationships.entity.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);

    @Query(nativeQuery = true,value = "select * from subject s join student_enrolled e on s.id=e.student_id where e.student_id=:student_id")
    List<Subject>getSubjectsByStudentId(Integer student_id);
}
