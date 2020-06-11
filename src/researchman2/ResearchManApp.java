package researchman2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import researchman2.controllers.PublicationManager;

/**
 * @overview Represents the main class of the ResearchMan program.
 * 
 * @attributes pman PublicationManager gui JFrame
 * 
 * @abstract_properties optional(pman) = false /\ optional(gui) = false
 * 
 * @author congnv
 */
public class ResearchManApp extends WindowAdapter implements ActionListener {
    @DomainConstraint(type = DomainConstraint.Type.String, optional = false)
    private PublicationManager pman; // the book manager

    @DomainConstraint(type = DomainConstraint.Type.Object, optional = false)
    private JFrame gui;
    // more view components here

    /**
     * @effects initialise <tt>pman</tt> <br>
     *          {@link #createGUI()}: create <tt>gui</tt>
     */
    public ResearchManApp() {
    	this.pman = new PublicationManager();
    	this.createGUI();
    }

    /**
     * @modifies this.gui
     * @effects create <tt>gui</tt> that has a menu bar with:
     *          <ol>
     *          <li>File menu has one item: Exit
     *          <li>Publication menu has two items: 
     *          	New Publication (to create a new publication), 
     *          	All Publicatons (to show all publications)
     *          </ol>
     *          The action listener of the menu items is <tt>this</tt>.
     */
    public void createGUI() {

    	this.gui = new JFrame();
    	
    	JButton b=new JButton("click");//creating instance of JButton  
    	b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
    	          
    	this.gui.add(b);//adding button in JFrame  
    	this.gui.setTitle("ResearchManApp2");        
    	this.gui.setSize(400,500);//400 width and 500 height  
    	JMenu file_menu, publication_menu;  
        JMenuItem exit, all_publications, new_publication;  
    	JMenuBar mb=new JMenuBar();  
        file_menu=new JMenu("File");
        exit=new JMenuItem("Exit");
        exit.addActionListener(this);
        publication_menu = new JMenu("Publication");
        all_publications=new JMenuItem("All Publications");  
        new_publication=new JMenuItem("New Publication");  
        all_publications.addActionListener(this); new_publication.addActionListener(this); 
        file_menu.add(exit); publication_menu.add(all_publications); publication_menu.add(new_publication);
        mb.add(file_menu);
        mb.add(publication_menu);
        this.gui.setJMenuBar(mb);
        b.addActionListener(this);
        this.gui.setLayout(null);//using no layout managers 
    	 
    	
    }

    /**
     * @effects show <tt>gui</tt>
     */
    public void display() {
    	this.gui.setVisible(true);//making the frame visible
    }
    
    /**
     * {@link #shutDown()}
     */
    @Override
    public void windowClosing(WindowEvent e) {
        this.shutDown();
    }

    /**
     * @effects handles user actions on the menu items
     * 
     *          <pre>
     *          if menu item is Publication/New Publication
     *            {@link #pman}.display()
     *          if menu item is Publication/All Publications
     *            {@link #pman}.showPublications()}
     *          else if menu item is File/Exit 
     *            {@link #shutDown()}
     *          </pre>
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: complete this code
    	System.out.println(e.getActionCommand());
    	String action = e.getActionCommand();
    	switch(action){
    		case "All Publications":
    			pman.showPublications();
    			break;
    		case "New Publication":
    			//gui.setVisible(false);
    			pman.display();
    			break;
    		case "Exit":
    			shutDown();
    			break;
    		default:
    			break;
    	}
    }

    /**
     * @effects start up <tt>pman</tt>
     */
    public void startUp() {
        this.pman.startUp();
    	
    }

    /**
     * @effects shut down <tt>pman</tt> <br>
     *          dispose <tt>gui</tt> and end the program.
     */
    public void shutDown() {
        this.pman.shutDown();
        this.gui.dispose();
    }

    /**
     * The run method
     * 
     * @effects create an instance of <tt>ResearchManApp</tt> <br>
     *          {@link #startUp()}: start up the <tt>ResearchManApp</tt> <br>
     *          {@link #display()}: display the main gui of <tt>ResearchManApp</tt>
     */
    public static void main(String[] args) {
        ResearchManApp app = new ResearchManApp();
        app.startUp();
        app.display();
    }
}
