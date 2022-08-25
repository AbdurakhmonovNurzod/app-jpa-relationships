package uz.itm.appjparelationships.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityLoader {
    private String name;
    private String city;
    private String district;
    private String street;
}
