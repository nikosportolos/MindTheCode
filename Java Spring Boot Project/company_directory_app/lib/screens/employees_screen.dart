import 'package:company_directory_app/models/responses/employee_response.dart';
import 'package:company_directory_app/repositories/employee_repo.dart';
import 'package:company_directory_app/widgets/employee_info_widget.dart';
import 'package:flutter/material.dart';

class EmployeesScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _EmployeesState();
  }
}

class _EmployeesState extends State<EmployeesScreen> {
  final TextEditingController _textFieldController = TextEditingController();
  final EmployeeRepo repo = new EmployeeRepo();
  List<EmployeeResponse> employeesList = new List<EmployeeResponse>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Employees'),
      ),
      body: Container(
        child: employeesListWidget(),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.search),
        onPressed: () async {
          _displayDialog(context);
        },
      ),
    );
  }

  Widget employeesListWidget() {
    return Flex(
      direction: Axis.vertical,
      children: <Widget>[
        Expanded(
          child: FutureBuilder(
            future: employeesList.isEmpty ? getAllEmployees() : null,
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              // Show loading animation while fetching data
              if (employeesList.isEmpty) return Center(child: CircularProgressIndicator());

              // Show error if any
              if (snapshot.hasError) return new Text('${snapshot.error}');

              return ListView.builder(
                  itemCount: employeesList.length,
                  itemBuilder: (BuildContext context, int index) {
                    return InkWell(
                      child: Card(
                        child: ListTile(
                          leading: Text(
                            '${employeesList[index].id}',
                          ),
                          title: Text('${employeesList[index].firstName} ${employeesList[index].lastName}'),
                          subtitle: Text('${employeesList[index].position.name}'),
                        ),
                      ),
                      onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (context) => EmployeeInfoWidget(employeesList[index]))),
                    );
                  });
            },
          ),
        )
      ],
    );
  }

  getAllEmployees() async {
    repo.getAllEmployees().then((list) {
      setState(() {
        employeesList = list;
      });
    });
  }

  getEmployeeById(int id) async {
    repo.getEmployeeById(id).then((employee) {
      setState(() {
        if (employee.id > 0) {
          employeesList = new List<EmployeeResponse>();
          employeesList.add(employee);
        }
      });
    });
  }

  _displayDialog(BuildContext context) async {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: Text('Search Employees'),
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
                  getEmployeeById(id);
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  }
}
