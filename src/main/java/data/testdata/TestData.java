package data.testdata;

import data.entity.Companies;
import data.entity.Developers;
import data.entity.Projects;
import data.entity.Skills;
import data.queries.RequestsForDevelopers;
import data.queries.RequestsForSkills;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestData {

    public List<Developers> dataDevelopers() {
        List<Developers> list = new ArrayList<>();

        list.add(new Developers("Andrey", 25, Developers.Sex.male, 3000));
        list.add(new Developers("Bogdan", 30, Developers.Sex.male, 2000));
        list.add(new Developers("Denis", 35, Developers.Sex.male, 5000));
        list.add(new Developers("Tanya", 30, Developers.Sex.female, 6000));
        list.add(new Developers("Olya", 40, Developers.Sex.female, 3500));
        list.add(new Developers("Ira", 50, Developers.Sex.female, 3400));
        return list;
    }

    public List<Skills> dataSkills() {
        List<Skills> list = new ArrayList<>();

        list.add(new Skills(Skills.Position.Java, Skills.SkillLevel.Junior));
        list.add(new Skills(Skills.Position.Java, Skills.SkillLevel.Middle));
        list.add(new Skills(Skills.Position.Java, Skills.SkillLevel.Senior));
        list.add(new Skills(Skills.Position.Cplus, Skills.SkillLevel.Junior));
        list.add(new Skills(Skills.Position.Cplus, Skills.SkillLevel.Middle));
        list.add(new Skills(Skills.Position.Cplus, Skills.SkillLevel.Senior));
        list.add(new Skills(Skills.Position.JS, Skills.SkillLevel.Junior));
        list.add(new Skills(Skills.Position.JS, Skills.SkillLevel.Middle));
        list.add(new Skills(Skills.Position.JS, Skills.SkillLevel.Senior));
        list.add(new Skills(Skills.Position.SSharp, Skills.SkillLevel.Junior));
        list.add(new Skills(Skills.Position.SSharp, Skills.SkillLevel.Middle));
        list.add(new Skills(Skills.Position.SSharp, Skills.SkillLevel.Senior));

        return list;
    }

    public List<Projects> dataProjects() {
        List<Projects> list = new ArrayList<>();
        list.add(new Projects("Сhat bot", "Creation and support of chat bots", LocalDate.of(2005, 5, 25)));
        list.add(new Projects("Sites", "Website development", LocalDate.of(2015,11,5 )));
        list.add(new Projects("Database", "Creation and support of Database",LocalDate.of(2020, 7 ,24)));
        return list;
    }

    public List<Companies> dataCompanies() {
        List<Companies> list = new ArrayList<>();
        list.add(new Companies("Gamedev", "Games development"));
        list.add(new Companies("WebDev", "Website development"));
        list.add(new Companies("Mobile", "Mobile development"));
        return list;
    }
}
