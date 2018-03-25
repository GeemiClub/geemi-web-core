package club.geemi.core;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2018/3/8 23:03
 * Copyright©2018 All rights reserved.
 */
public class BusinessException extends RuntimeException {

    private Long code;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Long code){
        super(message);
        this.code = code;
    }

    public Long getCode(){
        return code;
    }
}
