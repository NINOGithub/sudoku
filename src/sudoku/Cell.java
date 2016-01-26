package sudoku;

import java.util.*;

/**
 * Created by TT on 20.12.2015.
 */
public class Cell {
    private String id;

int x;
    int y;

//    private List<Cell> hrow;
//    private List<Cell> vrow;
    private List<Cell> group;
    private Field field;
    private boolean notSolved=true;
    private boolean processed=false;

    public static Cell createCell(int x, int y, Field field, Value value) {
        Cell cell = new Cell(x, y);
        cell.field = field;
        field.addUnsolved(cell);
        if (value != null) {
            cell.setValue(value);
        }
        return cell;
    }
    private Cell(int x,int y){
        this.x=x;
        this.y=y;


        id=y+":"+x;
    }
    public String getId() {
        return id;
    }



    Value value;



    public Set<Value> getPossibleValues() {
        return possibleValues;
    }

    public Set<Value> getForbiddenValues() {
        return forbiddenValues;
    }

    Set<Value> possibleValues= new HashSet<>(Arrays.asList(Value.values()));
    Set<Value> forbiddenValues= new HashSet<>();

    public void setValue(Value v) {
        if (this.value == null || notSolved) {
            notSolved=false;
            this.value = v;
            List<Value> valuesToForbid = new ArrayList<>(Arrays.asList(Value.values()));
            valuesToForbid.remove(v);
            forbidValues(valuesToForbid);
        }else if(this.value.equals(v)){
//            System.out.println("been there done that");
        }else{
            throw new RuntimeException("IT does not fit");
        }

    }

    private void forbidValues(List<Value> values){
        forbiddenValues.addAll(values);
        possibleValues.removeAll(values);
        if(possibleValues.size()==0){
            throw new RuntimeException(this +" has no more possible values");
        }

    }
    public void forbidValue(Value value){
        possibleValues.remove(value);
        forbiddenValues.add(value);
        if(possibleValues.size()==0){
            throw new RuntimeException(this +" has no more possible values");
        }
    }

    public Value getValue() {
        return possibleValues.size()==1?possibleValues.iterator().next():null;
    }
    public boolean equals(Object o){
        return o instanceof Cell && ((Cell) o).getId().equals(getId());

    }
    public int hashCode(){
        return getId().hashCode();
    }




    public String toString(){
        return getId();
    }
    public String getInfo(){
        return "Cell id "+getId()+" Possible values "+possibleValues+" value "+value+" is Processed "+isProcessed()+" is unsolved "+notSolved;
    }
    public List<Cell> getGroup() {
        return group;
    }

    public void setGroup(List<Cell> group) {
        this.group = group;
    }

    public boolean isNotSolved() {
        return notSolved;
    }



    public void markAsSolved() {
        this.notSolved=false;
        this.processed=true;

        field.removeUnsolved(this);
    }

    public boolean isProcessed() {
        return processed;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
