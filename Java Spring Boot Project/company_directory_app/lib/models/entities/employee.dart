import 'package:company_directory_app/common/enums.dart';
import 'package:company_directory_app/models/entities/position.dart';

class Employee {
  final int id;
  final String firstName;
  final String lastName;
  final String phoneNumber;
  final String address;
  final DateTime hireDate;
  final DateTime departureDate;
  final Position position;
  final EmployeeStatus status;
  final ContractType contractType;

  Employee({this.id, this.firstName, this.lastName, this.phoneNumber, this.address, this.hireDate, this.departureDate, this.position, this.status, this.contractType});
}
