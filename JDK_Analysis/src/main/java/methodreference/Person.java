package methodreference;

public class Person {
    public int no;
    private String name;
    public Person(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public Person() {

    }

    public String getName() {
//        System.out.println(name);
        return name;
    }

    public int getNo() {
        return no;
    }

    @Override
    public String toString() {
        return "Person{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public String print2() {
        return name;
    }

    public void print(Person person) {
        System.out.println(person);
    }
}