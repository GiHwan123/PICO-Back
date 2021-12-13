package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photographer_idx")
    private Long photographerIdx;

//    @ManyToOne
    @OneToOne
    @JoinColumn(name="user_idx")
    @JsonManagedReference
    private User user;

    @Column(name="is_studio")//ERD 에는 studio로 되어있지만 bool 타입은 is_로 통일하기로 합의.
    private boolean isStudio;

    @Column
    private float grade;

    @Column(length = 100)
    private String activityCity;

    @Column(length = 100)
    private String activityAddress;

    @Column(length= 100)
    private String studioCity;

    @Column(length= 100)
    private String studioAddress;

    // 활동영역 타지역 협의가능 여부
    @Column(length = 100)
    private boolean otherArea;

    @OneToMany(mappedBy = "photographer")
    @JsonBackReference
    List<Apply> applyList = new ArrayList<>();

    @OneToMany(mappedBy = "photographer")
    @JsonBackReference
    List<Work> workList = new ArrayList<>();

    @OneToMany(mappedBy = "photographer")
    @JsonBackReference
    List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "photographer")
    @JsonBackReference
    List<PCategory> pCategoryList = new ArrayList<>();
}
