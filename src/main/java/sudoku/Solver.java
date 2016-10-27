package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by TT on 24.12.2015.
 */
public class Solver {
    Field field;
    public Solver(Field field){
        this.field=field;
    }
    private void log(String message){
        passInfo.append(message+"\n");
            }

    public String getPassInfo() {
        return passInfo.toString();
    }

    StringBuffer passInfo= new StringBuffer();
    public void pass(){
        passInfo= new StringBuffer();
       log("Start pass");
        for(List<Cell> row:field.getField()){
            log("Start row " + row);
            for(Cell cell:row){
                Value v=cell.getValue();

                if (v != null
                        && !cell.isProcessed()
                        ) {
                    log("Process cell "+cell.getInfo());

                    forbidValueInGroup(field.getRow(cell.getY()), cell);
                    forbidValueInGroup(field.getColumn(cell.getX()), cell);
                    forbidValueInGroup(cell.getGroup(), cell);
                    cell.markAsSolved();
                }else{
                    if (v == null){
//                        log("cell "+cell.getInfo() +" was not processed cell.isProcessed() = "+cell.isProcessed());
                    }
                }

            }
        }

    }
    private void forbidValueInGroup(List<Cell> group,Cell originalCell){
        for(Cell cell:group){
            if(originalCell!=cell)
                cell.forbidValue(originalCell.getValue());

        }
        checkGroup(group,originalCell);
    }
    private void checkGroup(List<Cell> group,Cell originalCell){
        for(Value value:Value.values()){
            List<Cell>possibleCells = new ArrayList<>();
            List<Cell>impossibleCells = new ArrayList<>();
            for(Cell cell:group){
                if(cell.getPossibleValues().contains(value)){
                    possibleCells.add(cell);
                }else{
                    impossibleCells.add(cell);
                }
            }
            if(possibleCells.size()==1){
                Cell cell=possibleCells.get(0);
                if(cell.isNotSolved()) cell.setValue(value);
            }
        }

    }
    public void guess(){
        Set<Cell> unsolved =field.getUnSolved();
        Cell c =unsolved.iterator().next();
        System.out.println("Cell "+c+" what should I choose "+c.getPossibleValues());
        c.setValue(c.getPossibleValues().iterator().next());
    }
}
