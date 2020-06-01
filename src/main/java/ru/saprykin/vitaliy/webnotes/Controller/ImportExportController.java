package ru.saprykin.vitaliy.webnotes.Controller;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.saprykin.vitaliy.webnotes.DAO.NoteDAO;
import ru.saprykin.vitaliy.webnotes.Model.Note;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

@Controller
public class ImportExportController {
    private final NoteDAO noteDAO;
    String pathToDocumentsFolder;

    @Autowired
    public ImportExportController(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
        pathToDocumentsFolder = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    }

    @RequestMapping(value = {"/exportNote"}, method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<byte[]> exportNote(Model model,
                                         @RequestParam(value = "id", required = true) int id,
                                         HttpServletResponse response) {
        try {

            Note note = noteDAO.getOneNote(id);


            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            String documentsPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
            File file = new File(documentsPath + "/"+ "note" + ".json");
            /*writer.writeValue(file , note);*/
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, note);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            response.setHeader("Content-Disposition", "attachment; filename=note.json");

            /*return new FileSystemResource(file);*/
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return new HttpEntity<byte[]>(fileContent, headers);

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = {"/import"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "import";
    }


    @RequestMapping(value = {"/import"}, method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            return "import";
        }

        try {
            byte[] bytes = file.getBytes();
            File jsonFile = new File(pathToDocumentsFolder + "/" + file.getOriginalFilename());
            try (FileOutputStream stream = new FileOutputStream(jsonFile)) {
                stream.write(bytes);
            }

            ObjectMapper mapper = new ObjectMapper();
            Note note = mapper.readValue(jsonFile, Note.class);
            noteDAO.create(note);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/notesList";
    }
}
