import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GroupFormation2 {
	
	public static int[] init(int size) {
		int[] rootArr = new int[size];
		for(int i=0; i<size; i++) rootArr[i] = i;
		return rootArr;
	}
	
	static class GroupData {
		public int[] grade = new int[4];
	}
	
    static void membersInTheLargestGroups(int n, int m, int a, int b, int f, int s, int t) {
//    	System.out.println(String.format("n=%s, m=%s, a=%s, b=%s, f=%s, s=%s, t=%s"
//    			, n, m, a, b, f, s, t));
    	
    	Map<String, Integer> gradeMap = new HashMap<>();
    	int[] gradeMax = { 0, f, s, t };

    	Map<String, Set<String>> groupInfo = new HashMap<>();
    	Map<String, int[]> gradeInfo = new HashMap<>();
    	
    	for(int i=0; i<n; i++) {
    		String name = scanner.next();
    		int grade = scanner.nextInt();
    		gradeMap.put(name, grade);
    	}
    	
    	for(int i=0; i<m; i++) {
    		String name1 = scanner.next();
    		String name2 = scanner.next();

    		if(name1.equals(name2))
    			continue;
    		
    		Set<String> set1 = groupInfo.get(name1);
    		Set<String> set2 = groupInfo.get(name2);
    		int[] gradeInfo1 = gradeInfo.get(name1);
    		int[] gradeInfo2 = gradeInfo.get(name2);
    		int plus1 = 0;
    		int plus2 = 0;
    		if(set1 == null) {
    			set1 = new HashSet<String>();
    			gradeInfo1 = new int[4];
    			plus1++;
    		}
    		if(set2 == null) {
    			set2 = new HashSet<String>();
    			gradeInfo2 = new int[4];
    			plus2++;
    		}
    		if(set1 != set2) {
    			
    			int grade1 = gradeMap.get(name1);
    			int grade2 = gradeMap.get(name2);
    			
    			//add name1 to name2's group
    			if(plus1 ==1 && plus2 == 0) {
    				if(gradeInfo2[grade1] + 1 <= gradeMax[grade1]
    						&& set2.size() + 1 <= b) {
    					set2.add(name1);
        				groupInfo.put(name1, set2);
//        				groupInfo.put(name2, set2);
        				gradeInfo2[grade1]++;
        				gradeInfo.put(name1, gradeInfo2);
//        				gradeInfo.put(name2, gradeInfo2);
    				}
    			}
    			//add name2 to name1's group
    			else if(plus1 ==0 && plus2 == 1) {
    				if(gradeInfo1[grade2] + 1 <= gradeMax[grade2]
    						&& set1.size() + 1 <= b) {
    					set1.add(name2);
//        				groupInfo.put(name1, set1);
        				groupInfo.put(name2, set1);
        				gradeInfo1[grade2]++;
//        				gradeInfo.put(name1, gradeInfo1);
        				gradeInfo.put(name2, gradeInfo1);
    				}
    			}
    			//create a new group
    			else if(plus1 == 1 && plus2 == 1){
    				set1.add(name1);
    				set1.add(name2);
    				groupInfo.put(name1, set1);
    				groupInfo.put(name2, set1);
    				gradeInfo1[grade1]++;
    				gradeInfo1[grade2]++;
    				gradeInfo.put(name1, gradeInfo1);
    				gradeInfo.put(name2, gradeInfo1);
    			}
    			//merge
    			else if(plus1 == 0 && plus2 == 0){
    				if(set1.size() + set2.size() > b)
    					continue;
    				if(name1.equals("B") && name2.equals("C")) {
    					System.out.println("start");
    				}
    				boolean checkFlg = true;
    				for(int g=1; g<=3; g++) {
    					if(gradeInfo1[g] + gradeInfo2[g] > gradeMax[g]) {
    						checkFlg = false;
    						break;
    					}
    				}
    				if(!checkFlg)
    					continue;
    				
    				if(gradeInfo2[grade1] + gradeInfo1[grade1] > gradeMax[grade1]
    						|| gradeInfo2[grade2] + gradeInfo1[grade2] > gradeMax[grade2])
    					continue;

    				set1.addAll(set2);
    				for(int g=1; g<=3; g++) {
    					gradeInfo1[g] = gradeInfo1[g] + gradeInfo2[g];
    				}
    				
    				for(String name : set1) {
    					groupInfo.put(name, set1);
    					gradeInfo.put(name, gradeInfo1);
    				}
    				
    				
    			}

//    			for(String name : gradeInfo.keySet()) {
//    				System.out.println("name:" + name + ", value:" + Arrays.toString(gradeInfo.get(name)));
//    			}
//    			System.out.println("-------------");
    		}
    		
//    		System.out.println(gradeInfo);
    	}
//    	System.out.println(groupInfo);
//		for(String name : gradeInfo.keySet()) {
//			System.out.println("name:" + name + ", value:" + Arrays.toString(gradeInfo.get(name)));
//		}

    	int maxSize = -1;
    	List<Set<String>> answerList = new ArrayList<>();
    	for(Set<String>set : groupInfo.values()){
    		if(set.size() < a)
    			continue;
    		if(set.size() > maxSize)
    			answerList.clear();
    		if(set.size() >= maxSize) {
    			maxSize = set.size();
    			if(!answerList.contains(set))
    				answerList.add(set);
    		}
    	}

		List<String> newList = new ArrayList<String>();
    	for(int i=0; i<answerList.size(); i++) {
    		newList.addAll(answerList.get(i));
    	}
    	
    	if(newList.size() == 0) {
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
