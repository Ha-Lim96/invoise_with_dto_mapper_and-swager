package com.mycompany.invoise.customer.mapper;

import com.mycompany.invoise.core.entity.customer.Address;
import com.mycompany.invoise.customer.dto.AddressDto;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto map(Address address);
}
