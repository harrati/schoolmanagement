package  ma.harrati.school.dao.specification.core.school;

import ma.harrati.school.dao.criteria.core.school.StaffCriteria;
import ma.harrati.school.bean.core.school.Staff;
import ma.harrati.school.zynerator.specification.AbstractSpecification;


public class StaffSpecification extends  AbstractSpecification<StaffCriteria, Staff>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicate("role", criteria.getRole(),criteria.getRoleLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
    }

    public StaffSpecification(StaffCriteria criteria) {
        super(criteria);
    }

    public StaffSpecification(StaffCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
