package ru.saprykin.vitaliy.webnotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.saprykin.vitaliy.webnotes.Model.DBAgent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    private final DBAgent dbAgent;

    @Autowired
    public MainController(DBAgent dbAgent) {
        this.dbAgent = dbAgent;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/notesList"}, method = RequestMethod.GET)
    public String personList(Model model) throws SQLException {

        List<Note> notes = dbAgent.getNotes();

        model.addAttribute("notes", notes);

        return "notesList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            //persons.add(newPerson);

            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

}
