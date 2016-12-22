package com.company.mssqlview.service;

import com.company.mssqlview.helper.FieldsMapper;
import com.haulmont.bali.db.ArrayHandler;
import com.haulmont.bali.db.QueryRunner;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Service(QueryService.NAME)
public class QueryServiceBean implements QueryService {

    @Inject
    private Persistence persistence;

    @Override
    public <T extends AbstractNotPersistentEntity> List<T> executeQuery(String query, Class<T> clazz) {
        List<T> result;

        try (Transaction tx = persistence.createTransaction()){
            javax.persistence.Query q = persistence.getEntityManager()
                    .getDelegate().createNativeQuery(query);
            q.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
            result = FieldsMapper.doMapResult(clazz, q.getResultList());
            tx.commit();
        }

        return result;
    }

    @Override
    public int getCount(String countQuery) {
        QueryRunner runner = new QueryRunner(persistence.getDataSource());
        try {
            Object[] row = runner.query(countQuery, new ArrayHandler());
            return ((Number) row[0]).intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}