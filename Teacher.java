public class Teacher extends User {

    public Teacher(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public String getUserType() {
        return "teacher";
    }
}
