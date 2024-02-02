package edu.hw7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> idPersonMap;
    private final Map<String, List<Person>> namePersonMap;
    private final Map<String, List<Person>> addressPersonMap;
    private final Map<String, List<Person>> phoneNumberPersonMap;

    public AbstractPersonDatabase() {
        idPersonMap = new HashMap<>();
        namePersonMap = new HashMap<>();
        addressPersonMap = new HashMap<>();
        phoneNumberPersonMap = new HashMap<>();
    }

    private Person updatePerson(Person oldPerson, Person newPerson) {
        if (oldPerson.id() != newPerson.id()) {
            throw new IllegalArgumentException("Persons must have the same index");
        }
        String name = newPerson.name() == null ? oldPerson.name() : newPerson.name();
        String address = newPerson.address() == null ? oldPerson.address() : newPerson.address();
        String phoneNumber  = newPerson.phoneNumber() == null ? oldPerson.phoneNumber() : newPerson.phoneNumber();
        return new Person(oldPerson.id(), name, address, phoneNumber);
    }

    private boolean isPersonFieldsNotNull(Person person) {
        return person.name() != null
                && person.address() != null
                && person.phoneNumber() != null;
    }

    private void deleteFromReverseIndexes(Person person) {
        deleteFromSingleReverseIndex(person.name(), person, namePersonMap);
        deleteFromSingleReverseIndex(person.address(), person, addressPersonMap);
        deleteFromSingleReverseIndex(person.phoneNumber(), person, phoneNumberPersonMap);
    }

    private void deleteFromSingleReverseIndex(String key, Person person,
                                              Map<String, List<Person>> reverseIndex) {
        reverseIndex.get(key).remove(person);
    }

    private void addToReverseIndexes(Person person) {
        addToSingleReverseIndex(person.name(), person, namePersonMap);
        addToSingleReverseIndex(person.address(), person, addressPersonMap);
        addToSingleReverseIndex(person.phoneNumber(), person, phoneNumberPersonMap);
    }

    private void addToSingleReverseIndex(String key, Person person,
                                         Map<String, List<Person>> reverseIndex) {
        if (reverseIndex.containsKey(key)) {
            reverseIndex.get(key).add(person);
        } else {
            var newList =  new ArrayList<Person>();
            newList.add(person);
            reverseIndex.put(key, newList);
        }
    }

    @Override
    public void add(Person person) {
        var oldPerson = idPersonMap.get(person.id());
        var newPerson = person;
        if (oldPerson != null) {
            newPerson = updatePerson(oldPerson, person);
        }
        idPersonMap.put(newPerson.id(), newPerson);
        if (isPersonFieldsNotNull(newPerson)) {
            addToReverseIndexes(newPerson);
        }
    }

    @Override
    public void delete(int id) {
        var person = idPersonMap.get(id);
        if (person == null) {
            throw new IllegalArgumentException("No person by this id");
        }
        if (isPersonFieldsNotNull(person)) {
            deleteFromReverseIndexes(person);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        var personsList = namePersonMap.get(name);
        if (personsList == null) {
            return List.of();
        }
        return Collections.unmodifiableList(personsList);
    }

    @Override
    public List<Person> findByAddress(String address) {
        var personsList = addressPersonMap.get(address);
        if (personsList == null) {
            return List.of();
        }
        return Collections.unmodifiableList(personsList);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        var personsList = phoneNumberPersonMap.get(phone);
        if (personsList == null) {
            return List.of();
        }
        return Collections.unmodifiableList(personsList);
    }
}
