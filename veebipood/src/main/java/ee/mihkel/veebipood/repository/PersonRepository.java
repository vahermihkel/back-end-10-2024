package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.dto.PersonAddressDTO;
import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Address;
import ee.mihkel.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findByAddress_StreetContainsIgnoreCase(String street);




}