package uz.itm.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.appjparelationships.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
