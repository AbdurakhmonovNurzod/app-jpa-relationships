package uz.itm.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.itm.appjparelationships.entity.Subject;
import uz.itm.appjparelationships.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //CREATE
    @RequestMapping(method = RequestMethod.POST)
     public String addSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject is already exist";
        subjectRepository.save(subject);
        return "subject added";
     }
     //READ
    @GetMapping
    public List<Subject>getSubjects(){
        return subjectRepository.findAll();
    }

    //UPDATE
    @PutMapping(value = "/{subjectName}")
    public String updateSubject(@PathVariable String subjectName,@RequestBody Subject subject){

        return "updated successfully";

    }
}
