package com.baconfire.authserver.dao.Person;

import com.baconfire.authserver.entity.Person;

public interface PersonDAO {
    Person savePerson(Person p);

    Person getPersonByID(int employeeID);
}
