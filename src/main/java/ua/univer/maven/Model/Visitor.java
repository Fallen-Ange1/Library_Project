package ua.univer.maven.Model;

public class Visitor {
    private String name;
    private int id;
    private String lastName;
    private String pasportD;

    public Visitor(String name, int id, String lastName, String pasportD) {
        this.name = name;
        this.id = id;
        this.lastName = lastName;
        this.pasportD = pasportD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasportD() {
        return pasportD;
    }

    public void setPasportD(String pasportD) {
        this.pasportD = pasportD;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", pasportD='" + pasportD + '\'' +
                '}';
    }
}
