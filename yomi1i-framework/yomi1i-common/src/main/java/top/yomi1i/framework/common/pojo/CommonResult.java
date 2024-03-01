package top.yomi1i.framework.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;
import top.yomi1i.framework.common.exception.ErrorCode;
import top.yomi1i.framework.common.exception.ServiceException;
import top.yomi1i.framework.common.exception.enums.GlobalErrorCodeConstants;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回结果
 *
 * @param <T> 泛型数据
 * @author abcran
 * @since 2024/3/2
 */
public class CommonResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 提示信息
     */
    private String msg;

    public CommonResult() {
    }

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return isSuccess(code);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    /**
     * 判断是否有异常。如果有则抛出异常
     *
     * @throws ServiceException 业务异常
     */
    public void checkError() throws ServiceException {
        if (isSuccess()) {
            return;
        }
        // 业务异常
        throw new ServiceException(code, msg);
    }

    /**
     * 没有异常则返回数据
     *
     * @return 数据
     */
    @JsonIgnore
    public T getCheckedData() {
        checkError();
        return data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    //=== static method ===//
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.getCode().equals(code),
                "code must be error");
        return new CommonResult<>(code, message);
    }

    public static <T> CommonResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> CommonResult<T> error(ServiceException serviceException) {
        return error(serviceException.getCode(), serviceException.getMessage());
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(GlobalErrorCodeConstants.SUCCESS.getCode(),
                data, "");
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(GlobalErrorCodeConstants.SUCCESS.getCode(), code);
    }
}
