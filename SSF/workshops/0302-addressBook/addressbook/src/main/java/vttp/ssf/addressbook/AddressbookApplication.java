package vttp.ssf.addressbook;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static vttp.ssf.addressbook.util.IOUtil.*;

@SpringBootApplication
public class AddressbookApplication {

	private static final Logger logger = Logger.getLogger(AddressbookApplication.class.getName());
	private static final String DATA_DIR = "dataDir";

	public static void main(String[] args) {
		// SpringApplication.run(AddressbookApplication.class, args);
		SpringApplication app = new SpringApplication(AddressbookApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> optVals = appArgs.getOptionValues(DATA_DIR);
		logger.log(Level.INFO, "optVals > " + optVals);

		// data directory option is specified
		if (optVals != null) {
			createDir((String) optVals.get(0));
		} else {
			logger.log(Level.WARNING, "No data directory specified!!!");
		}
		app.run(args);
	}

}

/*
 * mvn spring-boot:run -Dspring-boot.run.arguments=--dataDir=<filePath>
 * mvn spring-boot:run -Dspring-boot.run.arguments=--dataDir=/Users/zerita3101/Projects/vttp/SSF/workshops/0302-addressBook/contacts
 */
