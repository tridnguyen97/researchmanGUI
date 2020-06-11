package researchman2.controllers;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import researchman2.NotPossibleException;
import researchman2.models.Publication;
import researchman2.models.ResearchPaper;
import researchman2.models.SimplePublicationTableModel;
import researchman2.models.JournalArticle;

/**
 * @overview represents the data manager class responsible for managing the publication
 *           objects.
 * 
 * @attributes publications ArrayList <br>
 *             gui JFrame
 * 
 * @abstract_properties
 *  optional(publications) = false /\ <br>
 *  optional(gui) = false
 * @author congnv
 */

public class PublicationManager extends WindowAdapter implements ActionListener {
    private final String STORAGE_FILE = "publications.dat";

    private ArrayList<Publication> publications = new ArrayList<Publication>();

    // view components
    private JFrame gui;
    // more view components here
    JTextField tfDoi,tfTitle,tfYop,tfJournal;
    JLabel lb1,lb2,lb3,lb4,lb5;
    JComboBox cb;

    /**
     * @effects initialise <tt>this</tt> with an empty set of publications <br>
     *          {@link #createGUI()}: create <tt>gui</tt>
     */
    public PublicationManager() {
    	createGUI();
    }

    /**
     * @modifies this.gui
     * @effects create <tt>gui</tt> as required (mockup image) <br>
     *          The action listener of the buttons & combobox is <tt>this</tt>.
     */
    public void createGUI() {
        // TODO: complete this code
    	this.gui = new JFrame();
    	this.lb1 = new JLabel("Publication type:");
    	lb1.setBounds(10,50,100,40);
    	lb2 = new JLabel("Doi: (*)");
    	lb2.setBounds(10,100,100,40);
    	lb3 = new JLabel("Title: (*)");
    	lb3.setBounds(10,150,100,40);
    	lb4 = new JLabel("Year of publication: (*)");
    	lb4.setBounds(10,200,150,40);
    	lb5 = new JLabel("Journal Title");
    	this.lb5.setBounds(10,250,100,40);
    	this.lb5.setVisible(false);
    	

    	this.tfDoi = new JTextField();
    	this.tfDoi.setBounds(150,100,200,40);
    	this.tfTitle = new JTextField();
    	this.tfTitle.setBounds(150,150,200,40);
    	this.tfYop = new JTextField();
    	this.tfYop.setBounds(150,200,200,40);
    	this.tfJournal = new JTextField();
    	this.tfJournal.setBounds(150,250,200,40);
    	this.tfJournal.setVisible(false);
    	
    	this.gui.add(lb1);
    	this.gui.add(lb2);
    	this.gui.add(lb3);
    	this.gui.add(lb4);
    	this.gui.add(lb5);
    	
    	this.gui.add(this.tfDoi);
    	this.gui.add(this.tfTitle);
    	this.gui.add(this.tfYop);
    	this.gui.add(this.tfJournal);
    	
    	String publication_type[]={"Research Paper","Journal Article"};        
        cb = new JComboBox(publication_type);    
        cb.setBounds(150, 50,200,40);
        cb.addActionListener(this);
        this.gui.add(cb);
    	
    	JButton b=new JButton("add");//creating instance of JButton  
    	b.setBounds(80,305,100, 40);//x axis, y axis, width, height  
    	this.gui.add(b);//adding button in JFrame  
    	b.addActionListener(this);
    	
    	
    	JButton add_btn=new JButton("cancel");//creating instance of JButton  
    	add_btn.setBounds(280,305,100, 40);//x axis, y axis, width, height  
    	this.gui.add(add_btn);//adding button in JFrame  
    	add_btn.addActionListener(this);
    	
    	
    	
    	this.gui.setTitle("create new Publication");        
    	this.gui.setSize(500,400);  
    	this.gui.setLayout(null); 
    	
    }
    
    /**
     * @requires <tt>gui != null</tt>
     * @effects show <tt>gui</tt>
     */
    public void display() {
        if(this.gui != null) this.gui.setVisible(true);
    }

