package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="estimate_idx")
    private Long estimateIdx;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_idx")
    @JsonManagedReference
    private User user;

//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_idx")
    @JsonManagedReference
    private Category category;

    @Column(length = 100)
    private String content;

    @CreationTimestamp
    private Timestamp created;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String address;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(columnDefinition = "varchar(255) default '1'")
    private String status;

//    @OneToMany(mappedBy = "estimate" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private final List<Apply> applyList = new ArrayList<>();

    @Builder
    public Estimate(User user, Category category, String content, Timestamp created,String city,String address,
                    LocalDate startDate,LocalDate endDate,String status){
        this.user=user;
        this.category=category;
        this.content=content;
        this.created=created;
        this.city=city;
        this.address=address;
        this.startDate=startDate;
        this.endDate=endDate;
        this.status=status;
    }

}
