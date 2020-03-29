package com.baconfire.onboardwebapp.dao.Person.impl;

import com.baconfire.onboardwebapp.dao.AbstractHibernateDAO;
//import com.baconfire.onboardwebapp.dao.Permission.PermissionDAO;
import com.baconfire.onboardwebapp.dao.Person.PersonDAO;
import com.baconfire.onboardwebapp.domain.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl extends AbstractHibernateDAO<Person> implements PersonDAO {
    public PersonDAOImpl() {
        setClazz(Person.class);
    }

    @Override
    public void savePerson(Person p) {
        getCurrentSession().merge(p);
    }

    @Override
    public Person getPersonByID(int employeeID) {
        return findById(employeeID);
    }
}
