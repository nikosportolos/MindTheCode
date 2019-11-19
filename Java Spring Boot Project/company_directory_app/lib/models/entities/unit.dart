import 'package:company_directory_app/models/entities/department.dart';

class Unit {
  final int id;
  final String name;
  final Department department;

  Unit({this.id, this.name, this.department});

  factory Unit.fromJson(Map<String, dynamic> json) {
    return Unit(id: json['id'], name: json['name'], department: Department.fromJson(json['department']));
  }
}
