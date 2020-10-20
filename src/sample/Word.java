package sample;

public class Word {
    String word_target;
    String word_explain;
    String word_pronounce;
    String word_round;

    public Word(String word_target, String word_round, String word_pronounce, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.word_round = word_round;
        this.word_pronounce = word_pronounce;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public void setWord_pronounce(String word_pronounce) {
        this.word_pronounce = word_pronounce;
    }

    public void setWord_round(String word_round) {
        this.word_round = word_round;
    }

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public String getWord_pronounce() {
        return word_pronounce;
    }

    public String getWord_round() {
        return word_round;
    }

}
