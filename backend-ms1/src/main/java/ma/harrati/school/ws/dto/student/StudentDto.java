package  ma.harrati.school.ws.dto.student;

import ma.harrati.school.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.harrati.school.zynerator.security.bean.Role;
import java.util.Collection;
import ma.harrati.school.zynerator.security.ws.dto.UserDto;
import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.harrati.school.ws.dto.school.SchoolDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto  extends UserDto {

    private String firstName  ;
    private String lastName  ;
    private String phoneNumber  ;
    private String registrationDate ;

    private SchoolDto school ;

    private List<PaymentDto> payments ;


    private Collection<Role> roles;
    public StudentDto(){
        super();
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getRegistrationDate(){
        return this.registrationDate;
    }
    public void setRegistrationDate(String registrationDate){
        this.registrationDate = registrationDate;
    }










    public SchoolDto getSchool(){
        return this.school;
    }

    public void setSchool(SchoolDto school){
        this.school = school;
    }



    public List<PaymentDto> getPayments(){
        return this.payments;
    }

    public void setPayments(List<PaymentDto> payments){
        this.payments = payments;
    }




    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
