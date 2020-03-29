package com.baconfire.onboardwebapp.dao.Person;

import com.baconfire.onboardwebapp.domain.Person;

public interface PersonDAO {
    void savePerson(Person p);

    Person getPersonByID(int employeeID);
}
