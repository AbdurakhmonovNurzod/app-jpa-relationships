package uz.itm.appjparelationships.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupLoader {
    private String name;
    private Integer facultyId;
}
