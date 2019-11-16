import 'package:company_directory_app/screens/business_units_screen.dart';
import 'package:company_directory_app/screens/departments_screen.dart';
import 'package:company_directory_app/screens/employees_screen.dart';
import 'package:company_directory_app/screens/companies_screen.dart';
import 'package:company_directory_app/screens/positions_screen.dart';
import 'package:company_directory_app/screens/units_screen.dart';
import 'package:flutter/material.dart';

class MyHomePage extends StatelessWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;
  double containerWidth = 150;
  double containerHeight = 150;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(this.title),
      ),
      body: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Container(
                height: containerHeight,
                width: containerWidth,
                color: Colors.lightBlueAccent,
                child: MaterialButton(
                  child: Text(
                    'Companies',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => CompaniesScreen()));
                  }),
                ),
              ),
              Container(
                height: containerHeight,
                width: containerWidth,
                color: Colors.lightBlueAccent,
                child: MaterialButton(
                  child: Text(
                    'Business Units',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => BusinessUnitsScreen()));
                  }),
                ),
              ),
              Container(
                height: containerHeight,
                width: containerWidth,
                color: Colors.lightBlueAccent,
                child: MaterialButton(
                  child: Text(
                    'Departments',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => DepartmentsScreen()));
                  }),
                ),
              ),
            ],
          ),
          Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Container(
                height: containerHeight,
                width: containerWidth,
                color: Colors.lightBlueAccent,
                child: MaterialButton(
                  child: Text(
                    'Units',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => UnitsScreen()));
                  }),
                ),
              ),
              Container(
                height: containerHeight,
                width: containerWidth,
                color: Colors.lightBlueAccent,
                child: MaterialButton(
                  child: Text(
                    'Positions',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => PositionsScreen()));
                  }),
                ),
              ),
              Container(
                height: containerHeight,
                width: containerWidth,
                color: Colors.lightBlueAccent,
                child: MaterialButton(
                  child: Text(
                    'Employees',
                    style: TextStyle(color: Colors.white),
                  ),
                  onPressed: (() {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => EmployeesScreen()));
                  }),
                ),
              )
            ],
          )
        ],
      ),
    );
  }
}
