package  ma.harrati.school.dao.specification.core.school;

import ma.harrati.school.dao.criteria.core.school.PrincipalCriteria;
import ma.harrati.school.bean.core.school.Principal;
import ma.harrati.school.zynerator.specification.AbstractSpecification;


public class PrincipalSpecification extends  AbstractSpecification<PrincipalCriteria, Principal>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
    }

    public PrincipalSpecification(PrincipalCriteria criteria) {
        super(criteria);
    }

    public PrincipalSpecification(PrincipalCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
