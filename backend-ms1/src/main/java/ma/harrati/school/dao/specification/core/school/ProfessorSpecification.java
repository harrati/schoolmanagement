package  ma.harrati.school.dao.specification.core.school;

import ma.harrati.school.dao.criteria.core.school.ProfessorCriteria;
import ma.harrati.school.bean.core.school.Professor;
import ma.harrati.school.zynerator.specification.AbstractSpecification;


public class ProfessorSpecification extends  AbstractSpecification<ProfessorCriteria, Professor>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicate("speciality", criteria.getSpeciality(),criteria.getSpecialityLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
    }

    public ProfessorSpecification(ProfessorCriteria criteria) {
        super(criteria);
    }

    public ProfessorSpecification(ProfessorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
