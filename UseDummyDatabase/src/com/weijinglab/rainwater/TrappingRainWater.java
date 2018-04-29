package com.weijinglab.rainwater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrappingRainWater {

	public static void main(String[] args) {
		System.out.println("积水量: " + trapRainWater(new Integer[]{0,1,0,2,1,0,1,3,2,1,2,1}));
		System.out.println("积水量: " + trapRainWater(new Integer[]{0,8,0,1}));
		System.out.println("积水量: " + trapRainWater(new Integer[]{0,8,0,1,4}));
		System.out.println("积水量: " + trapRainWater(new Integer[]{4,-2,8,0,1}));
		System.out.println("积水量: " + trapRainWater(new Integer[]{1,0,7,0,1}));
		System.out.println("积水量: " + trapRainWater(new Integer[]{0,1,3,2,1}));
		System.out.println("积水量: " + trapRainWater(new Integer[]{0,1,-1,-2,1}));
		System.out.println("积水量: " + trapRainWater(new Integer[]{0,1,-1,-2,1}));
	}
	
	public static Integer trapRainWater(Integer[] elevationMap){
		Integer waterVolume = 0;
		
		List<HeightInfo> heightInfoList = new ArrayList<HeightInfo>();
		Set<Integer> heightSet = new HashSet<Integer>();
		List<Integer> sortedHeightSetList = new ArrayList<Integer>();
		for(int i=0; i<elevationMap.length; i++) {
			HeightInfo heightInfo = new HeightInfo();
			heightInfo.height = elevationMap[i];
			heightInfo.index = i;
			heightSet.add(elevationMap[i]);
			heightInfoList.add(heightInfo);
		}

		sortedHeightSetList.addAll(heightSet);
		Collections.sort(sortedHeightSetList);
		Collections.sort(heightInfoList);
		System.out.println("下雨前：" + Arrays.asList(elevationMap));

		Integer preMaxIdx = null;
		Integer preMinIdx = null;
		for(int i=sortedHeightSetList.size() - 1; i>=0; i--) {
			
			Integer maxIdx = Integer.MIN_VALUE;
			Integer minIdx = Integer.MAX_VALUE;
			
			Integer currentHeight = sortedHeightSetList.get(i);

			//从大到小找区间
			for(int j=heightInfoList.size() - 1; j>=0; j--) {
				if(heightInfoList.get(j).height==currentHeight) {
					Integer index = heightInfoList.get(j).index;
					if(maxIdx <= index) {
						maxIdx = index;
					} 
					if(minIdx >= index) {
						minIdx = index;
					}
				}
			}
//			System.out.println("------");
//			System.out.println(currentHeight);
//			System.out.println(maxIdx);
//			System.out.println(minIdx);
			
			if(preMaxIdx != null && preMinIdx != null ) {
				if(maxIdx < preMinIdx) {
					for(Integer j = minIdx; j <= preMinIdx; j++) {
						Integer water = currentHeight - elevationMap[j];
						if(water <= 0) {
							continue;
						}
						waterVolume = waterVolume + water;
						elevationMap[j] = currentHeight;
						
					}
				}
				else if(minIdx > preMaxIdx) {
					for(Integer j = preMaxIdx; j <= maxIdx; j++) {
						Integer water = currentHeight - elevationMap[j];
						if(water <= 0) {
							continue;
						}
						waterVolume = waterVolume + water;
						elevationMap[j] = currentHeight;
					}
				}
				else {
					for(Integer j = minIdx; j <= maxIdx; j++) {
						Integer water = currentHeight - elevationMap[j];
						if(water <= 0) {
							continue;
						}
//						System.out.println("wat" + water);
						waterVolume = waterVolume + water;
						elevationMap[j] = currentHeight;
					}
				}
			}else {
				for(Integer j = minIdx; j <= maxIdx; j++) {
					Integer water = currentHeight - elevationMap[j];
					if(water <= 0) {
						continue;
					}
//					System.out.println("wat" + water);
					waterVolume = waterVolume + water;
					elevationMap[j] = currentHeight;
				}
			}
			
			
			preMaxIdx = maxIdx;
			preMinIdx = minIdx;
//			System.out.println("------");
		}

		System.out.println("下雨后：" + Arrays.asList(elevationMap));
		
		
		return waterVolume;
		
	}

}
