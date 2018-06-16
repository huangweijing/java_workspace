import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GroupFormation {
	
	public static int[] init(int size) {
		int[] rootArr = new int[size];
		for(int i=0; i<size; i++) rootArr[i] = i;
		return rootArr;
	}
	
    static void membersInTheLargestGroups(int n, int m, int a, int b, int f, int s, int t) {
    	Map<String, Integer> gradeMap = new HashMap<>();
//    	Map<String, Integer> nameMap = new HashMap<>();
//    	String[] nameArr = new String[n];
    	int[] gradeMax = { 0, f, s, t };
    	List<Set<String>> setList = new ArrayList<>();
    	List<int[]> gradeList = new ArrayList<>();
//    	int[] tree = init(n);
    	
    	for(int i=0; i<n; i++) {
    		String name = scanner.next();
    		int grade = scanner.nextInt();
    		gradeMap.put(name, grade);
//    		nameMap.put(name, i);
//    		nameArr[i] = name;
    	}
    	
    	for(int i=0; i<m; i++) {
    		String name1 = scanner.next();
    		String name2 = scanner.next();
    		int setIdx1 = -1;
    		int setIdx2 = -1;
    		Set<String> pushSet1 = null;
    		Set<String> pushSet2 = null;
    		for(int j=0; j<setList.size(); j++) {
    			Set<String> set = setList.get(j);
    			if(set.contains(name1)) {
    				pushSet1 = set;
    				setIdx1 = j;
    			}
    			if(set.contains(name2)) {
    				pushSet2 = set;
    				setIdx2 = j;
    			}
    			if(pushSet1 != null && pushSet2 != null) {
    				break;
    			}
    		}
    		
    		if(pushSet1 == null && pushSet2 == null) {
    			Set<String> pushSet = new HashSet<>();
    			setList.add(pushSet);
    			int[] gradeArr = new int[4];
    			gradeList.add(gradeArr);
    			setIdx1 = gradeList.size() - 1;
    		}
    		
    		if(pushSet1 == null) {
    			pushSet1 = new HashSet<>();
    			setList.add(pushSet1);
    			int[] gradeArr = new int[4];
    			gradeList.add(gradeArr);
    			setIdx1 = gradeList.size() - 1;
    		}
    		int[] gradeArr = gradeList.get(setIdx1);
    		int grade1 = gradeMap.get(name1);
    		int grade2 = gradeMap.get(name1);
    		int gradeCount1 = gradeArr[gradeMap.get(name1)];
    		int gradeCount2 = gradeArr[gradeMap.get(name2)];
    		
    		if(pushSet1.size() >= b ||
    				gradeCount1 >= gradeMax[grade1] ||
    				gradeCount2 >= gradeMax[grade2]) { 
    			
    		} else {
        		pushSet1.add(name1);
        		pushSet1.add(name2);
        		
        		gradeArr[grade1]++;
        		gradeArr[grade2]++;
    		}
    		
    		System.out.println("pushset: " + pushSet1);
    	}
    	

    	int maxSize = -1;
    	Set<String> answer = null;
    	for(int i=0; i<setList.size(); i++) {
    		Set<String> set = setList.get(i);
    		if(set.size() < a)
    			continue;
    		if(set.size() > maxSize) {
    			maxSize = set.size();
    			answer = set;
    		}
    	}

		List<String> newList = new ArrayList<String>();
    	for(int i=0; i<setList.size(); i++) {
    		Set<String> set = setList.get(i);
    		if(set.size() == maxSize) {
    			newList.addAll(set);
    		}
    	}
    	
    	
    	if(answer == null) {
    		System.out.println("no groups");
    	} else {
    		Collections.sort(newList);
    		for(String member : newList) {
    			System.out.println(member);
    		}
    	}
    	
    }
    
    static void addToTree(int[] tree, int n1, int n2) {
		if(tree[n1] == n2 || tree[n2] == n1)
			return;
		if(tree[n1] == n1) {
			tree[n1] = n2;
		} else if(tree[n2] == n2) {
			tree[n1] = n1;
		} else {
			int current = tree[n1];
			List<Integer> list = new ArrayList<Integer>();
			while(tree[current] != current) {
				current = tree[current];
				list.add(current);
			}
			for(int i=1; i<list.size(); i++) {
				tree[i] = list.get(i-1);
			}
		}
    	
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nmabfst = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nmabfst[0]);

        int m = Integer.parseInt(nmabfst[1]);

        int a = Integer.parseInt(nmabfst[2]);

        int b = Integer.parseInt(nmabfst[3]);

        int f = Integer.parseInt(nmabfst[4]);

        int s = Integer.parseInt(nmabfst[5]);

        int t = Integer.parseInt(nmabfst[6]);

        membersInTheLargestGroups(n, m, a, b, f, s, t);

        scanner.close();
    }
}
