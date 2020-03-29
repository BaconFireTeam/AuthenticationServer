package com.baconfire.authserver.dao.Person.impl;

import com.baconfire.authserver.dao.AbstractHibernateDAO;
import com.baconfire.authserver.dao.Person.PersonDAO;
import com.baconfire.authserver.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl extends AbstractHibernateDAO<Person> implements PersonDAO {
    public PersonDAOImpl() {
        setClazz(Person.class);
    }

    @Override
    public Person savePerson(Person p) {
        return (Person) getCurrentSession().merge(p);
    }

    @Override
    public Person getPersonByID(int employeeID) {
        return findById(employeeID);
    }
}
