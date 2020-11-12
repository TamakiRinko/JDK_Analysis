package methodreference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    public int no;
    private String name;

    public String print2() {
        return name;
    }

    public void print(Person person) {
        System.out.println(person);
    }
}