import 'package:company_directory_app/models/responses/department_response.dart';
import 'package:company_directory_app/repositories/department_repo.dart';
import 'package:company_directory_app/widgets/department_info_widget.dart';
import 'package:flutter/material.dart';

class DepartmentsScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _DepartmentsState();
  }
}

class _DepartmentsState extends State<DepartmentsScreen> {
  final TextEditingController _textFieldController = TextEditingController();
  final DepartmentRepo repo = new DepartmentRepo();
  List<DepartmentResponse> departmentsList = new List<DepartmentResponse>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Departments'),
      ),
      body: Container(
        child: departmentsListWidget(),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.search),
        onPressed: () async {
          _displayDialog(context);
        },
      ),
    );
  }

  Widget departmentsListWidget() {
    return Flex(
      direction: Axis.vertical,
      children: <Widget>[
        Expanded(
          child: FutureBuilder(
            future: departmentsList.isEmpty ? getAllDepartments() : null,
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              // Show loading animation while fetching data
              if (departmentsList.isEmpty) return Center(child: CircularProgressIndicator());

              // Show error if any
              if (snapshot.hasError) return new Text('${snapshot.error}');

              return ListView.builder(
                  itemCount: departmentsList.length,
                  itemBuilder: (BuildContext context, int index) {
                    return InkWell(
                      child: Card(
                        child: ListTile(
                          leading: Text(
                            '${departmentsList[index].id}',
                          ),
                          title: Text('${departmentsList[index].name}'),
                          subtitle: Text('${departmentsList[index].businessUnit.name}'),
                        ),
                      ),
                      onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (context) => DepartmentInfoWidget(departmentsList[index]))),
                    );
                  });
            },
          ),
        )
      ],
    );
  }

  getAllDepartments() async {
    repo.getAllDepartments().then((list) {
      setState(() {
        departmentsList = list;
      });
    });
  }

  getDepartmentById(int id) async {
    repo.getDepartmentById(id).then((businessUnit) {
      setState(() {
        if (businessUnit.id > 0) {
          departmentsList = new List<DepartmentResponse>();
          departmentsList.add(businessUnit);
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
                  getDepartmentById(id);
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  }
}
