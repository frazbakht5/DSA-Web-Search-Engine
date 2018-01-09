/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Urwah Asrar
 */
public class Page implements Serializable{
    int id;
    String title;
    
    
    HashMap<String,List<Integer>> wordOccurances = new HashMap<String,List<Integer>>();
    
    
}
