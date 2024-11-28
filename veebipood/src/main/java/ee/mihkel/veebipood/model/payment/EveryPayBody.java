package ee.mihkel.veebipood.model.payment;

import lombok.Data;

import java.util.Date;

@Data
public class EveryPayBody {
    private String account_name;
    private String nonce;
    private String timestamp;
    private double amount;
    private String order_reference;
    private String customer_url;
    private String api_username;
}
