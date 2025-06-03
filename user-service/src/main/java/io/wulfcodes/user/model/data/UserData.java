package io.wulfcodes.user.model.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserData {
    private String userId;
    private String name;
    private String email;
    private String about;
    private List<RatingData> ratings;
}
