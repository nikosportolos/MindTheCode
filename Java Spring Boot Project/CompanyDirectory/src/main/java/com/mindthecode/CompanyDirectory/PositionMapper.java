package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.models.responses.PositionResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PositionMapper {

    public List<PositionResponse> mapPositions(Iterable<Position> all) {
        List<PositionResponse> response = new ArrayList<>();
        for (Position position : all) {
            response.add(mapPositionToResponse(position));
        }
        return response;
    }

    public PositionResponse mapPositionToResponse(Position position) {
        return new PositionResponse(position.getId(), position.getName(), position.getUnit());
    }
}
