package com.weijinglab.firenet;

public class FireNet {
	
	public static char STREET = '.';
	public static char WALL = 'X';
	public static char BLOCKHOUSE = 'O';
	public static char FIRE = 'F';

	public static void main(String[] args) {
		char[][] cityMap = new char[][]{
//				new char[]{ STREET, WALL, STREET, STREET, STREET, STREET }
//				, new char[]{ STREET, STREET, WALL, STREET, STREET, STREET }
//				, new char[]{ STREET, WALL, STREET, STREET, STREET, STREET }
//				, new char[]{ STREET, STREET, STREET, STREET, STREET, STREET }
//				, new char[]{ STREET, WALL, STREET, STREET, STREET, STREET }
//				, new char[]{ STREET, STREET, WALL, STREET, STREET, STREET }
				

				  new char[]{ STREET, WALL,   STREET, STREET,  }
				, new char[]{ STREET, STREET, STREET, STREET, }
				, new char[]{ WALL,   WALL,   STREET, STREET, }
				, new char[]{ STREET, STREET, STREET, STREET, }
				
//				  new char[]{ STREET, STREET, STREET,  }
//				, new char[]{ STREET, WALL, WALL,  }
//				, new char[]{ STREET, WALL, WALL,  }

//				  new char[]{ STREET, WALL, STREET,  }
//				, new char[]{ WALL, STREET, WALL,  }
//				, new char[]{ STREET, WALL, STREET,  }
				
//				  new char[]{ WALL, WALL,  }
//				, new char[]{ STREET, WALL,  }
				
		};

		FirenetResult result = calcMaxBlockhouse(cityMap);
		System.out.println(result.blockhouseCnt);
		printCityMap(result.solution);
	}

	public static FirenetResult calcMaxBlockhouse(char[][] cityMap) {
		if(sumElementCount(cityMap, STREET) == 0) {
			FirenetResult result = new FirenetResult();
			result.blockhouseCnt = sumElementCount(cityMap, BLOCKHOUSE);
			result.copySolution(cityMap);
			return result;
		}

		FirenetResult maxResult = new FirenetResult();
		maxResult.blockhouseCnt = Integer.MIN_VALUE;
		for(int row = 0; row < cityMap.length; row++) {
			for(int col = 0; col < cityMap[row].length; col++) {
				if(cityMap[row][col] == STREET) {
					cityMap[row][col] = BLOCKHOUSE;
					resetFireNet(cityMap);
//					printCityMap(cityMap);
					FirenetResult result = calcMaxBlockhouse(cityMap);
					if(result.blockhouseCnt > maxResult.blockhouseCnt) {
						maxResult = result;
					}
					cityMap[row][col] = STREET;
					resetFireNet(cityMap);
//					printCityMap(cityMap);
				}
			}
		}
		return maxResult;
	}
	
	public static void resetFireNet(char[][] cityMap) {

		for(int row = 0; row < cityMap.length; row++) {
			for(int col = 0; col < cityMap[row].length; col++) {
				if(cityMap[row][col] == FIRE) {
					cityMap[row][col] = STREET;
				}
			}
		}
		
		for(int row = 0; row < cityMap.length; row++) {
			for(int col = 0; col < cityMap[row].length; col++) {
				if(cityMap[row][col] == BLOCKHOUSE) {
					
					for(int i = col + 1; i < cityMap[row].length; i++) {
						if(cityMap[row][i] != WALL) {
							cityMap[row][i] = FIRE;
						} else {
							break;
						}
					}
					
					for(int i = col - 1; i >= 0; i--) {
						if(cityMap[row][i] != WALL) {
							cityMap[row][i] = FIRE;
						} else {
							break;
						}
					}

					for(int i = row + 1; i < cityMap.length; i++) {
						if(cityMap[i][col] != WALL) {
							cityMap[i][col] = FIRE;
						} else {
							break;
						}
					}
					
					
					for(int i = row - 1; i >= 0; i--) {
						if(cityMap[i][col] != WALL) {
							cityMap[i][col] = FIRE;
						} else {
							break;
						}
					}
				}
			}
		}
	}
	
	public static Integer sumElementCount(char[][] cityMap, char element) {
		Integer elementCount = 0;
		for(int row = 0; row < cityMap.length; row++) {
			for(int col = 0; col < cityMap[row].length; col++) {
				if(cityMap[row][col] == element) {
					elementCount++;
				}
			}
		}
		return elementCount;
	}
	
	
	public static void printCityMap(char[][] cityMap) {
		for(int row = 0; row < cityMap.length; row++) {
			for(int col = 0; col < cityMap[row].length; col++) {
				if(cityMap[row][col] == FIRE) {
					System.out.print(STREET);
				} else {
					System.out.print(cityMap[row][col]);					
				}
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}
	
	
}

class FirenetResult{
	public Integer blockhouseCnt;
	public char[][] solution;
	public void copySolution(char[][] cityMap) {
		this.solution = new char[cityMap.length][cityMap[0].length];
		for(int row = 0; row < cityMap.length; row++) {
			System.arraycopy(cityMap[row], 0, this.solution[row], 0, cityMap[row].length);
		}
	}
}
