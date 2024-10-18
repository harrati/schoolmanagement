package  ma.harrati.school.dao.specification.core.student;

import ma.harrati.school.dao.criteria.core.student.StudentCriteria;
import ma.harrati.school.bean.core.student.Student;
import ma.harrati.school.zynerator.specification.AbstractSpecification;


public class StudentSpecification extends  AbstractSpecification<StudentCriteria, Student>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicate("registrationDate", criteria.getRegistrationDate(), criteria.getRegistrationDateFrom(), criteria.getRegistrationDateTo());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateFk("school","id", criteria.getSchool()==null?null:criteria.getSchool().getId());
        addPredicateFk("school","id", criteria.getSchools());
    }

    public StudentSpecification(StudentCriteria criteria) {
        super(criteria);
    }

    public StudentSpecification(StudentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
