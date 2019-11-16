import 'package:flutter/material.dart';

class DepartmentsScreen extends StatelessWidget {
  final TextEditingController _textFieldController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Departments'),
      ),
      body: Container(),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.search),
        onPressed: (() {
          _displayDialog(context);
        }),
      ),
    );
  }

  _displayDialog(BuildContext context) async {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Seach Departments'),
            content: TextField(
              controller: _textFieldController,
            ),
            actions: <Widget>[
              new FlatButton(
                child: new Text('Search'),
                onPressed: () async {
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  }
}
