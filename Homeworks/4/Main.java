package hw4;

public class Main {
    public static void main(String[] args) {
        Human hum1 = new Human();
        System.out.println(hum1.toString());

        Human hum2 = new Human("father", "fatherLastname", 1995);
        System.out.println(hum2.toString());

        Human hum3 =  new Human("mother", "motherLastname", 1995);
        System.out.println(hum3.toString());

        Schedule schedule = new Schedule();

        Pet dog = new Pet("Dog", "bolt", 2, 50, new String[]{"eat", "pray", "love"});
        Human hum4 = new Human("son", "sonLastname", 2300, 120, dog, hum3, hum2, schedule);
        System.out.println(hum4.toString());
    }
}
