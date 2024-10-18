package ma.harrati.school.bean.core.student;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.harrati.school.bean.core.school.School;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.harrati.school.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.harrati.school.zynerator.security.bean.User;

@Entity
@Table(name = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="student_seq",sequenceName="student_seq",allocationSize=1, initialValue = 1)
public class Student  extends User    {


    public Student(String username) {
        super(username);
    }


    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String phoneNumber;

    private LocalDateTime registrationDate ;









    private School school ;

    private List<Payment> payments ;

    public Student(){
        super();
    }

    public Student(Long id){
        this.id = id;
    }

    public Student(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="student_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public LocalDateTime getRegistrationDate(){
        return this.registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate){
        this.registrationDate = registrationDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school")
    public School getSchool(){
        return this.school;
    }
    public void setSchool(School school){
        this.school = school;
    }
    @OneToMany(mappedBy = "student")
    public List<Payment> getPayments(){
        return this.payments;
    }

    public void setPayments(List<Payment> payments){
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id != null && id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

