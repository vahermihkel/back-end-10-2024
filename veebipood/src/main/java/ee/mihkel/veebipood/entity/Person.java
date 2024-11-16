package ee.mihkel.veebipood.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Person {
    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @ColumnDefault("false")
    private boolean admin;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
