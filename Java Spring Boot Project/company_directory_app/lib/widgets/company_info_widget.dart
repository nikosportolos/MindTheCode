import 'package:company_directory_app/models/responses/company_response.dart';
import 'package:flutter/material.dart';

class CompanyInfoWidget extends StatelessWidget {
  final CompanyResponse company;

  CompanyInfoWidget(this.company);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Company Info'),
      ),
      body: Column(
        children: <Widget>[
          Card(
              child: Column(
            children: <Widget>[
              ListTile(
                leading: Text(company.id.toString()),
                title: Text(company.name),
              ),
            ],
          )),
        ],
      ),
    );
  }
}
