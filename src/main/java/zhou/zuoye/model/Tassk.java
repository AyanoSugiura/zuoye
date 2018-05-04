package zhou.zuoye.model;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import javax.persistence.*;

@Entity
@Table(name="tassk")
public class Tassk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cid")
    private Course course;

    @Column(nullable = false)
    private String title;
    private String content;

    private String files_links;

    private String files_names;

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

    public String getFiles_links() {
        return files_links;
    }

    public void setFiles_links(String files_links) {
        this.files_links = files_links;
    }

    public String getFiles_names() {
        return files_names;
    }

    public void setFiles_names(String files_names) {
        this.files_names = files_names;
    }

    public Tassk(){}

    public Tassk(Integer id){
        this.id=id;
    }

    public Tassk(Course course, String title, String content, String files_links) {
        this.course = course;
        this.title = title;
        this.content = content;
        this.files_links = files_links;
    }
}
