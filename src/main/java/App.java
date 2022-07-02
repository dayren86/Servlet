import data.connection.Db;
import data.connection.DbInitService;

import data.entity.Companies;
import data.entity.Developers;
import data.entity.Projects;
import data.entity.Skills;
import data.queries.RequestsForCompanies;
import data.queries.RequestsForDevelopers;
import data.queries.RequestsForProjects;
import data.queries.RequestsForSkills;
import data.queries.hw.HwQuery;
import data.testdata.TestData;

import javax.swing.text.html.HTML;
import java.sql.SQLException;
import java.time.LocalDate;


public class App {
    public static void main(String[] args) throws SQLException {
//        new DbInitService().dbInit();

        Db instance = Db.getInstance();

        RequestsForDevelopers requestsForDevelopers = new RequestsForDevelopers(instance);
        RequestsForSkills requestsForSkills = new RequestsForSkills(instance);
        RequestsForProjects requestsForProjects = new RequestsForProjects(instance);
        RequestsForCompanies requestsForCompanies = new RequestsForCompanies(instance);

//        Заполнить базу тестовыми данными
//        requestsForDevelopers.createDevelopersFromList(new TestData().dataDevelopers());
//        requestsForSkills.createSkillsFromList(new TestData().dataSkills());
//        requestsForProjects.createProjectsFromList(new TestData().dataProjects());
//        requestsForCompanies.createCompaniesFromList(new TestData().dataCompanies());
//        requestsForDevelopers.addDevelopersSkills(
//                requestsForDevelopers.selectDevelopersByName("Bogdan"),
//                requestsForSkills.selectSkillsByPosition("Java", "Junior")
//        );
//        requestsForDevelopers.addDevelopersSkills(
//                requestsForDevelopers.selectDevelopersByName("Andrey"),
//                requestsForSkills.selectSkillsByPosition("Java", "Middle"));
//        requestsForDevelopers.addDevelopersSkills(
//                requestsForDevelopers.selectDevelopersByName("Tanya"),
//                requestsForSkills.selectSkillsByPosition("SSharp", "Middle")
//        );
//        requestsForDevelopers.addDevelopersSkills(
//                requestsForDevelopers.selectDevelopersByName("Denis"),
//                requestsForSkills.selectSkillsByPosition("SSharp", "Senior")
//        );
//        requestsForDevelopers.addDevelopersSkills(
//                requestsForDevelopers.selectDevelopersByName("Olya"),
//                requestsForSkills.selectSkillsByPosition("JS", "Senior")
//        );
//        requestsForDevelopers.addDevelopersSkills(
//                requestsForDevelopers.selectDevelopersByName("Ira"),
//                requestsForSkills.selectSkillsByPosition("JS", "Middle")
//        );
//        int[] developers = {1,2,3,4,5,6};
//        int[] projects = {1,1,2,2,3,3};
//        requestsForDevelopers.addDeveloperToProjects(developers, projects);
//

        //Запросы домашнего задания
        HwQuery hwQuery = new HwQuery(instance);
        hwQuery.getSalary("Sites");
//        hwQuery.getDevelopersBYProject("Database");
//        hwQuery.getDevelopersByProgrammingLanguage("Java");
//        hwQuery.getDevelopersBySkillLevel("Middle");
//        hwQuery.getListProjects();

//        System.out.println(requestsForDevelopers.selectDevelopersByName("Andrey"));
//        System.out.println(requestsForSkills.selectSkillsByPosition("Java", "Senior"));
//        System.out.println(requestsForProjects.selectProjectsByProjectName("Sites"));
//        System.out.println(requestsForCompanies.selectCompaniesByItCompanies("Gamedev"));


//        requestsForDevelopers.updateDevelopersById(new Developers("Andrey", 18, Developers.Sex.male,10000));
//        requestsForProjects.updateProjects(3, new Projects("Games","Games development", LocalDate.of(2021, 5, 8)));
//        requestsForSkills.updateSkills(5, new Skills(Skills.Position.JS, Skills.SkillLevel.Junior ));
//        requestsForCompanies.updateCompanies(2, new Companies("Melcosoft", "windows"));

    }
}
