/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
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

        Double expResult = 0.0;
        Double result = instance.getFinalDriveRatio();
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
        List<Gear> result = instance.getGearList();
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
        List<Throttle> result = instance.getThrottleList();
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

        obj = null;
        assertEquals("Should be false because obj is null", false, instance.equals(obj));

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
        instance.setFinalDriveRatio(1.0);
        assertEquals("Should be false if they have not the same min", false, instance.equals(obj));

    }

    /**
     * Test of setMinRpm method, of class Energy.
     */
    @Test
    public void testSetMinRpm() {
        System.out.println("setMinRpm");
        int minRpm = 3;
        instance = new Energy();
        instance.setMinRpm(minRpm);
        assertTrue(instance.getMinRpm() == 3);
    }

    /**
     * Test of setMaxRpm method, of class Energy.
     */
    @Test
    public void testSetMaxRpm() {
        System.out.println("setMaxRpm");
        int maxRpm = 7000;
        instance = new Energy();
        instance.setMaxRpm(maxRpm);
        assertTrue(instance.getMaxRpm() == 7000);
    }

    /**
     * Test of setFinalDriveRatio method, of class Energy.
     */
    @Test
    public void testSetFinalDriveRatio() {
        System.out.println("setFinalDriveRatio");
        Double finalDriveRatio = 1.0;
        instance = new Energy();
        instance.setFinalDriveRatio(finalDriveRatio);
        assertTrue(instance.getFinalDriveRatio() == 1.0);
        
    }

    /**
     * Test of addGear method, of class Energy.
     */
    @Test
    public void testAddGear() {
        System.out.println("addGear");
        Gear gear = new Gear("1",1.5);
        instance = new Energy();
        instance.addGear(gear);
        List<Gear> listGears = instance.getGearList();
        assertTrue(listGears.contains(gear));
        
        Energy instance2 = new Energy();
        List<Gear> listGears2 = instance2.getGearList();
        listGears2.add(gear);
        assertTrue(listGears2.contains(gear));
        
        instance2.addGear(gear);
        assertTrue(listGears2.contains(gear));
    }

}
