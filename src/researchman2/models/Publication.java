package researchman2.models;

import researchman2.DomainConstraint;
import researchman2.DomainConstraint.Type;
/**
 * @overview This class is able to store a bunch of required publication information. 
 * There are two children classes inheriting from this class: ResearchPaper and JournalArticle  
 * @Attribute DOI        	 String   
 *            title           String
 *            yop            int
 * @Abstract properties mutable(DOI) = true /\ optional == false /\ length = 20
 *           mutable(title) = true /\ optional == false /\ length = 50
 *           mutable(yop) = false /\ optional= false /\ min = 1900 /\ max = 2020
 * @author 
 *
 */

public class Publication {
	private final int LENGTH_DOI = 20;
	private final int LENGTH_title = 50;
	private final int MIN_YOP = 0;
    private final int MAX_YOP = 2020;
	
	@DomainConstraint(type = Type.String, mutable = true, optional = false, length = LENGTH_DOI)
	protected String DOI;
	@DomainConstraint(type = Type.String, mutable = true, optional = false, length = LENGTH_title)
	protected String title;
	@DomainConstraint(type = Type.Integer, mutable = false, optional = false, min = MIN_YOP,max = MAX_YOP)
	protected int yop;
    
	public Publication() {
	}
    
	
	public Publication(String DOI, String title, int yop) {
		
		this.DOI = DOI;
		this.title = title;
		this.yop = yop;

	}
    
	/**
     * 
     * @Effect return DOI of publication 
     */
	
	public String getDOI() {
		return DOI;
	}
    
	/**
     * 
     * @Effect return title of publication 
     */
	
	public String getTitle() {
		return title;
	}
	
	/**
     * 
     * @Effect return Year Of Publication 
     */
    
	public int getYOP() {
		return yop;
	}
    
	/**
     * @modifies this.DOI
     * @effects  set value for DOI of the publication 
     */

	public void setDOI(String DOI){
		this.DOI = DOI;
	}
	/**
     * @modifies this.YOP
     * @effects  set value for YOP for the publication 
     */
	public void setYOP(int yop){
		this.yop = yop;
	}
	
	/**
     * @modifies this.title
     * @effects  set value for title for the publication 
     */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
     * 
     * @Effect if title is valid return true, else return false
     */
    private boolean validTitle(String title){
        if(title== null || title.length() == 0 || title.length()>LENGTH_title){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * 
     * @Effect if year of publish is valid return true, else return false
     */
    private boolean validYOP(int yop){
        if(yop<MIN_YOP && yop > MAX_YOP){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * 
     * @Effect if DOI is valid return true, else return false
     */
    private boolean validDOI(String DOI){
    	if(DOI== null || DOI.length() == 0 || DOI.length()>LENGTH_title){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * 
     * @Effect if all title, DOI and year of birth is valid return true, else return false
     */
    private boolean validate(String title, String DOI, int yop){
        return validTitle(title)&&validDOI(DOI)&&validYOP(yop);
    }
    
    /**
     * 
     * @Effects if this is valid with regards to abstract properties return all attributes
     */  
    public boolean repOK(){
        return validTitle(title)&&validDOI(DOI)&&validYOP(yop);
    }
    
    /**
     * 
     * @Effects Return a string of the object
     */
    
    public String toString(){
        String str = String.format("<%s, %s, %s>",getTitle(), getDOI().toString(), getYOP());
        return str;
    }
}
