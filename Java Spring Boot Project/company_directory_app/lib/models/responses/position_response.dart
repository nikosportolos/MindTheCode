import 'package:company_directory_app/models/entities/unit.dart';

class PositionResponse {
  final int id;
  final String name;
  final Unit unit;

  PositionResponse({this.id, this.name, this.unit});

  factory PositionResponse.fromJson(Map<String, dynamic> json) {
    return PositionResponse(id: json['id'], name: json['name'], unit: Unit.fromJson(json['unit']));
  }
}
