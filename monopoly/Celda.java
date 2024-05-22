package monopoly;

public abstract class Celda {
    private boolean available = true;
    private String name;
    protected Jugador player;

    public String getName() {
        return name;
    }

    public Jugador getOwner() {
        return player;
    }
	
    public int getPrice() {
        return 0;
    }

    public boolean isAvailable() {
        return available;
    }
	
    public void playAction(Controlador mainCtl) {};

    public void setAvailable(boolean available) {
        this.available = available;
    }
	
    public void setName(String name) {
        this.name = name;
    }

    public void setPlayer(Jugador player) {
        this.player = player;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
