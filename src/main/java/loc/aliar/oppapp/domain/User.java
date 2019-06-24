package loc.aliar.oppapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    @JsonProperty(access = READ_ONLY)
    private Long id;

    @Length(min = 4, max = 50)
    @Column(length = 50)
    private String name;

    @Column
    @JsonProperty(access = READ_ONLY)
    private String photo;

    @Pattern(regexp = "^(\\+)?\\d{10,14}$")
    @NaturalId(mutable = true)
    @Column(length = 15, nullable = false, unique = true)
    @JsonProperty(access = READ_ONLY)
    private String phone;

    @Length(max = 255)
    @Column
    private String background;

    @Length(max = 255)
    @Column
    private String need;

    @Length(max = 255)
    @Column
    private String usefulness;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private Boolean male;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Role role;

    @ManyToOne
    private City city;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedAt;

    //    private Set<Activity> favouriteActivities;
//    @OneToMany(mappedBy = "createdBy")
//    private Set<Network> networks;

    public User(@Pattern(regexp = "^(\\+)?\\d{10,14}$") String phone, Role role) {
        this.phone = phone;
        this.role = role;
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }
}
