package com.evol.challenge.util;

import com.evol.challenge.dto.MeterDto;
import com.evol.challenge.entity.Meter;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MeterDtoConverter class, converts Meter to MeterDto and vice versa
 */
@Component
public class MeterDtoConverter implements EntityConverter<Meter, MeterDto>  {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MeterDto convertToDto(Meter entity) {
        return modelMapper.map(entity, MeterDto.class);
    }

    @Override
    public Meter convertToEntity(MeterDto dto) {
        return modelMapper.map(dto, Meter.class);
    }
}
