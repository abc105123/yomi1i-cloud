package top.yomi1i.framework.common.exception;

/**
 * 错误码
 *
 * @author abcran
 * @since 2024/3/2
 */
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String msg;

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ErrorCode{" + "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
