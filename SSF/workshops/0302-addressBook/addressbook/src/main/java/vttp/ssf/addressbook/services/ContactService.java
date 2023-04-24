package vttp.ssf.addressbook.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import vttp.ssf.addressbook.models.Contact;

public class ContactService {
	
	private Logger logger = Logger.getLogger(ContactService.class.getName());

    public void saveContact(Contact contact, Model model, ApplicationArguments appArgs) {
        String dataFileName = contact.getId();
        //appArgs.getOptionNames();

		String[] optValuesArr = getOptValuesArr(appArgs);

        PrintWriter printWriter = null;
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(optValuesArr[0] + "/" + 
                dataFileName, Charset.forName("UTF-8"));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhoneNumber());
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        } finally {
            printWriter.close();
            try {
                fileWriter.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        model.addAttribute("contact", 
            new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber()));
    }

    public void getContactById(Model model, String contactId, ApplicationArguments appArgs) {
        String[] optValuesArr = getOptValuesArr(appArgs);

        Contact cResp = new Contact();
        try {
            Path filePath = new File(optValuesArr[0] + "/" + contactId).toPath();
            Charset charset = Charset.forName("UTF-8");
            List<String> stringListVal = Files.readAllLines(filePath, charset);
            // cResp.setId(contactId);
            cResp.setName(stringListVal.get(0));
            cResp.setEmail(stringListVal.get(1));
            cResp.setPhoneNumber(stringListVal.get(2));
            cResp.setId(contactId); // take note if constructor order matters...
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        model.addAttribute("contact", cResp);
    }

	public String[] getOptValuesArr(ApplicationArguments appArgs) {
		// return names of all option arguments
		Set<String> optNames = appArgs.getOptionNames();
		String[] optNamesArr = optNames.toArray(new String[optNames.size()]);

		// return collection of values associated with the argument option
		// having the same name
		List<String> optValues = appArgs.getOptionValues(optNamesArr[0]);
		String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
		return optValuesArr;
	}
}
