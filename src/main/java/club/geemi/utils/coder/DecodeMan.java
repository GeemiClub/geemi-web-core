package club.geemi.utils.coder;

import club.geemi.utils.closer.Closer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Package club.geemi.club.geemi.utils.coder
 * @Description:
 * @Author: Geemi @Everlin
 * @Date: 2017/11/18 21:48
 * @Version V1.0
 * Copyright©2017 All rights reserved.
 */
public class DecodeMan {

    public void decode(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            throw new DecodeException("无法找到文件%s", filePath);
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            Decoder decoder = new MarkDownDecoder();
            List<Module> moduleList = decoder.decode(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            Closer.closeCloseable(inputStream);
        }
    }
}
