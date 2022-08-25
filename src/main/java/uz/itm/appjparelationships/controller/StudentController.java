package uz.itm.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.itm.appjparelationships.entity.Address;
import uz.itm.appjparelationships.entity.Student;
import uz.itm.appjparelationships.loader.StudentLoader;
import uz.itm.appjparelationships.repository.AddressRepository;
import uz.itm.appjparelationships.repository.GroupRepository;
import uz.itm.appjparelationships.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    AddressRepository addressRepository;

    //1.Vazirlik
    @GetMapping("/forMinistry")
    public Page<Student> getStudentList(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        return studentRepository.findAll(pageable);
    }
    //2.University
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student>getStudentList(@PathVariable Integer universityId,@RequestParam int page){
    Pageable pageable=PageRequest.of(page,10);
    return studentRepository.findAllByGroup_Faculty_UniversityId(universityId,pageable);

    }
    //3.Faculty Dekanat
    @GetMapping("/forFaculty/{facultyId}")
    public Page<Student>getStudentPageForFaculty(@PathVariable Integer facultyId,@RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
        return studentRepository.findAllByGroup_Faculty_Id(facultyId,pageable);

    }
    //4.Group teacher
    @GetMapping("forGroup/{groupId}")
    public Page<Student>getStudentsByGroupId(@PathVariable Integer groupId,@RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
        return studentRepository.findAllByGroup_Id(groupId,pageable);
    }

    @PostMapping
    public String addStudent(@RequestBody StudentLoader studentLoader){
        Student student=new Student();
        student.setFirstName(studentLoader.getFirstName());
        student.setLastName(studentLoader.getLastName());
        Address address=new Address();
        address.setCity(studentLoader.getCity());
        address.setDistrict(studentLoader.getDistrict());
        address.setStreet(studentLoader.getStreet());
        addressRepository.save(address);
        student.setAddress(address);
        student.setGroup(groupRepository.findById(studentLoader.getGroupId()).get());
        studentRepository.save(student);
        return "added successfully";
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            Student student = studentOptional.get();
            studentRepository.delete(student);
            return "deleted successfully";
        }
    return "there is no Student with this data";
    }
    @GetMapping("/{subjectId}")
    public List<Student>getStudentsBySubjectId(@PathVariable Integer subjectId){
        return studentRepository.findAllBySubjectId(subjectId);
    }
}
