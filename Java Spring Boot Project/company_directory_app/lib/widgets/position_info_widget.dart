import 'package:company_directory_app/models/responses/position_response.dart';
import 'package:flutter/material.dart';

class PositionInfoWidget extends StatelessWidget {
  final PositionResponse position;

  PositionInfoWidget(this.position);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Position Info'),
      ),
      body: Column(
        children: <Widget>[
          Card(
            child: ListTile(
              title: Text(position.name),
            ),
          )
        ],
      ),
    );
  }
}
