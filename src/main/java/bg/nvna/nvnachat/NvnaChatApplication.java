package bg.nvna.nvnachat;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NvnaChatApplication {

    public static void main(String[] args) {
        Dotenv.load().entries().forEach(it -> System.setProperty(it.getKey(), it.getValue()));
        SpringApplication.run(NvnaChatApplication.class, args);
    }

}
