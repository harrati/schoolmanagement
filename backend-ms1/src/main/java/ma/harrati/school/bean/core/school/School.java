package ma.harrati.school.bean.core.school;

import java.util.List;





import ma.harrati.school.bean.core.student.Payment;
import ma.harrati.school.bean.core.student.Student;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.harrati.school.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "school")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="school_seq",sequenceName="school_seq",allocationSize=1, initialValue = 1)
public class School  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String address;

    @Column(length = 500)
    private String phoneNumber;

    private String description;

    private Principal principal ;

    private List<Staff> staffs ;
    private List<Professor> professors ;
    private List<Student> students ;

    public School(){
        super();
    }

    public School(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="school_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "principal")
    public Principal getPrincipal(){
        return this.principal;
    }
    public void setPrincipal(Principal principal){
        this.principal = principal;
    }
    @OneToMany(mappedBy = "school")
    public List<Staff> getStaffs(){
        return this.staffs;
    }

    public void setStaffs(List<Staff> staffs){
        this.staffs = staffs;
    }
    @OneToMany(mappedBy = "school")
    public List<Professor> getProfessors(){
        return this.professors;
    }

    public void setProfessors(List<Professor> professors){
        this.professors = professors;
    }
    @OneToMany(mappedBy = "school")
    public List<Student> getStudents(){
        return this.students;
    }

    public void setStudents(List<Student> students){
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id != null && id.equals(school.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

