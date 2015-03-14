package japan;

import japan.algorythms.Algorythm;
import japan.algorythms.CornersAlgorythm;

import java.util.ArrayList;
import java.util.List;

public class GameProcessor {
	private Picture picture;
	private List<Algorythm> algorythms;
	
	public GameProcessor(Picture picture) {
		this.picture = picture;
		
	}

	public GameProcessor(Picture picture,List<Algorythm> algorythms) {
		this.picture = picture;
		this.algorythms = algorythms;
	}
	
	List<Algorythm> getDefaultAlgorythms(){
		List<Algorythm> defaultAlgorythms = new ArrayList<Algorythm>();
		defaultAlgorythms.add(new CornersAlgorythm());

		return defaultAlgorythms;
		
	}
	
	
	public void process(){
		List<ColumObj> columsSplited = picture.getColumsSplited();
		while (picture.isDone()){			
			for (ColumObj columObj : columsSplited) {
				for (Algorythm alg : algorythms) {
					alg.calculate(columObj);
				}
			}
		}
	}
}
