/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 */
public class UserListTest {

    private final UserList instance;

    public UserListTest() {
        instance = new UserList();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getUserList method, of class UserList.
     */
    @Test
    public void testGetUserList() {
        System.out.println("getUserList");
        Set<User> result = instance.getUserList();
        assertTrue(result.isEmpty());
       
    }

    /**
     * Test of setUserList method, of class UserList.
     */
    @Test
    public void testSetUserList() {
        System.out.println("setUserList");
        Set<User> userList = new HashSet<>();
        User u1 = new User("username", 1, "Test", "password", "email");
        userList.add(u1);
        instance.setUserList(userList);
       assertEquals(userList,instance.getUserList());
    }

}
