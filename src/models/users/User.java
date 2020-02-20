package models.users;

public class User {
    private UserCredentials credentials;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipOrPostalCode;
    private String country;
    private String mobilePhone;
    private String addressAlias;

    User(String email, String password) {
        this.credentials = new UserCredentials(email, password);
    }

    public User(UserCredentials credentials, String firstName, String lastName, String address, String city, String state, String zipOrPostalCode, String country, String mobilePhone, String addressAlias) {
        this.credentials = credentials;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipOrPostalCode = zipOrPostalCode;
        this.country = country;
        this.mobilePhone = mobilePhone;
        this.addressAlias = addressAlias;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public String getEmail() {
        return getCredentials().getEmail();
    }

    public String getPassword() {
        return getCredentials().getPassword();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipOrPostalCode() {
        return zipOrPostalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getAddressAlias() {
        return addressAlias;
    }

}
