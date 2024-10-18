package  ma.harrati.school.dao.criteria.core.student;



import ma.harrati.school.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class PaymentCriteria extends  BaseCriteria  {

    private String amount;
    private String amountMin;
    private String amountMax;
    private LocalDateTime paymentDate;
    private LocalDateTime paymentDateFrom;
    private LocalDateTime paymentDateTo;

    private StudentCriteria student ;
    private List<StudentCriteria> students ;


    public String getAmount(){
        return this.amount;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }   
    public String getAmountMin(){
        return this.amountMin;
    }
    public void setAmountMin(String amountMin){
        this.amountMin = amountMin;
    }
    public String getAmountMax(){
        return this.amountMax;
    }
    public void setAmountMax(String amountMax){
        this.amountMax = amountMax;
    }
      
    public LocalDateTime getPaymentDate(){
        return this.paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate){
        this.paymentDate = paymentDate;
    }
    public LocalDateTime getPaymentDateFrom(){
        return this.paymentDateFrom;
    }
    public void setPaymentDateFrom(LocalDateTime paymentDateFrom){
        this.paymentDateFrom = paymentDateFrom;
    }
    public LocalDateTime getPaymentDateTo(){
        return this.paymentDateTo;
    }
    public void setPaymentDateTo(LocalDateTime paymentDateTo){
        this.paymentDateTo = paymentDateTo;
    }

    public StudentCriteria getStudent(){
        return this.student;
    }

    public void setStudent(StudentCriteria student){
        this.student = student;
    }
    public List<StudentCriteria> getStudents(){
        return this.students;
    }

    public void setStudents(List<StudentCriteria> students){
        this.students = students;
    }
}
