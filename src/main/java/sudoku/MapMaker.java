package sudoku;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




public class MapMaker {
    Field field;
    final static Logger log = Logger.getLogger(MapMaker.class);
    public Field parseReader() {
        log.debug(String.format("User dir =%s",System.getProperty("user.dir")));

        Path path = Paths.get( "src","main","resources", "sudoku.txt");
        Charset charset = Charset.forName("US-ASCII");

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            field = new Field();
            parseReader(reader);
            field.count();
        } catch (IOException e) {
            e.printStackTrace();
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
