package com.baconfire.onboardwebapp.dao.RegistrationToken.impl;

import com.baconfire.onboardwebapp.dao.AbstractHibernateDAO;
import com.baconfire.onboardwebapp.dao.RegistrationToken.RegistrationTokenDAO;
import com.baconfire.onboardwebapp.domain.RegistrationToken;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationTokenDAOImpl extends AbstractHibernateDAO<RegistrationToken> implements RegistrationTokenDAO {
    public RegistrationTokenDAOImpl() {
        setClazz(RegistrationToken.class);
    }

    @Override
    public boolean checkToken(String token, String email) {
        RegistrationToken t = getCurrentSession().createQuery("FROM RegistrationToken WHERE email =: email", RegistrationToken.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findFirst().orElse(null);

        return (t != null) && (token.equals(t.getToken()));
    }
}
