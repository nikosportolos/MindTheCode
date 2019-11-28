import 'package:company_directory_app/repositories/business_unit_repo.dart';
import 'package:company_directory_app/widgets/business_unit_info_widget.dart';
import 'package:flutter/material.dart';
import 'package:company_directory_app/models/responses/business_unit_response.dart';

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
    return Flex(
      direction: Axis.vertical,
      children: <Widget>[
        Expanded(
          child: FutureBuilder(
            future: businessUnitsList.isEmpty ? getAllBusinessUnits() : null,
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              // Show loading animation while fetching data
              if (businessUnitsList.isEmpty) return Center(child: CircularProgressIndicator());

              // Show error if any
              if (snapshot.hasError) return new Text('${snapshot.error}');

              return ListView.builder(
                  itemCount: businessUnitsList.length,
                  itemBuilder: (BuildContext context, int index) {
                    return InkWell(
                      child: Card(
                        child: ListTile(
                          leading: Text(
                            '${businessUnitsList[index].id}',
                          ),
                          title: Text('${businessUnitsList[index].name}'),
                          subtitle: Text('${businessUnitsList[index].company.name}'),
                        ),
                      ),
                      onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (context) => BusinessUnitInfoWidget(businessUnitsList[index]))),
                    );
                  });
            },
          ),
        )
      ],
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
