package br.edu.infinet.app.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericMapper {

    private final ModelMapper modelMapper;

    public GenericMapper() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D map(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public <S, D> List<D> mapList(List<S> source, Class<D> destinationClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, destinationClass))
                .toList();
    }
}
