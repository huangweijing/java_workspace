import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class TwoEfficientTeams2 {

	static class Group {
		public Set<Integer> members = new HashSet<Integer>();
		public int efficient = 0;
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("members:");
			sb.append(members.toString());
			sb.append(", efficient:");
			sb.append(efficient);
			return sb.toString();
		}
	}

//	static Map<Set<Integer>, Map<Set<Integer>, Map<List<Group>, Integer>>> maxEffdb = new HashMap<>();
	static Map<Set<Set<Integer>>, Map<Set<Group>, Long>> maxEffdb = new HashMap<>();
	
    // Complete the maximumEfficiency function below.
    static long maximumEfficiency(int n, int m) {
    	
    	Set<Integer> nSet = new HashSet<Integer>();
    	for(int i=1; i<=n; i++) {
    		nSet.add(i);
    	}
    	Set<Group> groupList = new HashSet<Group>();
    	
    	for(int i=0; i<m; i++) {
    		Group newGroup = new Group();
    		int groupMemberCount = scanner.nextInt();
    		newGroup.efficient = scanner.nextInt();
    		groupList.add(newGroup);
    		for(int j=0; j<groupMemberCount; j++) {
    			int memberIndex = scanner.nextInt();
    			newGroup.members.add(memberIndex);
    		}
    	}
    	
    	long result = sumEfficient(groupList, n);
    	if(result == 0)
    		result = calcMaxEfficient_dbmode(new HashSet<Integer>(), new HashSet<Integer>(), groupList, n);
    	System.out.println("hit/total:" + hitCnt + "/" + totalCnt);
    			
    	return result;
    }
    
    static int totalCnt = 0;
    static int hitCnt = 0;

    static long sumEfficient(Set<Group> groupList, int n) {
    	Set<Integer> allSet = new HashSet<>();
    	long sumEfficient = 0;
    	for(Group group : groupList) {
    		allSet.addAll(group.members);
    		sumEfficient += group.efficient;
    	}
    	if(allSet.size() < n) {
    		return sumEfficient;
    	} else
    		return 0;
    }
    
    static long calcMaxEfficient_dbmode(Set<Integer> left, Set<Integer> right, Set<Group> groupList, int n) {
    	Set<Set<Integer>> key = new HashSet<>();
    	key.add(left);
    	key.add(right);
    	Map<Set<Group>, Long> map = maxEffdb.get(key);
    	if(map == null) {
    		map = new HashMap<Set<Group>, Long>();
    		maxEffdb.put(key, map);
    	}
    	Long result = map.get(groupList);
    	if(result == null) {
    		result = calcMaxEfficient(left, right, groupList, n);
    		map.put(groupList, result);
    	} else {
    		hitCnt++;
    	}
    	totalCnt++;
    	return result;
    }
    
    static long calcMaxEfficient(Set<Integer> left, Set<Integer> right, Set<Group> groupList, int n) {
		
    	if(groupList.size() == 0) {
    		return 0;
    	}
    	
    	Set<Group> newList = new HashSet<Group>(groupList);
    	long maxEfficient = 0;
    	long efficient = 0;
    	for(Group group : groupList) {
			newList.remove(group);
    		if(!containsAny(right, group.members)) {
    			Set<Integer> newLeft = new HashSet<Integer>(left);
    			newLeft.addAll(group.members);
    			if(newLeft.size() != n) {
    				efficient = group.efficient + calcMaxEfficient_dbmode(newLeft, right, newList, n);
        			if(efficient > maxEfficient)
        				maxEfficient = efficient;
    			}
    		}
    		
    		if(!containsAny(left, group.members)) {
    			Set<Integer> newRight = new HashSet<Integer>(right);
    			newRight.addAll(group.members);
    			if(newRight.size() != n) {
    				efficient = group.efficient + calcMaxEfficient_dbmode(left, newRight, newList, n);
        			if(efficient > maxEfficient)
        				maxEfficient = efficient;
    			}
    		}
    		
    		efficient = calcMaxEfficient_dbmode(left, right, newList, n);
			if(efficient > maxEfficient)
				maxEfficient = efficient;
			
			newList.add(group);
    	}
    	
    	return maxEfficient;
    }
    
    static boolean containsAny(Set<Integer>set1, Set<Integer>set2) {
    	for(Integer i : set2) {
    		if(set1.contains(i))
    			return true;
    	}
    	return false;
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);
        
        long result = maximumEfficiency(n, m);
        System.out.println(result);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}