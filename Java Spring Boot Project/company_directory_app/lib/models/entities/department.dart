import 'package:company_directory_app/models/entities/business_unit.dart';

class Department {
  final int id;
  final String name;
  final BusinessUnit businessUnit;

  Department({this.id, this.name, this.businessUnit});

  factory Department.fromJson(Map<String, dynamic> json) {
    return Department(id: json['id'], name: json['name'], businessUnit: BusinessUnit.fromJson(json['businessUnit']));
  }
}
