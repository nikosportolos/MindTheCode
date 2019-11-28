import 'dart:async';
import 'dart:convert';
import 'package:company_directory_app/models/responses/error_response.dart';
import 'package:company_directory_app/models/responses/generic_response.dart';
import 'package:http/http.dart' as http;

class RestHelperUtil {
  final int timeout = 30;

  // Returns required http headers
  Map<String, String> _getHeaders() {
    return {
      'content-type': "application/json",
      'Content-Length': "0",
      'accept': "application/json",
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "POST,GET,DELETE,PUT,OPTIONS",
      "Access-Control-Allow-Headers": "Origin,X-Requested-With,Content-Type,Accept,content-type,application/json"
    };
  }

  // Returns base endpoint URI
  String _getBaseURI() {
//    return "http://localhost:8080/";
    return "https://mtc-company-directory.herokuapp.com/";
  }

  Future<GenericResponse> getRequest(String endpoint) async {
    try {
      String endpointURI = _getBaseURI() + endpoint;

      print('> Sending GET request to endpoint: [$endpointURI] with $timeout seconds timeout ');
      http.Response response = await http.get(endpointURI, headers: _getHeaders()).timeout(Duration(seconds: timeout)).catchError((error) {
        print('${error.toString()}');
        return GenericResponse(error: new ErrorResponse(code: 0, title: 'Error sending get request', description: '$error'));
      });

      print('> Status code: ${response.statusCode}');
      print('> Headers: ${response.headers}');
      print('> Response: ${response.body}');

      return GenericResponse.fromJson(json.decode(response.body));
    } catch (error) {
      return GenericResponse(error: new ErrorResponse(code: 0, title: 'Error sending get request', description: '$error'));
    }
  }
}
