package ma.harrati.school.bean.core.student;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.harrati.school.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="payment_seq",sequenceName="payment_seq",allocationSize=1, initialValue = 1)
public class Payment  extends BaseEntity     {




    private BigDecimal amount = BigDecimal.ZERO;

    private LocalDateTime paymentDate ;

    private Student student ;


    public Payment(){
        super();
    }

    public Payment(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="payment_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public LocalDateTime getPaymentDate(){
        return this.paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate){
        this.paymentDate = paymentDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    public Student getStudent(){
        return this.student;
    }
    public void setStudent(Student student){
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id != null && id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

