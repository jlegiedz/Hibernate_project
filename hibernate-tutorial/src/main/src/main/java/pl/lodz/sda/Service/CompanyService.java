package pl.lodz.sda.Service;

import pl.lodz.sda.dao.CompanyManager;
import pl.lodz.sda.model.Company;

import java.io.IOException;
import java.util.List;

/**
 * Created by asia on 21/09/17.
 */
public class CompanyService {

    CompanyManager companyManager;

    public CompanyService(){
        try {
            this.companyManager = new CompanyManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Company saveCompany(Company company){
        return companyManager.save(company);
    }

    public Company findFirstCompany(){
        return companyManager.find(1l);
    }

    public List<Company>  findAll(){
        return companyManager.findAll();
    }


}
