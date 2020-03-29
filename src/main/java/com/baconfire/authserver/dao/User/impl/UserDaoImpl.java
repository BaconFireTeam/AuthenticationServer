package com.baconfire.authserver.dao.User.impl;

import com.baconfire.authserver.dao.AbstractHibernateDAO;
import com.baconfire.authserver.dao.User.UserDao;
import com.baconfire.authserver.entity.Role;
import com.baconfire.authserver.entity.User;
import com.baconfire.authserver.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl extends AbstractHibernateDAO<User> implements UserDao {
    public UserDaoImpl() {
        setClazz(User.class);
    }

    @Override
    public User getUserByUserName(String username) {
        String hql = "FROM User WHERE username = :username";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserRole getUserRole(int id) {
        String hql = "FROM UserRole WHERE userId = :id";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        try {
            UserRole ur = (UserRole) query.getSingleResult();
            return ur;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Role> getRolesByUserId(int userId) {
        String sql = "SELECT r.Id, r.RoleName, r.Description, r.CreateDate, r.LastModificationUser, r.ModificationDate " +
                "FROM User u, UserRole ur, Role r " +
                "WHERE u.Id = ur.UserID " +
                "AND ur.RoleID = r.Id " +
                "AND ur.ActiveFlag = 1 and u.Id = :userId";
        Query<Role> query = getCurrentSession().createNativeQuery(sql, Role.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public boolean checkUsername(String username) {
        List users = getCurrentSession().createQuery("FROM User WHERE username =: un")
                .setParameter("un", username)
                .getResultList();
        return users.size() == 0;
    }

    @Override
    public void registerUser(User user) {
        getCurrentSession().merge(user);
    }
}
