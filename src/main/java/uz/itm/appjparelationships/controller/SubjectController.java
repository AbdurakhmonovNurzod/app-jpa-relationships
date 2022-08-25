package uz.itm.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.itm.appjparelationships.entity.Address;
import uz.itm.appjparelationships.entity.Student;
import uz.itm.appjparelationships.entity.Subject;
import uz.itm.appjparelationships.loader.StudentLoader;
import uz.itm.appjparelationships.repository.StudentRepository;
import uz.itm.appjparelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;

    //CREATE
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject is already exist";
        subjectRepository.save(subject);
        return "subject added";
    }

    //READ
    @GetMapping
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    //UPDATE
    @PutMapping(value = "/{subjectId}/students/{studentId}")
    public Subject enrollStudentToSubject(@PathVariable Integer subjectId, @PathVariable Integer studentId) {
        Student student = studentRepository.findById(studentId).get();
        Subject subject = subjectRepository.findById(subjectId).get();
        subject.enrollStudent(student);
        return subjectRepository.save(subject);

    }
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id){
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()){
            subjectRepository.delete(subjectOptional.get());
            return "deleted successfully";
        }
        return "there is no subject with this name";

    }
    @PutMapping("/{id}")
    public String updateSubject(@PathVariable Integer id,@RequestBody Subject subject){
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()){
            Subject editingSubject = subjectOptional.get();
            editingSubject.setName(subject.getName());
            subjectRepository.save(editingSubject);
            return "updated successfully";
        }
        return "there is no subject with this name";
    }
    @DeleteMapping("/{subjectId}/student/{studentId}")
    public String removeSubjectFromStudent(@PathVariable Integer subjectId,@PathVariable Integer studentId){
        Subject subject = subjectRepository.findById(subjectId).get();
        Student student = studentRepository.findById(studentId).get();
        subject.removeStudent(student);
        return "removed successfully";
    }
    @GetMapping("/{studentId}")
    public List<Subject>getSubjectsByStudentId(@PathVariable Integer studentId){
        return subjectRepository.getSubjectsByStudentId(studentId);

    }

}

