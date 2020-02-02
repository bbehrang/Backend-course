import java.util.*;
import java.util.regex.Pattern;

public class Game {

    private static final Object[][] EVENTS = {
            {1939, "When did the World War II begin?"},
            {1989, "When did Tim Berners-Lee invent the World Wide Web?"},
            {1961, "When did Yuri Gagarin become the first spaceman?"},
            {1945, "When did  Hiroshima and Nagasaki experience the first dropping of the Atom Bomb?"},
            {1959, "When was the silicon chip invented?"}
    };

    private Player player;
    private int[] answers;
    private Random random;
    private Scanner scanner;

    public Game(){
        answers = new int[]{};
        random = new Random();
        scanner = new Scanner(System.in);
    }

    public int[] getAnswers(){
        return answers;
    }
    public void addAnswer(int answer){
        int length = answers.length;
        answers = Arrays.copyOf(answers, length + 1);
        answers[length] = answer;
    }

    private void readPlayerName(){
        String playerName = scanner.nextLine();
        while(playerName.length() == 0){
            System.out.println("Player name must consist of at least 1 symbol, try again!");
            playerName = scanner.nextLine();
        }
        player = new Player(playerName);
    }

    private int readAnswer(){
        int answer;
        Pattern pattern = Pattern.compile("\\d+");
        while(!scanner.hasNext(pattern)){
            System.out.println("Please enter a number");
            scanner.next();
        }
        try{
            answer = scanner.nextInt();
        } catch (InputMismatchException e){
            throw e;
        }

        addAnswer(answer);
        return answer;
    }

    private boolean isAnswerCorrect(int playerAnswer, int eventIndex){
        int correctAnswer = (int) EVENTS[eventIndex][0];
        if(playerAnswer < correctAnswer)
            System.out.println("Your number is too small. Please, try again.");
        else if(playerAnswer > correctAnswer)
            System.out.println("Your number is too big. Please, try again.");
        else
            System.out.println("Congratulations, "+ player.getName() + "!");
        return playerAnswer == correctAnswer;
    }
    private Integer[] getSortedAnswers(){
        Integer[] toSort =  Arrays.stream(answers).boxed().toArray(Integer[]::new);
        Arrays.sort(toSort, Collections.reverseOrder());
        return toSort;
    }

    public void start(){
        System.out.println("Please enter your name:");
        readPlayerName();

        System.out.println("Let the game begin!");

        int randomEventIndex = random.nextInt(EVENTS.length);
        System.out.println(EVENTS[randomEventIndex][1]);

        int answer = readAnswer();

        while (!isAnswerCorrect(answer, randomEventIndex)){
            answer = readAnswer();
        }
        System.out.println(player.getName() + ", your answers were: " + Arrays.toString(getSortedAnswers()));

        scanner.close();
    }
}
