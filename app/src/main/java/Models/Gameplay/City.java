package Models.Gameplay;

public class City {
    private String name;
    private double xPosition;
    private double yPosition;

    public City(){}

    public City(String name, double x, double y)
    {
        this.name = name;
        xPosition=x;
        yPosition=y;
    }

    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double x) {
        xPosition=x;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setYPosition(double y) {
        yPosition=y;
    }
}
