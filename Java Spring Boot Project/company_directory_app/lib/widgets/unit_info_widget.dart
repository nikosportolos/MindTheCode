import 'package:company_directory_app/models/responses/unit_response.dart';
import 'package:flutter/material.dart';

class UnitInfoWidget extends StatelessWidget {
  final UnitResponse unit;

  UnitInfoWidget(this.unit);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Unit Info'),
      ),
      body: Column(
        children: <Widget>[
          Card(
            child: ListTile(
              title: Text(unit.name),
            ),
          )
        ],
      ),
    );
  }
}
