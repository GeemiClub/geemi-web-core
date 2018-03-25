package club.geemi.utils.coder;

import java.io.InputStream;
import java.util.List;

/**
 * @Package club.geemi.club.geemi.utils.coder
 * @Description:
 * @Author: Geemi @Everlin
 * @Date: 2017/11/18 21:37
 * @Version V1.0
 * Copyright©2017 All rights reserved.
 */
public interface Decoder {

    /**
     * 根据文件流解析出模型
     *
     * @param inputStream
     * @return
     */
    List<Module> decode(InputStream inputStream);
}
