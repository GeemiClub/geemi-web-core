package club.geemi.utils.coder;

/**
 * @Package club.geemi.club.geemi.utils.coder
 * @Description:
 * @Author: Geemi @Everlin
 * @Date: 2017/11/18 21:52
 * @Version V1.0
 * Copyright©2017 All rights reserved.
 */
public class DecodeException extends RuntimeException {

    public DecodeException(String message, Object... args) {
        super(String.format(message, args));
    }

}
