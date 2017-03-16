package Beans;

/**
 * Created by ganleb13 on 16.03.2017.
 * This is our data-class Score
 */
public class Score {

    private String user;
    private int score;

    public Score(String user, int score) {
        this.user = user;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "user='" + user + '\'' +
                ", score=" + score +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
