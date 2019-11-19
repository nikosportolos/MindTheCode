import 'package:company_directory_app/models/entities/unit.dart';

class Position {
  final int id;
  final String name;
  final Unit unit;

  Position({this.id, this.name, this.unit});

  factory Position.fromJson(Map<String, dynamic> json) {
    return Position(id: json['id'], name: json['name'], unit: Unit.fromJson(json['unit']));
  }
}
