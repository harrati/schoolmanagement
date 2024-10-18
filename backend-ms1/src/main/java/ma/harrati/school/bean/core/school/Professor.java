package ma.harrati.school.bean.core.school;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.harrati.school.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.harrati.school.zynerator.security.bean.User;

@Entity
@Table(name = "professor")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="professor_seq",sequenceName="professor_seq",allocationSize=1, initialValue = 1)
public class Professor  extends User    {


    public Professor(String username) {
        super(username);
    }


    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String phoneNumber;

    @Column(length = 500)
    private String speciality;











    public Professor(){
        super();
    }

    public Professor(Long id){
        this.id = id;
    }

    public Professor(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="professor_seq")
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
    public String getSpeciality(){
        return this.speciality;
    }
    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return id != null && id.equals(professor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

