package top.yomi1i.framework.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author abcran
 * @since 2024/3/2
 */
@Schema(description = "可排序的分页参数")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SortablePageParam extends PageParam {

    @Schema(description = "排序字段")
    private List<SortingField> sortingFields;

    public List<SortingField> getSortingFields() {
        return sortingFields;
    }

    public void setSortingFields(List<SortingField> sortingFields) {
        this.sortingFields = sortingFields;
    }
}
