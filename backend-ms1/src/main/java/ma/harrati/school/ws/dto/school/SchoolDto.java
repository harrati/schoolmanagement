package  ma.harrati.school.ws.dto.school;

import ma.harrati.school.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.harrati.school.ws.dto.student.PaymentDto;
import ma.harrati.school.ws.dto.student.StudentDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolDto  extends AuditBaseDto {

    private String name  ;
    private String address  ;
    private String phoneNumber  ;
    private String description  ;

    private PrincipalDto principal ;

    private List<StaffDto> staffs ;
    private List<ProfessorDto> professors ;
    private List<StudentDto> students ;


    public SchoolDto(){
        super();
    }




    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }


    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public PrincipalDto getPrincipal(){
        return this.principal;
    }

    public void setPrincipal(PrincipalDto principal){
        this.principal = principal;
    }



    public List<StaffDto> getStaffs(){
        return this.staffs;
    }

    public void setStaffs(List<StaffDto> staffs){
        this.staffs = staffs;
    }
    public List<ProfessorDto> getProfessors(){
        return this.professors;
    }

    public void setProfessors(List<ProfessorDto> professors){
        this.professors = professors;
    }
    public List<StudentDto> getStudents(){
        return this.students;
    }

    public void setStudents(List<StudentDto> students){
        this.students = students;
    }



}
