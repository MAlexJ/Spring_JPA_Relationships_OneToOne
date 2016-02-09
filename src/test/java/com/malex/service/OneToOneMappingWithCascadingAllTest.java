package com.malex.service;

import com.malex.configuration.AppConfigTest;
import com.malexj.model.oneToOneMappinpWithCascadingAll.Breed;
import com.malexj.model.oneToOneMappinpWithCascadingAll.Cat;
import com.malexj.service.BreedService;
import com.malexj.service.CatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Random;

import static junit.framework.TestCase.*;

/**
 * Created by malex on 08.02.16.
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@WebAppConfiguration
public class OneToOneMappingWithCascadingAllTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CatService catService;

    @Autowired
    private BreedService breedService;

    @Test
    @Rollback
    public void testCreateCat_01() {
        //given
        Cat cat = new Cat();
        cat.setName("Myrrr" + new Random().nextInt(100));

        //when
        Cat actualCat = catService.save(cat);

        // then
        System.err.println(actualCat);
        assertNotNull(actualCat);
        assertEquals(cat, actualCat);

    }

    @Test
    @Rollback
    public void testCreateCat_02() {
        //given
        Cat cat = new Cat();
        cat.setName("Myrrr" + new Random().nextInt(100));

        Breed breed = new Breed();
        breed.setName("Asss" + new Random().nextInt(100));

        //when
        cat.setBreed(breed);
        Cat actualCat = catService.save(cat);

        // then
        System.err.println(actualCat);
        assertNotNull(actualCat);
        assertEquals(cat, actualCat);
    }

    @Test
    @Rollback
    public void testCreateCat_03() {
        //given
        Cat cat = new Cat();
        cat.setName("Myrrr" + new Random().nextInt(100));
        Cat cat_01 = catService.save(cat);


        //when
        Cat expectCat = catService.findById(cat_01.getId());
        System.err.println(expectCat.getId());

        Breed breed = new Breed();
        breed.setName("Asss" + new Random().nextInt(100));
        breed.setState("Xxx");
        expectCat.setBreed(breed);

        Cat actualCat = catService.update(expectCat);


        // then
        System.err.println(actualCat.getId());
        System.err.println(actualCat);
        assertNotNull(actualCat);
        assertEquals(expectCat, actualCat);
        assertEquals(expectCat.getId(), actualCat.getId());
    }

    @Test
    @Rollback
    public void testCreateCat_04() {
        //given
        Cat cat = new Cat();
        cat.setName("Myrrr" + new Random().nextInt(100));
        Cat cat_01 = catService.save(cat);


        //when
        Cat expectCat_01 = catService.findById(cat_01.getId());
        System.err.println(expectCat_01.getId());
        Breed breed_01 = new Breed();
        breed_01.setName("34643" + new Random().nextInt(100));
        breed_01.setState("Xxghkghkx");
        expectCat_01.setBreed(breed_01);
        Cat actualCat_02 = catService.update(expectCat_01);

        Cat expectCat_03 = catService.findById(actualCat_02.getId());
        System.err.println(expectCat_03.getId());
        Breed breed_02 = new Breed();
        breed_02.setName("Asshmbmbss" + new Random().nextInt(100));
        breed_02.setState("Xxsdfhshfdx");
        expectCat_03.setBreed(breed_02);
        Cat actualCat_04 = catService.update(expectCat_03);


        Cat expectCat_05 = catService.findById(actualCat_04.getId());
        System.err.println(expectCat_05.getId());
        Breed breed_03 = new Breed();
        breed_03.setName("sgdsgsd" + new Random().nextInt(100));
        breed_03.setState("Xsdhdfshxx");
        expectCat_05.setBreed(breed_03);
        Cat actualCat = catService.update(expectCat_05);


        // then
        System.err.println(actualCat.getId());
        System.err.println(actualCat);
        System.err.println(breedService.findAll());
        assertNotNull(actualCat);
        assertEquals(expectCat_05, actualCat);
        assertEquals(expectCat_05.getId(), actualCat.getId());
    }

    @Test
    @Rollback
    public void testDeleteBreedForCat() {
        //given
        Cat cat = new Cat();
        cat.setName("Myrrr" + new Random().nextInt(100));
        Breed breed = new Breed();
        breed.setName("Asss" + new Random().nextInt(100));
        cat.setBreed(breed);
        Cat actualCat = catService.save(cat);

        //when
        breedService.delete(actualCat.getBreed().getId());

        // then
        System.err.println(actualCat);
        System.err.println(breedService.findAll());
        assertNotNull(actualCat);

    }

    @Test
    @Rollback
    public void testDeleteBreedForCat_01() {
        //given
        Cat cat = new Cat();
        cat.setName("Myrrr" + new Random().nextInt(100));
        Breed breed = new Breed();
        breed.setName("Asss" + new Random().nextInt(100));
        cat.setBreed(breed);
        Cat actualCat = catService.save(cat);

        //when
        catService.delete(actualCat.getId());

        // then
        System.err.println(catService.findAll());
        System.err.println(breedService.findAll());
        assertTrue(catService.findAll().isEmpty());
        assertTrue(breedService.findAll().isEmpty());
    }

    @Test
    @Rollback
    public void testCreateBreed_01() {
        //given
        Breed breed = new Breed();
        breed.setName("Asss" + new Random().nextInt(100));

        //when
        Breed actualBreed = breedService.save(breed);

        // then
        System.err.println(actualBreed);
        assertNotNull(actualBreed);
    }

    @Test
    @Rollback (value = false)
    public void testCreateBreed_02() {
        //given
        Breed breed = new Breed();
        breed.setName("Asss" + new Random().nextInt(100));
        Breed actualBreed = breedService.save(breed);
        System.err.println(actualBreed.getId());

        Cat cat = new Cat();
        cat.setName("Myrrr" + new Random().nextInt(100));
        Cat actualCat = catService.save(cat);
        System.err.println(actualCat.getId());

        //when
        Cat catServiceById = catService.findById(actualCat.getId());
        Breed breedServiceById = breedService.findById(actualBreed.getId());

        catServiceById.setBreed(breedServiceById);
        Cat catSave = catService.save(catServiceById);


        // then
        Cat resultCat = catService.findById(catSave.getId());

        assertEquals(resultCat.getId(), catServiceById.getId());
        assertEquals(resultCat.getBreed().getId(), breedServiceById.getId());
        System.err.println(catSave);

    }

}
