package application;

public class Cate_ques {
    private String cate;
    private String ques;
    private String text;

    public Cate_ques(String cate, String ques, String text) {
        this.cate = cate;
        this.ques = ques;
        this.text = text;
    }

    public Cate_ques() {
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
