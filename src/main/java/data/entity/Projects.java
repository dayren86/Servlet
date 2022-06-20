package data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Projects {
    private int id;
    private String projectName;
    private String projectDescription;
    private LocalDate dateCreation;

    public Projects(String projectName, String projectDescription, LocalDate dateCreation) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.dateCreation = dateCreation;
    }
}
