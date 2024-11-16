package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.dto.PersonAddressDTO;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service // teeb Beaniks (serveris luuakse teda 1 kord), võimalik autowire'dada
public class PersonService {

    @Autowired
    PersonRepository personRepository;

//    @Autowired
//    private AddressRepository addressRepository;

    @Autowired
    ModelMapper modelMapper;

    //@Transactional // salvestab ära kõik mis on funktsiooni sees, aga kui kasvõi 1 feilib
    // siis keerab kogu asja tagasi
    public Person savePerson(Person person) {
//        Address address = addressRepository.save(person.getAddress());
//        person.setAddress(address);
        return personRepository.save(person);
    }

    public List<PersonAddressDTO> getPersonAddressDTO(List<Person> persons) {
//        List<PersonAddressDTO> personAddressDTOs = new ArrayList<>();
//        for (Person p: persons) {
//            PersonAddressDTO dto = new PersonAddressDTO();
//            dto.setAddress(p.getAddress());
//            dto.setFirstName(p.getFirstName());
//            dto.setLastName(p.getLastName());
//            personAddressDTOs.add(dto);
//        }
//        ModelMapper modelMapper = new ModelMapper();
        System.out.println(modelMapper);
        PersonAddressDTO[] personAddressDTOs = modelMapper.map(persons, PersonAddressDTO[].class);
        return Arrays.asList(personAddressDTOs);
    }
}
