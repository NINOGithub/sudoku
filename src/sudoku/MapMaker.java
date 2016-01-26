package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by TT on 22.12.2015.
 */
public class MapMaker {
    Field field;

    public Field parseReader() {


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(".." + File.separator + "resources" + File.separator + "sudoku.txt"));
            field = new Field();
            parseReader(bufferedReader);
            field.count();
        } catch (IOException e) {
            throw new RuntimeException("Cant parse field", e);
        }
        return field;

    }

    private void parseReader(BufferedReader bufferedReader) throws IOException {
        int y = 0;
        String sCurrentLine;

        while ((sCurrentLine = bufferedReader.readLine()) != null) {
          field.setRow(parseRow(sCurrentLine, y),y);
            y++;
        }
    }

    private Cell[] parseRow(String sCurrentLine, int y) {
        char[] chars = sCurrentLine.toCharArray();
        int x = 0;
        Cell[]cells=new Cell[sCurrentLine.length()];
        for (Character c : chars) {
            Value v = ValueAdapter.getValue(c);
            Cell cell = Cell.createCell(x, y,field, v);
            cells[x]=(cell);
            x++;
        }
        return cells;
    }
}
