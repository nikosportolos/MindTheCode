import 'package:company_directory_app/models/entities/department.dart';

class UnitResponse {
  final int id;
  final String name;
  final Department department;

  UnitResponse({this.id, this.name, this.department});

  factory UnitResponse.fromJson(Map<String, dynamic> json) {
    return UnitResponse(id: json['id'], name: json['name'], department: Department.fromJson(json['department']));
  }
}
