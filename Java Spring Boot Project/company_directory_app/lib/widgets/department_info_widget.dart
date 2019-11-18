import 'package:company_directory_app/models/responses/department_response.dart';
import 'package:flutter/material.dart';

class DepartmentInfoWidget extends StatelessWidget {
  final DepartmentResponse department;

  DepartmentInfoWidget(this.department);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Department Info'),
      ),
      body: Column(
        children: <Widget>[
          Card(
            child: ListTile(
              title: Text(department.name),
            ),
          )
        ],
      ),
    );
  }
}
