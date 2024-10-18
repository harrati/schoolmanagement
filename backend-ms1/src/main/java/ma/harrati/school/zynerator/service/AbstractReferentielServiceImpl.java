package ma.harrati.school.zynerator.service;

import ma.harrati.school.zynerator.bean.BaseEntity;
import ma.harrati.school.zynerator.criteria.BaseCriteria;
import ma.harrati.school.zynerator.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends BaseEntity, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
