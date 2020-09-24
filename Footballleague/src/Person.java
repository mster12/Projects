
public class Person {
    private final Name name;
    private final String phone;
    private final String email;

    public Person(final Name name, final String phone, final String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
