public class Person {
    private static int nextID = 1;

    private int personID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int telephone;

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
        return false;
    }

    public int register(String firstName, String lastName, String email, String password, int telephone){
        return 0;
    }

    public int getPersonID() {
        return personID;
    }

    // String uniqueID = UUID.randomUUID().toString();
}
