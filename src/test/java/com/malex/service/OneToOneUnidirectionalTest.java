package com.malex.service;

import com.malex.configuration.AppConfigTest;
import com.malexj.model.oneToOneUnidirectional.Department;
import com.malexj.model.oneToOneUnidirectional.Worker;
import com.malexj.service.DepartmentService;
import com.malexj.service.WorkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@WebAppConfiguration
public class OneToOneUnidirectionalTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private WorkerService workerService;


    @Test
    @Rollback
    public void testCreateWorkerAddDepartment_01() {

        /**
         Hibernate: select nextval ('store_sequence')
         Hibernate: insert into department (name, id) values (?, ?)
         Hibernate: insert into worker (DEPT_ID, name, id) values (?, ?, ?)
         */

        //given
        Department department = new Department();
        department.setName("Dep" + new Random().nextInt(100));

        Worker expectWorker = new Worker();
        expectWorker.setName("Worker_01");
        expectWorker.setDepartment(department);

        //when
        Worker actualWorker = workerService.save(expectWorker);

        // then
        System.err.println(actualWorker);
        assertNotNull(actualWorker);
        assertEquals(expectWorker, actualWorker);
    }

    @Test
    @Rollback
    public void testCreateWorkerAddDepartment_02() {

        /**
         Hibernate: insert into worker (DEPT_ID, name, id) values (?, ?, ?)
         */

        //given
        Worker expectWorker = new Worker();
        expectWorker.setName("Worker_01");
        // Department = null

        //when
        Worker actualWorker = workerService.save(expectWorker);

        // then
        System.err.println(actualWorker);
        assertNotNull(actualWorker);
        assertEquals(expectWorker, actualWorker);
    }

    @Test
    @Rollback
    public void testCreateWorkerAddDepartment_03() {

        /**
         Hibernate: insert into worker (DEPT_ID, name, id) values (?, ?, ?)
         */

        //given
        Worker worker = new Worker();
        worker.setName("Worker_01");
        Worker expectWorker = workerService.save(worker);

        //when
        Department department = new Department();
        department.setName("Dep" + new Random().nextInt(100));
        expectWorker.setDepartment(department);

        Worker actualWorker = workerService.update(expectWorker);

        // then
        System.err.println(actualWorker);
        assertNotNull(actualWorker);
        assertEquals(expectWorker, actualWorker);
    }

    @Test
    @Rollback
    public void testCreateWorkerAddDepartment_04() {

        /**
         Hibernate: insert into worker (DEPT_ID, name, id) values (?, ?, ?)
         Hibernate: select worker0_.id as id1_3_1_, worker0_.DEPT_ID as DEPT_ID3_3_1_, worker0_.name as name2_3_1_, department1_.id as id1_1_0_, department1_.name as name2_1_0_ from worker worker0_ left outer join department department1_ on worker0_.DEPT_ID=department1_.id where worker0_.id=?
         Hibernate: select nextval ('store_sequence')
         Hibernate: insert into department (name, id) values (?, ?)
         Hibernate: update worker set DEPT_ID=?, name=? where id=?
         Hibernate: select worker0_.id as id1_3_1_, worker0_.DEPT_ID as DEPT_ID3_3_1_, worker0_.name as name2_3_1_, department1_.id as id1_1_0_, department1_.name as name2_1_0_ from worker worker0_ left outer join department department1_ on worker0_.DEPT_ID=department1_.id where worker0_.id=?
         Hibernate: select nextval ('store_sequence')
         Hibernate: insert into department (name, id) values (?, ?)
         Hibernate: update worker set DEPT_ID=?, name=? where id=?
         Hibernate: select worker0_.id as id1_3_1_, worker0_.DEPT_ID as DEPT_ID3_3_1_, worker0_.name as name2_3_1_, department1_.id as id1_1_0_, department1_.name as name2_1_0_ from worker worker0_ left outer join department department1_ on worker0_.DEPT_ID=department1_.id where worker0_.id=?
         Hibernate: select nextval ('store_sequence')
         Hibernate: insert into department (name, id) values (?, ?)
         Hibernate: update worker set DEPT_ID=?, name=? where id=?
         */

        //given
        Worker worker = new Worker();
        worker.setName("Worker_01" + new Random().nextInt(100));
        Worker newWorker = workerService.save(worker);

        //when

        //step 1 get Worker
        Worker worker_01 = workerService.findById(newWorker.getId());
        //step 1 create Department
        Department department_01 = new Department();
        department_01.setName("Dep" + new Random().nextInt(100));
        //step 1 add Department to Worker
        worker_01.setDepartment(department_01);
        //step 1 update Worker
        Worker worker_02 = workerService.update(worker_01);

        //step 2 get Worker
        Worker worker_03 = workerService.findById(worker_02.getId());
        //step 2 create Department
        Department department_02 = new Department();
        department_02.setName("Dep" + new Random().nextInt(100));
        //step 2 add Department to Worker
        worker_03.setDepartment(department_02);
        //step 2 update Worker
        Worker worker_04 = workerService.update(worker_03);

        // then
        System.err.println(worker_04);
        assertNotNull(worker_04);
        assertEquals(worker.getName(), worker_04.getName());
        //TODO !!!! 2 x Department
        System.err.println(departmentService.findAll());
    }


    @Test
    @Rollback(value = false)
    public void testDeleteDepartment() {

        /**
         DataIntegrityViolationException: could not execute statement;
         SQL [n/a]; constraint [fk_k09hii1rvh4aqha0dci7u1ivs];
         nested exception is org.hibernate.exception.ConstraintViolationException:
         could not execute statement
         */

        //given
        Department department = new Department();
        department.setName("Dep" + new Random().nextInt(100));

        Worker expectWorker = new Worker();
        expectWorker.setName("Worker_01" + new Random().nextInt(100));
        expectWorker.setDepartment(department);

        //when
        Worker actualWorker = workerService.save(expectWorker);

        Department department_01 = actualWorker.getDepartment();
        departmentService.delete(department_01.getId());

        // then
        System.err.println(actualWorker);
        assertNotNull(actualWorker);
        assertEquals(expectWorker.getName(), actualWorker.getName());

        assertTrue(departmentService.findAll().isEmpty());
    }


    @Test
    @Rollback
    public void testDeleteWorker_01() {

        /**
         []
         [Department{name='Dep93'}, Department{name='Dep89'}]
         junit.framework.AssertionFailedError
         OneToOneUnidirectionalTest.testDeleteWorker_01
         */

        //given
        Department department = new Department();
        department.setName("Dep" + new Random().nextInt(100));

        Worker expectWorker = new Worker();
        expectWorker.setName("Worker_01"+ new Random().nextInt(100));
        expectWorker.setDepartment(department);

        Worker worker = workerService.save(expectWorker);
        System.err.println(worker);


        //step 1 get Worker
        Worker worker_01 = workerService.findById(worker.getId());
        //step 1 create Department
        Department department_01 = new Department();
        department_01.setName("Dep" + new Random().nextInt(100));
        //step 1 add Department to Worker
        worker_01.setDepartment(department_01);
        //step 1 update Worker
        Worker worker_02 = workerService.update(worker_01);

        //step 2 get Worker
        Worker worker_03 = workerService.findById(worker_02.getId());
        //step 2 create Department
        Department department_02 = new Department();
        department_02.setName("Dep" + new Random().nextInt(100));
        //step 2 add Department to Worker
        worker_03.setDepartment(department_02);
        //step 2 update Worker
        Worker worker_04 = workerService.update(worker_03);

        //when
        Worker actualWorker = workerService.findById(worker_04.getId());
        workerService.delete(actualWorker.getId());

        List<Worker> workerList = workerService.findAll();
        List<Department> departmentList = departmentService.findAll();


        // then
        System.err.println(workerList);
        System.err.println(departmentList);
        assertTrue(workerList.isEmpty());
        //TODO !!!! 2 x Department
        assertTrue(departmentList.isEmpty());
    }


    @Test
    @Rollback
    public void testDeleteWorker_02() {

        /**
         Hibernate: delete from worker where id=?
         Hibernate: delete from department where id=?
         */

        //given
        Department department = new Department();
        department.setName("Dep" + new Random().nextInt(100));

        Worker expectWorker = new Worker();
        expectWorker.setName("Worker_01"+ new Random().nextInt(100));
        expectWorker.setDepartment(department);

        Worker worker = workerService.save(expectWorker);
        System.err.println(worker);

        //when
        Worker actualWorker = workerService.findById(worker.getId());
        workerService.delete(actualWorker.getId());

        List<Worker> workerList = workerService.findAll();
        List<Department> departmentList = departmentService.findAll();


        // then
        System.err.println(workerList);
        System.err.println(departmentList);
        assertTrue(workerList.isEmpty());
        assertTrue(departmentList.isEmpty());
    }

}
