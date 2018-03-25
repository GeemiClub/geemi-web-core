package club.geemi.utils.coder;

import java.util.List;

/**
 * @Package club.geemi.club.geemi.utils.coder
 * @Description:
 * @Author: Geemi @Everlin
 * @Date: 2017/11/18 21:38
 * @Version V1.0
 * Copyright©2017 All rights reserved.
 */
public class Module {

    private String moduleName;
    private List<Property> properties;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
