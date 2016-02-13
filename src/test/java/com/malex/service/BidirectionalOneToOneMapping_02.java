package com.malex.service;

import com.malex.configuration.AppConfigTest;
import com.malexj.model.bidirectionalOneToOneMapping_02.Account;
import com.malexj.model.bidirectionalOneToOneMapping_02.User;
import com.malexj.service.AccountService;
import com.malexj.service.UserService;

import static junit.framework.TestCase.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
@WebAppConfiguration
public class BidirectionalOneToOneMapping_02 extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Test
    @Rollback
    public void testCreateUser() {
        /**
         - Создание обычного User без Account
         */

        // given
        User user = new User();
        user.setName("Max");

        // when
        User actualUser = userService.save(user);
        printLog(actualUser);

        // then
        assertNotNull(actualUser);
        assertEquals("error", user, actualUser);
    }

    @Test
    @Rollback
    public void testCreateUserAccount() {

        /**
         - Создание обычного User без Account
         - Создание User'у нового Account (Account создаеться со своей стороны, со стороны User -> ошибка)
         - Добавление в Account нужно только существующего в БД User'а. (Иначе не будет связи с сущностью)
         c определенным условием
         */

        // given

        // 1. Создание обычного User без Account
        User user = new User();
        user.setName("Max");
        User userResult = userService.save(user);
        printLog(userResult);

        //2. Получение User'a
        User actualUser = userService.findById(user.getId());

        //3. Создание User'у нового Account
        Account account = new Account();
        account.setName("Аккк 2354");
        account.setUser(actualUser);
        actualUser.setAccount(account); //  c определенным условием

        // when
        Account actualAccount = accountService.save(account);
        printLog(actualAccount);

        // then
        assertNotNull(actualAccount);
        assertEquals("error", account, actualAccount);
    }

    @Test
    @Rollback
    public void testGetUsersAccount() {
        // given

        /**
         - Получение User's -> Аккаунта
         * */
        // 1. Создание обычного User без Account
        User user = new User();
        user.setName("Max");
        User userResult = userService.save(user);
        printLog(userResult);

        // 2. Получение User'a
        User actualUser = userService.findById(user.getId());

        // 3. Создание User'у нового Account
        Account account = new Account();
        account.setName("Аккк 2354");
        account.setUser(actualUser);
        actualUser.setAccount(account);  /** c определенным условием */

        // 4. Запись Account в БД
        Account actualAccount = accountService.save(account);
        printLog(actualAccount);

        // when
        // 5. Получаю User
        User actualUserRes = userService.findById(actualUser.getId());
        printLog(actualUserRes);
        // 6. Получаю Аккаунт у User
        Account actualAccountRes = actualUserRes.getAccount();
        printLog(actualAccountRes);

        // then
        assertNotNull(actualAccountRes);
        assertEquals("error", account, actualAccountRes);
    }


    @Test
    @Rollback
    public void testGetAccountsUser() {
        // given
        /**
         - Получение у Account -> User's
         * */
        // 1. Создание обычного User без Account
        User user = new User();
        user.setName("Max");
        User userResult = userService.save(user);
        printLog(userResult);

        // 2. Получение User'a
        User actualUser = userService.findById(user.getId());

        // 3. Создание User'у нового Account
        Account account = new Account();
        account.setName("Аккк 2354");
        account.setUser(actualUser);
        actualUser.setAccount(account);  /** c определенным условием */

        // 4. Запись Account в БД
        Account actualAccount = accountService.save(account);
        printLog(actualAccount);

        // when
        // 5. Получаю Аккаунт
        Account accountResult = accountService.findById(actualAccount.getId());
        printLog(accountResult, "Получаю Аккаунт");
        // 6. Получаю User у Аккаунт
        User userRes = accountResult.getUser();
        printLog(userRes, " Получаю User у Аккаунт'a");

        // then
        assertNotNull(userRes);
        assertEquals("error", actualUser, userRes);
    }


    @Test
    @Rollback
    public void testUpdateUsersAccount() {
        // given
        /**
         - изменение Пользователем своего Аккаунта
         * */
        // 1. Создание обычного User без Account
        User user = new User();
        user.setName("Max");
        User userResult = userService.save(user);
        printLog(userResult);

        // 2. Получение User'a
        User actualUser = userService.findById(user.getId());

        // 3. Создание User'у нового Account
        Account account = new Account();
        account.setName("Аккк 2354");
        account.setUser(actualUser);
        actualUser.setAccount(account);  /** c определенным условием */

        // 4. Запись Account в БД
        Account actualAccount = accountService.save(account);
        printLog(actualAccount);

        // when
        // 5. Получаю User
        User actualUserRes = userService.findById(actualUser.getId());
        printLog(actualUserRes,"Получаю User");
        // 6. Получаю Аккаунт у User
        Account actualAccountRes = actualUserRes.getAccount();
        printLog(actualAccountRes,"Получаю Аккаунт у User");
        // 7. изменяю Аккаунт
        actualAccountRes.setName("Nae Akkkk 2222");
        // 8. Обновляю аккаунт
        User updateUser = userService.update(actualUserRes);
        printLog(updateUser,"Обновляю аккаунт");

        // then
        assertNotNull(updateUser);
        assertEquals("error", userService.findAll().size(), 1);
        assertEquals("error", accountService.findAll().size(), 1);
    }

    @Test
    @Rollback
    public void testDeleteUsers() {
        // given
        /**
         1. Удаление пользователя

          Проблеммa:
         с userService.findAll() и  accountService.findAll(), выскакивает PSQLException!!!!!!!!!!!
         !!! что то убрал у сушности и все ок!!!(Не получаеться) -> PSQLException: ERROR: null value in column "user_id" violates not-null constraint
         PSQLException: ERROR: null value in column "user_id" violates not-null constraint

         Решение проблеммы:
         Убрать у FK notNull (у обейх сторон!)

         * */

        // 1. Создание обычного User без Account
        User user = new User();
        user.setName("Max");
        User userResult = userService.save(user);
        printLog(userResult);

        // 2. Получение User'a
        User actualUser = userService.findById(user.getId());

        // 3. Создание User'у нового Account
        Account account = new Account();
        account.setName("Аккк 2354");
        account.setUser(actualUser);
        actualUser.setAccount(account);  /** c определенным условием */

        // 4. Запись Account в БД
        Account actualAccount = accountService.save(account);
        printLog(actualAccount);

        // when
        // 5. Получаю User
        User actualUserRes = userService.findById(actualUser.getId());
        printLog(actualUserRes,"Получаю User");
        // 6. Delete User
        userService.delete(actualUserRes.getId());

        // then
        assertEquals("error", userService.findAll().size(), 0);
        assertEquals("error", accountService.findAll().size(), 0);
    }


    @Test
    @Rollback
    public void testDeleteAccount() {
        // given
        /**
         1. Удаление Account y пользователя

         Проблеммa:
         удаляя аккаунт можно или удалить всю сввязь или не удалять ваше ни чего!!!

         Решение:
         нет
         * */

        // 1. Создание обычного User без Account
        User user = new User();
        user.setName("Max");
        User userResult = userService.save(user);
        printLog(userResult);

        // 2. Получение User'a
        User actualUser = userService.findById(user.getId());

        // 3. Создание User'у нового Account
        Account account = new Account();
        account.setName("Аккк 2354");
        account.setUser(actualUser);
        actualUser.setAccount(account);  /** c определенным условием */

        // 4. Запись Account в БД
        Account actualAccount = accountService.save(account);
        printLog(actualAccount);

        // when
        // 5. Получаю User
        User actualUserRes = userService.findById(actualUser.getId());
        printLog(actualUserRes,"Получаю User");
        // 6. Получаю Аккаунт у User
        Account account_01 = actualUserRes.getAccount();
        // 6. Delete Account
        accountService.delete(account_01.getId());

        // then
        assertEquals("error", userService.findAll().size(), 1);
        assertEquals("error", accountService.findAll().size(), 1);
    }

    void printLog(Object object, String... message) {
        if (object instanceof User) {
            User user = (User) object;
            System.err.println("User-> ID: " + user.getId() + ", NAME: " + user.getName() + ((user.getAccount() != null) ? ", Account: " + user.getAccount().getId() + " " + user.getAccount().getName() + " " : "") + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
        if (object instanceof Account) {
            Account account = (Account) object;
            System.err.println("Account-> ID: " + account.getId() + ", NAME: " + account.getName() + ", User: " + account.getUser().getId() + " " + account.getUser().getName() + " " + ((message.length > 0) ? "       " + message[0] + " >>>>  " : ""));
        }
    }


}
