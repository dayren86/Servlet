package data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Companies {
    int id;
    String itCompanies;
    String companyDescription;

    public Companies(String itCompanies, String companyDescription) {
        this.itCompanies = itCompanies;
        this.companyDescription = companyDescription;
    }
}
