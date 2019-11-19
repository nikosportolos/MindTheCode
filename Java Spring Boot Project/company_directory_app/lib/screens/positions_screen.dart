import 'package:company_directory_app/models/responses/company_response.dart';
import 'package:company_directory_app/models/responses/position_response.dart';
import 'package:company_directory_app/repositories/position_repo.dart';
import 'package:company_directory_app/widgets/position_info_widget.dart';
import 'package:flutter/material.dart';

class PositionsScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _PositionsState();
  }
}

class _PositionsState extends State<PositionsScreen> {
  final TextEditingController _textFieldController = TextEditingController();
  final PositionRepo repo = new PositionRepo();
  List<PositionResponse> positionsList = new List<PositionResponse>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Positions'),
      ),
      body: Container(
        child: positionsListWidget(),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.search),
        onPressed: () async {
          _displayDialog(context);
        },
      ),
    );
  }

  Widget positionsListWidget() {
    return Flex(
      direction: Axis.vertical,
      children: <Widget>[
        Expanded(
          child: FutureBuilder(
            future: positionsList.isEmpty ? getAllPositions() : null,
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              // Show loading animation while fetching data
              if (positionsList.isEmpty) return Center(child: CircularProgressIndicator());

              // Show error if any
              if (snapshot.hasError) return new Text('${snapshot.error}');

              return ListView.builder(
                  itemCount: positionsList.length,
                  itemBuilder: (BuildContext context, int index) {
                    return InkWell(
                      child: Card(
                        child: ListTile(
                          leading: Text(
                            '${positionsList[index].id}',
                          ),
                          title: Text('${positionsList[index].name}'),
                          subtitle: Text('${positionsList[index].unit.name}'),
                        ),
                      ),
                      onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (context) => PositionInfoWidget(positionsList[index]))),
                    );
                  });
            },
          ),
        )
      ],
    );
  }

  getAllPositions() async {
    repo.getAllPositions().then((list) {
      setState(() {
        positionsList = list;
      });
    });
  }

  getPositionById(int id) async {
    repo.getPositionById(id).then((businessUnit) {
      setState(() {
        if (businessUnit.id > 0) {
          positionsList = new List<PositionResponse>();
          positionsList.add(businessUnit);
        }
      });
    });
  }

  _displayDialog(BuildContext context) async {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Seach Positions'),
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
                  getPositionById(id);
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  }
}
