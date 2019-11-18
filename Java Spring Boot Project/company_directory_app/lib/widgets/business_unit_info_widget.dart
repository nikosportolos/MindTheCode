import 'package:company_directory_app/models/responses/business_unit_response.dart';
import 'package:flutter/material.dart';

class BusinessUnitInfoWidget extends StatelessWidget {
  final BusinessUnitResponse businessUnit;

  BusinessUnitInfoWidget(this.businessUnit);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('BusinessUnit Info'),
      ),
      body: Column(
        children: <Widget>[
          Card(
            child: ListTile(
              title: Text(businessUnit.name),
            ),
          )
        ],
      ),
    );
  }
}
