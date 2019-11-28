import 'package:company_directory_app/models/entities/company.dart';

class BusinessUnit {
  final int id;
  final String name;
  final Company company;

  BusinessUnit({this.id, this.name, this.company});

  factory BusinessUnit.fromJson(Map<String, dynamic> json) {
    return BusinessUnit(id: json['id'], name: json['name'], company: Company.fromJson(json['company']));
  }
}
