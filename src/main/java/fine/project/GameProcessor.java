package fine.project;

import java.util.ArrayList;
import java.util.List;

import fine.project.algorythms.Algorythm;
import fine.project.algorythms.CornersAlgorythm;
import fine.project.model.ColumnObj;

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
		List<ColumnObj> columsSplited = picture.getColumsSplited();
		while (picture.isDone()){			
			for (ColumnObj columObj : columsSplited) {
				for (Algorythm alg : algorythms) {
					alg.calculate(columObj);
				}
			}
		}
	}
}
