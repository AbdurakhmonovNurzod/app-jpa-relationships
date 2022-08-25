package uz.itm.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.itm.appjparelationships.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {

    List<Group>findAllByFaculty_University_Id(Integer faculty_university_id);

    @Query("select gr from groups gr where gr.faculty.university.id=:universityId")
    List<Group>getGroupsByUniversityId(Integer universityId);

    @Query(value = "select from groups g join f on f.id=g.faculty_id join university u on u.id=f.university.id where u.id=:universityId",nativeQuery = true)
    List<Group>getGroupByUniversityIdNative(Integer universityId);
}
