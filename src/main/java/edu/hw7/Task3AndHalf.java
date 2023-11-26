package edu.hw7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task3AndHalf {

    public static class ReadWriteLockPersonDatabase implements PersonDatabase {

        private final ReadWriteLock lock;
        private final Map<Integer, Person> idPersonMap;
        private final Map<String, List<Person>> namePersonMap;
        private final Map<String, List<Person>> addressPersonMap;
        private final Map<String, List<Person>> phoneNumberPersonMap;

        public ReadWriteLockPersonDatabase() {
            lock = new ReentrantReadWriteLock();
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
                reverseIndex.put(person.name(), newList);
            }
        }

        @Override
        public void add(Person person) {
            lock.writeLock().lock();
            try {
                var oldPerson = idPersonMap.get(person.id());
                if (oldPerson != null) {
                    person = updatePerson(oldPerson, person);
                }
                idPersonMap.put(person.id(), person);
                if (isPersonFieldsNotNull(person)) {
                    addToReverseIndexes(person);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }

        @Override
        public synchronized void delete(int id) {
            lock.writeLock().lock();
            try {
                var person = idPersonMap.get(id);
                if (person == null) {
                    throw new IllegalArgumentException("No person by this id");
                }
                if (isPersonFieldsNotNull(person)) {
                    deleteFromReverseIndexes(person);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }

        @Override
        public List<Person> findByName(String name) {
            lock.readLock().lock();
            try {
                return namePersonMap.get(name);
            } finally {
                lock.readLock().unlock();
            }
        }

        @Override
        public synchronized List<Person> findByAddress(String address) {
            lock.readLock().lock();
            try {
                return addressPersonMap.get(address);
            } finally {
                lock.readLock().unlock();
            }
        }

        @Override
        public synchronized List<Person> findByPhone(String phone) {
            lock.readLock().lock();
            try {
                return phoneNumberPersonMap.get(phone);
            } finally {
                lock.readLock().unlock();
            }
        }
    }
}
