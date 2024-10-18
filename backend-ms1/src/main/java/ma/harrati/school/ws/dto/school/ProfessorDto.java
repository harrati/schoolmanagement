package  ma.harrati.school.ws.dto.school;

import ma.harrati.school.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.harrati.school.zynerator.security.bean.Role;
import java.util.Collection;
import ma.harrati.school.zynerator.security.ws.dto.UserDto;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessorDto  extends UserDto {

    private String firstName  ;
    private String lastName  ;
    private String phoneNumber  ;
    private String speciality  ;




    private Collection<Role> roles;
    public ProfessorDto(){
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


    public String getSpeciality(){
        return this.speciality;
    }
    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }

















    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
