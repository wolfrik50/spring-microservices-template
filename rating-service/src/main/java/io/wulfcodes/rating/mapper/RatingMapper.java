package io.wulfcodes.rating.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import io.wulfcodes.rating.model.data.RatingData;
import io.wulfcodes.rating.model.entity.Rating;

@Component
@Mapper(componentModel = "spring")
public interface RatingMapper {

    Rating toEntity(RatingData ratingData);

    RatingData toData(Rating rating);

    List<RatingData> toDataList(List<Rating> ratings);
    
}
