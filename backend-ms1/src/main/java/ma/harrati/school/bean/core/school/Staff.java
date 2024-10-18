package ma.harrati.school.bean.core.school;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.harrati.school.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.harrati.school.zynerator.security.bean.User;

@Entity
@Table(name = "staff")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="staff_seq",sequenceName="staff_seq",allocationSize=1, initialValue = 1)
public class Staff  extends User    {


    public Staff(String username) {
        super(username);
    }


    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String phoneNumber;

    @Column(length = 500)
    private String role;











    public Staff(){
        super();
    }

    public Staff(Long id){
        this.id = id;
    }

    public Staff(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="staff_seq")
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
    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id != null && id.equals(staff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

