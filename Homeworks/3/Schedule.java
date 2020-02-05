package hw3;

import java.util.Scanner;

public class Schedule{
    private static final int DAYS_OF_WEEK = 7;
    private static final int DAILY_SCHEDULE_LENGTH = 2;

    private String[][] schedule;
    private Scanner scanner;

    public Schedule(){
        scanner = new Scanner(System.in);
        schedule = new String[DAYS_OF_WEEK][DAILY_SCHEDULE_LENGTH];
    }

    private void initScheduleDefaultValues(){
        schedule[0][0] = "Sunday";
        schedule[0][1] = "do home work";
        schedule[1][0] = "Monday";
        schedule[1][1] = "go to courses; watch a film";
        schedule[2][0] = "tuesday";
        schedule[3][0] = "wednesday";
        schedule[4][0] = "thursday";
        schedule[5][0] = "friday";
        schedule[6][0] = "saturday";
    }
    private int getDayId(String day){
        switch (day){
            case "sunday": return 0;
            case "monday": return 1;
            case "tuesday": return 2;
            case "wednesday": return 3;
            case "thursday": return 4;
            case "friday": return 5;
            case "saturday": return 6;
            default: return -1;
        }
    }
    private int readDay(String command){
        command = command.toLowerCase().trim();
        if(command.contains("change"))
            return changeDay(command);
        else
            return getDayId(command);

    }
    private int changeDay(String command){
        command = command.substring(6).trim();
        int dayToChange =  getDayId(command);
        setDaySchedule(dayToChange);
        return dayToChange;
    }
    private String getDaySchedule(int index){
        if(schedule[index][1] != null)
            return schedule[index][1];
        else return setDaySchedule(index);
    }
    private String setDaySchedule(int index){
        System.out.println("Enter task for this day");
        return schedule[index][1] = scanner.nextLine();
    }
    public void init(){
        initScheduleDefaultValues();
        while(true){
            System.out.println("Please, input the day of the week: ");
            String command = scanner.nextLine();
            if(command.contains("exit")) break;
            int requestedIndex = readDay(command);
            while(requestedIndex == -1){
                System.out.println("Sorry, I didn't understand you, try again");
                command = scanner.nextLine();
                requestedIndex = readDay(command);
            }
            String task = getDaySchedule(requestedIndex);
            String response = "Your task for " + command.toLowerCase().trim() + " : " + task;
            System.out.println(response);
        }
    }


}