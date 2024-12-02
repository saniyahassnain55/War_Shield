package Player;

public class Player {
    String name;
    int score;
    int missedObjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMissedObjects() {
        return missedObjects;
    }

    public void setMissedObjects(int missedObjects) {
        this.missedObjects = missedObjects;
    }
    public void increaseScore(){
        this.score++;
    }
    public void increaseMissingObjects(){
        this.missedObjects++;
    }

}