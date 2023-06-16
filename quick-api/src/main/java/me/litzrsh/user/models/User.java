package me.litzrsh.user.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.litzrsh.authority.models.Authority;
import me.litzrsh.commons.Constants;
import me.litzrsh.commons.models.BaseModel;
import me.litzrsh.thirdparty.jpa.converters.BooleanToStringAttributeConverter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "QC_USER")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel implements UserDetails {

    @Serial
    private static final long serialVersionUID = Constants.QUICK_DEFAULT_VERSION;

    @Id
    @Column(name = "USER_ID")
    private String id;
    @Column(name = "LOGIN_ID")
    private String loginId;
    @JsonIgnore
    @Column(name = "LOGIN_PWD")
    private String password;
    @Column(name = "USER_NM")
    private String name;
    @Column(name = "USER_NCNM")
    private String nickname;
    @Column(name = "USER_DC")
    private String remarks;
    @Column(name = "USER_EMAIL")
    private String email;
    @Convert(converter = BooleanToStringAttributeConverter.class)
    @Column(name = "USER_EMAIL_VERIFY_YN")
    private boolean emailVerified = false;
    @Column(name = "USER_MOBILE")
    private String mobile;
    @Column(name = "USER_MOBILE_VERIFY_YN")
    private boolean mobileVerified = false;
    @JsonIgnore
    @Column(name = "USER_CI_VAL")
    private String ci;
    @JsonIgnore
    @Column(name = "USER_DI_VAL")
    private String di;
    @Column(name = "USER_JOIN_TYPE")
    private String joinType;
    @Column(name = "USER_JOIN_CHANNEL")
    private String joinChannel;
    @Column(name = "USER_JOIN_DTTM")
    private Date joinAt;
    @Column(name = "USER_LAST_LOGIN_DTTM")
    private Date lastLoginAt;
    @Column(name = "USER_LAST_CHANGE_PWD_DTTM")
    private Date lastChangePasswordAt;
    @Column(name = "USER_LEAVE_DTTM")
    private Date leaveAt;
    @Column(name = "USER_LEAVE_REASON_CD")
    private String leaveReasonCode;
    @Column(name = "USER_LEAVE_REASON_DC")
    private String leaveReason;
    @Column(name = "USER_STTUS_CD")
    private String status = Constants.USER_STATUS_PENDING;
    @Transient
    private List<Authority> authorities = new ArrayList<>();

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return Constants.USER_STATUS_NORMAL.equals(this.status);
    }
}
