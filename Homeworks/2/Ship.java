public class Ship {
    private boolean isHorizontal;
    private int[] coordinates;

    public Ship(int[] coordinates, boolean isHorizontal){
        this.coordinates = coordinates;
        this.isHorizontal = isHorizontal;
    }

    public boolean getIsHorizontal(){
        return isHorizontal;
    }
    public int[] getCoordinates(){
        return coordinates;
    }
}
