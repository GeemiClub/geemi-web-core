package club.geemi.core;

import club.geemi.core.interceptor.JsonParam;
import club.geemi.core.translate.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 18:04
 * Copyright©2017 All rights reserved.
 */
public class BaseResource<E, ID extends Serializable, S extends BaseService<E, ID>> {

    private final String SPLIT = ",";

    @Autowired
    protected S service;

    @Autowired
    private ConversionService conversionService;

    @Translate
    @RequestMapping(method = RequestMethod.GET)
    public RestResponse get(@RequestParam ID id) {
        return RestResponse.success(service.get(id));
    }

    @Translate
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public RestResponse findAll(@JsonParam E params) {
        return RestResponse.success(service.find(params));
    }

    @Translate
    @RequestMapping(method = RequestMethod.GET, value = "page")
    public RestResponse find(@JsonParam E params, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        return RestResponse.success(service.find(params, page == null || page <= 0 ? 1 : page, pageSize == null || pageSize <= 0 ? 10 : pageSize));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public RestResponse delete(@RequestParam ID id) {
        service.delete(id);
        return RestResponse.success();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "list")
    public RestResponse delete(@RequestParam String ids) {
        for (String id : ids.split(SPLIT)) {
            service.delete(conversionService.convert(id, getIDClass()));
        }
        return RestResponse.success();
    }


    private Class<ID> idClass;
    private Class<ID> getIDClass(){
        if(idClass == null){
            idClass = (Class <ID>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return idClass;
    }
}
