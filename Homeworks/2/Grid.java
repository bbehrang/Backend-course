import java.util.Arrays;

public class Grid {
    private char[][] field;

    public Grid(int size){
        field = new char[size][size];
    }

    public void fill(char symbol){
        for(char[] row:field)
            Arrays.fill(row, symbol);
    }
    public char getElement(int x, int y){
        return field[x][y];
    }
    public void setElement(int x, int y, char character){
        field[x][y] = character;
    }
    public void show(){
        for(int i = 0; i < field.length + 1; i++){
            System.out.print(i + " | ");
        }
        System.out.println();

        for (int i = 0; i < field.length; i++) {
            System.out.print(i + 1 + " | ");
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
