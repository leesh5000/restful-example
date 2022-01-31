package leesh.study.hellorest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentRegisterResponse {

    private Long id;

}
