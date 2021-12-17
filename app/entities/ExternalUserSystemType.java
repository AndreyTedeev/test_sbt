package entities;

import io.ebean.annotation.DbEnumType;
import io.ebean.annotation.DbEnumValue;

public enum ExternalUserSystemType {
    VTB_ID(1),
    FIODULDR(2);

    public final int code;

    private ExternalUserSystemType(int code) {
        this.code = code;
    }

    @DbEnumValue(storage = DbEnumType.INTEGER)
    public int getValue() {
        return code;
    }

}
