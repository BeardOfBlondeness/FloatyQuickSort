package boot;

import java.util.Random;

public class StartUp {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Random maxRand = new Random();
		int maxSize = maxRand.nextInt(4000);

		Float[] list = new Float[maxSize];

		for(int i = 0; i < maxSize; i++) {

			int isOutlier = new Random().nextInt(50);

			if(isOutlier!=1) {
				Random tempRand = new Random();
				list[i] = tempRand.nextFloat() * 2 - 1;
			} else {
				int choiceOfOutlier = new Random().nextInt(5);
				if(choiceOfOutlier == 0) 
					list[i] = list[i].intBitsToFloat(0x7f800000);
				else if(choiceOfOutlier == 1) 
					list[i] = list[i].intBitsToFloat(0xff800000);
				else if(choiceOfOutlier == 2) 
					list[i] = new Float(-0.0);
				else if(choiceOfOutlier == 3)
					list[i] = Float.NaN;
				else if(choiceOfOutlier == 4)
					list[i] = new Float(0.0);
			}
		}

		quickSort(list, 0, maxSize - 1);
		
		System.out.println("=============================================");
		for(int x = 0; x < maxSize; x++) {
			System.out.println(list[x]);
		}
	}

	static void quickSort(Float[] list, int lowest, int max) {
		if(list == null || list.length == 0 || lowest >= max)
			return;
		int middle = lowest + (max - lowest) / 2;
		Float pivot = list[middle];
		int i = lowest, j = max;
		while (i <= j) {
			while (list[i] < pivot || (list[i].equals(new Float(-0.0)) && pivot.equals(new Float(0.0)))) {
				i++;
			}

			while (list[j] > pivot || (list[j].equals(new Float(0.0)) && pivot.equals(new Float(-0.0)))) {
				j--;
			}
			if (i <= j) {
				float temp = list[i];
				list[i] = list[j];
				list[j] = temp;
				i++;
				j--;
			}
		}

		if (lowest < j)
			quickSort(list, lowest, j);
		if (max > i)
			quickSort(list, i, max);
	}
}
