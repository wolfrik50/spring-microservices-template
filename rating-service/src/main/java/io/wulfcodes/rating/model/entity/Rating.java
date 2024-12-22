package io.wulfcodes.rating.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ratings")
@Data @NoArgsConstructor @AllArgsConstructor
public class Rating {

    @Id
    @Field(name = "ratingId")
    private String ratingId;

    @Field(name = "userId")
    private String userId;

    @Field(name = "hotelId")
    private String hotelId;

    @Field(name = "rating")
    private Integer rating;

    @Field(name = "remark")
    private String remark;
}
