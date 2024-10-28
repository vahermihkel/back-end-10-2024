package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
//import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "toode")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id-d Long
    private String name;
    private double price;
    private String image;
    private boolean active;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Nutrients nutrients;

    @ManyToOne
//    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name) {
        this.name = name;
    }

}

// Piimatooted
// Piim -> Category piimatooted
// Või -> Category piimatooted
// kui on @OneToOne, siis seda ei lubata

// @OneToOne on siis, kui isik + tema kontaktandmed

// @OneToOne --> üksühele seos (kontaktandmed, toidu toitained)
// @ManyToOne --> Üks, aga võib korrata
// @OneToMany --> Mitu (list), aga iga sealolev Toode tohib olla ühe korra
// @ManyToMany --> Mitu (list), aga iga sealolev Toode võib olla ka mingil teisel kategoorial

// int, double, boolean, long, char
// String --> klass, mis koosneb char'dest
