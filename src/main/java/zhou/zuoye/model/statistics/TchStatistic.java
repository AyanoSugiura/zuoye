package zhou.zuoye.model.statistics;



import zhou.zuoye.model.StudentWork;
import zhou.zuoye.model.User;

import java.util.List;

public class TchStatistic {
    private User student;
    private List<StudentWork> score;
    private String excelLink;
    private Double stuAv;

    public TchStatistic(){}

    public TchStatistic(User student) {
        this.student = student;
    }

    public TchStatistic(List<StudentWork> score) {
        this.score = score;
    }

    public TchStatistic(User student, List<StudentWork> score) {
        this.student = student;
        this.score = score;
    }

    public TchStatistic(User student, List<StudentWork> score, Double stuAv) {
        this.student = student;
        this.score = score;
        this.stuAv = stuAv;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public List<StudentWork> getScore() {
        return score;
    }

    public String getExcelLink() {
        return excelLink;
    }

    public void setExcelLink(String excelLink) {
        this.excelLink = excelLink;
    }

    public void setScore(List<StudentWork> score) {
        this.score = score;
    }

    public Double getStuAv() {
        return stuAv;
    }

    public void setStuAv(Double stuAv) {
        this.stuAv = stuAv;
    }
}
