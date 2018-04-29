package com.weijinglab.lib.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerticalOrderOfBinaryTree {

   void topView(Node root) {
	      
       Map<Integer, List<Integer>> verticalMap = new HashMap<Integer, List<Integer>>();
       getVerticalOrder(root, 0, verticalMap); 
       List<Integer> newList = new ArrayList<Integer>(verticalMap.keySet());
       Collections.sort(newList);
       for(Integer i : newList) {
           System.out.print(verticalMap.get(i).get(0) + " ");
       }
    }
	   
    public void getVerticalOrder(Node root, Integer pos, Map<Integer, List<Integer>> verticalMap) {
        if(root == null) {
            return;
        }
        List<Integer> verticalList = verticalMap.get(pos);
        if(verticalList == null) {
            verticalList = new ArrayList<Integer>();
            verticalMap.put(pos, verticalList);
        }
        verticalList.add(root.data);
        
        getVerticalOrder(root.left, pos - 1, verticalMap);
        getVerticalOrder(root.right, pos + 1, verticalMap);
    }
    
}
