package researchman2.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
public class SimplePublicationTableModel extends AbstractTableModel {
   private String columnNames[]={"Doi","Title","Year of publication"};         
   private ArrayList<Publication> myList;
   public SimplePublicationTableModel(ArrayList<Publication> pubList) {
      myList = pubList;
   }
   public int getColumnCount() {
      return this.columnNames.length;
   }
   public int getRowCount() {
      int size;
      if (myList == null) {
         size = 0;
      }
      else {
         size = myList.size();
      }
      return size;
   }
   public Object getValueAt(int row, int col) {
      Object temp = null;
      if (col == 0) {
         temp = myList.get(row).getDOI();
      }
      else if (col == 1) {
         temp = myList.get(row).getTitle();
      }
      else if (col == 2) {
         temp = Integer.valueOf(myList.get(row).getYOP());
      }
      
      return temp;
   }
   // needed to show column names in JTable
   public String getColumnName(int col) {
      return columnNames[col];
   }
   public Class getColumnClass(int col) {
      if (col == 2) {
         return Double.class;
      }
      else {
         return String.class;
      }
   }
}
