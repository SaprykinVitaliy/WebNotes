package ru.saprykin.vitaliy.webnotes.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.saprykin.vitaliy.webnotes.Model.DBAgent;
import ru.saprykin.vitaliy.webnotes.View.NoteForm;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Controller
public class MainController {

    @Value("${error.message}")
    private String errorMessage;

    private final DBAgent dbAgent;

    @Autowired
    public MainController(DBAgent dbAgent) {
        this.dbAgent = dbAgent;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = {"/notesList"}, method = RequestMethod.GET)
    public String personList(Model model) throws SQLException {

        List<Note> notes = dbAgent.getNotes();

        model.addAttribute("notes", notes);

        return "notesList";
    }

    @RequestMapping(value = {"/addNote"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        NoteForm noteForm = new NoteForm();
        model.addAttribute("noteForm", noteForm);

        return "addNote";
    }

    @RequestMapping(value = {"/addNote"}, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("noteForm") NoteForm noteForm) throws SQLException {

        String title = noteForm.getTitle();
        String text = noteForm.getText();

        if (title != null && title.length() > 0 //
                && text != null && text.length() > 0) {
            Timestamp ts = Timestamp.from(Instant.now());
            String timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);
            dbAgent.newNote(timestamp, title, text);

            return "redirect:/notesList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addNote";
    }

    @RequestMapping(value = {"/deleteNote"}, method = RequestMethod.POST)
    public String deleteNote(Model model,
                             @RequestParam(value = "_method", required = false) String _method,
                             @RequestParam(value = "id", required = false) int id) {
        try {
            if (_method.equals("delete")) {

                dbAgent.deleteNote(id);
                return "redirect:/notesList";

            } else {
                throw new IllegalArgumentException();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/notesList";
    }
}
