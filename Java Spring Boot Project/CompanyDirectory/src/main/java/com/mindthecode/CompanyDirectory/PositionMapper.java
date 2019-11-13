package com.mindthecode.CompanyDirectory;

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

    /*uncomment when Unit is ready*/
    public PositionResponse mapPositionToResponse(Position position) {
        return new PositionResponse(
                position.getId(),
                position.getName().getText()/*,
                position.getUnit()*/);
    }
}
