import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:company_directory_app/repositories/business_unit_repo.dart';
import 'package:flutter/material.dart';

import '../models/responses/business_unit_response.dart';

class BusinessUnitsScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _BusinessUnitsState();
  }
}

class _BusinessUnitsState extends State<BusinessUnitsScreen> {
  final TextEditingController _textFieldController = TextEditingController();
  final BusinessUnitRepo repo = new BusinessUnitRepo();
  List<BusinessUnitResponse> businessUnitsList = new List<BusinessUnitResponse>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Business Units'),
      ),
      body: Container(
        child: businessUnitsListWidget(),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.search),
        onPressed: () async {
          _displayDialog(context);
        },
      ),
    );
  }

  Widget businessUnitsListWidget() {
    return FutureBuilder(
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.none && snapshot.hasData == null) {
          //print('project snapshot data is: ${projectSnap.data}');
          return Center(child: CircularProgressIndicator());
        }
        return ListView.builder(
          itemCount: snapshot.data.length,
          itemBuilder: (context, index) {
            GenericResponse project = snapshot.data[index];
            return businessUnitsList.isEmpty
                ? Text('No business units found')
                : Column(
                    children: <Widget>[Text(project.data)],
                  );
          },
        );
      },
      future: getAllBusinessUnits(),
    );
  }

  getAllBusinessUnits() async {
    repo.getAllBusinessUnits().then((list) {
      setState(() {
        businessUnitsList = list;
      });
    });
  }

  getBusinessUnitById(int id) async {
    repo.getBusinessUnitById(id).then((businessUnit) {
      setState(() {
        if (businessUnit.id > 0) {
          businessUnitsList = new List<BusinessUnitResponse>();
          businessUnitsList.add(businessUnit);
        }
      });
    });
  }

  _displayDialog(BuildContext context) async {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Seach Business Units'),
            content: TextField(
              controller: _textFieldController,
              keyboardType: TextInputType.number,
            ),
            actions: <Widget>[
              new FlatButton(
                child: new Text('Cancel'),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
              new FlatButton(
                child: new Text('Search'),
                onPressed: () {
                  int id = int.fromEnvironment(_textFieldController.text);
                  getBusinessUnitById(id);
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  }
}
