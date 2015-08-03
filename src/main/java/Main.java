import io.github.hylinn.statistics.hibernate.entity.Player;
import io.github.hylinn.statistics.hibernate.service.PlayerService;
import io.github.hylinn.statistics.spring.configuration.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(final String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        PlayerService service = context.getBean("playerService", PlayerService.class);

        Player hylinn = new Player("hylinn", "Hylinn Taggart");
        service.save(hylinn);
    }
}
