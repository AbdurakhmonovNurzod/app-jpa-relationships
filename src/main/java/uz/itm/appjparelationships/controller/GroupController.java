package uz.itm.appjparelationships.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.itm.appjparelationships.entity.Faculty;
import uz.itm.appjparelationships.entity.Group;
import uz.itm.appjparelationships.loader.GroupLoader;
import uz.itm.appjparelationships.repository.FacultyRepository;
import uz.itm.appjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    //GENERAL
    //READ
    @GetMapping
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    //UNIVERSITET XODIMI
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsUniversityId(@PathVariable Integer universityId) {
        groupRepository.getGroupsByUniversityId(universityId);
        groupRepository.getGroupByUniversityIdNative(universityId);

        return groupRepository.findAllByFaculty_University_Id(universityId);

    }
    @PostMapping
    public String addGroup(@RequestBody GroupLoader groupLoader){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupLoader.getFacultyId());
        if (!optionalFaculty.isPresent())
            return "There is no faculty with this name";
        Faculty faculty = optionalFaculty.get();
        Group group=new Group();
        group.setName(groupLoader.getName());
        group.setFaculty(faculty);
        groupRepository.save(group);
        return "added successfully";
    }
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id){
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()){
            Group group = groupOptional.get();
            groupRepository.delete(group);
            return "deleted successfully";
        }
        return  "there is no group with this name";

    }
    @PutMapping("/{id}")
    public String updateGroup(@PathVariable Integer id,@RequestBody GroupLoader groupLoader){
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()){
            Group group = groupOptional.get();
            Faculty faculty = facultyRepository.findById(groupLoader.getFacultyId()).get();
            group.setName(groupLoader.getName());
            group.setFaculty(faculty);
            groupRepository.save(group);
            return "updated successfully";
        }
        return "there is no group with this name";
    }

}
