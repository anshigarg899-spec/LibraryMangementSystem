public class Student extends User {

    public Student(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public String getUserType() {
        return "student";
    }
}
