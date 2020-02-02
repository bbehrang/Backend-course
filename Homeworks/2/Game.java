import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    private static final int GRID_SIZE = 5;
    private static final int SHIP_SIZE = 3;
    private static final char GRID_ATTACKED_SYMBOL = '*'; //unsuccessful hit
    private static final char GRID_HIT_SYMBOL = 'x'; //successful hit
    private static final char GRID_EMPTY_SYMBOL = '-';

    private Grid grid;
    private Ship ship; //immutable
    private int[][] successfulHits;
    private Random random;
    private Scanner scanner;
    
    public Game(){
        grid = new Grid(GRID_SIZE);
        random = new Random();
        scanner = new Scanner(System.in);
        successfulHits = new int[SHIP_SIZE][];
    }
    private void generateTargetCoords(){ //also initializes ship field
        int x, y;
        boolean isTargetHorizontal = random.nextBoolean();
        if(isTargetHorizontal) {
            x = random.nextInt(GRID_SIZE);
            y = random.nextInt(GRID_SIZE - SHIP_SIZE + 1);
        }
        else{
            x = random.nextInt(GRID_SIZE - SHIP_SIZE + 1);
            y = random.nextInt(GRID_SIZE);
        }
        if(x < 0 || x > GRID_SIZE - 1){
            throw new Error("Wrong X coordinate for target, check game settings");
        }
        if(y < 0 || y > GRID_SIZE - 1){
            throw new Error("Wrong X coordinate for target, check game settings");
        }
        ship = new Ship(new int[]{x,y}, isTargetHorizontal);
    }
    private int readInput(){
        int input;
        Pattern pattern = Pattern.compile("\\d+");
        while(!scanner.hasNext(pattern)){
            System.out.println("Please enter a valid number");
            scanner.next();
        }
        try{
            input = scanner.nextInt();
        } catch (InputMismatchException e){
            throw e;
        }
        return input;
    }
    private boolean isTargetValid(int[] coords){
        return grid.getElement(coords[0], coords[1]) == GRID_EMPTY_SYMBOL;
    }
    private boolean isHitHorizontal(int [] coords){
        int[] shipCoordinates = ship.getCoordinates();
        boolean isColumnHit = coords[1] >= shipCoordinates[1]
                && coords[1] <= shipCoordinates[1] + SHIP_SIZE - 1;
        return (coords[0] == shipCoordinates[0] && isColumnHit);
    }
    private boolean isHitVertical(int [] coords){
        int[] shipCoordinates = ship.getCoordinates();
        boolean isRowHit = coords[0] >= shipCoordinates[0]
                && coords[0] <= shipCoordinates[0] + SHIP_SIZE - 1;
        return (coords[1] == shipCoordinates[1] && isRowHit);
    }
    private boolean isTargetHit(int[] coords){
        boolean isHit;
        if(ship.getIsHorizontal()) isHit = isHitHorizontal(coords);
        else isHit = isHitVertical(coords);

        if(isHit){
            for(int i=0;i<successfulHits.length; i++){
                if(successfulHits[i] == null){
                    successfulHits[i] = coords;
                    break;
                }
            }
        }
        return isHit;
    }
    private void attack(int[] coords){
        char status = isTargetHit(coords) ? GRID_HIT_SYMBOL : GRID_ATTACKED_SYMBOL;
        grid.setElement(coords[0], coords[1], status);
    }
    private boolean isGameFinished(){
        for (int[] clientTargetHit : successfulHits) {
            if (clientTargetHit == null)
                return false;
        }
        return true;
    }
    private int[] getClientTargetCoords(){
        System.out.println("Enter row number");
        int clientTargetX = readInput();
        while (clientTargetX < 1 || clientTargetX > GRID_SIZE){
            System.out.println("Target coordinates should be between 1 and " + GRID_SIZE);
            clientTargetX = readInput();
        }
        System.out.println("Enter column number");
        int clientTargetY = readInput();
        while (clientTargetY < 1 || clientTargetY > GRID_SIZE){
            System.out.println("Target coordinates should be between 1 and " + GRID_SIZE);
            clientTargetY = readInput();
        }
        //Client chooses between 1 to GRID_SIZE(inclusive)
        return new int[]{clientTargetX - 1, clientTargetY - 1};
    }
    public void start(){
        generateTargetCoords();
        grid.fill(GRID_EMPTY_SYMBOL);
        System.out.println("All set. Get ready to rumble!");
        boolean isGameFinished = isGameFinished();
        while(!isGameFinished){
            grid.show();
            int[] clientTarget = getClientTargetCoords();
            while (!isTargetValid(clientTarget)) {
                System.out.println("those coordinates were already attacked, try again!");
                clientTarget = getClientTargetCoords();
            }
            attack(clientTarget);
            isGameFinished = isGameFinished();
        }
        grid.show();
        System.out.println("You have won!");
        scanner.close();
    }
}
