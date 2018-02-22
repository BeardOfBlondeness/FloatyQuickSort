package boot;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartUp {

	static int bigO = 0;
	static int[] bigOs = new int[4000];
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		for(int o = 0; o < 4000; o++) {
			bigO = 0;
			Random maxRand = new Random();
			int maxSize = o;
			Float[] list = new Float[maxSize];
			for(int i = 0; i < maxSize; i++) {
				int isOutlier;
				if(o > 100)
					isOutlier = new Random().nextInt(o/80);
				else if(o > 30)
					isOutlier = 9;
				else if(o > 20)
					isOutlier = 6;
				else if(o > 10)
					isOutlier = 4;
				else 
					isOutlier = 0;
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
			bigOs[o] = bigO;
		}

		for(int i = 0; i < 4000; i++) {
			System.out.println(bigOs[i]);
		}
		drawFrame();
	}

	static void quickSort(Float[] list, int lowest, int max) {
		if(list == null || list.length == 0 || lowest >= max)
			return;
		int middle = lowest + (max - lowest) / 2;
		Float pivot = list[middle];
		int i = lowest, j = max;
		while (i <= j) {
			while (Float.isNaN(list[i]) && (pivot.equals(new Float(+0.0)) || list[i].equals(new Float(-0.0)) && Float.isNaN(pivot) || Float.isNaN(list[i]) && pivot > 0) || list[i] < pivot || list[i].equals(new Float(-0.0)) && pivot.equals(new Float(+0.0))) {
				i++;
				bigO++;
			}
			while (Float.isNaN(list[j]) && (pivot.equals(new Float(-0.0)) || list[j].equals(new Float(0.0)) && Float.isNaN(pivot)|| Float.isNaN(list[j]) && pivot < 0) || list[j] > pivot || list[j].equals(new Float(+0.0)) && pivot.equals(new Float(-0.0))) {
				j--;
				bigO++;
			}
			if (i <= j) {
				float temp = list[i];
				list[i] = list[j];
				list[j] = temp;
				i++;
				j--;
				bigO++;
			}
		}

		if (lowest < j)
			quickSort(list, lowest, j);
		if (max > i)
			quickSort(list, i, max);
	}

	static void createGraphFrame() {
		JFrame f = new JFrame();
		f.setSize(1000, 8000);
	}


	static void drawFrame() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GraphingData(bigOs));
        f.setSize(4000,1000);
        f.setLocation(200,200);
        f.setVisible(true);
	}
}
