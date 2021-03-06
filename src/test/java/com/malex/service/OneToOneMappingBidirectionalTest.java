package com.malex.service;

import com.malex.configuration.AppConfigTest;
import com.malexj.model.oneToOneMappingBidirectional.Dog;
import com.malexj.model.oneToOneMappingBidirectional.DogHouse;
import com.malexj.service.DogHouseService;
import com.malexj.service.DogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.*;

/**
 * Created by malex on 11.02.16.
 */

// Dog: 1
// DogHouse: 2
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@WebAppConfiguration
public class OneToOneMappingBidirectionalTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DogService dogService;

    @Autowired
    private DogHouseService houseService;


    @Test
    @Rollback
    public void testCreate_1_0() {
        //given
        Dog dog = new Dog();
        dog.setUserName("Dogg");

        //when
        Dog actualDog = dogService.save(dog);                            // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        // then
        assertNotNull(actualDog);
    }

    @Test
    @Rollback
    public void testCreate_0_1() {
        //given
        DogHouse dogHouse = new DogHouse();
        dogHouse.setName("House");

        //when
        DogHouse actualDogHouse = houseService.save(dogHouse);          // 2->RDBMS
        printLog(dogHouse, "2->RDBMS");

        // then
        assertNotNull(actualDogHouse);
    }


    @Test
    @Rollback
    public void testCreate_1_1() {
        //given
        DogHouse dogHouse = new DogHouse();
        dogHouse.setName("House");

        Dog dog = new Dog();
        dog.setUserName("Dogg");

        //when
        Dog actualDog = dogService.save(dog);                                   // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        DogHouse actualDogHouse = houseService.save(dogHouse);                // 2->RDBMS
        printLog(actualDogHouse, "2->RDBMS");

        // then
        assertNotNull(actualDogHouse);
        assertNotNull(actualDog);
    }

    @Test
    @Rollback
    public void testCreate_1_1_1to2() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseW");
        DogHouse actualHouse = houseService.save(house);                // 2->RDBMS
        printLog(actualHouse, "2->RDBMS");

        Dog dog = new Dog();
        dog.setUserName("DogW");
        Dog actualDog = dogService.save(dog);                                   // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        //when
        Dog actualDog_01 = dogService.findById(actualDog.getId());                 // RDBMS->1
        DogHouse actualHouse_01 = houseService.findById(actualHouse.getId());   // RDBMS->2
        actualDog_01.setHouse(actualHouse_01);                                     // 2->1
        Dog actualDog_02 = dogService.update(actualDog_01);                        // 1->RDBMS
        printLog(actualDog_02, "1->RDBMS");

        // then
        assertNotNull(actualDog_02);
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void testCreate_1_1_2to1() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseW");
        DogHouse actualHouse = houseService.save(house);                // 2->RDBMS
        printLog(actualHouse, "2->RDBMS");

        Dog dog = new Dog();
        dog.setUserName("DogW");
        Dog actualDog = dogService.save(dog);                                   // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        //when
        Dog actualDog_01 = dogService.findById(actualDog.getId());                 // RDBMS->1
        DogHouse actualHouse_01 = houseService.findById(actualHouse.getId());   // RDBMS->2
        actualHouse_01.setDog(actualDog_01);                                       // 1->2
        DogHouse actualHouse_011 = houseService.update(actualHouse_01);        // 2->RDBMS
        printLog(actualHouse_011, "1->RDBMS");
        printLog(actualHouse_011.getDog());

        // then
        assertNotNull(actualHouse_011);
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void testCreate_1_0_2to1() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseW");
        printLog(house);

        Dog dog = new Dog();
        dog.setUserName("DogW");
        dog.setHouse(house);                                                     // 2->1
        printLog(dog);

        //when
        Dog actualDog = dogService.save(dog);                                   // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        // then
        assertNotNull(actualDog);
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void testCreate_0_1_1to2() {

        /**
         InvalidDataAccessApiUsageException
         save the transient instance before flushing : DogHouse.dog -> com.malexj.model.oneToOneMappingBidirectional.Dog
         * */

        //given
        Dog dog = new Dog();
        dog.setUserName("DogW12");
        printLog(dog);

        DogHouse house = new DogHouse();
        house.setName("HouseWasd");
        house.setDog(dog);                                                     // 1->2
        printLog(house);

        //when
        DogHouse actualHouse = houseService.save(house);                         // 2->RDBMS
        printLog(actualHouse, "2->RDBMS");

        // then
        assertNotNull(actualHouse);
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void testDelete_01() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseW");
        printLog(house);

        Dog dog = new Dog();
        dog.setUserName("DogW");
        dog.setHouse(house);                                                     // 2->1
        printLog(dog);

        Dog actualDog = dogService.save(dog);                                   // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        //when
        dogService.delete(actualDog.getId());

        // then
        assertTrue(dogService.findAll().isEmpty());
        assertTrue(houseService.findAll().isEmpty());

    }

    @Test
    @Rollback
    public void testDelete_02() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseWa");
        printLog(house);

        Dog dog = new Dog();
        dog.setUserName("DogWs");
        dog.setHouse(house);                                                     // 2->1
        printLog(dog);

        Dog actualDog = dogService.save(dog);                                   // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        //when
        houseService.delete(house.getId());

        // then
        System.err.println(dogService.findAll());
        System.err.println(houseService.findAll());
        assertTrue(dogService.findAll().isEmpty());
        assertTrue(houseService.findAll().isEmpty());

    }


    @Test
    @Rollback
    public void testUpdate_u_0_1() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseWa");
        printLog(house);

        Dog dog = new Dog();
        dog.setUserName("DogWs");
        dog.setHouse(house);                                                     // 2->1
        printLog(dog);

        Dog actualDog_01 = dogService.save(dog);                                 // 1->RDBMS
        printLog(actualDog_01, "1->RDBMS");

        //when
        Dog expectDog = dogService.findById(actualDog_01.getId());               //  RDBMS->1
        DogHouse expectHouse = expectDog.getHouse();
        expectHouse.setName("LaHate");

        Dog actualDog = dogService.update(expectDog);                            // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        // then
        assertEquals(expectDog, actualDog);
        assertEquals(expectHouse, actualDog.getHouse());
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);

    }


    @Test
    @Rollback
    public void testUpdate_u_1_0() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseWa");
        printLog(house);

        Dog dog = new Dog();
        dog.setUserName("DogWs");
        dog.setHouse(house);                                                    // 2->1
        printLog(dog);

        Dog actualDog_01 = dogService.save(dog);                                // 1->RDBMS
        printLog(actualDog_01, "1->RDBMS");

        //when
        Dog expectDog = dogService.findById(actualDog_01.getId());              //  RDBMS->1
        expectDog.setUserName("Bob");
        Dog actualDog = dogService.update(expectDog);                           // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        // then
        assertEquals(expectDog, actualDog);
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }


    @Test
    @Rollback
    public void testUpdate_u_1_1() {
        //given
        DogHouse house = new DogHouse();
        house.setName("HouseWa");
        printLog(house);

        Dog dog = new Dog();
        dog.setUserName("DogWs");
        dog.setHouse(house);                                                     // 2->1
        printLog(dog);

        Dog actualDog_01 = dogService.save(dog);                                 // 1->RDBMS
        printLog(actualDog_01, "1->RDBMS");

        //when
        Dog expectDog = dogService.findById(actualDog_01.getId());               //  RDBMS->1
        expectDog.setUserName("Bobik");
        DogHouse expectHouse = expectDog.getHouse();
        expectHouse.setName("LaHate");

        Dog actualDog = dogService.update(expectDog);                            // 1->RDBMS
        printLog(actualDog, "1->RDBMS");

        // then
        assertEquals(expectDog, actualDog);
        assertEquals(expectHouse, actualDog.getHouse());
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);

    }


    void printLog(Object object, String... message) {
        if (object instanceof Dog) {
            Dog dog = (Dog) object;
            System.err.println("Dog-> ID: " + dog.getId() + ", NAME: " + dog.getUserName() + ", HOUSE: " + dog.getHouse() + " " + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
        if (object instanceof DogHouse) {
            DogHouse dogHouse = (DogHouse) object;
            System.err.println("DogHouse-> ID: " + dogHouse.getId() + ", NAME: " + dogHouse.getName() + ", DOG: " + dogHouse.getDog() + " " + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
    }

    void printLog(String... message) {
        System.err.println(message.length > 0 ? "       " + message[0] + " >>>>  " : "");
    }


    ///******************************************** испытание боем *******************************************

    @Test
    @Rollback
    public void testCreate_Dog_After_1to2() {
        //given
        Dog dog = new Dog();
        dog.setUserName("DogW");
        Dog dog_01 = dogService.save(dog);                                     // Dog->RDBMS
        printLog(dog_01, "1->RDBMS");

        //when
        Dog expectDog = dogService.findById(dog_01.getId());                   // RDBMS-> Dog

        DogHouse house = new DogHouse();
        house.setName("HouseW");

        expectDog.setHouse(house);                                             // DogHouse-> Dog

        Dog actualDog = dogService.update(expectDog);                          // RDBMS-> Dog

        // then
        assertNotNull(actualDog);
        assertEquals(expectDog, actualDog);
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }


    @Test
    @Rollback
    public void testGet_House_1to2() {
        //given
        Dog dog = new Dog();
        dog.setUserName("DogW");
        Dog dog_01 = dogService.save(dog);                                     // Dog->RDBMS
        printLog(dog_01, "1->RDBMS");

        //when
        Dog expectDog = dogService.findById(dog_01.getId());                   // RDBMS-> Dog

        DogHouse house = new DogHouse();
        house.setName("HouseW");

        expectDog.setHouse(house);                                             // DogHouse-> Dog

        Dog actualDog = dogService.update(expectDog);                          // Dog->RDBMS
        printLog(actualDog, "Dog->RDBMS");

        DogHouse house1 = actualDog.getHouse();
        System.err.println(house1);

        // then
        assertNotNull(actualDog);
        assertEquals(expectDog, actualDog);
        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void testGetUpdate_House_1to2() {
        //given
        DogHouse house = new DogHouse();
        house.setName("Wssff");

        Dog dog = new Dog();
        dog.setUserName("DogW");
        dog.setHouse(house);                                             // DogHouse-> Dog
        Dog expectDog = dogService.update(dog);                          // Dog->RDBMS
        printLog(expectDog, "Dog->RDBMS");

        //when
        Dog expectDog_01 = dogService.findById(expectDog.getId());

        DogHouse expectHouse = expectDog_01.getHouse();
        expectHouse.setName("new House");

        //    update возможно сервисом dogService
        //    dogService.update(expectDog_01);

        // update возможно сервисом houseService
        houseService.update(expectHouse);

        // then

        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void testDelete_Dog_House_1to2() {
        //given
        DogHouse house = new DogHouse();
        house.setName("Wssff");

        Dog dog = new Dog();
        dog.setUserName("DogW");
        dog.setHouse(house);                                             // DogHouse-> Dog
        Dog expectDog = dogService.update(dog);                          // Dog->RDBMS
        printLog(expectDog, "Dog->RDBMS");

        //when
        Dog expectDog_01 = dogService.findById(expectDog.getId());

        dogService.delete(expectDog_01.getId());


        // then

        assertEquals(dogService.findAll().size(), 0);
        assertEquals(houseService.findAll().size(), 0);
    }


    @Test
    @Rollback
    public void testDelete_House_1to2() {
        //given
        DogHouse house = new DogHouse();
        house.setName("Wssff");

        Dog dog = new Dog();
        dog.setUserName("DogW");
        dog.setHouse(house);                                             // DogHouse-> Dog
        Dog expectDog = dogService.update(dog);                          // Dog->RDBMS
        printLog(expectDog, "Dog->RDBMS");

        //when
        Dog expectDog_01 = dogService.findById(expectDog.getId());
        DogHouse house1 = expectDog_01.getHouse();
        houseService.delete(house1.getId());


        // then

        assertEquals(dogService.findAll().size(), 1);
        assertEquals(houseService.findAll().size(), 0);
    }


}
