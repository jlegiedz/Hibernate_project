package pl.lodz.sda.runner;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.sda.Service.CompanyService;
import pl.lodz.sda.model.Company;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by asia on 21/09/17.
 */
@RestController
public class CompanyController {

    CompanyService companyService = new CompanyService();

    // domyslnie value czyli sciezka
    @RequestMapping(path = "/company/first")
    public Company getFirstCompany(){
        return companyService.findFirstCompany();
    }
    // jak mamy dwa parametry adnotacji dodajemy value=
//    @RequestMapping(value = "/company/save", method = POST, consumes = "application/json")
//    public String save(Company company){
//        return "saved";
//    }

    @RequestMapping(path = "/company/save",
            method = POST,
            consumes = "application/json",
            produces = "application/json"
    )
    // RB mowi ze to co save przyjmuje mozemy interpretowac
    // jako obiekt- pola zostana zamapowane
    public Company save(@RequestBody Company company){
      //return company;
       return companyService.saveCompany(company);
    }

//    @RequestMapping(path = "/company/save",
//            method = POST,
//            produces = "application/json"
//    )
//    public Company save(Company company){
//        Company cp = new Company();
//        cp.setId(1l);
//        cp.setName("MyCompany");
//        return cp;
//    }

    @RequestMapping(path = "/company/all")
    public List<Company> saveAll(){
        return companyService.findAll();


    }





}
