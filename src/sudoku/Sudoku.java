package sudoku;

/**
 * Created by TT on 20.12.2015.
 */
public class Sudoku {
    public static void main(String[] args) {


        Field field = new MapMaker().parseReader();
        Solver solver = new Solver(field);
//        field.assignGroups();
//        System.out.println("show fields\n" + showArray(field.field));
//
//        System.out.println("show columns\n"+showArray(field.columns));

//        field.showField();
//        solve(field, solver);
//        int guess = 0;
//        int unsolved = field.getUnSolvedSize();
//        while (field.getUnSolvedSize() != 0) {
//            guess++;
//            System.out.println("guess " + guess);
//            solver.guess();
//            solve(field, solver);
//            if (unsolved == field.getUnSolvedSize()) {
//                System.out.println("No progress");
//                break;
//            }
//            unsolved = field.getUnSolvedSize();
//        }
        field.showField();
//        System.out.println(field.getColumn(0));
    }

    private static void solve(Field field, Solver solver) {
        int n = 0;
        int unsolved = -1;
        while (field.getUnSolvedSize() != 0) {
            System.out.println("Pass #" + n + " " + field.getUnSolvedSize() + "/" + field.getTotal() + " = " + field.percentage());
            String previousPassInfo = solver.getPassInfo();
            solver.pass();
            if (unsolved == field.getUnSolvedSize()) {
                System.out.println("No progress");
                System.out.println("Previous log " + previousPassInfo);
                break;
            }
            unsolved = field.getUnSolvedSize();
            field.showField();
//            Cell c = field.getCell(4, 6);
//            System.out.println("4:6\t" + c.getPossibleValues());
//            System.out.println("4:6\t" + c.getForbiddenValues());
            n++;
        }
        System.out.println("Pass #" + n + " " + field.getUnSolvedSize() + "/" + field.getTotal() + " = " + field.percentage());
    }

    private static <T> String showArray(T[][] array) {
        StringBuffer sb = new StringBuffer();
        for (T[] row : array) {
            for (T t : row) {
                sb.append(t.toString() + ":");
            }
            sb.append("\n");
        }
        return sb.toString();

    }
}
