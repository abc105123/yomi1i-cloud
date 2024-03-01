package top.yomi1i.framework.common.exception.util;

import lombok.extern.slf4j.Slf4j;
import top.yomi1i.framework.common.exception.ErrorCode;
import top.yomi1i.framework.common.exception.ServiceException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * {@link top.yomi1i.framework.common.exception.ServiceException} 工具类
 * <p>
 * 目的在于，格式化异常信息提示。
 * 考虑到 String.format 在参数不正确时会报错，因此使用 {} 作为占位符，并使用 {@link #doFormat(int, String, Object...)} 方法来格式化
 * <p>
 * 因为 {@link #MESSAGES} 里面默认是没有异常信息提示的模板的，所以需要使用方自己初始化进去。目前想到的有几种方式：
 * 1. 异常提示信息，写在枚举类中，例如说，api.constants.ErrorCodeEnum 类 + ServiceExceptionConfiguration
 * 2. 异常提示信息，写在 .properties 等等配置文件
 * 3. 异常提示信息，写在 Apollo 等等配置中心中，从而实现可动态刷新
 * 4. 异常提示信息，存储在 db 等等数据库中，从而实现可动态刷新
 *
 * @author abcran
 * @since 2024/3/2
 */
@Slf4j
public class ServiceExceptionUtil {

    /**
     * 异常信息提示模板
     */
    private static final ConcurrentMap<Integer, String> MESSAGES = new ConcurrentHashMap<>();

    public static void putAll(Map<Integer, String> messages) {
        ServiceExceptionUtil.MESSAGES.putAll(messages);
    }

    public static void put(Integer code, String message) {
        ServiceExceptionUtil.MESSAGES.put(code, message);
    }

    public static void delete(Integer code, String message) {
        ServiceExceptionUtil.MESSAGES.remove(code, message);
    }

    //=== ServiceException
    public static ServiceException exception(ErrorCode errorCode) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.getCode(), errorCode.getMsg());
        return exception0(errorCode.getCode(), messagePattern);
    }

    public static ServiceException exception(ErrorCode errorCode, Object... params) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.getCode(), errorCode.getMsg());
        return exception0(errorCode.getCode(), messagePattern, params);
    }

    /**
     * 根据 code 创建 ServiceException
     *
     * @param code 编号
     * @return ServiceException
     */
    public static ServiceException exception(Integer code) {
        return exception0(code, MESSAGES.get(code));
    }

    /**
     * 根据 code 创建 ServiceException
     *
     * @param code   编号
     * @param params 参数
     * @return ServiceException
     */
    public static ServiceException exception(Integer code, Object... params) {
        return exception0(code, MESSAGES.get(code), params);
    }

    public static ServiceException exception0(Integer code, String messagePattern, Object... params) {
        return new ServiceException(code,
                ServiceExceptionUtil.doFormat(code, messagePattern, params));
    }

    /**
     * 格式化错误提示
     *
     * @param code           错误编号
     * @param messagePattern 消息模板
     * @param params         参数
     * @return 格式化后的提示
     */
    public static String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder strBuilder = new StringBuilder(messagePattern.length() + 50);
        int i = 0, j, l;

        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            // 不存在
            if (j == -1) {
                log.error("[doFormat][参数过多:错误码({})]|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    strBuilder.append(messagePattern.substring(i));
                    return strBuilder.toString();
                }
            } else {
                strBuilder.append(messagePattern, i, j);
                strBuilder.append(params[l]);
                i = j + 2;
            }
        }

        if (messagePattern.indexOf("{}", i) != -1) {
            log.error("[doFormat][参数过少:错误码({})]|错误内容({})|参数({})", code, messagePattern, params);
        }

        strBuilder.append(messagePattern.substring(i));
        return strBuilder.toString();
    }
}
