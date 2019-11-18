import 'package:company_directory_app/models/responses/unit_response.dart';

class AllUnitsResponse {
  final List<UnitResponse> units;

  AllUnitsResponse({this.units});

  factory AllUnitsResponse.fromJson(Map<String, dynamic> json) {
    var list = json['units'] as List;
    List<UnitResponse> itemsList = list.map((i) => UnitResponse.fromJson(i)).toList();

    return AllUnitsResponse(units: itemsList);
  }
}
