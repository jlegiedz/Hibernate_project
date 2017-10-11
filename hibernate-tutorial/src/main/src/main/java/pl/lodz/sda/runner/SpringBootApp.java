package pl.lodz.sda.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.lodz.sda.Service.CompanyService;
import pl.lodz.sda.model.Address;
import pl.lodz.sda.model.Company;
import pl.lodz.sda.model.Department;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by asia on 21/09/17.
 */
@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootApp.class, args);

    }
}
