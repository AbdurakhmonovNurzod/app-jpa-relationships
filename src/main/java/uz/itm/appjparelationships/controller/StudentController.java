package uz.itm.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.itm.appjparelationships.entity.Student;
import uz.itm.appjparelationships.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

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
    //4.Group teacher
}
