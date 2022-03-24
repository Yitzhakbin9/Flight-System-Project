package POCO;

public class Customer implements POCO {

    public int id;
    public String firstName;
    public String lastName;
    public String address;
    public String phoneNumber;
    public String creditCard;
    public int userId;

    public Customer(int id, String firstName, String lastName, String address, String phoneNumber, String creditCard, int userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", userId=" + userId +
                '}';
    }
}
