package zhou.zuoye.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="course")
//@JsonIgnoreProperties(value={"teacher"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String course_code;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT '95|90|85|80|75|70|65|60|50|80' ")
    private String selectscr="95|90|85|80|75|70|65|60|50|80";

    @ManyToOne
    @JoinColumn(name = "tid")
    private User teacher;

    @Column(columnDefinition = "INTEGER DEFAULT 1")
    private Integer verify=1;    //审核状态 0--未通过 1--待审核 2--已通过
    @Column(columnDefinition = "INTEGER DEFAULT 1")
    private Integer usable=1;    //可用状态 1--可用 0--不可用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Integer getUsable() {
        return usable;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }

    public String getSelectscr() {
        return selectscr;
    }
    public void setSelectscr(String selectscr) {
        this.selectscr = selectscr;
    }

    public Course(String name, User teacher) {
        this.name = name;
        this.teacher = teacher;
    }



    public Course(User teacher) {
        this.teacher = teacher;
    }

    public Course(Integer id){
        this.id=id;
    }

    public Course(){}
}
