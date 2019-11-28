import 'package:company_directory_app/models/responses/business_unit_response.dart';

class DepartmentResponse {
  final int id;
  final String name;
  final BusinessUnitResponse businessUnit;

  DepartmentResponse({this.id, this.name, this.businessUnit});

  factory DepartmentResponse.fromJson(Map<String, dynamic> json) {
    return DepartmentResponse(id: json['id'], name: json['name'], businessUnit: BusinessUnitResponse.fromJson(json['businessUnit']));
  }
}
