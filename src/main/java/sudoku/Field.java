package sudoku;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by TT on 20.12.2015.
 */
public class Field {
    final static Logger log = Logger.getLogger(Field.class);
    Set<Cell> unSolved= new HashSet<>();
    Cell[][] field= new Cell[9][9];
    Cell[]rows;
    Cell[][]columns;
    int solved=0;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    int total=0;
   public void addRow(List <Cell> row){
//       field.add(row);
   }
    public Cell getCell(int y,int x){
        int rowNum=0;
        for(Cell[]row:field){
            if(y==rowNum){
                int colNum=0;
                for(Cell c:row){
                    if(x==colNum){
                     return c;
                    }
                    colNum++;
                }
            }
            rowNum++;
        }
        return null;
    }



    public void addUnsolved(Cell c) {
        unSolved.add(c);
    }
    public void removeUnsolved(Cell c) {
        unSolved.remove(c);
    }

    public void showField(){
      for(Cell[]row:field){
          for(Cell cell:row){
              log.debug(ValueAdapter.getValue(cell.getValue()));
          }
          log.debug("");
      }
    }
    public void showField2(){
      for(Cell[]row:field){
          for(Cell cell:row){
              log.debug("\t" + cell.getId() + " " + ValueAdapter.getValue(cell.getValue()));
          }
          log.debug("");
      }
    }
    public  List<List<Cell>> getField(){
        return null;
    }
    public void assignGroups(){



        columns= new Cell[field.length][field[0].length];

        for( Cell[] row:field){

            for(Cell cell:row){
               log.debug(" "+cell);
                columns[cell.getX()][cell.getY()]=cell;

            }
//            y++;
        }
    }
//
    private void assignSmallGroups(Cell[][] array) {
//       Cell[] g=new Cell[9]{array}
    }

    private Cell[][] listToArray(List<List<Cell>> field) {
        Cell[][] array=new Cell[field.size()][];
        for (int i = 0; i < field.size(); i++) {
            List<Cell> row = field.get(i);
            array[i] = row.toArray(new Cell[row.size()]);
        }
        return array;
    }

    public Cell[][] transposeField(Cell[][] field) { //todo make generic

        int m = field.length;
        int n = field[0].length;

        Cell[][] trasposedField = new Cell[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                trasposedField[x][y] = field[y][x];
            }
        }
        return trasposedField;

    }

    public void count() {
        if (total == 0) {


            for (Cell[] row : field) {
                for (Cell cell : row) {
                    total++;
                }
            }
        }
    }
    public float percentage(){
       return (total-getUnSolvedSize())*100/total;
    }

    public int getUnSolvedSize() {
        return unSolved.size();
    }
    public Set<Cell> getUnSolved() {
        return unSolved;
    }

    public void setRow(Cell[] cells, int y) {
        field[y]=cells;
    }

    public List<Cell> getRow(int y) {
        return Arrays.asList(field[y]);
    }

    public List<Cell> getColumn(int x) {

        return Arrays.asList(columns[x]);
    }
    public void showColumns(){

    }
}
