import 'package:company_directory_app/models/responses/company_response.dart';
import 'package:company_directory_app/repositories/business_unit_repo.dart';
import 'package:company_directory_app/repositories/company_repo.dart';
import 'package:flutter/material.dart';

import '../widgets/company_info_widget.dart';

class CompaniesScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _CompaniesState();
  }
}

class _CompaniesState extends State<CompaniesScreen> {
  final TextEditingController _textFieldController = TextEditingController();
  final CompanyRepo repo = new CompanyRepo();
  List<CompanyResponse> companiesList = new List<CompanyResponse>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Companies'),
      ),
      body: Container(
        child: companiesListWidget(),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.search),
        onPressed: () async {
          _displayDialog(context);
        },
      ),
    );
  }

  Widget companiesListWidget() {
    return Flex(
      direction: Axis.vertical,
      children: <Widget>[
        Expanded(
          child: FutureBuilder(
            future: companiesList.isEmpty ? getAllCompanies() : null,
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              // Show loading animation while fetching data
              if (companiesList.isEmpty) return Center(child: CircularProgressIndicator());

              // Show error if any
              if (snapshot.hasError) return new Text('${snapshot.error}');

              return ListView.builder(
                  itemCount: companiesList.length,
                  itemBuilder: (BuildContext context, int index) {
                    return InkWell(
                      child: Card(
                        child: ListTile(
                          leading: Text(
                            '${companiesList[index].id}',
                          ),
                          title: Text('${companiesList[index].name}'),
                        ),
                      ),
                      onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (context) => CompanyInfoWidget(companiesList[index]))),
                    );
                  });
            },
          ),
        )
      ],
    );
  }

  getAllCompanies() async {
    repo.getAllCompanies().then((list) {
      setState(() {
        companiesList = list;
      });
    });
  }

  getCompanyById(int id) async {
    repo.getCompanyById(id).then((businessUnit) {
      setState(() {
        if (businessUnit.id > 0) {
          companiesList = new List<CompanyResponse>();
          companiesList.add(businessUnit);
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
                  getCompanyById(id);
                  Navigator.of(context).pop();
                },
              )
            ],
          );
        });
  }
}
