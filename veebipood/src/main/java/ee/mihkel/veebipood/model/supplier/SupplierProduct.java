package ee.mihkel.veebipood.model.supplier;

import lombok.Data;

@Data
public class SupplierProduct {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}