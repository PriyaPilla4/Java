public class Pair {
    public Pair() {
        this.setStreak(0);
        this.setNumberOfMark(0);
    }

    public void setStreak(int s) {
        this.streak = s;
    }

    public int getStream() {
        return this.streak;
    }

    public void setNumberOfMark(int n) {
        this.numOfMark = n;
    }

    public int getNumberOfMark() {
        return this.numOfMark;
    }

    private int streak; // Represent a possible streak
    private int numOfMark; // Represent number of marks in the streak
}
