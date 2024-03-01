package top.yomi1i.framework.common.exception;

import lombok.EqualsAndHashCode;

/**
 * 服务器异常
 *
 * @author abcran
 * @since 2024/3/2
 */
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {

    private Integer code;

    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServerException() {
    }

    public ServerException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServerException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public ServerException setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ServerException setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ServerException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
