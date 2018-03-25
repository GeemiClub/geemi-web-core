package club.geemi.core;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 18:09
 * Copyright©2017 All rights reserved.
 */
public class RestResponse<T> extends RestSimpleResponse {

    private T data;

    public RestResponse(Long resultCode, String message, T data) {
        this.data = data;
        this.setResultCode(resultCode);
        this.setMessage(message);
    }

    public static <T> RestResponse<T> success() {
        return success(null);
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(0L, "success", data);
    }

    public static <T> RestResponse<T> error(Long resultCode, Throwable throwable) {
        return new RestResponse<>(resultCode, throwable.getMessage(), null);
    }

    public static <T> RestResponse<T> error(Long resultCode, String message) {
        return new RestResponse<>(resultCode, message, null);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
