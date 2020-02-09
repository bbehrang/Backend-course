package hw4;

public class Human {
    private String name;
    private String surname;
    private int year;
    private int iq;
    private Pet pet;
    private Human mother;
    private Human father;
    private Schedule schedule;

    public Human(){

    }
    public Human(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }
    public Human(String name, String surname, int year, Human mother, Human father) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.mother = mother;
        this.father = father;
    }
    public Human(String name, String surname, int year, int iq, Pet pet, Human mother, Human father, Schedule schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.pet = pet;
        this.mother = mother;
        this.father = father;
        this.schedule = schedule;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void greetPet() {
        System.out.println("Hello " + pet.getNickname());
    }

    public void describePet() {
        String trickLevel = pet.getTrickLevel() > 50 ? "very sly" : " almost not sly";
        System.out.println("I have a " + pet.getSpecies()
                + ", he is " + pet.getAge()
                + "years old, he is " + trickLevel);
    }

    @Override
    public String toString() {
        String motherInfo = "", fatherInfo = "", petInfo = "";
        if(mother != null) motherInfo +=  ", mother="+ mother.name + " " + mother.surname;
        if(father != null) fatherInfo += ", father="+ father.name + " " + father.surname;
        if(pet != null) petInfo = pet.toString();
        return "Human={name='" + name
                + "', surname='" + surname
                + "', year=" + year
                + ", iq=" + iq + motherInfo + fatherInfo + " " + petInfo + "}";
    }
}
