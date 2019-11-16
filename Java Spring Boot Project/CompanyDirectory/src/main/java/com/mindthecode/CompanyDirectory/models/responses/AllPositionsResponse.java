package com.mindthecode.CompanyDirectory.models.responses;

import java.util.List;

public class AllPositionsResponse {
    private List<PositionResponse> positions;

    public AllPositionsResponse(List<PositionResponse> positions) {
        this.positions = positions;
    }

    public List<PositionResponse> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionResponse> positions) {
        this.positions = positions;
    }
}
