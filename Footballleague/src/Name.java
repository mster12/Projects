
public class Name {
    private final String firstName;
    private final String middleName;
    private final String lastName;

    public Name(final String firstName, final String middleName, final String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }
}
