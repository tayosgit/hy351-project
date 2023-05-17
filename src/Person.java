/**
 * The Person class is the generalization for the classes "Employee" and "Administrator" and represents a Person with
 * basic information.
 */
public class Person {
    private static int nextID = 1;

    private int personID;       // Unique identifier for a person
    private String firstName;   // First name of a person
    private String lastName;    // Last name of a person
    private String email;       // Email address of a person
    private String password;    // Password of a persons account
    private int telephone;      // Telephone number of a person


    /**
     * Constructs a Person object with the given information.
     *
     * @param firstName The first name of the person.
     * @param lastName  The last name of the person.
     * @param email     The email address of the person.
     * @param password  The password of the person.
     * @param telephone The telephone number of the person.
     */
    public Person(String firstName, String lastName, String email, String password, int telephone) {
        this.personID = nextID;
        nextID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }

    public boolean signIn(){
        // Implement for bonus
        return false;
    }

    public int register(String firstName, String lastName, String email, String password, int telephone){
        // Implement for bonus
        return 0;
    }

    /**
     * Retrieves the person's unique identifier.
     *
     * @return The person's ID.
     */
    public int getPersonID() {
        return personID;
    }

}
