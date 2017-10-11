package pl.lodz.sda.dao;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.sda.model.Company;

import java.io.IOException;

/**
 * Created by asia on 20/09/17.
 */
public class CompanyManagerTest extends TestCase {

    CompanyManager companyManager;
    @Before
    public void init(){
        try {
            companyManager = new CompanyManager();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @Test
    public void save() throws Exception {
        //given
        Company company = new Company();
        //when
        Company savedEntity = companyManager.save(company);
        //then
        Assert.assertEquals(savedEntity,company);
    }
    @Test
    public void testUpdate() throws Exception {
    }
    @Test
    public void testFind() throws Exception {
    }
    @Test
    public void testFindAll() throws Exception {
    }
    @Test
    public void testDeleteAll() throws Exception {
    }
    @Test
    public void testDelete() throws Exception {
    }

}