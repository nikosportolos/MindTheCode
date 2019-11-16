import 'package:company_directory_app/models/entities/position.dart';

class Employee {
  double id;
  String firstName;
  String lastName;
  String phoneNumber;
  String address;
  DateTime hireDate;
  DateTime departureDate;
  Position position;

  Employee({this.id, this.firstName, this.lastName, this.phoneNumber, this.address, this.hireDate, this.departureDate, this.position});
}
