package ee.mihkel.veebipood.dto;

import ee.mihkel.veebipood.entity.Address;
import lombok.Data;

@Data
public class PersonAddressDTO { // DTO --> Data Transfer Object
    private String firstName;
    private String lastName;
    private Address address;
}
