package Beans;

/**
 * Created by ganleb13 on 16.03.2017.
 * Score Dataclass
 */
public class Score {

    private String user;
    private int score;
    private int rank;

    public Score(String user, int score) {
        this.user = user;
        this.score = score;
        this.rank = 0;
    }

    @Override
    public String toString() {
        return "Score{" +
                "user='" + user + '\'' +
                ", score=" + score +
                ", rank=" + rank +
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
