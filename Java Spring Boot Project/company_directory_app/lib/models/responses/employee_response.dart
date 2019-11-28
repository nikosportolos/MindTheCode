import 'package:company_directory_app/common/enums.dart';
import 'package:company_directory_app/models/responses/position_response.dart';

class EmployeeResponse {
  final int id;
  final String firstName;
  final String lastName;
  final String phoneNumber;
  final String address;
  final DateTime hireDate;
  final DateTime departureDate;
  final PositionResponse position;
  final EmployeeStatus status;
  final ContractType contractType;

  EmployeeResponse({this.id, this.firstName, this.lastName, this.phoneNumber, this.address, this.hireDate, this.departureDate, this.position, this.status, this.contractType});

  factory EmployeeResponse.fromJson(Map<String, dynamic> json) {
    return EmployeeResponse(
        id: json['id'],
        firstName: json['firstName'],
        lastName: json['lastName'],
        phoneNumber: json['phoneNumber'],
        address: json['address'],
        hireDate: DateTime.parse(json['hireDate']),
        departureDate: DateTime.parse(json['departureDate']),
        status: EmployeeStatus.values.firstWhere((e) => e.toString() == 'EmployeeStatus.' + json['status']),
        contractType: ContractType.values.firstWhere((e) => e.toString() == 'ContractType.' + json['contractType']),
        position: PositionResponse.fromJson(json['position']));
  }
}
