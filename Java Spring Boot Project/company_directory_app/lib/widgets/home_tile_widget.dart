import 'package:flutter/material.dart';

class HomeTileWidget extends StatelessWidget {
  final String title;
  final Icon icon;
  final VoidCallback onTapCallback;

  HomeTileWidget({this.title, this.icon, this.onTapCallback});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      splashColor: Colors.blue.withAlpha(30),
      onTap: this.onTapCallback,
//      onTap: () { Navigator.of(context).push(MaterialPageRoute(builder: (context) => this.screen)); },
      child: Card(
        child: ListTile(
          leading: icon,
          title: Text(this.title),
        ),
      ),
    );
  }
}
