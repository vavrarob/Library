package sin.semestral_work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//package sin.semestral_work;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private final Builder builder;

    public DataLoader(Builder builder) {
        this.builder = builder;
    }

    public void run(ApplicationArguments args) {
        builder.build();
    }

}