    /**
     * @effects handles user actions on the buttons & combobox
     * 
     *          <pre>
     *          if button is OK
     *            {@link #createPublication()}: create a new publication from form data
     *          else if button is Cancel
     *            {@link #clearInput()}: reset form fields
     *          else if combobox Type
     *            show (or add)/ hide (or remove) the input for episode as corresponding to the selected type
     *          </pre>
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
    	Object source = e.getSource();
    	if(source instanceof JButton){
    		
    	}
    	if(e.getActionCommand().equals("click")){
    		String infoMessage = "ahaha";
    		String titleBar = "ahihi";
    		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    	}
    	
    	if(e.getActionCommand().equals("add")){
    		this.createPublication();
    	}
    	
    	if(e.getActionCommand().equals("cancel")) this.clearInput();
    	
    	else if(source instanceof JComboBox){
    		JComboBox cb = (JComboBox)source;
    		String publicationType = (String)cb.getSelectedItem();
        	if(publicationType.equals("Journal Article")){
        		this.tfJournal.setVisible(true);
        		this.lb5.setVisible(true);
        	}
        	else{
        		this.tfJournal.setVisible(false);
        		this.lb5.setVisible(false);
        	}
    	}
    	
    }
    
    /**
     * @effects create an independent <tt>reportGUI</tt> interface with a table of all <tt>publications</tt>, 
     *  and show that <tt>reportGUI</tt>
     */
    public void showPublications() {
        // TODO: complete this code
    	this.startUp();
    	JFrame report_gui = new JFrame();
    	SimplePublicationTableModel tableModel = new SimplePublicationTableModel(this.publications);
    	JTable jt=new JTable(tableModel);    
    	JScrollPane sp=new JScrollPane(jt);

    	sp.setBounds(30,40,400,500);          
    	report_gui.add(sp);  
    	JButton b=new JButton("click");//creating instance of JButton  
    	b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
        
    	//report_gui.add(table);
    	report_gui.add(b);//adding button in JFrame  
    	report_gui.setTitle("show all Publications");        
    	report_gui.setSize(400,500);//400 width and 500 height  
    	report_gui.setLayout(null);//using no layout managers 
    	report_gui.setVisible(true);//making the frame visible 
    }

    /**
     * @effects
     * 
     *          <pre>
     *  create a new module from the data in the data panel of gui and add it to <tt>this.publications</tt>
     *     
     *  if not success (exception is thrown)
     *      display the exception message on the GUI dialog
     *          </pre>
     */
    private void createPublication() {
    	String newDOI = this.tfDoi.getText();
    	String newTitle = this.tfTitle.getText();
    	int newYOP = Integer.valueOf(this.tfYop.getText());
    	Publication publication = new Publication(newDOI,newTitle,newYOP);
		this.publications.add(publication);
		
    }

    /**
     * @requires this.tfDoi != null /\ this.tfTitle != null /\ this.tfYop != null
     *           /\ this.tfJournal != null
     * @effects clear form fields
     */
    private void clearInput() {
        if((this.tfDoi != null) && (this.tfTitle != null) && (this.tfYop != null) && (this.tfJournal != null)){
        	this.tfDoi.setText("");
        	this.tfTitle.setText("");
        	this.tfYop.setText("");
        	this.tfJournal.setText("");
        }
    }

    /**
     * @requires <tt>gui != null</tt>
     * @effects hide <tt>gui</tt>
     */
    @Override
    public void windowClosing(WindowEvent e) {
    	if(gui!= null) this.gui.setVisible(false);//making the frame visible 
    	
    }

    /**
     * @requires <tt>publications != null</tt>
     * @modifies this
     * @effects load into <tt>publications</tt> the data publications in the storage file
     *          <tt>publications.dat</tt> that was saved before.
     * 
     *          <pre>
     *          if succeeds 
     *            display a console message 
     *          else 
     *            display a console error message
     *          </pre>
     */
    public void startUp() {
        try {
			
			FileInputStream fi = new FileInputStream(new File(this.STORAGE_FILE));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			// Read objects
			this.publications = (ArrayList<Publication>) oi.readObject();
			System.out.println("Starting up...");
			System.out.println("PublicationManager.loaded ..." + this.publications.size()+ " objects");
			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    

    /**
     * @requires <tt>gui != null</tt>
     * @modifies this
     * @effects store <code>this.publications</code> to the file <br>
     *          dispose <tt>gui</tt> <br>
     *          clear <tt>publications</tt> and shutdown the application
     */
    public void shutDown() {
    	
    	FileOutputStream f;
    	ObjectOutputStream o;
		try {
			f = new FileOutputStream(this.STORAGE_FILE);
			o = new ObjectOutputStream(f);
			// Write objects to file
			o.writeObject(this.publications);
			System.out.println("Shutting down...");
			System.out.println("PublicationManager.shutDown ...stored " + this.publications.size()+ " objects");
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
