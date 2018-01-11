/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.List;

/**
 * This class has the purpose of recieving a collection of data that need to fit into the datalayer package. Since some list possess
 * the ability of managing their own collection of data, this class aims to help those who dont have enough independence to do that
 * but need this class to be exported.
 * @author Utilizador
 */
public class DataExchangableList extends DatabaseExchangable{

    protected List<DatabaseExchangable> list;
    
    public DataExchangableList(List<DatabaseExchangable> list) {
        this.list=list;
    }

    @Override
    public List<DatabaseExchangable> getDBData() {
        return this.list;
    }

}
