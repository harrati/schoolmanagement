package  ma.harrati.school.ws.dto.student;

import ma.harrati.school.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto  extends AuditBaseDto {

    private BigDecimal amount  ;
    private String paymentDate ;

    private StudentDto student ;



    public PaymentDto(){
        super();
    }




    public BigDecimal getAmount(){
        return this.amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getPaymentDate(){
        return this.paymentDate;
    }
    public void setPaymentDate(String paymentDate){
        this.paymentDate = paymentDate;
    }


    public StudentDto getStudent(){
        return this.student;
    }

    public void setStudent(StudentDto student){
        this.student = student;
    }






}
