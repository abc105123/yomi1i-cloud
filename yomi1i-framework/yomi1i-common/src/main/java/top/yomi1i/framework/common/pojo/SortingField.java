package top.yomi1i.framework.common.pojo;

import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author abcran
 * @since 2024/3/2
 */
@ToString
public class SortingField implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String ORDER_ASC = "asc";

    public static final String ORDER_DESC = "desc";

    /**
     * 字段名
     */
    private String field;

    /**
     * 排序方式
     */
    private String order;

    public SortingField() {
    }

    public SortingField(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
