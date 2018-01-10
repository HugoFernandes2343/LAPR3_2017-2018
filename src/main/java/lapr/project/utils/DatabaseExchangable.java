/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.List;

/**
 *
 * @author Utilizador
 */
public interface DatabaseExchangable {
    /**
     * Sole purpose of this interface is to allow the DataLayer to properly 
     * recieve and treat the data from objects which implement this interface
     */
    
    /**
     * Returns the necessary data from a said class
     * @return 
     */
    public List<DatabaseExchangable> getDBData();
}
