package se.almstudio.booking.api;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class JunitTestRunListener extends RunListener {

  @Override
  public void testRunStarted(Description description) {
    Configuration configuration = Flyway.configure()
      .dataSource("jdbc:postgresql://localhost:5432/bookme", "postgres", "postgres")
      .locations("classpath:db");
    Flyway flyway = new Flyway(configuration);
    flyway.clean();
    flyway.migrate();
  }
}
