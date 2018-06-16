import java.io.*;
import java.util.*;

public class SuperiorCharacters {

    // Complete the maximumSuperiorCharacters function below.
    static long maximumSuperiorCharacters(int[] freq) {
		long result = 0;
		long count_sum = 0;
		for(int f : freq) {
			count_sum += f;
		}
		
		List<Long> listMin = new ArrayList<>();
		List<Long> listMax = new ArrayList<>();
		
//		int listMin = int[14];
		
		long middle = (count_sum + 1)/ 2;
		long minSum = 0;
		long middle_left = 0;
		long middle_right = 0;
		for(int f : freq) {
			if(f == 0)
				continue;
			if(minSum + f <= middle) {
				listMin.add((long)f);
			} else if(minSum >= middle) {
				listMax.add((long)f);
			} else {
				middle_left = middle - minSum;
				middle_right = minSum + f - middle;
				listMin.add(middle_left);
				listMax.add(middle_right);
			}
			minSum = minSum + f;
		}
		
//		System.out.println(listMin);
//		System.out.println(listMax);
		
		for(int i=0; i<listMin.size(); i++) {
			for(int j=0; j<listMax.size(); j++) {
				long minCnt = listMin.get(i);
				long maxCnt = listMax.get(j);
				
				if(i == listMin.size() - 1
						&& j == 0) {
					if(middle_left != 0 &&
							listMax.get(j) != 0)
						continue;
				}
				
				
				if(maxCnt == 0)
					continue;
				if(minCnt >= maxCnt) {
					minCnt = minCnt - maxCnt;
					listMax.set(j, 0L);
					listMin.set(i, minCnt);
					result += maxCnt;
				} else {
					maxCnt = maxCnt - minCnt;
					listMax.set(j, maxCnt);
					listMin.set(i, 0L);
					result += minCnt;
					break;
				}
			}
		}

//		System.out.println("----");
//		System.out.println(listMin);
//		System.out.println(listMax);
//		System.out.println(listMin.get(0).equals(listMax.get(0)));
		
		if(listMin.size() == 0 || listMax.size() == 0)
			return result;
//		if(listMin.get(listMin.size() - 1).equals(listMax.get(0)))
//			return result;
//		
		if(listMin.get(listMin.size() - 1) != 0
				&& listMax.get(0) == 0) {
			
		} else {
			if(result != 0)
				result--;
		}
		
		
		
		return result;


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int[] freq = new int[26];

            String[] freqItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int freqItr = 0; freqItr < 26; freqItr++) {
                int freqItem = Integer.parseInt(freqItems[freqItr]);
                freq[freqItr] = freqItem;
            }

            long result = maximumSuperiorCharacters(freq);
            System.out.println(result);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
