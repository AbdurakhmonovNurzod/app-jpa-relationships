package uz.itm.appjparelationships.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentLoader {
    private String firstName;
    private String lastName;
    private String city;
    private String district;
    private String street;
    private Integer groupId;
}
