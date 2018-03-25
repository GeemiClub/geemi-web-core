package club.geemi.core;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 18:07
 * Copyright©2017 All rights reserved.
 */
public class RestSimpleResponse{

    private Long resultCode;
    private String message;

    public void setResultCode(Long resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStatus(){
        return resultCode;
    }
}
