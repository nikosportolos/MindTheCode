import 'dart:core';
import 'package:company_directory_app/models/responses/all_departments_response.dart';
import 'package:company_directory_app/models/responses/department_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/utils/rest_helper_util.dart';

class DepartmentRepo {
  RestHelperUtil restHelper = new RestHelperUtil();

  Future<List<DepartmentResponse>> getAllDepartments() async {
    print('Loading all departments ');
    GenericResponse genericResponse = await restHelper.getRequest("departments");

    print(genericResponse);

    if (genericResponse.error != null) {
      return new List<DepartmentResponse>();
    }

    AllDepartmentsResponse allResponse = AllDepartmentsResponse.fromJson(genericResponse.data);

    return allResponse.departments;
  }

  Future<DepartmentResponse> getDepartmentById(int id) async {
    GenericResponse genericResponse = await restHelper.getRequest("department/$id");
    if (genericResponse.error != null) {
      return new DepartmentResponse();
    } else {
      return DepartmentResponse.fromJson(genericResponse.data);
    }
  }
}
