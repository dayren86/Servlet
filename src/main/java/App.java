import data.connection.Db;
import data.connection.DbInitService;

import data.queries.RequestsForCompanies;
import data.queries.RequestsForDevelopers;
import data.queries.RequestsForProjects;
import data.queries.RequestsForSkills;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        new DbInitService().dbInit();

        Db instance = Db.getInstance();

        RequestsForDevelopers requestsForDevelopers = new RequestsForDevelopers(instance);
//        requestsForDevelopers.createDevelopers("Andrey", 25, "male");
//        requestsForDevelopers.createDevelopers("Bogdan", 30, "male");
//        requestsForDevelopers.createDevelopers("Denis", 35, "male");
//        requestsForDevelopers.createDevelopers("Tanya", 30, "female");
//        requestsForDevelopers.createDevelopers("Olya", 40, "female");
//        requestsForDevelopers.createDevelopers("Ira", 50, "female");
//        System.out.println(requestsForDevelopers.selectDevelopersByName("Bogdan"));
        System.out.println(requestsForDevelopers.getIdByName("Andrey"));


//        RequestsForSkills requestsForSkills = new RequestsForSkills(instance);
//        requestsForSkills.createSkills("Java","Junior");
//        requestsForSkills.createSkills("Java","Middle");
//        requestsForSkills.createSkills("Java","Senior");
//        System.out.println(requestsForSkills.selectSkillsByPosition("Java"));

//        RequestsForProjects requestsForProjects = new RequestsForProjects(instance);
//        requestsForProjects.createProjects("Сhat bot", "Creation and support of chat bots");
//        requestsForProjects.createProjects("Sites", "Website development");
//        requestsForProjects.createProjects("Database", "Creation and support of Database");
//        System.out.println(requestsForProjects.selectProjectsByProjectName("Sites"));

//         RequestsForCompanies requestsForCompanies = new RequestsForCompanies(instance);
////         requestsForCompanies.createCompanies("Gamedev", "Games development");
////         requestsForCompanies.createCompanies("WebDev", "Website development");
////         requestsForCompanies.createCompanies("Mobile", "Mobile development");
//        System.out.println(requestsForCompanies.selectCompaniesByItCompanies("Gamedev"));

    }
}
