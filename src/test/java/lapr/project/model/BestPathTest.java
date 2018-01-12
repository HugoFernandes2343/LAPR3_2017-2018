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
 * @author hugod
 */
public class BestPathTest {
    private BestPath instance;
    
    public BestPathTest() {
        instance = new BestPath();
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
     * Test of getSectionList method, of class BestPath.
     */
    @Test
    public void testGetSectionList() {
        System.out.println("getSectionList");
        List<RoadSection> result = instance.getSectionList();
        assertTrue(result.isEmpty());
        
    }

    /**
     * Test of setSectionList method, of class BestPath.
     */
    @Test
    public void testSetSectionList() {
        System.out.println("setSectionList");
        List<RoadSection> sectionList = new LinkedList<>();
        instance.setSectionList(sectionList);
        assertTrue(instance.getSectionList().isEmpty());
    }
    
}
