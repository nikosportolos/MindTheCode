import 'package:company_directory_app/models/responses/unit_response.dart';
import 'package:company_directory_app/repositories/unit_repo.dart';
import 'package:company_directory_app/widgets/unit_info_widget.dart';
import 'package:flutter/material.dart';

class UnitsScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _UnitsState();
  }
}

class _UnitsState extends State<UnitsScreen> {
  final TextEditingController _textFieldController = TextEditingController();
  final UnitRepo repo = new UnitRepo();
  List<UnitResponse> unitsList = new List<UnitResponse>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Units'),
      ),
      body: Container(
        child: unitsListWidget(),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.search),
        onPressed: () async {
          _displayDialog(context);
        },
      ),
    );
  }

  Widget unitsListWidget() {
    return Flex(
      direction: Axis.vertical,
      children: <Widget>[
        Expanded(
          child: FutureBuilder(
            future: unitsList.isEmpty ? getAllUnits() : null,
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              // Show loading animation while fetching data
              if (unitsList.isEmpty) return Center(child: CircularProgressIndicator());

              // Show error if any
              if (snapshot.hasError) return new Text('${snapshot.error}');

              return ListView.builder(
                  itemCount: unitsList.length,
                  itemBuilder: (BuildContext context, int index) {
                    return InkWell(
                      child: Card(
                        child: ListTile(
                          leading: Text(
                            '${unitsList[index].id}',
                          ),
                          title: Text('${unitsList[index].name}'),
                          subtitle: Text('${unitsList[index].department.name}'),
                        ),
                      ),
                      onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (context) => UnitInfoWidget(unitsList[index]))),
                    );
                  });
            },
          ),
        )
      ],
    );
  }

  getAllUnits() async {
    repo.getAllUnits().then((list) {
      setState(() {
        unitsList = list;
      });
    });
  }

  getUnitById(int id) async {
    repo.getUnitById(id).then((businessUnit) {
      setState(() {
        if (businessUnit.id > 0) {
          unitsList = new List<UnitResponse>();
          unitsList.add(businessUnit);
        }
      });
    });
  }

  _displayDialog(BuildContext context) async {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Seach Unit'),
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
                  getUnitById(id);
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  }
}
