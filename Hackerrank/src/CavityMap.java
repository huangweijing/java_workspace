import java.util.Scanner;


public class CavityMap {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int n = Integer.valueOf(scanner.nextLine());
		int[][] map = new int[n][n];
		for(int i=0; i<n; i++) {
			String line = scanner.nextLine();
			for(int j=0; j<line.length(); j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i>=1 && j >=1 && i<n-1 && j<n-1) {
					if(map[i-1][j] < map[i][j] &&
						map[i+1][j] < map[i][j] &&
						map[i][j-1] < map[i][j] &&
						map[i][j+1] < map[i][j]) {
						System.out.print('X');
					}else {
						System.out.print(map[i][j]);
					}
									
				} else {
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}
		
	}

}
