package data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Skills {
    private int id;
    private Position position;
    private SkillLevel skillLevel;

    public Skills(Position position, SkillLevel skillLevel) {
        this.position = position;
        this.skillLevel = skillLevel;
    }

    public enum Position {
        Java,
        CSharp,
        JS,
        CPlus

    }

    public enum SkillLevel {
        Junior,
        Middle,
        Senior
    }
}


