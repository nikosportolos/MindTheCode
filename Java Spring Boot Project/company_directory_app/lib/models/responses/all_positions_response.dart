import 'package:company_directory_app/models/responses/position_response.dart';

class AllPositionsResponse {
  final List<PositionResponse> positions;

  AllPositionsResponse({this.positions});

  factory AllPositionsResponse.fromJson(Map<String, dynamic> json) {
    var list = json['positions'] as List;
    List<PositionResponse> itemsList = list.map((i) => PositionResponse.fromJson(i)).toList();

    return AllPositionsResponse(positions: itemsList);
  }
}
