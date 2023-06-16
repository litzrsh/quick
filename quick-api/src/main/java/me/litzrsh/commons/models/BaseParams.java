package me.litzrsh.commons.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.litzrsh.commons.Constants;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BaseParams implements Serializable {

    @Serial
    private static final long serialVersionUID = Constants.QUICK_DEFAULT_VERSION;

    private int page = 1;
    private int perPage = 10;

    public int getOffset() {
        return (this.page - 1) * this.perPage;
    }
}
