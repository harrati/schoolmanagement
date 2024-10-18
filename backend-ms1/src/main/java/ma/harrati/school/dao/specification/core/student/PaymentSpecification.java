package  ma.harrati.school.dao.specification.core.student;

import ma.harrati.school.dao.criteria.core.student.PaymentCriteria;
import ma.harrati.school.bean.core.student.Payment;
import ma.harrati.school.zynerator.specification.AbstractSpecification;


public class PaymentSpecification extends  AbstractSpecification<PaymentCriteria, Payment>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("amount", criteria.getAmount(), criteria.getAmountMin(), criteria.getAmountMax());
        addPredicate("paymentDate", criteria.getPaymentDate(), criteria.getPaymentDateFrom(), criteria.getPaymentDateTo());
        addPredicateFk("student","id", criteria.getStudent()==null?null:criteria.getStudent().getId());
        addPredicateFk("student","id", criteria.getStudents());
        addPredicateFk("student","email", criteria.getStudent()==null?null:criteria.getStudent().getEmail());
    }

    public PaymentSpecification(PaymentCriteria criteria) {
        super(criteria);
    }

    public PaymentSpecification(PaymentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
