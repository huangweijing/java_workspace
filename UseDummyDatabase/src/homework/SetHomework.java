package homework;
import java.util.*;

public class SetHomework {
	public static void main(String[] args) {
		Random random = new Random();
		Set<Integer> mySet1 = new HashSet<Integer>();
		Set<Integer> mySet2 = new HashSet<Integer>();
	
//		int listCount = random.nextInt(10)+2;
		int listCount = 3;

		List<List<Integer>> questionList = new ArrayList<List<Integer>>();
		
		questionList.add(Arrays.asList(new Integer[]{3, 3, 3, 2, 5}));
		questionList.add(Arrays.asList(new Integer[]{2, 1, 5, 7}));
		questionList.add(Arrays.asList(new Integer[]{3, 4, 6, 6}));
		questionList.add(Arrays.asList(new Integer[]{1, 2, 3}));
		
		
		ListOperation myList = new ListOperation();
//		for(int i=0; i<listCount; i++) {
		for(int i=0; i<questionList.size(); i++) {
//			List randomList = myList.createList();
			List randomList = questionList.get(i);
			System.out.print("List"+(i+1));
			myList.printList(randomList);
			if(i==0) {	
				mySet1.addAll(randomList);
				mySet2.addAll(randomList);
			}else if(i==1) {
				mySet1.addAll(randomList);
				mySet2.retainAll(randomList);
				mySet1.removeAll(mySet2);
			}else {
				mySet2.clear();
				mySet2.addAll(mySet1);
				mySet1.addAll(randomList);
				mySet2.retainAll(randomList);
				mySet1.removeAll(mySet2);
			}
		}
		
		Iterator it = mySet1.iterator();
		System.out.print("The symmetric difference of these lists is:\n[");
		while(it.hasNext()) {
			System.out.print(it.next()+",");
		}
		System.out.print("]");
	}
}

class ListOperation{
	Random random = new Random();
	public List<Integer> createList(){
		List<Integer> intList= new ArrayList<Integer>();
		int listSize = random.nextInt(5)+1;
		for(int i=0 ; i<listSize; i++) {
			int randomInt = random.nextInt(10) +1;
			intList.add(randomInt);
		}
		return intList;
	}
	public void printList(List<Integer> intList){
		System.out.print("[");
		for(int i=0; i<intList.size(); i++) {
			if(i!=(intList.size()-1)) {
				System.out.print(intList.get(i)+",");
			}else {
				System.out.print(intList.get(i)+"]\n");
			}
		}
	}
}