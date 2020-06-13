package researchman2.models;

import java.io.Serializable;


/**
 * @overview This class could create a publication instance that contains "research paper" type.   
 * @Attribute DOI        	 	String   
 *            title          	String
 *            yop            	int
 *            journal_title  	String
 * @author 
 *
 */


public class JournalArticle extends Publication implements Comparable<Publication>,Serializable {
	
	private String journal_title;
	
	public JournalArticle(){
		super();
	}
		
	public JournalArticle(String DOI,String title,int yop, String journal_title){
		this.DOI = DOI;
		this.title = title;
		this.yop = yop;
		this.journal_title = journal_title;
	}
	
	/**
     * @effects  return title of the Journal Article 
     */
	@Override
	public String getJournalTitle(){
		return this.journal_title;
	}
	
	/**
     * @modifies this.journal_title
     * @effects  set value for title for the JournalArticle 
     */
	@Override
	public void setJournalTitle(String journal_title){
		this.journal_title = journal_title;
	}
	/**
     * @effects  return string format of the JournalArticle object 
     */
	
	private boolean validJournalTitle(String journal_title){
		if(journal_title== null || journal_title.length() == 0){
            return false;
        }else{
            return true;
        }
	}

	 /**
     * 
     * @Effect if all title, DOI and year of birth and journal title is valid return true, else return false
     */
	@Override
    public boolean validate(String title, String DOI, int yop, String journal_title){
        return validTitle(title)&&validDOI(DOI)&&validYOP(yop)&&validJournalTitle(journal_title);
    }
	
	@Override
	public String toString() {
		String str = String.format("<%s, %s, %s, %s>",getTitle(), getDOI().toString(), getYOP(),getJournalTitle());
		return str;
	}

	@Override
	public int compareTo(Publication o) {
		return this.title.compareTo(o.title);
	}
	
}
