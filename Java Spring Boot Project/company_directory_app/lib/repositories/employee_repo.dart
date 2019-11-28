import 'dart:core';
import 'package:company_directory_app/models/responses/all_employees_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/models/responses/employee_response.dart';
import 'package:company_directory_app/utils/rest_helper_util.dart';

class EmployeeRepo {
  RestHelperUtil restHelper = new RestHelperUtil();

  Future<List<EmployeeResponse>> getAllEmployees() async {
    print('Loading all employees ');
    GenericResponse genericResponse = await restHelper.getRequest("employees");

    print(genericResponse);

    if (genericResponse.error != null) {
      return new List<EmployeeResponse>();
    }

    AllEmployeesResponse allResponse = AllEmployeesResponse.fromJson(genericResponse.data);

    return allResponse.employees;
  }

  Future<EmployeeResponse> getEmployeeById(int id) async {
    GenericResponse genericResponse = await restHelper.getRequest("employee/$id");
    if (genericResponse.error != null) {
      return new EmployeeResponse();
    } else {
      return EmployeeResponse.fromJson(genericResponse.data);
    }
  }
}
