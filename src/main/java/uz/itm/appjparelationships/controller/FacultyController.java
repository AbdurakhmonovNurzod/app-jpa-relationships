package uz.itm.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.itm.appjparelationships.entity.Faculty;
import uz.itm.appjparelationships.entity.University;
import uz.itm.appjparelationships.loader.FacultyLoader;
import uz.itm.appjparelationships.repository.FacultyRepository;
import uz.itm.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyLoader facultyLoader) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyLoader.getName(), facultyLoader.getUniversityId());
        if (exists)
            return "This is already exist in the University";
        Faculty faculty = new Faculty();
        faculty.setName(facultyLoader.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyLoader.getUniversityId());
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            faculty.setUniversity(university);
            facultyRepository.save(faculty);
            return "added successfully";
        }
        return "there is no university with this name";


    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;

    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (facultyOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            facultyRepository.delete(faculty);
            return "deleted successfully";

        }
        return "there is no faculty with this name";
    }

    @PutMapping("/{id}")
    public String updateFaculty(@PathVariable Integer id, @RequestBody FacultyLoader facultyLoader) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (facultyOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            faculty.setName(facultyLoader.getName());
            Optional<University> universityOptional = universityRepository.findById(facultyLoader.getUniversityId());
            if (universityOptional.isPresent()) {
                University university = universityOptional.get();
                faculty.setUniversity(university);
                return "updated successfully";
            }
            return "there is no university with this name";

        }
        return "there is no faculty with this name";
    }

}
