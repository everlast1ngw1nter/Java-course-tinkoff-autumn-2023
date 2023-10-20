package edu.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import org.jetbrains.annotations.NotNull;



public class Task5 {

    private Task5() {
    }

    public static Contact[] parseContacts(String[] stringContacts, String order) {
        if (stringContacts == null) {
            return new Contact[] {};
        }
        ArrayList<Contact> contacts = new ArrayList<>();
        for (var elem : stringContacts) {
            String[] names = elem.split(" ");
            String firstName = names[0];
            String secondName = switch (names.length) {
                case 1 -> "";
                case 2 -> names[1];
                default -> throw new IllegalArgumentException();
            };
            contacts.add(new Contact(firstName, secondName));
        }
        contacts.sort(Comparator.naturalOrder());
        return switch (order) {
            case "DESC" -> contacts.reversed().toArray(new Contact[0]);
            case "ASC" -> contacts.toArray(new Contact[0]);
            default -> throw new IllegalArgumentException();
        };
    }

    public record Contact(String firstName, String secondName) implements Comparable<Contact> {

        public Contact(String firstName) {
            this(firstName, "");
        }

        @Override
        public int compareTo(@NotNull Task5.Contact o) {
            if (!secondName.isEmpty() && !o.secondName.isEmpty()) {
                return secondName.compareTo(o.secondName);
            }
            if (secondName.isEmpty() && !o.secondName.isEmpty()) {
                return firstName.compareTo(o.secondName);
            }
            if (!secondName.isEmpty()) {
                return secondName.compareTo(o.firstName);
            }
            return firstName.compareTo(o.firstName);
        }
    }
}
