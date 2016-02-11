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

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


// Bank: 1
// Employee: 2
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
        Bank actualBank = bankService.save(expectBank);                     // 1->RDBMS
        printLog(actualBank, "1->RDBMS");

        // then
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
        Employee actualEmployee = employeeService.save(expectEmployee);     // 2->RDBMS
        printLog(actualEmployee, "2->RDBMS");

        // then
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
        Bank actualBank = bankService.save(expectBank);                     // 1->RDBMS
        printLog(actualBank, "1->RDBMS");

        Employee actualEmployee = employeeService.save(expectEmployee);     // 2->RDBMS
        printLog(actualEmployee, "2->RDBMS");

        // then
        assertNotNull(actualEmployee);
        assertEquals(expectEmployee, actualEmployee);

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
        expectBank.setEmployee(expectEmployee);                            // 2->1

        //when
        Bank actualBank = bankService.save(expectBank);                    // 1->RDBMS

        // then
        printLog(actualBank);
        assertNotNull(actualBank);
        assertEquals(expectBank, actualBank);
    }

    @Test
    @Rollback
    public void testCreate_03_1_1() {
        //given
        Employee employee = new Employee();
        employee.setName("Anna");
        employee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(employee);      // 2->RDBMS
        printLog(actualEmployee);

        Bank bank = new Bank();
        bank.setName("STB");
        Bank actualBank = bankService.save(bank);                      // 1->RDBMS
        printLog(actualBank);

        //when
        Bank expectBank = bankService.findById(actualBank.getId());     // RDBMS->1
        expectBank.setEmployee(actualEmployee);                         // 2->1
        Bank actualBankResult = bankService.save(expectBank);           // 1->RDBMS

        // then
        printLog(actualBankResult);
        printLog(actualBankResult.getEmployee());
        assertNotNull(actualBankResult);
        assertEquals(expectBank, actualBankResult);
    }

    @Test
    @Rollback
    public void testCreate_04_1_1() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);              // 2->RDBMS
        printLog(actualEmployee);

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        expectBank.setEmployee(expectEmployee);
        Bank actualBank_01 = bankService.save(expectBank);                           // 1->RDBMS
        printLog(actualBank_01);

        //when
        Bank actualBank = bankService.findById(actualBank_01.getId());               // RDBMS->1
        Employee employee = employeeService.findById(actualEmployee.getId());        // RDBMS->2
        employee.setBank(actualBank);                                                // 1->2
        Employee expectEmployee_01 = employeeService.save(employee);                 // 2->RDBMS

        // then
        printLog(expectEmployee_01);
        printLog(expectEmployee_01.getBank());
        assertNotNull(expectEmployee_01);
        assertEquals(employee, expectEmployee_01);
    }

    @Test
    @Rollback
    public void testDelete_01() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);      // 2->RDBMS
        printLog(actualEmployee,"2->RDBMS");

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        Bank actualBank = bankService.save(expectBank);                      // 1->RDBMS
        printLog(actualBank,"1->RDBMS");

        Bank bankServiceById = bankService.findById(actualBank.getId());     // RDBMS->1
        printLog("RDBMS->1");
        bankServiceById.setEmployee(actualEmployee);                         // 2->1
        printLog("2->1");
        Bank actualBank_01 = bankService.save(bankServiceById);              // 1->RDBMS
        printLog(actualBank_01,"1->RDBMS");

        //when
        bankService.delete(actualBank_01.getId());                           // delete(1) -> 1,2
        printLog("delete(1) -> 1,2");

        // then
        assertTrue(bankService.findAll().isEmpty());
        assertTrue(employeeService.findAll().isEmpty());
    }


    @Test
    @Rollback
    public void testDelete_02() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);      // 2->RDBMS
        printLog(actualEmployee,"2->RDBMS");

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        Bank actualBank = bankService.save(expectBank);                      // 1->RDBMS
        printLog(actualBank,"1->RDBMS");


        Bank bankServiceById = bankService.findById(actualBank.getId());     // RDBMS->1
        printLog("RDBMS->1");
        bankServiceById.setEmployee(actualEmployee);                         // 2->1
        printLog("2->1");
        Bank actualBank_01 = bankService.save(bankServiceById);              // 1->RDBMS
        printLog(actualBank_01, "1->RDBMS");

        //when
        Bank bank = bankService.findById(actualBank_01.getId());
        Employee employee = bank.getEmployee();
        employeeService.delete(employee.getId());                            // delete(2) -> 0
        printLog("delete(2) -> 0");

        // then
        assertTrue(bankService.findAll().isEmpty());
        assertTrue(employeeService.findAll().isEmpty());
    }

    @Test
    @Rollback
    public void testDelete_03() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);              // 2->RDBMS
        System.err.println("actualEmployee: " + actualEmployee);
        System.err.println("actualEmployee.getId(): " + actualEmployee.getId());

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        expectBank.setEmployee(expectEmployee);
        Bank actualBank_01 = bankService.save(expectBank);                           // 1->RDBMS
        System.err.println("actualBank: " + actualBank_01);
        System.err.println("actualBank.getId(): " + actualBank_01.getId());

        Bank actualBank = bankService.findById(actualBank_01.getId());               // RDBMS->1
        Employee employee = employeeService.findById(actualEmployee.getId());        // RDBMS->2
        employee.setBank(actualBank);                                                // 1->2
        Employee expectEmployee_01 = employeeService.save(employee);                // 2->RDBMS

        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());

        //when
        employeeService.delete(expectEmployee_01.getId());                            // delete(2) -> 1,2


        // then
        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());
    }

    @Test
    @Rollback
    public void testDelete_04() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);              // 2->RDBMS
        System.err.println("actualEmployee: " + actualEmployee);
        System.err.println("actualEmployee.getId(): " + actualEmployee.getId());

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        expectBank.setEmployee(expectEmployee);
        Bank actualBank_01 = bankService.save(expectBank);                           // 1->RDBMS
        System.err.println("actualBank: " + actualBank_01);
        System.err.println("actualBank.getId(): " + actualBank_01.getId());

        Bank actualBank = bankService.findById(actualBank_01.getId());               // RDBMS->1
        Employee employee = employeeService.findById(actualEmployee.getId());        // RDBMS->2
        employee.setBank(actualBank);                                                // 1->2
        Employee expectEmployee_01 = employeeService.save(employee);                // 2->RDBMS

        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());

        //when
        Employee employeeServiceById = employeeService.findById(expectEmployee_01.getId());
        Bank bank = employeeServiceById.getBank();
        bankService.delete(bank.getId());                                            // delete(1) -> 1,2

        // then
        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());
    }

    @Test
    @Rollback
    public void testUpdate_01() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);              // 2->RDBMS
        System.err.println("actualEmployee: " + actualEmployee);
        System.err.println("actualEmployee.getId(): " + actualEmployee.getId());

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        expectBank.setEmployee(expectEmployee);
        Bank actualBank_01 = bankService.save(expectBank);                           // 1->RDBMS
        System.err.println("actualBank: " + actualBank_01);
        System.err.println("actualBank.getId(): " + actualBank_01.getId());

        Bank actualBank = bankService.findById(actualBank_01.getId());               // RDBMS -> 1
        Employee employee = employeeService.findById(actualEmployee.getId());        // RDBMS -> 2
        employee.setBank(actualBank);                                                // 1->2
        Employee expectEmployee_01 = employeeService.save(employee);                // 2->RDBMS

        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());

        //when

        //update Employee
        Employee employeeUp = new Employee();
        employeeUp.setName("Max");
        employeeUp.setUserName("MmmMAss");

        Bank bank = bankService.findById(actualBank.getId());
        System.err.println("bank: " + bank.getId());
        System.err.println("bank: " + bank);
        bank.setEmployee(employeeUp);                                                // new 2 s-> 1
        Bank updateBank = bankService.update(bank);                                  // 1 u-> RDBMS
        System.err.println("updateBank: " + updateBank.getId());
        System.err.println("updateBank: " + updateBank.getEmployee());
        System.err.println("updateBank: " + updateBank.getEmployee());

        // then
        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());
    }


    @Test
    @Rollback
    public void testUpdate_02() {
        //given
        Employee expectEmployee = new Employee();
        expectEmployee.setName("Anna");
        expectEmployee.setUserName("XwXXX");
        Employee actualEmployee = employeeService.save(expectEmployee);              // 2 -> RDBMS
        System.err.println("actualEmployee: " + actualEmployee);
        System.err.println("actualEmployee.getId(): " + actualEmployee.getId());

        Bank expectBank = new Bank();
        expectBank.setName("STB");
        expectBank.setEmployee(expectEmployee);
        Bank actualBank_01 = bankService.save(expectBank);                           // 1->RDBMS
        System.err.println("actualBank: " + actualBank_01);
        System.err.println("actualBank.getId(): " + actualBank_01.getId());

        Bank actualBank = bankService.findById(actualBank_01.getId());               // RDBMS->1
        Employee employee = employeeService.findById(actualEmployee.getId());        // RDBMS->2
        employee.setBank(actualBank);                                                // 1 -> 2
        Employee expectEmployee_01 = employeeService.save(employee);                 // 2 -> RDBMS

        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());

        //when
        Employee employee_01 = employeeService.findById(expectEmployee_01.getId());  // RDBMS->2

        Bank bbank = new Bank();
        bbank.setName("Weeeewwww5555");
        employee_01.setBank(bbank);                                                    // 1 -> 2

        Employee updateEmployee = employeeService.update(employee_01);                 // 2 -> RDBMS

        System.err.println("updateEmployee: " + updateEmployee.getId());
        System.err.println("updateEmployee: " + updateEmployee);

        // then
        System.err.println(bankService.findAll());
        System.err.println(employeeService.findAll());
    }

    void printLog(Object object, String... message) {
        if (object instanceof Bank) {
            Bank bank = (Bank) object;
            System.err.println("Bank-> ID: " + bank.getId() + ", NAME: " + bank.getName() + ", EMPLOYEE: " + bank.getEmployee() + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
        if (object instanceof Employee) {
            Employee employee = (Employee) object;
            System.err.println("Employee-> ID: " + employee.getId() + ", NAME: " + employee.getName() + ", USERNAME: " + employee.getUserName() + ", BANK: " + employee.getBank() + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
    }

    void printLog(String... message) {
        System.err.println(message.length > 0 ? "       " + message[0] + " >>>>  " : "");
    }

}
