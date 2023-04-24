package vttp.ssf.rng.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RNG {
	private Integer inputVal;
	private List<String> images = new LinkedList<>();

	public Integer getInputVal() {return inputVal;}
	public void setInputVal(Integer inputVal) {this.inputVal = inputVal;}

	public List<String> getImages() {return images;}
	public void setImages(List<String> images) {this.images = images;}

	public static RNG create(Integer inputVal) {
		RNG rng = new RNG();
		int min = 1;
		int max = 30;
		int multipliedVal = max - min + 1;
		int randNum;
		List<Integer> generatedNums = new ArrayList<Integer>();
		List<String> numImgs = new ArrayList<String>();

		if (inputVal >= min && inputVal <= max) {
			for (int i = 0; i < inputVal; i++) {
				randNum = (int)(Math.random() * multipliedVal) + min;
				if (generatedNums.indexOf(randNum) == -1) {
					generatedNums.add(randNum);
				} else {
					i--;
				}
			}
		}
        System.out.println(generatedNums);

        for (int n: generatedNums)
            numImgs.add("/images/" + n + ".jpg");

        rng.setImages(numImgs);
        return rng;
	}
}
