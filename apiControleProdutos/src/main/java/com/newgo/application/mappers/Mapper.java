package com.newgo.application.mappers;

import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination) throws Exception {
        try {
            return mapper.map(origin, destination);
        } catch (MappingException e) {
            throw new Exception(e.getCause().getCause().getCause().getMessage());
        }
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) throws Exception {
        List<D> destinationObjects = new ArrayList<D>();
        try {
            origin.forEach(o -> destinationObjects.add(mapper.map(o, destination)));
        } catch (MappingException e) {
            throw new Exception(e.getCause().getCause().getCause().getMessage());
        }
        return destinationObjects;
    }
}