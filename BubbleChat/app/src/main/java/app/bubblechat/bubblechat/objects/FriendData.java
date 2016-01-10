package app.bubblechat.bubblechat.objects;

/**
 * Created by gerybravo on 2016.01.10..
 */
public class FriendData {
    private String name;
    private Boolean state;
    public FriendData(String name, Boolean state) {
        setName(name);
        setState(state);
    }
    public boolean isState() {
        return state;
    }
    public void setState(Boolean state) {
        this.state = state;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
