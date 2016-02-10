package com.malex.service;

import com.malex.configuration.AppConfigTest;
import com.malexj.model.bidirectionalOneToOneMapping.Bank;
import com.malexj.model.bidirectionalOneToOneMapping.Employee;
import com.malexj.service.BankService;
import com.malexj.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by malex on 10.02.16.
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@WebAppConfiguration
public class BidirectionalOneToOneMapping extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BankService bankService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Rollback
    public void testCreate_1_0() {
        //given
        Bank expectBank = new Bank();
        expectBank.setName("STB");

        //when
        Bank actualBank = bankService.save(expectBank);

        // then
        System.err.println(actualBank);
        assertNotNull(actualBank);
        assertEquals(expectBank, actualBank);
    }

    @Test
    @Rollback
    public void testCreate_0_1() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");

        //when
        Employee actualEmployee = employeeService.save(expectEmployee);

        // then
        System.err.println(actualEmployee);
        assertNotNull(actualEmployee);
        assertEquals(expectEmployee, actualEmployee);
    }

    @Test
    @Rollback
    public void testCreate_01_1_1() {
        //given
        Bank expectBank = new Bank();
        expectBank.setName("STB");

        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");

        //when
        Bank actualBank = bankService.save(expectBank);
        Employee actualEmployee = employeeService.save(expectEmployee);

        // then
        System.err.println(actualEmployee);
        assertNotNull(actualEmployee);
        assertEquals(expectEmployee, actualEmployee);

        System.err.println(actualBank);
        assertNotNull(actualBank);
        assertEquals(expectBank, actualBank);
    }


    @Test
    @Rollback
    public void testCreate_02_1_1() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        expectBank.setEmployee(expectEmployee);

        //when
        Bank actualBank = bankService.save(expectBank);


        // then
        System.err.println(actualBank);
        assertNotNull(actualBank);
        assertEquals(expectBank, actualBank);
    }

    @Test
    @Rollback
    public void testCreate_03_1_1() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        expectBank.setEmployee(expectEmployee);
        Bank actualBank = bankService.save(expectBank);

        //when



        // then
        System.err.println(actualBank);
        assertNotNull(actualBank);
        assertEquals(expectBank, actualBank);
    }
}
