package edu.hw3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;


public class Task5 {

    private Task5() {
    }

    public static List<Contact> parseContacts(List<String> stringContacts, Order order) {
        if (stringContacts == null) {
            return new ArrayList<>();
        }
        return stringContacts
                .stream()
                .map(Contact::create)
                .sorted(order == Order.ASC ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public record Contact(String firstName, String secondName) implements Comparable<Contact> {

        public Contact(String firstName) {
            this(firstName, "");
        }

        public static Contact create(String fullName) {
            var names = fullName.strip().split(" +");
            String firstName = names[0];
            String secondName = switch (names.length) {
                case 1 -> "";
                case 2 -> names[1];
                default -> throw new IllegalArgumentException("String must contain 1 or 2 words"
                        + "separated by whitespace");
            };
            return new Contact(firstName, secondName);
        }

        @Override
        public int compareTo(@NotNull Task5.Contact o) {
            var thisComparableNamePart = secondName.isEmpty() ? firstName : secondName;
            var otherComparableNamePart = o.secondName.isEmpty() ? o.firstName : o.secondName;
            return thisComparableNamePart.compareTo(otherComparableNamePart);
        }
    }

    public enum Order {
        ASC,
        DESC
    }
}
