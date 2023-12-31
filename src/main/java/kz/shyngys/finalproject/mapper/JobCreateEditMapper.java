package kz.shyngys.finalproject.mapper;

import kz.shyngys.finalproject.dto.JobCreateEditDto;
import kz.shyngys.finalproject.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobCreateEditMapper {

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Job toEntity(JobCreateEditDto dto);
}
