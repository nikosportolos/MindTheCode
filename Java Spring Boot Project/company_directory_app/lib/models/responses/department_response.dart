import 'package:company_directory_app/models/entities/business_unit.dart';

class DepartmentResponse {
  double id;
  String departmentName;
  BusinessUnit businessUnit;

  DepartmentResponse({this.id, this.departmentName, this.businessUnit});
}
