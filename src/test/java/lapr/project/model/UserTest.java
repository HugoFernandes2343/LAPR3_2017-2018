/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author 
 */
public class UserTest {

    private User instance;

    public UserTest() {
        instance = new User("username", 1, "Test", "password", "email");
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
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = "username";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "test";
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());

    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getNome");
        String expResult = "Test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        System.out.println("setNome");
        String name = "Name";
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "new email";
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);//should be the same when its the same object
    }

    /**
     * Test of equals method, of class User,false case.
     */
    @Test
    public void testEqualsFalseCase() {
        System.out.println("equals");
        Object obj = "test";
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User,true case.
     */
    @Test
    public void testEqualsTrueCase() {
        System.out.println("equals");
        Object obj = new User("username", 1, "Test", "password", "email");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Name: Test, Email: email";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "azerty";
        User instance = new User("hdany", 2, "hugo", "qwerty", "1161155@isep.ipp.pt");
        instance.setPassword(password);
        String result = instance.getPassword();
        assertEquals(result,password);
        
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new User("hdany", 2, "hugo", "qwerty", "1161155@isep.ipp.pt");
        User instance = new User("hdany", 2, "hugo", "qwerty", "1161155@isep.ipp.pt");
        assertEquals("Should be true if they are the same object",true, instance.equals(obj));
        obj = "test";
         assertEquals("Should be false if they are not from the same class",false, instance.equals(obj));
         obj = new User("hdany2", 2, "hugo", "qwerty", "1161155@isep.ipp.pt");
        assertEquals("Should be false if they have not the same username",false, instance.equals(obj));
        obj = new User("hdany", 2, "hugo", "qwerty", "2161155@isep.ipp.pt");
        assertEquals("Should be false if they have not the same email",false, instance.equals(obj));
        obj = new User("hdany", 2, "hugo", "azerty", "1161155@isep.ipp.pt");
        assertEquals("Should be false if they have not the same password",false, instance.equals(obj));
        
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User("hdany", 2, "hugo", "qwerty", "1161155@isep.ipp.pt");
        String expResult = "qwerty";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        }
}
