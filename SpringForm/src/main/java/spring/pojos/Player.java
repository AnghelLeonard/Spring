package spring.pojos;

/**
 *
 * @author Anghel Leonard
 */
public class Player {

    private String name;
    private byte age;
    private String birthplace;
    private String residence;
    private short height;
    private byte weight;

    public Player() {
    }

    public Player(String name, byte age, String birthplace, String residence, short height, byte weight) {
        this.name = name;
        this.age = age;
        this.birthplace = birthplace;
        this.residence = residence;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public byte getWeight() {
        return weight;
    }

    public void setWeight(byte weight) {
        this.weight = weight;
    }
}
