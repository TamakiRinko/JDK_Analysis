package cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CASUser {
    private int id;
    private String name;
    @Override
    public boolean equals(Object obj) {
        return id == ((CASUser) obj).id && name.equals(((CASUser) obj).name);
    }
}
