package me.litzrsh.commons.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.litzrsh.commons.Constants;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = Constants.QUICK_DEFAULT_VERSION;

    @Column(name = "REGIST_DT", updatable = false)
    private Date createAt;
    @JsonIgnore
    @Column(name = "REGIST_ID", updatable = false)
    private String creatorId;
    @Column(name = "UPDT_DT")
    private Date updateAt;
    @JsonIgnore
    @Column(name = "UPDT_ID")
    private String updaterId;
}
