package zhou.zuoye.model;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import javax.persistence.*;

@Entity
@Table(name="tassk")
public class Tassk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Course course;

    private DateTimeLiteralExpression.DateTime time;

    @Column(columnDefinition = "INTEGER DEFAULT 2")
    private Integer verify=2;    //审核状态 0--未通过 1--待审核 2--已通过
    @Column(columnDefinition = "INTEGER DEFAULT 1")
    private Integer usable=1;    //可用状态 1--可用 0--不可用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public DateTimeLiteralExpression.DateTime getTime() {
        return time;
    }

    public void setTime(DateTimeLiteralExpression.DateTime time) {
        this.time = time;
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





}
