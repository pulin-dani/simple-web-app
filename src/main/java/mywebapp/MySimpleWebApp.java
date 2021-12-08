package mywebapp;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class MySimpleWebApp implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(MySimpleWebApp.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

  }
}
