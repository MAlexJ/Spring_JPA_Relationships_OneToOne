package com.malex.service;

import com.malex.configuration.AppConfigTest;
import com.malexj.model.oto.corwin.calepin.Car;
import com.malexj.model.oto.corwin.calepin.Engine;
import com.malexj.service.CarService;
import com.malexj.service.EngineService;
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
 * Created by malex on 11.02.16.
 */

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@WebAppConfiguration
public class OtoCorwinCalepinTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CarService carService;

    @Autowired
    private EngineService engineService;

    // Car <-u Engine

    @Test
    @Rollback
    public void testCreate_1_0() {
        //given
        Car car = new Car();
        car.setName("BMW");

        //when
        Car actualCar = carService.save(car);       // CAR -> RDBMS
        printLog(actualCar);

        // then
        assertNotNull(actualCar);
        assertEquals(car, actualCar);
    }

    @Test
    @Rollback
    public void testCreate_0_1() {
        //given
        Engine engine = new Engine();
        engine.setName("1000H");

        //when
        Engine actualEngine = engineService.save(engine);   // Engine -> RDBMS
        printLog(actualEngine);

        // then
        assertNotNull(actualEngine);
        assertEquals(engine, actualEngine);
    }


    @Test
    @Rollback
    public void testCreate_U1_1() {
        //given
        Car car = new Car();
        car.setName("AUDI");
        Car expectCar = carService.save(car);                                     // CAR -> RDBMS
        printLog(expectCar, "CAR -> RDBMS");

        Engine engine = new Engine();
        engine.setName("2000S");
        Engine expectEngine = engineService.save(engine);                         // Engine -> RDBMS
        printLog(expectEngine, "Engine -> RDBMS");

        //when
        Car carServiceById = carService.findById(expectCar.getId());               // RDBMS -> CAR
        Engine engineServiceById = engineService.findById(expectEngine.getId());   // RDBMS -> Engine

        engineServiceById.setCar(carServiceById);                                   // CAR -> Engine

        Engine expectEngineResult = engineService.update(engineServiceById);        // Engine -> RDBMS
        printLog(expectEngineResult, "Engine -> RDBMS");

        // then
        assertNotNull(expectEngineResult);
        assertEquals(engineServiceById, expectEngineResult);
        assertEquals(carService.findAll().size(), 1);
        assertEquals(engineService.findAll().size(), 1);
    }


    @Test
    @Rollback
    public void testCreate_U0_1() {

        /**
         * InvalidDataAccessApiUsageException:
         * object references an unsaved transient instance -
         * save the transient instance before flushing :Engine.car -> Car
         * */

        //given
        Car car = new Car();
        car.setName("AUDI");

        Engine engine = new Engine();
        engine.setName("2000S");
        engine.setCar(car);                                                       // Car -> Engine

        //when
        Engine expectEngine = engineService.save(engine);                         // Engine -> RDBMS
        printLog(expectEngine, "Engine -> RDBMS");
        // then

        assertNotNull(expectEngine);
        assertEquals(engine, expectEngine);
        assertEquals(carService.findAll().size(), 1);
        assertEquals(engineService.findAll().size(), 1);
    }


    @Test
    @Rollback
    public void testCreate_R1_1() {
        //given
        Car car = new Car();
        car.setName("AUDI");
        Car expectCar_01 = carService.save(car);                                     // CAR -> RDBMS
        printLog(expectCar_01, "CAR -> RDBMS");

        Engine engine = new Engine();
        engine.setName("2000S");
        Engine expectEngine = engineService.save(engine);                         // Engine -> RDBMS
        printLog(expectEngine, "Engine -> RDBMS");

        //when
        Car expectCar = carService.findById(car.getId());                          // RDBMS ->CAR
        expectCar.setEngine(engine);                                               // Engine -> CAR
        Car actualCar = carService.update(expectCar);                              // CAR -> RDBMS
        printLog(actualCar, "CAR -> RDBMS");

        // then
        assertNotNull(actualCar);
        assertEquals(expectCar, actualCar);
        assertEquals(carService.findAll().size(), 1);
        assertEquals(engineService.findAll().size(), 1);
    }


    @Test
    @Rollback
    public void testCreate_R1_0() {
        // given
        Engine engine = new Engine();
        engine.setName("2000S");

        Car car = new Car();
        car.setName("AUDI");
        car.setEngine(engine);

        // when
        Car expectCar_01 = carService.save(car);                                     // CAR -> RDBMS
        printLog(expectCar_01, "CAR -> RDBMS");
        printLog(expectCar_01.getEngine());

        // then
        assertNotNull(expectCar_01);
        assertEquals(car, expectCar_01);
        assertEquals(carService.findAll().size(), 1);
        assertEquals(engineService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void test() {

        //given
        //when
        // then

    }

    void printLog(Object object, String... message) {
        if (object instanceof Car) {
            Car car = (Car) object;
            System.err.println("Car-> ID: " + car.getId() + ", NAME: " + car.getName() + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
        if (object instanceof Engine) {
            Engine engine = (Engine) object;
            System.err.println("Engine-> ID: " + engine.getId() + ", NAME: " + engine.getName() + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
    }

}
