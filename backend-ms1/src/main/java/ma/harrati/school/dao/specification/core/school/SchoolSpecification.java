package  ma.harrati.school.dao.specification.core.school;

import ma.harrati.school.dao.criteria.core.school.SchoolCriteria;
import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.zynerator.specification.AbstractSpecification;


public class SchoolSpecification extends  AbstractSpecification<SchoolCriteria, School>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("name", criteria.getName(),criteria.getNameLike());
        addPredicate("address", criteria.getAddress(),criteria.getAddressLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicateFk("principal","id", criteria.getPrincipal()==null?null:criteria.getPrincipal().getId());
        addPredicateFk("principal","id", criteria.getPrincipals());
        addPredicateFk("principal","email", criteria.getPrincipal()==null?null:criteria.getPrincipal().getEmail());
    }

    public SchoolSpecification(SchoolCriteria criteria) {
        super(criteria);
    }

    public SchoolSpecification(SchoolCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
