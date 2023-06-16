package me.litzrsh.authority.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.litzrsh.commons.Constants;
import me.litzrsh.commons.models.BaseModel;
import me.litzrsh.thirdparty.jpa.converters.BooleanToStringAttributeConverter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QC_AUTH")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends BaseModel implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = Constants.QUICK_DEFAULT_VERSION;

    @Id
    @Column(name = "AUTH_ID")
    private String id;
    @Column(name = "AUTH_PARENT_ID")
    private String parentId;
    @Column(name = "AUTH_CD")
    private String code;
    @Column(name = "AUTH_NM")
    private String name;
    @Column(name = "AUTH_DC")
    private String remarks;
    @Convert(converter = BooleanToStringAttributeConverter.class)
    @Column(name = "USE_YN")
    private boolean use = true;
    @Transient
    private List<Authority> children = new ArrayList<>();

    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.code;
    }
}
