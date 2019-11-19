import 'package:company_directory_app/models/responses/employee_response.dart';

class AllEmployeesResponse {
  final List<EmployeeResponse> employees;

  AllEmployeesResponse({this.employees});

  factory AllEmployeesResponse.fromJson(Map<String, dynamic> json) {
    var list = json['employees'] as List;
    List<EmployeeResponse> itemsList = list.map((i) => EmployeeResponse.fromJson(i)).toList();

    return AllEmployeesResponse(employees: itemsList);
  }
}
