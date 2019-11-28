import 'package:company_directory_app/screens/business_units_screen.dart';
import 'package:company_directory_app/screens/departments_screen.dart';
import 'package:company_directory_app/screens/employees_screen.dart';
import 'package:company_directory_app/screens/companies_screen.dart';
import 'package:company_directory_app/screens/positions_screen.dart';
import 'package:company_directory_app/screens/units_screen.dart';
import 'package:company_directory_app/widgets/home_tile_widget.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  State<StatefulWidget> createState() {
    return _HomePageState(title);
  }
}

class _HomePageState extends State<MyHomePage> {
  final String _title;

  _HomePageState(this._title);

  Future<bool> _onWillPop() {
    return showDialog(
          context: context,
          builder: (context) => new AlertDialog(
            title: Row(
              children: <Widget>[
                Padding(
                  child: Icon(
                    Icons.exit_to_app,
                    color: Colors.blue,
                  ),
                  padding: EdgeInsets.only(right: 5.0),
                ),
                Text("Exit"),
              ],
            ),
            content: new Text('Do you really want to exit $_title?'),
            actions: <Widget>[
              new FlatButton(
                color: Colors.blue,
                onPressed: () => Navigator.of(context).pop(false),
                child: new Text(
                  'No',
                  style: TextStyle(color: Colors.white),
                ),
              ),
              new FlatButton(
                color: Colors.blue,
                onPressed: () => Navigator.of(context).pop(true),
                child: new Text(
                  'Yes',
                  style: TextStyle(color: Colors.white),
                ),
              ),
            ],
          ),
        ) ??
        false;
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onWillPop,
      child: Scaffold(
          appBar: AppBar(
            title: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                Padding(
                  padding: EdgeInsets.only(right: 10),
                  child: Image.asset(
                    'assets/images/logo.png',
                    height: 50,
                    width: 50,
                  ),
                ),
                Text(this._title)
              ],
            ),
          ),
          body: ListView(
            children: <Widget>[
              HomeTileWidget(
                  title: 'Companies',
                  icon: Icon(Icons.account_balance),
                  onTapCallback: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => CompaniesScreen()));
                  })),
              HomeTileWidget(
                  title: 'Business Units',
                  icon: Icon(Icons.business),
                  onTapCallback: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => BusinessUnitsScreen()));
                  })),
              HomeTileWidget(
                  title: 'Departments',
                  icon: Icon(Icons.store_mall_directory),
                  onTapCallback: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => DepartmentsScreen()));
                  })),
              HomeTileWidget(
                  title: 'Units',
                  icon: Icon(Icons.streetview),
                  onTapCallback: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => UnitsScreen()));
                  })),
              HomeTileWidget(
                  title: 'Positions',
                  icon: Icon(Icons.label),
                  onTapCallback: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => PositionsScreen()));
                  })),
              HomeTileWidget(
                  title: 'Employees',
                  icon: Icon(Icons.people),
                  onTapCallback: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => EmployeesScreen()));
                  })),
            ],
          )),
    );
  }
}
