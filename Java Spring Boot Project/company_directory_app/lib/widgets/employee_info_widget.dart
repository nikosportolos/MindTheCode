import 'package:company_directory_app/models/responses/employee_response.dart';
import 'package:flutter/material.dart';

class EmployeeInfoWidget extends StatelessWidget {
  final EmployeeResponse employee;

  EmployeeInfoWidget(this.employee);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Employee Info'),
      ),
      body: Column(
        children: <Widget>[
          Card(
            child: ListTile(
              title: Text('${employee.firstName} ${employee.lastName}'),
            ),
          )
        ],
      ),
    );
  }
}
