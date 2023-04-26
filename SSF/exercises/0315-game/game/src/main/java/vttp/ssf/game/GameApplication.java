package vttp.ssf.game;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp.ssf.game.repositories.GameRepository;
import vttp.ssf.game.services.GameService;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

	@Autowired
	private GameService gameSvc;

	@Autowired
	private GameRepository gameRepo;

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	// Terminal:
	// mvn spring-boot:run -Dspring-boot.run.arguments=/Users/zerita3101/Projects/vttp/SSF/exercises/0315-game/bgg/game.json

	@Override
	public void run(String[] args) {
		System.out.print("args: " + args);

		if (args.length <= 0) {
			System.err.println("Please pass in a JSON file to parse");
			System.exit(-1);
		}

		JsonArray games = null;
		try (FileInputStream fis = new FileInputStream(args[0])) {
			games = gameSvc.loadData(fis);
			System.out.println("size: " + games.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		games.stream()
			.map(v -> (JsonObject) v)
			.forEach((JsonObject v) -> {
				gameRepo.save(v);
			});

		System.out.print("Completed!");
	}
}
