/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
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
public class EnergyTest {

    private Energy instance;

    public EnergyTest() {
        this.instance = new Energy();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMin_rpm method, of class Energy.
     */
    @Test
    public void testGetMinRpm() {
        System.out.println("getMin_rpm");
        int expResult = 0;
        int result = instance.getMinRpm();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMax_rpm method, of class Energy.
     */
    @Test
    public void testGetMaxRpm() {
        System.out.println("getMax_rpm");

        int expResult = 1000;
        int result = instance.getMaxRpm();
        assertEquals(expResult, result);

    }

    /**
     * Test of getFinal_drive_ratio method, of class Energy.
     */
    @Test
    public void testGetFinalDriveRatio() {
        System.out.println("getFinal_drive_ratio");

        int expResult = 0;
        int result = instance.getFinalDriveRatio();
        assertEquals(expResult, result);

    }

    /**
     * Test of getGear_List method, of class Energy.
     */
    @Test
    public void testGetGearList() {
        System.out.println("getGear_List");
        instance.getGearList().add(new Gear("1", 01));
        instance.getGearList().add(new Gear("2", 02));
        instance.getGearList().add(new Gear("3", 03));
        LinkedList<Gear> expResult = new LinkedList<>();
        expResult.add(new Gear("1", 01));
        expResult.add(new Gear("2", 02));
        expResult.add(new Gear("3", 03));
        LinkedList<Gear> result = instance.getGearList();
        assertEquals(expResult, result);

    }

    /**
     * Test of getThrottle_list method, of class Energy.
     */
    @Test
    public void testGetThrottleList() {
        System.out.println("getThrottle_list");
        LinkedList<Regime> regime_list = new LinkedList<>();
        instance.getThrottleList().add(new Throttle("25", regime_list));
        instance.getThrottleList().add(new Throttle("50", regime_list));
        instance.getThrottleList().add(new Throttle("100", regime_list));
        LinkedList<Throttle> expResult = new LinkedList<>();
        expResult.add(new Throttle("25", regime_list));
        expResult.add(new Throttle("50", regime_list));
        expResult.add(new Throttle("100", regime_list));
        LinkedList<Throttle> result = instance.getThrottleList();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Energy.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Energy temp = new Energy();
        int expResult = temp.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of equals method, of class Energy.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Energy();
        assertEquals("Should be true if they are the same object", true, instance.equals(obj));

        obj = "test";
        assertEquals("Should be false if they are not from the same class", false, instance.equals(obj));

        obj = new Energy();
        instance.setMinRpm(1);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));
        instance.setMinRpm(0);

        obj = new Energy();
        instance.setMaxRpm(1001);
        assertEquals("Should be false if they have not the same max", false, instance.equals(obj));
        instance.setMaxRpm(1001);

        obj = new Energy();
        instance.setFinalDriveRatio(1);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

    }

}
