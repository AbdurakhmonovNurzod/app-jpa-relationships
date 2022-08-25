package uz.itm.appjparelationships.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.itm.appjparelationships.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Page<Student>findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);
    Page<Student>findAllByGroup_Faculty_Id(Integer group_faculty_id, Pageable pageable);

    Page<Student>findAllByGroup_Id(Integer group_id, Pageable pageable);

    @Query(nativeQuery = true,value = "select * from student s join student_enrolled e on s.id=e.student_id where e.subject_id=:subject_id")
    List<Student>findAllBySubjectId(Integer subject_id);

//    @Query(value = "select * from auto_shop a join gm g on g.id=a.gm_factory_id where g.id=:factory_Id ",nativeQuery = true)
}
