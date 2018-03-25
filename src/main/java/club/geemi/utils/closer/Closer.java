package club.geemi.utils.closer;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Package club.geemi.uitls.closer
 * @Description:
 * @author: Geemi @Everlin
 * @Date: 2017/11/18 21:56
 * @Version V1.0
 * Copyright©2017 All rights reserved.
 */
public class Closer {

    public static void closeCloseable(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
