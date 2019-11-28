import 'package:company_directory_app/models/responses/department_response.dart';

class AllDepartmentsResponse {
  final List<DepartmentResponse> departments;

  AllDepartmentsResponse({this.departments});

  factory AllDepartmentsResponse.fromJson(Map<String, dynamic> json) {
    var list = json['departments'] as List;
    List<DepartmentResponse> itemsList = list.map((i) => DepartmentResponse.fromJson(i)).toList();

    return AllDepartmentsResponse(departments: itemsList);
  }
}
