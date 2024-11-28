package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

// PostgreSQL ei saa kasutada tabelite nimetustena:
// Order
// User

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "seq", initialValue = 1238765, allocationSize = 1)
@Table(name="orders") // vahetame tabeli nime
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

//    @ManyToMany
//    //@Column(name = "products")
//    //@JoinColumn(name = "product_id")
//    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> orderRows;


    @ManyToOne
//    @JoinColumn(name = "person_email")
    private Person person;

    private Date creation;

//    @ColumnDefault("0")
    private double totalSum;

//    @ColumnDefault("false")
    private boolean paid;
}
