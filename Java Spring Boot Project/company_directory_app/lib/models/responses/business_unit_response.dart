import 'package:company_directory_app/models/entities/company.dart';

class BusinessUnitResponse {
  double id;
  String name;
  Company company;

  BusinessUnitResponse({this.id, this.name, this.company});
}
