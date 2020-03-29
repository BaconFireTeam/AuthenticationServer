package com.baconfire.authserver.dao.RegistrationToken.impl;

import com.baconfire.authserver.dao.AbstractHibernateDAO;
import com.baconfire.authserver.dao.RegistrationToken.RegistrationTokenDAO;
import com.baconfire.authserver.entity.RegistrationToken;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RegistrationTokenDAOImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationTokenDAOImpl.class);

    public RegistrationTokenDAOImpl() {
        setClazz(RegistrationToken.class);
    }

    @Override
    public RegistrationToken validateTokenAndEmail(String token, String email) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<RegistrationToken> criteriaQuery = criteriaBuilder.createQuery(RegistrationToken.class);

        Root<RegistrationToken> registrationTokenRoot = criteriaQuery.from(RegistrationToken.class);
        criteriaQuery.select(registrationTokenRoot)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(registrationTokenRoot.get("token"), token),
                        criteriaBuilder.equal(registrationTokenRoot.get("email"), email)
                ));

        Query<RegistrationToken> query = getCurrentSession().createQuery(criteriaQuery);
        RegistrationToken registrationToken = null;

        try {
            registrationToken = query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return registrationToken;
    }

    @Override
    public boolean isTokenExisted(String token) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<RegistrationToken> criteriaQuery = criteriaBuilder.createQuery(RegistrationToken.class);

        Root<RegistrationToken> registrationTokenRoot = criteriaQuery.from(RegistrationToken.class);
        criteriaQuery.select(registrationTokenRoot).where(criteriaBuilder.equal(registrationTokenRoot.get("token"), token));

        Query<RegistrationToken> query = getCurrentSession().createQuery(criteriaQuery);

        try {
            RegistrationToken registrationToken = query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }

        return true;
    }

    @Override
    public RegistrationToken save(RegistrationToken token) {
        return super.save(token);
    }
}
