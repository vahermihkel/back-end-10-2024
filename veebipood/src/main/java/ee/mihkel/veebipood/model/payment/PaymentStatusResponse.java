package ee.mihkel.veebipood.model.payment;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentStatusResponse {
    private String account_name;
    private String order_reference;
    private Object email;
    private String customer_ip;
    private String customer_url;
    private Date payment_created_at;
    private double initial_amount;
    private double standing_amount;
    private String payment_reference;
    private String payment_link;
    private String api_username;
    private ProcessingError processing_error;
    private Object warnings;
    private int stan;
    private int fraud_score;
    private String payment_state;
    private String payment_method;
    private ObDetails ob_details;
    private Date transaction_time;
}

@Data
class ProcessingError{
    private int code;
    private String message;
}

@Data
class ObDetails{
    private Object error;
    private Object debtor_iban;
    private String creditor_iban;
    private String ob_payment_reference;
    private String ob_payment_state;
    private Object token;
}
