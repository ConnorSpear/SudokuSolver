import java.util.*;

public class SudokuSolver {

    private static int[][] grid;
    private static int filter;

    public static void main(String[] args){

        grid = getGrid();
        solve();
        if (filter != 1) {
            System.out.println("No solution");
        }
    }

    public static int[][] getGrid() {

        try (Scanner sc = new Scanner(System.in)) {
            int rows = 9;
            int cols = 9;
            grid = new int[rows][cols];
            while (sc.hasNextLine()){
                for (int i = 0; i < grid.length; i++){
                    String[] line = sc.nextLine().trim().split(" ");
                    if (line.length != 9){
                        System.err.println("Wrong input size please use 9x9 and ensure 1 space between each number");
                        System.exit(1);
                    }
                    for (int j = 0; j < line.length; j++){
                        if (!line[j].matches("[0-9]{1}")){
                            System.err.println("Sudoku input must only contain numbers 0-9 inclusive");
                            System.exit(0);
                        }
                        grid[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return grid;
    }

    public static void printMatrix(int[][] grid) {
        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static boolean possible(int y, int x, int n){
        for (int i = 0; i < 9; i++){
            if (grid[y][i] == n){
                return false;
            }
        }

        for (int i = 0; i < 9; i++){
            if (grid[i][x] == n){
                return false;
            }
        }

        int x1 = (int)Math.floor(x/3) * 3;
        int y1 = (int)Math.floor(y/3) * 3;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (grid[y1 + i][x1 + j] == n){
                    return false;
                }
            }
        }
        return true;
    }

    public static void solve(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (grid[i][j] == 0){
                    for (int z = 1; z < 10; z++){
                        if (possible(i, j, z)){
                            grid[i][j] = z;
                            solve();
                            grid[i][j] = 0;
                        }
                    }
                    return;
                }
            }
        }

        filter = 1;
        printMatrix(grid);
        System.out.println();
    }
}
