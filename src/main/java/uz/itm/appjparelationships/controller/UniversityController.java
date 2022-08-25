package uz.itm.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.itm.appjparelationships.entity.Address;
import uz.itm.appjparelationships.entity.University;
import uz.itm.appjparelationships.loader.UniversityLoader;
import uz.itm.appjparelationships.repository.AddressRepository;
import uz.itm.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    //Read
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getStudents() {
        return universityRepository.findAll();
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityLoader universityLoader) {
        Address address = new Address();

        address.setCity(universityLoader.getCity());

        address.setDistrict(universityLoader.getDistrict());

        address.setStreet(universityLoader.getStreet());

        Address savedAddress = addressRepository.save(address);

        University university = new University();

        university.setName(universityLoader.getName());

        university.setAddress(savedAddress);

        universityRepository.save(university);

        return "saved successfully";
    }
    //UPDATE
    @RequestMapping(value = "/university/{id}",method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id,@RequestBody UniversityLoader universityLoader){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            University university = optionalUniversity.get();
            university.setName(universityLoader.getName());
            //University Address
            Address address = university.getAddress();
            address.setCity(universityLoader.getCity());
            address.setDistrict(universityLoader.getDistrict());
            address.setStreet(universityLoader.getStreet());
            addressRepository.save(address);
            universityRepository.save(university);
            return "university edited";
        }
        return "University not found";
    }
    //DELETE
    @RequestMapping(value = "/university/{id}")
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "university deleted";

    }


}
