package com.malex.service;


import com.malex.configuration.AppConfigTest;
import com.malexj.model.retrieveOneToOneMappedEntity.Address;
import com.malexj.model.retrieveOneToOneMappedEntity.Student;
import com.malexj.service.AddressService;
import com.malexj.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@WebAppConfiguration
public class RetrieveOTOMappedServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AddressService addressService;

    @Test
    @Rollback(value = false)
    public void testCreateStudentAddAddress_01() {
        /**
         * Hibernate: select nextval ('store_sequence')
         Hibernate: insert into address (city, state, street, zip, id) values (?, ?, ?, ?, ?)
         Hibernate: select nextval ('store_sequence')
         Hibernate: insert into student (address_id, name, id) values (?, ?, ?)
         */

        //given
        Address address = new Address();
        address.setCity("Swer");
        address.setZip("123sf");
        addressService.save(address);

        Student expectStudent = new Student();
        expectStudent.setName("Вася");
        expectStudent.setAddress(address);

        //when
        Student actualStudent = studentService.save(expectStudent);

        // then
        System.err.println(actualStudent);
        assertNotNull(actualStudent);
        assertEquals(expectStudent, actualStudent);
    }

    @Test
    @Rollback(value = false)
    public void testCreateStudentAddAddress_02() {
        /**
         * Hibernate: select nextval ('store_sequence')
         Hibernate: insert into student (address_id, name, id) values (?, ?, ?)
         */

        //given
        Address address = new Address();
        address.setCity("Swer");
        address.setZip("123sf");


        Student expectStudent = new Student();
        expectStudent.setName("Вася");
        // TODO -> InvalidDataAccessApiUsageException
        // student.setAddress(address);

        //when
        Student actualStudent = studentService.save(expectStudent);

        // then
        System.err.println(actualStudent);
        assertNotNull(actualStudent);
        assertEquals(expectStudent, actualStudent);
    }

    @Test
    @Rollback(value = false)
    public void testDeleteStudentAddAddress_01() {
        /**
         *.dao.DataIntegrityViolationException: could not execute statement;
         * SQL [n/a]; constraint [fk_e3500f0n5n132n60x2ay1woj9];
         * nested exception is org.hibernate.exception.
         * ConstraintViolationException: could not execute statement
         */

        //given
        Address address = new Address();
        address.setCity("Swe3r");
        address.setZip("123sf");
        Address actualAddress = addressService.save(address);

        Student expectStudent = new Student();
        expectStudent.setName("Вася");
        expectStudent.setAddress(address);
        Student actualStudent = studentService.save(expectStudent);

        //when
        //TODO DataIntegrityViolationException: could not execute statement; SQL [n/a]; constraint [fk_e3500f0n5n132n60x2ay1woj9];
        addressService.delete(actualAddress.getId());

        // then
        assertNotNull(actualStudent);
    }




    @Test
    @Rollback(value = false)
    public void testCreateStudentAddAddress_03() {
        /**

            */

        //given
        Student expectStudent = new Student();
        expectStudent.setName("Вася");
        Student student = studentService.save(expectStudent);

        Address address = new Address();
        address.setCity("Sw2er");
        address.setZip("123sf");

        student.setAddress(address);

        //when
        studentService.update(student);

        // then

    }

    @Test
    @Rollback(value = false)
    public void testDeleteStudentAddAddress_02() {
        /**
         *
         */

        //given
        Address address = new Address();
        address.setCity("Swere");
        address.setZip("123sf");
        Address actualAddress = addressService.save(address);

        Student expectStudent = new Student();
        expectStudent.setName("Вася3");
        expectStudent.setAddress(address);
        Student actualStudent = studentService.save(expectStudent);

        //when
        studentService.delete(actualStudent.getId());

        // then
        //TODO null
        assertNull(studentService.findById(actualStudent.getId()));
        //TODO !!!! not null
        assertNotNull(addressService.findById(actualAddress.getId()));
    }





}
