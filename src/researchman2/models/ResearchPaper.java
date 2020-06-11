package researchman2.models;

import java.io.Serializable;

/**
 * @overview This class could create a publication instance that contains "research paper" type.   
 * @Attribute DOI        	 String   
 *            title          String
 *            yop            int
 * @author 
 *
 */

public class ResearchPaper extends Publication implements Comparable<Publication>,Serializable {


	public ResearchPaper() {
		super();
	}
	
	public ResearchPaper(String DOI,String title, int yop) {
		this.DOI = DOI;
		this.title = title;
		this.yop = yop;
	}


	@Override
	public int compareTo(Publication o) {
		return this.title.compareTo(o.title);
	}
}